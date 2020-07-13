package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.vectory.enums.OrderStatusEnum;
import com.vectory.enums.YesOrNo;
import com.vectory.mapper.OrderStatusMapper;
import com.vectory.mapper.OrdersMapper;
import com.vectory.pojo.OrderStatus;
import com.vectory.pojo.Orders;
import com.vectory.service.IMyOrderService;
import com.vectory.utils.PagedGridResult;
import com.vectory.vo.MyOrdersVO;
import com.vectory.vo.OrderStatusCountsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyOrderServiceImpl extends BaseServiceImpl implements IMyOrderService {

    @Resource
    public OrdersMapper ordersMapper;
    @Resource
    public OrderStatusMapper orderStatusMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrdersVO> list = ordersMapper.queryMyOrders(map);

        return setterPagedGrid(list, page);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        updateOrder.setDeliverTime(new Date());

        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("order_id", orderId);
        orderStatusQueryWrapper.eq("order_status", OrderStatusEnum.WAIT_DELIVER.type);

        orderStatusMapper.update(updateOrder, orderStatusQueryWrapper);
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public Orders queryMyOrder(String userId, String orderId) {
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("user_id", userId);
        ordersQueryWrapper.eq("order_id", orderId);
        ordersQueryWrapper.eq("is_delete", YesOrNo.NO.type);

        return ordersMapper.selectOne(ordersQueryWrapper);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public boolean updateReceiveOrderStatus(String orderId) {
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        updateOrder.setSuccessTime(new Date());

        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("order_id", orderId);
        orderStatusQueryWrapper.eq("order_status", OrderStatusEnum.WAIT_RECEIVE.type);

        int result = orderStatusMapper.update(updateOrder, orderStatusQueryWrapper);

        return result == 1;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public boolean deleteOrder(String userId, String orderId) {
        Orders updateOrder = new Orders();
        updateOrder.setIsDelete(YesOrNo.YES.type);
        updateOrder.setUpdatedTime(new Date());

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("user_id", userId);
        ordersQueryWrapper.eq("order_id", orderId);

        int result = ordersMapper.update(updateOrder, ordersQueryWrapper);

        return result == 1;
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.type);
        int waitPayCounts = ordersMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts = ordersMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceiveCounts = ordersMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.SUCCESS.type);
        map.put("isComment", YesOrNo.NO.type);
        int waitCommentCounts = ordersMapper.getMyOrderStatusCounts(map);

        return new OrderStatusCountsVO(waitPayCounts,
                waitDeliverCounts,
                waitReceiveCounts,
                waitCommentCounts);
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<OrderStatus> list = ordersMapper.getMyOrderTrend(map);

        return setterPagedGrid(list, page);
    }
}
