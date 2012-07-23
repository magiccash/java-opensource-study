package com.messagecenter.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author magiccash
 *
 */
public class CounterService extends BaseService {

    private final static CounterService instance = new CounterService ();

    public static CounterService getInstance () {

        return instance;
    }

    private CounterService () {

    }

    /**
     * 判断计数器是否存在
     * 
     * @param counterName
     * @return
     */
    public boolean exist (String key, String counterName) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hexists (key, counterName);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 得到某个账号下某个计数器的值
     * 
     * @param key
     * @param counterName 
     * @return  If the field is not found or the key does not exist, a special 'nil' value is returned. 
     */
    public String getValue (String key, String counterName) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hget (key, counterName);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 得到某个账号下所有计数器的值
     *   
     * @param key  platform_weiboaccounid
     * @return  All the fields and values contained into a hash.
     */
    public Map <String, String> getValue (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hgetAll (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 更改计数器的值 
     * 
     * @param key         platform_weiboaccounid
     * @param counterName
     * @param increaseBy 可以为正数、负数或0
     * @return Integer reply The new value at field after the increment operation.
     */
    public long increaseCounter (String key, String counterName, long increaseBy) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hincrBy (key, counterName, increaseBy);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 某个计数器清零
     * 将计数器从redis中物理删除，不可恢复
     * 
     * @param key   platform_weiboaccounid
     * @param counterName
     * 
     * @return If the field was present in the hash it is deleted and 1 is returned, otherwise 0 is returned and no operation is performed.
     */
    public long clearCounter (String key, String counterName) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hdel (key, counterName);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 删除某个账号所有计数器清零
     * 
     * @param key  platform_weiboaccounid
     * @return  Integer reply, specifically: an integer greater than 0 if one or more keys were removed 0 if none of the specified key existed
     */
    public long clearCounter (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.del (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 修改计数器的值
     * @param key
     * @param counterName
     * @param value
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is returned, otherwise if a new field is created 1 is returned.
     */
    public long setCounter (String key, String counterName, long value) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hset (key, counterName, String.valueOf (value));
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public static void main (String [] args) {

        CounterService service = CounterService.getInstance ();
        System.out.println (service.clearCounter ("test:*"));
    }
}
