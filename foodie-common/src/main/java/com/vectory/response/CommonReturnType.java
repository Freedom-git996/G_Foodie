package com.vectory.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vectory.response.error.EmBusinessResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "统一JSON返回格式")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CommonReturnType<T> implements Serializable {
    private static final long serialVersionUID = 2734789743624970908L;

    @ApiModelProperty(value = "返回标志，0表示成功返回")
    private Integer status;

    @ApiModelProperty(value = "返回信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public static <T> CommonReturnType<T> create(EmBusinessResult emBusinessResult, T data) {
        return new CommonReturnType<>(emBusinessResult.getStatus(), emBusinessResult.getMsg(), data);
    }

    public static CommonReturnType success() {
        return success(null);
    }

    public static <T> CommonReturnType success(T data) {
        return CommonReturnType.create(EmBusinessResult.OK, data);
    }

    public static CommonReturnType fail(EmBusinessResult emBusinessResult) {
        return new CommonReturnType<>(emBusinessResult.getStatus(), emBusinessResult.getMsg(), null);
    }

    public static CommonReturnType fail(Integer status, String msg) {
        return new CommonReturnType<>(status, msg, null);
    }
}
