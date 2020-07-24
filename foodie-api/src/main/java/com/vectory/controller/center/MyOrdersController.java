package com.vectory.controller.center;

import com.vectory.controller.BaseController;
import com.vectory.qo.ModifyOrderStatusQO;
import com.vectory.qo.OrderListQO;
import com.vectory.qo.QueryOrderStatusQO;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.util.validator.ValidatorUtil;
import com.vectory.vo.PagedGridResultVO;
import com.vectory.vo.OrderStatusCountsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "MY_ORDER")
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @ApiOperation(value = "ORDER_STATUS_COUNT", httpMethod = "GET")
    @GetMapping("statusCounts")
    public CommonReturnType statusCounts(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam String userId) {
        OrderStatusCountsVO orderStatusCountsVO = myOrderService.getOrderStatusCounts(userId);
        return CommonReturnType.success(orderStatusCountsVO);
    }

    @ApiOperation(value = "ORDER_LIST", httpMethod = "GET")
    @GetMapping("query")
    public CommonReturnType query(OrderListQO orderListQO) {
        ValidatorUtil.validate(orderListQO);
        PagedGridResultVO pagedGridResultVO = myOrderService.queryMyOrders(orderListQO);
        return CommonReturnType.success(pagedGridResultVO);
    }

    // TODO 商家发货没有后端，所以这个接口仅仅只是用于模拟
    @ApiOperation(value="模拟发货", httpMethod = "GET")
    @GetMapping("deliver")
    public CommonReturnType deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {
        myOrderService.updateDeliverOrderStatus(orderId);
        return CommonReturnType.success();
    }

    @ApiOperation(value="CONFIRM_RECEIVER", httpMethod = "POST")
    @PostMapping("confirmReceive")
    public CommonReturnType confirmReceive(@RequestBody ModifyOrderStatusQO modifyOrderStatusQO) {
        ValidatorUtil.validate(modifyOrderStatusQO);
        CommonReturnType checkResult = checkUserOrder(modifyOrderStatusQO.getUserId(), modifyOrderStatusQO.getOrderId());
        if (checkResult.getStatus() != HttpStatus.OK.value())
            return checkResult;
        boolean res = myOrderService.updateReceiveOrderStatus(modifyOrderStatusQO.getOrderId());
        if (!res)
            return CommonReturnType.fail(EmBusinessResult.CONFIRM_RECEIVER_FAIL);
        return CommonReturnType.success();
    }

    @ApiOperation(value="用户删除订单", notes="用户删除订单", httpMethod = "POST")
    @PostMapping("delete")
    public CommonReturnType delete(@RequestBody ModifyOrderStatusQO modifyOrderStatusQO) throws Exception {
        ValidatorUtil.validate(modifyOrderStatusQO);
        CommonReturnType checkResult = checkUserOrder(modifyOrderStatusQO.getUserId(), modifyOrderStatusQO.getOrderId());
        if (checkResult.getStatus() != HttpStatus.OK.value())
            return checkResult;
        boolean res = myOrderService.deleteOrder(modifyOrderStatusQO.getUserId(), modifyOrderStatusQO.getOrderId());
        if (!res)
            return CommonReturnType.fail(EmBusinessResult.DELETE_ORDER_FAIL);
        return CommonReturnType.success();
    }

    @ApiOperation(value = "查询订单动向", notes = "查询订单动向", httpMethod = "GET")
    @GetMapping("trend")
    public CommonReturnType trend(QueryOrderStatusQO queryOrderStatusQO) {
        ValidatorUtil.validate(queryOrderStatusQO);
        PagedGridResultVO pagedGridResultVO = myOrderService.getOrdersTrend(queryOrderStatusQO);
        return CommonReturnType.success(pagedGridResultVO);
    }
}
