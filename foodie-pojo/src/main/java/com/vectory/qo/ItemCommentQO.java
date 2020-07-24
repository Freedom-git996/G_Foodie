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
@ApiModel(value="商品评价入参")
public class ItemCommentQO implements Serializable {
    private static final long serialVersionUID = 2384782038592038718L;

    @ApiModelProperty(name = "itemId", value = "商品ID", required = true)
    @NotBlank(message = "商品ID不能为空")
    private String itemId;

    @ApiModelProperty(name = "level", value = "评价等级")
    private Integer level;

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
