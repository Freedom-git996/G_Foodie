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
@ApiModel(value="商品图片信息出参", description="商品图片信息出参")
public class ItemsImgVO implements Serializable {
    private static final long serialVersionUID = 8018202804820323802L;

    @ApiModelProperty(name="id", value="图片ID")
    private String id;

    @ApiModelProperty(name="itemId", value="商品ID")
    private String itemId;

    @ApiModelProperty(name="url", value="图片地址")
    private String url;

    @ApiModelProperty(name="sort", value="图片顺序")
    private Integer sort;

    @ApiModelProperty(name="isMain", value="是否主图 1：是，0：否")
    private Integer isMain;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name="createdTime", value="创建时间")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name="updatedTime", value="更新时间")
    private Date updatedTime;
}
