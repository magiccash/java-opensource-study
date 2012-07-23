package com.messagecenter.config.redis;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class RedisConfiguration {

    public static final String  SERVER_URL;
    public static final int     SERVER_PORT;
    public static final boolean SERVER_NEEDAUTH;
    public static final String  SERVER_AUTH;

    public static final int     MAX_ACTIVE;
    public static final int     MAX_IDLE;
    public static final long    MAX_WAIT;
    public static final long    TIME_BETWEEN_EVICTION_RUNS_MILLS;
    public static final long    MIN_EVICTABLE_IDLE_TIME_MILLS;

    public static final int     MAX_NUM_RETRY;
    public static final int     RETRY_INTERVAL_IN_SECOND;

    private static final String DEFAULT_PATH = "config/";

    private static Logger       logger       = Logger.getLogger (RedisConfiguration.class);

    static {
        Properties props = new Properties ();
        try {
            props.load (new FileInputStream (DEFAULT_PATH + "redis_conf.properties"));
        }
        catch (Exception e) {
            e.printStackTrace ();
        }
        SERVER_URL = props.getProperty ("server_url", null) == null ? null : props.getProperty ("server_url", null)
                .trim ();
        checkNull ("server_url", SERVER_URL);

        SERVER_PORT = props.getProperty ("server_port", null) == null ? -1 : Integer.parseInt (props.getProperty (
                "server_port").trim ());
        checkLessThanZero ("server_port", SERVER_PORT);

        SERVER_NEEDAUTH = props.getProperty ("server_needAuth", null) == null ? false : Boolean.parseBoolean (props
                .getProperty ("server_needAuth").trim ());
        SERVER_AUTH = props.getProperty ("server_auth", null) == null ? null : props.getProperty ("server_auth", null)
                .trim ();
        checkNull ("server_auth", SERVER_AUTH);

        MAX_ACTIVE = props.getProperty ("maxActive", null) == null ? -1 : Integer.parseInt (props.getProperty (
                "maxActive").trim ());
        checkLessThanZero ("maxActive", MAX_ACTIVE);

        MAX_IDLE = props.getProperty ("maxIdle", null) == null ? -1 : Integer.parseInt (props.getProperty ("maxIdle")
                .trim ());
        checkLessThanZero ("maxIdle", MAX_IDLE);

        MAX_WAIT = props.getProperty ("maxWait", null) == null ? -1 : Long.parseLong (props.getProperty ("maxWait")
                .trim ());
        checkLessThanZero ("maxWait", MAX_WAIT);

        TIME_BETWEEN_EVICTION_RUNS_MILLS = props.getProperty ("timeBetweenEvictionRunsMillis", null) == null ? -1
                : Long.parseLong (props.getProperty ("timeBetweenEvictionRunsMillis").trim ());
        checkLessThanZero ("timeBetweenEvictionRunsMillis", TIME_BETWEEN_EVICTION_RUNS_MILLS);
        MIN_EVICTABLE_IDLE_TIME_MILLS = props.getProperty ("minEvictableIdleTimeMillis", null) == null ? -1 : Long
                .parseLong (props.getProperty ("minEvictableIdleTimeMillis").trim ());
        checkLessThanZero ("minEvictableIdleTimeMillis", MIN_EVICTABLE_IDLE_TIME_MILLS);

        MAX_NUM_RETRY = props.getProperty ("max_retry", null) == null ? -1 : Integer.parseInt (props.getProperty (
                "max_retry").trim ());
        checkLessThanZero ("max_retry", MAX_NUM_RETRY);
        RETRY_INTERVAL_IN_SECOND = props.getProperty ("retry_interval_inSecond", null) == null ? -1 : Integer
                .parseInt (props.getProperty ("retry_interval_inSecond").trim ());
        checkLessThanZero ("retry_interval_inSecond", RETRY_INTERVAL_IN_SECOND);
    }

    private static void checkNull (String name, String value) {

        if (null == value) {
            logger.error (name + " is null");
        }
    }

    private static void checkLessThanZero (String name, int value) {

        if (value < 0) {
            logger.error (name + " less than zero");
        }
    }

    private static void checkLessThanZero (String name, long value) {

        if (value < 0) {
            logger.error (name + " less than zero");
        }
    }
}
