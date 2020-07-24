package com.vectory.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="用户登录注册入参", description="用在注册和登录两个接口")
public class UserPassportQO implements Serializable {
    private static final long serialVersionUID = 2734980143624800908L;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, message = "用户名最小长度为4")
    @ApiModelProperty(value="用户名", name="username", example="Jack", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 15, message = "密码最小长度为6，最大为15")
    @ApiModelProperty(value="密码", name="password")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @ApiModelProperty(value="确认密码，在登录接口时，无需理会此字段", name="confirmPassword", required = true)
    private String confirmPassword;
}