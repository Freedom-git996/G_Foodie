package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value="保存评价入参")
public class SaveCommentQO implements Serializable {
    private static final long serialVersionUID = 2940283028012823023L;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(name = "orderId", value = "订单ID", required = true)
    @NotBlank(message = "订单ID不能为空")
    private String orderId;

    @ApiModelProperty(name = "commentList", value = "订单评论列表")
    private List<OrderItemCommentQO> commentList;
}
