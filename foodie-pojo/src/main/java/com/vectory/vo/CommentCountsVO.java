package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品评价信息出参", description="商品评价信息出参")
public class CommentCountsVO implements Serializable {
    private static final long serialVersionUID = 2390854802398549239L;

    @ApiModelProperty(name="totalCounts", value="总评价数")
    public Integer totalCounts;

    @ApiModelProperty(name="goodCounts", value="好评数")
    public Integer goodCounts;

    @ApiModelProperty(name="normalCounts", value="中评数")
    public Integer normalCounts;

    @ApiModelProperty(name="badCounts", value="差评数")
    public Integer badCounts;
}
