package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.qo.OrderListQO;
import com.vectory.qo.QueryOrderStatusQO;
import com.vectory.enums.OrderStatusEnum;
import com.vectory.enums.YesOrNo;
import com.vectory.mapper.OrderStatusMapper;
import com.vectory.mapper.OrdersMapper;
import com.vectory.pojo.OrderStatus;
import com.vectory.pojo.Orders;
import com.vectory.service.IMyOrderService;
import com.vectory.vo.PagedGridResultVO;
import com.vectory.vo.MyOrdersVO;
import com.vectory.vo.OrderStatusCountsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyOrderServiceImpl extends BaseServiceImpl implements IMyOrderService {

    @Resource
    public OrdersMapper ordersMapper;
    @Resource
    public OrderStatusMapper orderStatusMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResultVO queryMyOrders(OrderListQO orderListQO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", orderListQO.getUserId());
        if (orderListQO.getOrderStatus() != null)
            map.put("orderStatus", orderListQO.getOrderStatus());
        IPage<MyOrdersVO> myOrdersVOIPage = ordersMapper.queryMyOrders(
                new Page(orderListQO.getPageIndex(), orderListQO.getPageSize()), map);
        return setterPagedGrid(myOrdersVOIPage.getRecords(), Integer.parseInt(String.valueOf(myOrdersVOIPage.getCurrent())));
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

        return orderStatusMapper.update(updateOrder, orderStatusQueryWrapper) == 1;
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

        return ordersMapper.update(updateOrder, ordersQueryWrapper) == 1;
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        OrderStatusCountsVO orderStatusCountsVO = new OrderStatusCountsVO();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.type);
        int waitPayCounts = ordersMapper.getMyOrderStatusCounts(map);
        orderStatusCountsVO.setWaitPayCounts(waitPayCounts);

        map.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts = ordersMapper.getMyOrderStatusCounts(map);
        orderStatusCountsVO.setWaitDeliverCounts(waitDeliverCounts);

        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceiveCounts = ordersMapper.getMyOrderStatusCounts(map);
        orderStatusCountsVO.setWaitReceiveCounts(waitReceiveCounts);

        map.put("orderStatus", OrderStatusEnum.SUCCESS.type);
        map.put("isComment", YesOrNo.NO.type);
        int waitCommentCounts = ordersMapper.getMyOrderStatusCounts(map);
        orderStatusCountsVO.setWaitCommentCounts(waitCommentCounts);

        return orderStatusCountsVO;
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public PagedGridResultVO getOrdersTrend(QueryOrderStatusQO queryOrderStatusQO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", queryOrderStatusQO.getUserId());
        IPage<OrderStatus> orderStatusIPage = ordersMapper.getMyOrderTrend(
                new Page(queryOrderStatusQO.getPageIndex(), queryOrderStatusQO.getPageSize()), map);
        return setterPagedGrid(orderStatusIPage.getRecords(), Integer.parseInt(String.valueOf(orderStatusIPage.getCurrent())));
    }
}
