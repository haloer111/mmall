package com.mmall.common;

/**
 * @author gexiao
 * @date 2018/9/12 15:16
 */
public enum ERole {

    ROLE_CUSTOMER(0, "role_customer"),//普通用户
    ROLE_ADMIN(1, "role_admin");//管理员


    private final int code;
    private final String des;

    ERole(int code, String des) {
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
