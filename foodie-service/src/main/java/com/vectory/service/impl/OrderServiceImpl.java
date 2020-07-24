package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vectory.qo.SubmitOrderQO;
import com.vectory.enums.OrderStatusEnum;
import com.vectory.enums.YesOrNo;
import com.vectory.mapper.OrderItemsMapper;
import com.vectory.mapper.OrderStatusMapper;
import com.vectory.mapper.OrdersMapper;
import com.vectory.pojo.*;
import com.vectory.service.IAddressService;
import com.vectory.service.IItemService;
import com.vectory.service.IOrderService;
import com.vectory.util.DateUtil;
import com.vectory.vo.MerchantOrdersVO;
import com.vectory.vo.OrderVO;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderItemsMapper orderItemsMapper;
    @Resource
    private OrderStatusMapper orderStatusMapper;
    @Resource
    private IAddressService addressService;
    @Resource
    private IItemService itemService;
    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVO createOrder(SubmitOrderQO submitOrderQO) {
        String userId = submitOrderQO.getUserId();
        String addressId = submitOrderQO.getAddressId();
        String itemSpecIds = submitOrderQO.getItemSpecIds();
        Integer payMethod = submitOrderQO.getPayMethod();
        String leftMsg = submitOrderQO.getLeftMsg();

        Integer postAmount = 0;

        String orderId = sid.nextShort();

        UserAddress address = addressService.queryUserAddres(userId, addressId);

        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);

        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " "
                + address.getCity() + " "
                + address.getDistrict() + " "
                + address.getDetail());

        newOrder.setPostAmount(postAmount);

        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);

        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        String[] itemSpecIdArr = itemSpecIds.split(",");
        int totalAmount = 0;
        Integer realPayAmount = 0;
        for (String itemSpecId : itemSpecIdArr) {

            // TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts = 1;

            ItemsSpec itemSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            String itemId = itemSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgById(itemId);

            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemSpec.getName());
            subOrderItem.setPrice(itemSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrderItem);

            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);

        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);
        merchantOrdersVO.setPayMethod(payMethod);

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);

        return orderVO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());

        orderStatusMapper.updateById(paidStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus queryOrderStatusInfo(String orderId) {
        return orderStatusMapper.selectById(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void closeOrder() {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("order_status", OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> list = orderStatusMapper.selectList(orderStatusQueryWrapper);
        for (OrderStatus os : list) {
            Date createdTime = os.getCreatedTime();
            int days = DateUtil.daysBetween(createdTime, new Date());
            if (days >= 1) {
                doCloseOrder(os.getOrderId());
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void doCloseOrder(String orderId) {
        OrderStatus close = new OrderStatus();
        close.setOrderId(orderId);
        close.setOrderStatus(OrderStatusEnum.CLOSE.type);
        close.setCloseTime(new Date());
        orderStatusMapper.updateById(close);
    }
}
