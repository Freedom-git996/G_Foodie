package com.vectory.service;

import com.vectory.pojo.Orders;
import com.vectory.utils.PagedGridResult;
import com.vectory.vo.OrderStatusCountsVO;

public interface IMyOrderService {

    /**
     * 查询我的订单列表
     * @param userId userId
     * @param orderStatus orderStatus
     * @param page page
     * @param pageSize pageSize
     * @return PagedGridResult
     */
    PagedGridResult queryMyOrders(String userId,
                                         Integer orderStatus,
                                         Integer page,
                                         Integer pageSize);

    /**
     * 订单状态 --> 商家发货
     * @param orderId orderId
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单
     *
     * @param userId userId
     * @param orderId orderId
     * @return Orders
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * 更新订单状态 —> 确认收货
     *
     * @return boolean
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId userId
     * @param orderId orderId
     * @return boolean
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     * @param userId userId
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     * @param userId userId
     * @param page page
     * @param pageSize pageSize
     * @return PagedGridResult
     */
    PagedGridResult getOrdersTrend(String userId,
                                          Integer page,
                                          Integer pageSize);
}
