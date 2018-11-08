package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author gexiao
 * @date 2018/11/6 10:44
 */
public class RedisPool {
    private static JedisPool pool;//jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getValue("redis.max.total", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getValue("redis.max.idle", "20"));
    //再jedispool中最大的idle状态（空闲的）的jedis实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getValue("redis.min.idle", "20"));
    //再jedispool中最小的idle状态（空闲的）的jedis实例个数
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getValue("redis.test.borrow", "true"));
    //在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值是true，代表实例可用
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getValue("redis.test.return", "true"));
    //在return一个jedis实例的时候，是否要进行验证操作，如果赋值是true，代表实例可用

    private static String redisIp = PropertiesUtil.getValue("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getValue("redis.port","6379"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽时是否阻塞，false会抛出异常，true阻塞直到超时。默认true

        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2);
    }

    static {
        initPool();
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnJedis(Jedis jedis) {
            pool.returnResource(jedis);
    }
    public static void returnBrokenResource(Jedis jedis) {
            pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("gexiao","gexiaovalue");
        returnBrokenResource(jedis);
        pool.destroy();
        System.out.println("program is end");
    }
}
