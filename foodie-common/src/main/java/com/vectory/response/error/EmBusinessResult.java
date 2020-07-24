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
    LOGIN_ERROR(1004, "用户名或密码错误"),
    NO_USER_LOGIN(1005, "用户为登录"),
    IMAGE_UPLOAD_FAIL(1006, "用户头像上传失败"),
    FILE_SUFFIX_ERROR(1007, "上传头像需为png/jpg/jpeg"),
    USERINFO_UPDATE_FAIL(1008, "用户信息更新失败"),

    ORDER_CREATE_FAIL(2001, "库存不足，订单创建失败"),
    ORDER_COMMENTED(2002, "该笔订单已评价，不能再次评价"),
    ORDER_NOT_EXIST(2003, "订单不存在"),
    COMMENT_EMPTY(2004, "评论内容不能为空"),
    CONFIRM_RECEIVER_FAIL(2005, "确认收货失败"),
    DELETE_ORDER_FAIL(2006, "删除订单失败"),

    CATEGORY_NOT_EXIST(3001, "分类不存在"),

    PAYMENT_ORDER_FAIL(4001, "支付中心生成订单失败")
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
