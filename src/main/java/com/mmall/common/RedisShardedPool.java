package com.mmall.common;

import com.mmall.common.RedisPool;
import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/11/9 15:56
 */
public class RedisShardedPool {

    private static ShardedJedisPool pool;//sharded jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getValue("redis.max.total", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getValue("redis.max.idle", "20"));
    //再jedispool中最大的idle状态（空闲的）的jedis实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getValue("redis.min.idle", "20"));
    //再jedispool中最小的idle状态（空闲的）的jedis实例个数
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getValue("redis.test.borrow", "true"));
    //在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值是true，代表实例可用
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getValue("redis.test.return", "true"));
    //在return一个jedis实例的时候，是否要进行验证操作，如果赋值是true，代表实例可用

    private static String redis1Ip = PropertiesUtil.getValue("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getValue("redis1.port", "6379"));

    private static String redis2Ip = PropertiesUtil.getValue("redis2.ip");
    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getValue("redis2.port", "6380"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽时是否阻塞，false会抛出异常，true阻塞直到超时。默认true

        JedisShardInfo info1 = new JedisShardInfo(redis1Ip, redis1Port, 1000 * 2);
//        info1.setPassword();//设置密码
        JedisShardInfo info2 = new JedisShardInfo(redis2Ip, redis2Port, 1000 * 2);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);

        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);

        //Hashing.MURMUR_HASH 对应hash一致性算法
        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnJedis(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();

        for (int i = 0; i < 20; i++) {
            jedis.set("key"+i,"value"+i);
        }
        
        
        returnBrokenResource(jedis);
        pool.destroy();
        System.out.println("program is end");
    }
}
