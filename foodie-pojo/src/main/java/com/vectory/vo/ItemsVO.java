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
@ApiModel(value="商品基本信息出参", description="商品基本信息出参")
public class ItemsVO implements Serializable {
    private static final long serialVersionUID = 7880702840801848728L;

    @ApiModelProperty(value="商品ID", name="id")
    private String id;

    @ApiModelProperty(value="商品名称", name="itemName")
    private String itemName;

    @ApiModelProperty(value="分类id", name="catId")
    private Integer catId;

    @ApiModelProperty(value="一级分类外键id", name="rootCatId")
    private Integer rootCatId;

    @ApiModelProperty(value="累计销售", name="sellCounts")
    private Integer sellCounts;

    @ApiModelProperty(value="上下架状态", name="onOffStatus")
    private Integer onOffStatus;

    @ApiModelProperty(value="商品内容", name="content")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="创建时间", name="createdTime")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="更新时间", name="updatedTime")
    private Date updatedTime;
}
