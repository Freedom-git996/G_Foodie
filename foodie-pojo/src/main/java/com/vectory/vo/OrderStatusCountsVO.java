package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="订单状态数量信息出参", description="订单状态数量信息出参")
public class OrderStatusCountsVO implements Serializable {
    private static final long serialVersionUID = 8934798203978937402L;

    @ApiModelProperty(value="待付款订单数", name="waitPayCounts")
    private Integer waitPayCounts;

    @ApiModelProperty(value="待发货订单数", name="waitDeliverCounts")
    private Integer waitDeliverCounts;

    @ApiModelProperty(value="待收货订单数", name="waitReceiveCounts")
    private Integer waitReceiveCounts;

    @ApiModelProperty(value="待评价订单数", name="waitCommentCounts")
    private Integer waitCommentCounts;
}
