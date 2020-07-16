package com.vectory.response.error;

public class BusinessException extends RuntimeException implements CommonMsg {

    private CommonMsg commonMsg;

    public BusinessException(CommonMsg commonMsg) {
        super();
        this.commonMsg = commonMsg;
    }

    @Override
    public Integer getStatus() {
        return commonMsg.getStatus();
    }

    @Override
    public String getMsg() {
        return commonMsg.getMsg();
    }

    @Override
    public CommonMsg setMsg(String msg) {
        this.commonMsg.setMsg(msg);
        return this;
    }
}
