package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="用户订单出参", description="用户订单出参")
public class MyOrdersVO implements Serializable {
    private static final long serialVersionUID = 3058375304857387823L;

    @ApiModelProperty(value="订单ID", name="orderId")
    private String orderId;

    @ApiModelProperty(value="创建时间", name="createdTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty(value="支付方式", name="payMethod")
    private Integer payMethod;

    @ApiModelProperty(value="实际支付金额", name="realPayAmount")
    private Integer realPayAmount;

    @ApiModelProperty(value="邮费", name="postAmount")
    private Integer postAmount;

    @ApiModelProperty(value="是否评论", name="isComment")
    private Integer isComment;

    @ApiModelProperty(value="订单状态", name="orderStatus")
    private Integer orderStatus;

    @ApiModelProperty(value="子订单", name="subOrderItemList")
    private List<MySubOrderItemVO> subOrderItemList;
}
