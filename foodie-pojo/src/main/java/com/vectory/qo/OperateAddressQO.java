package com.vectory.qo;

import com.vectory.util.RegexpUtil;
import com.vectory.util.validator.ValidatorGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel(value="新增收货地址入参")
public class OperateAddressQO implements Serializable {
    private static final long serialVersionUID = 938450348494580238L;

    @ApiModelProperty(name = "addressId", value = "收货地址ID，新增时不填，更新时必填")
    @NotBlank(message = "收货地址ID不能为空", groups = ValidatorGroup.AddressUpdateBean.class)
    private String addressId;

    @ApiModelProperty(name = "userId", value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    @NotBlank(message = "用户ID不能为空", groups = ValidatorGroup.AddressUpdateBean.class)
    private String userId;

    @ApiModelProperty(name = "receiver", value = "收件人", required = true)
    @Length(max = 12, min = 2, message = "用户昵称最小2位，不能超过12位")
    @Length(max = 12, min = 2, message = "用户昵称最小2位，不能超过12位", groups = ValidatorGroup.AddressUpdateBean.class)
    private String receiver;

    @ApiModelProperty(name = "mobile", value = "收件人手机号码", required = true)
    @Pattern(regexp = RegexpUtil.REGEX_MOBILE, message = "手机号码格式不规范")
    @Pattern(regexp = RegexpUtil.REGEX_MOBILE, message = "手机号码格式不规范", groups = ValidatorGroup.AddressUpdateBean.class)
    private String mobile;

    @ApiModelProperty(name = "province", value = "收货省份", required = true)
    @NotBlank(message = "收货省份不能为空")
    @NotBlank(message = "收货省份不能为空", groups = ValidatorGroup.AddressUpdateBean.class)
    private String province;

    @ApiModelProperty(name = "city", value = "收货城市", required = true)
    @NotBlank(message = "收货城市不能为空")
    @NotBlank(message = "收货城市不能为空", groups = ValidatorGroup.AddressUpdateBean.class)
    private String city;

    @ApiModelProperty(name = "district", value = "收货区/县/市", required = true)
    @NotBlank(message = "收货区/县/市不能为空")
    @NotBlank(message = "收货区/县/市不能为空", groups = ValidatorGroup.AddressUpdateBean.class)
    private String district;

    @ApiModelProperty(name = "district", value = "收货详细地址", required = true)
    @NotBlank(message = "收货详细地址不能为空")
    @NotBlank(message = "收货详细地址不能为空", groups = ValidatorGroup.AddressUpdateBean.class)
    private String detail;
}
