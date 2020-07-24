package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="通过三级分类查找商品入参")
public class CatItemQO implements Serializable {
    private static final long serialVersionUID = 2390425830489028390L;

    @ApiModelProperty(name = "catId", value = "三级分类id", required = true)
    @NotNull(message = "分类ID不饿能为空")
    private Integer catId;

    @ApiModelProperty(name = "sort", value = "排序")
    private String sort;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    @ApiModelProperty(value="页码", name="pageIndex", required = true)
    private Integer pageIndex;

    @NotNull(message = "页面大小不能为空")
    @Min(value = 1, message = "页面大小最小为1")
    @Max(value = 50, message = "页面大小最大为50")
    @ApiModelProperty(value="页面大小", name="pageSize", required = true)
    private Integer pageSize;
}
