package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="用户评论出参", description="用户评论出参")
public class MyCommentVO {

    @ApiModelProperty(value="评论ID", name="commentId")
    private String commentId;

    @ApiModelProperty(value="评论", name="content")
    private String content;

    @ApiModelProperty(value="评论时间", name="createdTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty(value="商品ID", name="itemId")
    private String itemId;

    @ApiModelProperty(value="商品名称", name="itemName")
    private String itemName;

    @ApiModelProperty(value="商品规格", name="specName")
    private String specName;

    @ApiModelProperty(value="商品图片", name="itemImg")
    private String itemImg;
}
