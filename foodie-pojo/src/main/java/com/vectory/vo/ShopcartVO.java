package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="购物车信息出参", description="购物车信息出参")
public class ShopcartVO implements Serializable {
    private static final long serialVersionUID = 8238743482129873223L;

    @ApiModelProperty(value="商品主键id", name="itemId")
    private String itemId;

    @ApiModelProperty(value="商品图片url", name="itemImgUrl")
    private String itemImgUrl;

    @ApiModelProperty(value="商品名称", name="itemName")
    private String itemName;

    @ApiModelProperty(value="商品规格id", name="specId")
    private String specId;

    @ApiModelProperty(value="商品规格", name="specName")
    private String specName;

    @ApiModelProperty(value="商品折扣", name="priceDiscount")
    private String priceDiscount;

    @ApiModelProperty(value="商品原价", name="priceNormal")
    private String priceNormal;

}
