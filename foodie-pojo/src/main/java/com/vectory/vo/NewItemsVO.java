package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品出参", description="商品出参")
public class NewItemsVO implements Serializable {
    private static final long serialVersionUID = 3495738476408384738L;

    @ApiModelProperty(value="rootCatId", name="rootCatId")
    private Integer rootCatId;

    @ApiModelProperty(value="rootCatName", name="rootCatName")
    private String rootCatName;

    @ApiModelProperty(value="slogan", name="slogan")
    private String slogan;

    @ApiModelProperty(value="订单ID", name="catImage")
    private String catImage;

    @ApiModelProperty(value="bgColor", name="bgColor")
    private String bgColor;

    @ApiModelProperty(value="simpleItemList", name="simpleItemList")
    private List<SimpleItemVO> simpleItemList;
}
