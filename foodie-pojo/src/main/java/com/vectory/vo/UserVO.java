package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="用户信息出参", description="用户信息出参")
public class UserVO implements Serializable {
    private static final long serialVersionUID = 2235489801624808801L;

    @ApiModelProperty(value="用户ID", name="id")
    private String id;

    @ApiModelProperty(value="用户名", name="username")
    private String username;

    @SensitiveAnno(type = SensitiveType.PASSWORD)
    @ApiModelProperty(value="密码", name="password")
    private String password;

    @SensitiveAnno(type = SensitiveType.CHINESE_NAME)
    @ApiModelProperty(value="昵称", name="nickname")
    private String nickname;

    @SensitiveAnno(type = SensitiveType.CHINESE_NAME)
    @ApiModelProperty(value="真实姓名", name="realname")
    private String realname;

    @ApiModelProperty(value="头像", name="face")
    private String face;

    @SensitiveAnno(type = SensitiveType.MOBILE_PHONE)
    @ApiModelProperty(value="手机号", name="mobile")
    private String mobile;

    @SensitiveAnno(type = SensitiveType.EMAIL)
    @ApiModelProperty(value="邮箱地址", name="email")
    private String email;

    @ApiModelProperty(value="性别 1:男  0:女  2:保密", name="sex")
    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="生日", name="birthday")
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="创建时间", name="createdTime")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="更新时间", name="updatedTime")
    private Date updatedTime;
}
