package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="移出购物车入参")
public class DelShopCatQO implements Serializable {
    private static final long serialVersionUID = 2390425830489028390L;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(name = "itemSpecId", value = "商品规格ID", required = true)
    @NotBlank(message = "商品规格ID不能为空")
    private String itemSpecId;
}
