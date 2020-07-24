package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vectory.util.sensitive.SensitiveAnno;
import com.vectory.util.sensitive.SensitiveType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商品评价详情出参", description="商品评价详情出参")
public class ItemCommentVO implements Serializable {
    private static final long serialVersionUID = 3490945980345923492L;

    @ApiModelProperty(name="commentLevel", value="评价等级，好评|中评|差评")
    private Integer commentLevel;

    @ApiModelProperty(name="content", value="评语")
    private String content;

    @ApiModelProperty(name="specName", value="商品规格")
    private String specName;

    @ApiModelProperty(name="createdTime", value="评价时间")
    private Date createdTime;

    @ApiModelProperty(name="userFace", value="用户头像")
    private String userFace;

    @SensitiveAnno(type = SensitiveType.CHINESE_NAME)
    @ApiModelProperty(name="nickname", value="用户昵称")
    private String nickname;
}
