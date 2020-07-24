package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品规格信息出参", description="商品规格信息出参")
public class ItemsSpecVO implements Serializable {
    private static final long serialVersionUID = 8023804082083198421L;

    /**
     * 商品规格id
     */
    @ApiModelProperty(value="商品规格id", name="id")
    private String id;

    /**
     * 商品外键id
     */
    @ApiModelProperty(value="商品外键id", name="itemId")
    private String itemId;

    /**
     * 规格名称
     */
    @ApiModelProperty(value="规格名称", name="name")
    private String name;

    /**
     * 库存
     */
    @ApiModelProperty(value="库存", name="stock")
    private Integer stock;

    /**
     * 折扣力度
     */
    @ApiModelProperty(value="折扣力度", name="discounts")
    private BigDecimal discounts;

    /**
     * 优惠价
     */
    @ApiModelProperty(value="优惠价", name="priceDiscount")
    private Integer priceDiscount;

    /**
     * 原价
     */
    @ApiModelProperty(value="原价", name="priceNormal")
    private Integer priceNormal;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="创建时间", name="createdTime")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="更新时间", name="updatedTime")
    private Date updatedTime;
}
