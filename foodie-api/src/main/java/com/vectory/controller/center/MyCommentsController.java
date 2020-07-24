package com.vectory.controller.center;

import com.vectory.controller.BaseController;
import com.vectory.qo.OrderCommentQO;
import com.vectory.qo.QueryMyCommentQO;
import com.vectory.qo.SaveCommentQO;
import com.vectory.enums.YesOrNo;
import com.vectory.pojo.OrderItems;
import com.vectory.pojo.Orders;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.IMyCommentsService;
import com.vectory.util.validator.ValidatorUtil;
import com.vectory.vo.PagedGridResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "MY_COMMENTS")
@RestController
@RequestMapping("myComments")
public class MyCommentsController extends BaseController {

    @Resource
    private IMyCommentsService myCommentsService;

    @ApiOperation(value = "ORDER_LIST", httpMethod = "POST")
    @PostMapping("pending")
    public CommonReturnType pending(@RequestBody OrderCommentQO orderCommentQO) {
        ValidatorUtil.validate(orderCommentQO);
        CommonReturnType commonReturnType = checkUserOrder(orderCommentQO.getUserId(), orderCommentQO.getOrderId());
        if (commonReturnType.getStatus() != HttpStatus.OK.value())
            return commonReturnType;
        Orders myOrder = (Orders)commonReturnType.getData();
        if (myOrder.getIsComment().equals(YesOrNo.YES.type))
            return CommonReturnType.fail(EmBusinessResult.ORDER_COMMENTED);
        List<OrderItems> list = myCommentsService.queryPendingComment(orderCommentQO.getOrderId());
        return CommonReturnType.success(list);
    }

    @ApiOperation(value = "SAVE_COMMENTS", httpMethod = "POST")
    @PostMapping("saveList")
    public CommonReturnType saveList(@RequestBody SaveCommentQO saveCommentQO) {
        ValidatorUtil.validate(saveCommentQO);
        CommonReturnType commonReturnType = checkUserOrder(saveCommentQO.getUserId(), saveCommentQO.getOrderId());
        if (commonReturnType.getStatus() != HttpStatus.OK.value())
            return commonReturnType;
        if (saveCommentQO.getCommentList() == null || saveCommentQO.getCommentList().isEmpty())
            return CommonReturnType.fail(EmBusinessResult.COMMENT_EMPTY);
        myCommentsService.saveComments(saveCommentQO.getOrderId(), saveCommentQO.getUserId(), saveCommentQO.getCommentList());
        return CommonReturnType.success();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "GET")
    @GetMapping("query")
    public CommonReturnType query(QueryMyCommentQO queryMyCommentQO) {
        ValidatorUtil.validate(queryMyCommentQO);
        PagedGridResultVO pagedGridResultVO = myCommentsService.queryMyComments(queryMyCommentQO);
        return CommonReturnType.success(pagedGridResultVO);
    }
}
