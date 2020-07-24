package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="订单信息出参", description="订单信息出参")
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 8018202804820323802L;

    @ApiModelProperty(value="订单ID", name="id")
    private String orderId;

    @ApiModelProperty(value="订单", name="merchantOrdersVO")
    private MerchantOrdersVO merchantOrdersVO;
}