package com.vectory.controller;

import com.vectory.pojo.Orders;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.IMyOrderService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class BaseController {

    protected static final String FOODIE_SHOPCART = "shopcart";

    // 支付中心的调用地址
    protected String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    // 微信支付成功 -> 支付中心 -> 平台
    //                       |-> 回调通知的url
    protected String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";

    @Resource
    public IMyOrderService myOrderService;

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     */
    protected CommonReturnType checkUserOrder(String userId, String orderId) {
        Orders order = myOrderService.queryMyOrder(userId, orderId);
        if (order == null)
            return CommonReturnType.fail(EmBusinessResult.ORDER_NOT_EXIST);
        return CommonReturnType.success(order);
    }
}
