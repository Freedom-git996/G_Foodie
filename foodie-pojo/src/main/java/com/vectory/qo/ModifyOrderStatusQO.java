package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="订单状态操作入参")
public class ModifyOrderStatusQO implements Serializable {
    private static final long serialVersionUID = 4530498293873409238L;

    @ApiModelProperty(name = "orderId", value = "订单ID", required = true)
    @NotBlank(message = "订单ID不能为空")
    private String orderId;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;
}
