package com.messagecenter.redis;

import redis.clients.jedis.Jedis;

public class BaseService {

    protected final RedisService redisService = RedisService.getInstance ();

    public long expire (String key, int expireTime) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.expire (key, expireTime);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public long persist (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.persist (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

    public long delete (String key) {

        Jedis conn = null;
        try {
            conn = redisService.getConnection ();
            return conn.del (key);
        }
        finally {
            redisService.closeConnection (conn);
        }
    }

}
