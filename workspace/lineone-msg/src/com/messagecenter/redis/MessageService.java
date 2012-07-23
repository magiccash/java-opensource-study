package com.messagecenter.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MessageService {

    private static final MessageService instance     = new MessageService ();

    private static final RedisService   redisService = RedisService.getInstance ();

    public static MessageService getInstance () {

        return instance;
    }

    private MessageService () {

    }

    public Set <String> getAllMessages (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.smembers (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public List <String> getMessages (String key, int start, int end) {

        Jedis conn = null;

        try {
            conn = redisService.getConnection ();
            List <String> ret = new ArrayList <String> (conn.smembers (key));
            return ret.subList (start, end);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public long addMessage (String key, String msg) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();

            conn.zadd (key.getBytes (), 1, msg.getBytes ());
            return conn.sadd (key, msg);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public long removeMessage (String key, String msg) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.srem (key, msg);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }
}
