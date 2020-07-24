package com.vectory.qo;

import com.vectory.util.RegexpUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="用户信息更新入参")
public class UserUpdateQO implements Serializable {
    private static final long serialVersionUID = 8901837443690184952L;

    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty(value="用户ID", name="id", required = true)
    private String id;

    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12, min = 2, message = "用户昵称最小2位，不能超过12位")
    @ApiModelProperty(value="用户昵称", name="nickname", required = true)
    private String nickname;

    @Length(max = 12, min = 2, message = "用户昵称最小2位，不能超过12位")
    @ApiModelProperty(value="真实姓名", name="realname")
    private String realname;

    @Pattern(regexp = RegexpUtil.REGEX_MOBILE, message = "手机号码格式不规范")
    @ApiModelProperty(value="手机号", name="mobile")
    private String mobile;

    @Email(message = "邮箱格式不规范")
    @ApiModelProperty(value="邮箱地址", name="email")
    private String email;

    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 2, message = "性别选择不正确")
    @ApiModelProperty(value="性别", name="sex", example="0:女 1:男 2:保密")
    private Integer sex;

    @ApiModelProperty(value="生日", name="birthday", example="1900-01-01")
    private Date birthday;
}
