package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="保存评价入参")
public class OrderItemCommentQO implements Serializable {
    private static final long serialVersionUID = 2390348923043802390L;

    @ApiModelProperty(name = "commentId", value = "评论ID", required = true)
    @NotBlank(message = "评论ID不能为空")
    private String commentId;

    @ApiModelProperty(name = "itemId", value = "商品ID", required = true)
    @NotBlank(message = "商品ID不能为空")
    private String itemId;

    @ApiModelProperty(name = "itemName", value = "商品名称", required = true)
    @NotBlank(message = "商品名称不能为空")
    private String itemName;

    @ApiModelProperty(name = "itemSpecId", value = "商品规格ID", required = true)
    @NotBlank(message = "商品规格ID不能为空")
    private String itemSpecId;

    @ApiModelProperty(name = "itemSpecName", value = "商品规格名称", required = true)
    @NotBlank(message = "商品规格名称不能为空")
    private String itemSpecName;

    @ApiModelProperty(name = "commentLevel", value = "评价等级", required = true)
    @NotBlank(message = "评价等级不能为空")
    private Integer commentLevel;

    @ApiModelProperty(name = "content", value = "评论内容")
    private String content;
}
