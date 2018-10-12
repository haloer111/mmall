package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author gexiao
 * @date 2018/9/12 16:40
 */
public class Const {
    /**
     * 当前用户
     */
    public static final String  CURRENT_USER = "currentUser";
    public static final String  EMAIL = "email";
    public static final String  USERNAME = "username";

    public interface ProductListOrderBy{
        Set<String> Price_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public  enum ProductStatusEnum{
        /**
         * 在售
         */
        ON_SALE(1,"在线");
        private int code;
        private String value;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
