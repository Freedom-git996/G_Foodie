package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="用户子订单出参", description="用户子订单出参")
public class MySubOrderItemVO {

    @ApiModelProperty(value="商品ID", name="itemId")
    private String itemId;

    @ApiModelProperty(value="商品图片", name="itemImg")
    private String itemImg;

    @ApiModelProperty(value="商品名称", name="itemName")
    private String itemName;

    @ApiModelProperty(value="商品规格", name="itemSpecName")
    private String itemSpecName;

    @ApiModelProperty(value="购买数量", name="buyCounts")
    private Integer buyCounts;

    @ApiModelProperty(value="价格", name="price")
    private Integer price;
}
