package com.messagecenter.redis;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.messagecenter.config.redis.RedisConfiguration;

/**
 * 
 * @author magiccash
 *
 */

public class RedisService {

    private final static RedisService instance = new RedisService ();

    private JedisPool                 pool     = null;

    private RedisService () {

        initPool ();
    }

    public static RedisService getInstance () {

        return instance;
    }

    private void initPool () {

        JedisPoolConfig config = new JedisPoolConfig ();
        config.setMaxActive (RedisConfiguration.MAX_ACTIVE);
        config.setMaxIdle (RedisConfiguration.MAX_IDLE);

        //不要设置minIdle，否则会抛出超时错误！  config.setMinIdle(ConfigLoader.MIN_IDLE);
        config.setTestWhileIdle (true);
        config.setNumTestsPerEvictionRun (-1);
        config.setMaxWait (RedisConfiguration.MAX_WAIT);
        config.setTimeBetweenEvictionRunsMillis (RedisConfiguration.TIME_BETWEEN_EVICTION_RUNS_MILLS);
        config.setMinEvictableIdleTimeMillis (RedisConfiguration.MIN_EVICTABLE_IDLE_TIME_MILLS);
        config.setTestOnBorrow (true);
        pool = new JedisPool (config, RedisConfiguration.SERVER_URL, RedisConfiguration.SERVER_PORT, 5000);
        Logger.getLogger (RedisService.class).info (
                "Redis pool configuration: " + ToStringBuilder.reflectionToString (config));
    }

    @SuppressWarnings("unused")
    private void initBackupPool () {

    }

    public Jedis getConnection () {

        try {
            Jedis jedis = pool.getResource ();
            return jedis;
        }
        catch (Exception e) {
            Logger.getLogger (RedisService.class).error ("Cannot get a connection: " + e.getMessage ());
            return null;
        }
    }

    public Jedis getConnectionWithRetry () {

        Jedis conn = null;
        for (int i = 0; i < RedisConfiguration.MAX_NUM_RETRY; i++) {
            conn = getConnection ();
            if (conn != null)
                break;
            Logger.getLogger (RedisService.class).error (
                    "Wait for " + RedisConfiguration.RETRY_INTERVAL_IN_SECOND + " seconds and retry: " + (i + 1) + "/"
                            + RedisConfiguration.MAX_NUM_RETRY);
            try {
                Thread.sleep (RedisConfiguration.RETRY_INTERVAL_IN_SECOND * 1000L);
            }
            catch (Exception e) {
                e.printStackTrace ();
                break;
            }
        }
        return conn;
    }

    public void closeConnection (Jedis connection) {

        if (connection == null)
            return;
        try {
            pool.returnResource (connection);
        }
        catch (Exception e) {
            pool.returnBrokenResource (connection);
            Logger.getLogger (RedisService.class).error ("Cannot return connection: " + e.getMessage ());
        }
    }

    public void destoryPool () {

        pool.destroy ();
    }

    public String info (Jedis conn) {

        return conn.info ();
    }
}
