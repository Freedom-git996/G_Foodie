package com.vectory.service;

import com.vectory.bo.SubmitOrderBO;
import com.vectory.pojo.OrderStatus;
import com.vectory.vo.OrderVO;

public interface IOrderService {
    /**
     * 用于创建订单相关信息
     * @param submitOrderBO submitOrderBO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId orderId
     * @param orderStatus orderStatus
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId orderId
     * @return OrderStatus
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    void closeOrder();
}
