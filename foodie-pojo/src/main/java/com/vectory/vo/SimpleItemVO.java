package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品出参", description="商品出参")
public class SimpleItemVO implements Serializable {
    private static final long serialVersionUID = 2493475872737899273L;

    @ApiModelProperty(value="itemId", name="itemId")
    private String itemId;

    @ApiModelProperty(value="itemName", name="itemName")
    private String itemName;

    @ApiModelProperty(value="itemUrl", name="itemUrl")
    private String itemUrl;
}
