package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="查询我的评价入参")
public class QueryMyCommentQO implements Serializable {
    private static final long serialVersionUID = 2384782038592038718L;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;

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
