package com.mmall.util;

import com.mmall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author gexiao
 * @date 2018/11/7 14:55
 */
@Slf4j
public class RedisPoolUtil {

    /**
     *
     * @param key
     * @param value
     * @param exTime 单位：秒
     * @return
     */
    public static String setEx(String key, String value,int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime,value);
        } catch (Exception e) {
            log.error("setex key:{} value:{},error", key, value, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnJedis(jedis);
        return result;
    }

    /**
     * 设置key的有效期
     * @param key
     * @param exTime 单位：秒
     * @return
     */
    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result =null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} value:{},error", key,  e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnJedis(jedis);
        return result;
    }
    public static Long del(String key) {
        Jedis jedis = null;
        Long result =null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} value:{},error", key,  e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnJedis(jedis);
        return result;
    }

    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{},error", key, value, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnJedis(jedis);
        return result;
    }
    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("set key:{} ,error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnJedis(jedis);
        return result;
    }


    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();

        RedisPoolUtil.set("keyTest","valueTest");
        String value = RedisPoolUtil.get("keyTest");

        RedisPoolUtil.setEx("keyEx","valueEx",60*10);

        RedisPoolUtil.expire("keyTest",60*20);
        RedisPoolUtil.del("keyTest");



    }
}
