package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="分页入参", description="用在所有分页上")
public class PageQO implements Serializable {
    private static final long serialVersionUID = 8873018902800382802L;

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