package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vectory.pojo.Items;
import com.vectory.pojo.ItemsImg;
import com.vectory.pojo.ItemsParam;
import com.vectory.pojo.ItemsSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品信息出参", description="商品信息出参")
public class ItemInfoVO implements Serializable {
    private static final long serialVersionUID = 123920384980238920L;

    @ApiModelProperty(name="item", value="商品基本信息")
    private Items item;

    @ApiModelProperty(name="itemImgList", value="商品图片信息")
    private List<ItemsImg> itemImgList;

    @ApiModelProperty(name="itemSpecList", value="商品规格信息")
    private List<ItemsSpec> itemSpecList;

    @ApiModelProperty(name="itemParams", value="商品参数信息")
    private ItemsParam itemParams;
}
