package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品分类出参", description="商品分类出参")
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 123920384980238920L;

    @ApiModelProperty(name="id", value="分类ID")
    private Integer id;

    @ApiModelProperty(name="name", value="分类名")
    private String name;

    @ApiModelProperty(name="type", value="分类类型")
    private String type;

    @ApiModelProperty(name="fatherId", value="父级分类")
    private Integer fatherId;

    @ApiModelProperty(name="subCatList", value="子类")
    private List<SubCategoryVO> subCatList;
}
