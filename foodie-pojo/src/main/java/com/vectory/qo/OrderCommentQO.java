package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="订单评价入参")
public class OrderCommentQO implements Serializable {
    private static final long serialVersionUID = 2343985908237349083L;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(name = "orderId", value = "订单ID", required = true)
    @NotBlank(message = "订单ID不能为空")
    private String orderId;
}
