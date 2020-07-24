package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品子类出参", description="商品子类出参")
public class SubCategoryVO {

    @ApiModelProperty(value="分类ID", name="subId")
    private Integer subId;

    @ApiModelProperty(value="分类名", name="subName")
    private String subName;

    @ApiModelProperty(value="分类类型", name="subType")
    private String subType;

    @ApiModelProperty(value="父级分类ID", name="subFatherId")
    private Integer subFatherId;
}
