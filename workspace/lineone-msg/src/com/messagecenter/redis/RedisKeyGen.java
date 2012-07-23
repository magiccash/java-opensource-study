package com.messagecenter.redis;


public class RedisKeyGen {

    public static String generateWeiboCounterKey (String platform, String weiboAccountID) {

        return RedisKeyConstants.COUNTER + platform.charAt (0) + ":" + weiboAccountID;
    }

    public static String generateKMSCounterKey (String kmUserID) {

        return RedisKeyConstants.COUNTER + RedisKeyConstants.KMS + kmUserID;
    }

    public static String generateKMSCounterKey (String kmUserID, String platform, String weiboAccountID) {

        return RedisKeyConstants.COUNTER + RedisKeyConstants.KMS + kmUserID + ":" + platform.charAt (0) + ":"
                + weiboAccountID;
    }

    public static String generateKMSMsgGenIDKey () {

        return RedisKeyConstants.MSG + RedisKeyConstants.KMS + "id" + ":" + "gen";
    }

    public static String generateKMSNotifyKey (String kmUserID) {

        return RedisKeyConstants.NOTIFY + RedisKeyConstants.KMS + kmUserID;
    }
}
