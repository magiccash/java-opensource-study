package com.messagecenter.redis;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MessageServiceMapImpl extends BaseService {

    private static final MessageServiceMapImpl instance = new MessageServiceMapImpl ();

    public static MessageServiceMapImpl getInstance () {

        return instance;
    }

    private MessageServiceMapImpl () {

    }

    public Long generateMsgID (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.incr (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public Map <String, String> getAllMessages (String key) {

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
     * 
     * @param key
     * @param msgId
     * @param msg
     * @return  If the field already exists, and the HSET just produced an update of the value, 0 is returned, otherwise if a new field is created 1 is returned.
     */
    public long addMessage (String key, String msgId, String msg) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hset (key, msgId, msg);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    /**
     * 
     * @param key
     * @param msgId
     * @return  If the field was present in the hash it is deleted and 1 is returned, otherwise 0 is returned and no operation is performed.
     */
    public long removeMessage (String key, String msgId) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hdel (key, msgId);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public long getSize (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hlen (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public Set <String> getKeys (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.hkeys (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }
}
