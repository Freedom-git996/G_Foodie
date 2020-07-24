package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="加入购物车入参")
public class SubmitOrderQO implements Serializable {
    private static final long serialVersionUID = 3825803498534782389L;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(name = "itemSpecIds", value = "商品规格ID", required = true)
    @NotBlank(message = "商品规格ID不能为空")
    private String itemSpecIds;

    @ApiModelProperty(name = "addressId", value = "收货地址ID", required = true)
    @NotBlank(message = "收货地址ID不能为空")
    private String addressId;

    @ApiModelProperty(name = "payMethod", value = "支付方式，1微信，2支付宝", required = true)
    @Min(value = 1, message = "当前仅支持两种支付方式")
    @Max(value = 2, message = "当前仅支持两种支付方式")
    private Integer payMethod;

    @ApiModelProperty(name = "leftMsg", value = "备注", required = true)
    private String leftMsg;
}
