package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="加入购物车入参")
public class AddShopCatQO implements Serializable {
    private static final long serialVersionUID = 2390425830489028390L;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(name = "itemId", value = "商品ID", required = true)
    @NotBlank(message = "商品ID不能为空")
    private String itemId;

    @ApiModelProperty(name = "itemImgUrl", value = "商品图片", required = true)
    @NotBlank(message = "商品图片不能为空")
    private String itemImgUrl;

    @ApiModelProperty(name = "itemName", value = "商品名称", required = true)
    @NotBlank(message = "商品名称不能为空")
    private String itemName;

    @ApiModelProperty(name = "specId", value = "商品规格ID", required = true)
    @NotBlank(message = "商品规格ID不能为空")
    private String specId;

    @ApiModelProperty(name = "specName", value = "商品规格名称", required = true)
    @NotBlank(message = "商品规格名称不能为空")
    private String specName;

    @ApiModelProperty(name = "buyCounts", value = "购买数量", required = true)
    @NotNull(message = "购买数量不能为空")
    private Integer buyCounts;

    @ApiModelProperty(name = "priceDiscount", value = "商品折后价格", required = true)
    @NotBlank(message = "商品折后价格不能为空")
    private String priceDiscount;

    @ApiModelProperty(name = "priceNormal", value = "商品价格", required = true)
    @NotBlank(message = "商品价格不能为空")
    private String priceNormal;
}
