package com.vectory.response.error;

public interface CommonMsg {
    Integer getStatus();
    String getMsg();
    CommonMsg setMsg(String msg);
}
