package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="搜索商品时的信息出参", description="搜索商品时的信息出参")
public class SearchItemsVO implements Serializable {
    private static final long serialVersionUID = 8374803894728932398L;

    @ApiModelProperty(value="商品主键id", name="itemId")
    private String itemId;

    @ApiModelProperty(value="商品名称", name="itemName")
    private String itemName;

    @ApiModelProperty(value="商品销售量", name="sellCounts")
    private Integer sellCounts;

    @ApiModelProperty(value="商品图片url", name="imgUrl")
    private String imgUrl;

    @ApiModelProperty(value="商品折后价格", name="price")
    private Integer price;
}
