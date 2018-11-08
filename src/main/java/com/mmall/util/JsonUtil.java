package com.mmall.util;

import java.util.Date;

import com.google.common.collect.Lists;
import com.mmall.pojo.TestPojo;
import com.mmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/11/7 15:29
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
        //默认取消转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //所有日期格式统一为以下样式，即：yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));


        //忽略 在json字符串中存在，但是在java对象不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str,
                    typeReference));
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        TestPojo testPojo = new TestPojo();
        testPojo.setName("jim");
        testPojo.setAge(0);
/*{"name":"jim","age":0}*/
        String json = "{\"name\":\"jim\",\"color\":\"blue\",\"age\":0}";
        TestPojo pojo = JsonUtil.string2Obj(json, TestPojo.class);
        System.out.println("end");
      /*  String userJsonPretty = JsonUtil.obj2String(testPojo);
        log.info("userJson:{}", userJsonPretty);*/
        /*User user1 = new User();
        user1.setId(1);
        user1.setEmail("345249961@qq.com");
        user1.setCreateTime(new Date());
        String userJsonPretty = JsonUtil.obj2StringPretty(user1);
        log.info("userJson:{}", userJsonPretty);*/

      /*  User user2 = new User();
        user2.setId(2);
        user2.setEmail("345249962@qq.com");

        String userJson = JsonUtil.obj2String(user1);
        String userJsonPretty = JsonUtil.obj2StringPretty(user1);

        List<User> userList = Lists.newArrayList();
        userList.add(user1);
        userList.add(user2);
        String userJson1 = JsonUtil.obj2StringPretty(userList);
        log.info("===========", userJson);
        log.info("userJson:{}", userJson1);
        log.info("===========", userJson);


        log.info("userJson:{}", userJson);
        log.info("userJsonPretty:{}", userJsonPretty);

        User user3 = JsonUtil.string2Obj(userJson, User.class);

        List<User> list = JsonUtil.string2Obj(userJson1, new TypeReference<List<User>>() {
        });
        List<User> list2 = JsonUtil.string2Obj(userJson1,List.class,User.class);

        System.out.println("end");*/
    }
}
