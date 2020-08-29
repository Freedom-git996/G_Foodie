package com.vectory.controller.center;

import com.vectory.controller.BaseController;
import com.vectory.qo.SubmitOrderQO;
import com.vectory.enums.OrderStatusEnum;
import com.vectory.pojo.OrderStatus;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.IOrderService;
import com.vectory.util.CookieUtil;
import com.vectory.util.validator.ValidatorUtil;
import com.vectory.vo.MerchantOrdersVO;
import com.vectory.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(value = "ORDERS")
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    @Resource
    private IOrderService orderService;
    @Resource
    private RestTemplate restTemplate;

    @ApiOperation(value = "CREATE_ORDER")
    @PostMapping("create")
    public CommonReturnType create(@RequestBody SubmitOrderQO submitOrderQO,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        ValidatorUtil.validate(submitOrderQO);
        // 1. 创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderQO);
        String orderId = orderVO.getOrderId();
        // 2. 创建订单以后，移除购物车中已结算（已提交）的商品
        // TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtil.setCookie(request, response, FOODIE_SHOPCART, "", true);
        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);
        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        // 填写慕课平台提供的支付凭证
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId","5687683-3128877205");
        headers.add("password","30if-90de-wjio-jjio");

        HttpEntity<MerchantOrdersVO> entity =
                new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<CommonReturnType> responseEntity =
                restTemplate.postForEntity(paymentUrl,
                        entity,
                        CommonReturnType.class);
        CommonReturnType paymentResult = responseEntity.getBody();
        assert paymentResult != null;
        if (paymentResult.getStatus() != 200) {
            log.error("发送错误：{}", paymentResult.getMsg());
            return CommonReturnType.fail(EmBusinessResult.PAYMENT_ORDER_FAIL);
        }
        return CommonReturnType.success(orderId);
    }

    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("getPaidOrderInfo")
    public CommonReturnType getPaidOrderInfo(String orderId) {
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return CommonReturnType.success(orderStatus);
    }
}
