package com.vectory.response.error;

public enum EmBusinessResult implements CommonMsg {
    OK(200, "请求成功"),
    NO_HANDLER_FOUND(404, "请求的资源不可用"),
    MEDIATYPE_NOT_SUPPORTED(415, "内容类型不支持"),
    REQUESTMETHOD_NOT_SUPPORTED(405, "不合法的请求方法"),
    SERVER_INNER_ERROR(500, "服务器内部异常，请联系管理员"),

    ILLEGAL_ARGUMENT(801, "不合法参数"),
    UNKNOWN_ERROR(802, "未知错误"),

    REGISTER_ERROR(1001, "注册失败"),
    USERNAME_REPEAT(1002, "用户名已存在"),
    REPEAT_PASSWORD_ERROR(1003, "两次密码不一致"),
    LOGIN_ERROR(1004, "用户名或密码错误")
    ;

    private Integer status;

    private String msg;

    EmBusinessResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public CommonMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
