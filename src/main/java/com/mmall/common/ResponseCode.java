package com.mmall.common;

/**
 * @author gexiao
 * @date 2018/9/12 15:16
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN");

    private final int code;
    private final String des;

    ResponseCode(int code, String des) {
        this.code = code;
        this.des = des;
    }

    public int getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
