package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品参数信息出参", description="商品参数信息出参")
public class ItemsParamVO implements Serializable {
    private static final long serialVersionUID = 8023804082083198421L;

    /**
     * 商品参数id
     */
    @ApiModelProperty(name="id", value="商品参数id")
    private String id;

    /**
     * 商品外键id
     */
    @ApiModelProperty(name="itemId", value="商品外键id")
    private String itemId;

    /**
     * 产地 产地，例：中国江苏
     */
    @ApiModelProperty(name="producPlace", value="产地")
    private String producPlace;

    /**
     * 保质期 保质期，例：180天
     */
    @ApiModelProperty(name="footPeriod", value="保质期")
    private String footPeriod;

    /**
     * 品牌名 品牌名，例：三只大灰狼
     */
    @ApiModelProperty(name="brand", value="品牌名")
    private String brand;

    /**
     * 生产厂名 生产厂名，例：大灰狼工厂
     */
    @ApiModelProperty(name="factoryName", value="生产厂名")
    private String factoryName;

    /**
     * 生产厂址 生产厂址，例：大灰狼生产基地
     */
    @ApiModelProperty(name="factoryAddress", value="生产厂址")
    private String factoryAddress;

    /**
     * 包装方式 包装方式，例：袋装
     */
    @ApiModelProperty(name="packagingMethod", value="包装方式")
    private String packagingMethod;

    /**
     * 规格重量 规格重量，例：35g
     */
    @ApiModelProperty(name="weight", value="规格重量")
    private String weight;

    /**
     * 存储方法 存储方法，例：常温5~25°
     */
    @ApiModelProperty(name="storageMethod", value="存储方法")
    private String storageMethod;

    /**
     * 食用方式 食用方式，例：开袋即食
     */
    @ApiModelProperty(name="eatMethod", value="开袋即食")
    private String eatMethod;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name="createdTime", value="创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name="updatedTime", value="更新时间")
    private Date updatedTime;
}
