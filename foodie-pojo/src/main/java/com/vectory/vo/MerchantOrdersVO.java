package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="订单出参", description="订单出参")
public class MerchantOrdersVO implements Serializable {
    private static final long serialVersionUID = 3434503982348430909L;

    @ApiModelProperty(value="商户订单号", name="merchantOrderId")
    private String merchantOrderId;

    @ApiModelProperty(value="商户方的发起用户的用户主键id", name="merchantUserId")
    private String merchantUserId;

    @ApiModelProperty(value="实际支付总金额（包含商户所支付的订单费邮费总额）", name="amount")
    private Integer amount;

    @ApiModelProperty(value="支付方式 1:微信   2:支付宝", name="payMethod")
    private Integer payMethod;

    @ApiModelProperty(value="支付成功后的回调地址（自定义）", name="returnUrl")
    private String returnUrl;
}