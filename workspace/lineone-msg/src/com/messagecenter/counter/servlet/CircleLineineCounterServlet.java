package com.messagecenter.counter.servlet;

import java.util.Map;

import org.apache.log4j.Logger;

import com.caucho.hessian.server.HessianServlet;
import com.messagecenter.counter.ICircleLineoneCounter;
import com.messagecenter.counter.KMSCounterType;
import com.messagecenter.redis.CounterService;
import com.messagecenter.redis.RedisKeyGen;
import com.messagecenter.utils.Preconditions;
/**
 * 
 * @author magiccash
 *
 */
public class CircleLineineCounterServlet extends HessianServlet implements ICircleLineoneCounter {

    /**
     * 
     */
    private static final long           serialVersionUID = -1136672417008462166L;

    private static final CounterService counterService   = CounterService.getInstance ();

    @Override
    public long plus (String kmUserID, KMSCounterType counterName, long increaseBy) {

        try {
            if (checkKmUserID (kmUserID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name (),
                        increaseBy);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long plus (String kmUserID, String platform, String weiboID, KMSCounterType counterName, long increaseBy) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name (), increaseBy);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long increment (String kmUserID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name (), 1);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long increment (String kmUserID, String platform, String weiboID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name (), 1);

                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long minus (String kmUserID, KMSCounterType counterName, long reduceBy) {

        try {
            if (checkKmUserID (kmUserID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name (),
                        -reduceBy);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long minus (String kmUserID, String platform, String weiboID, KMSCounterType counterName, long reduceBy) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name (), -reduceBy);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }
        return -1;
    }

    @Override
    public long decrement (String kmUserID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name (), -1);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long decrement (String kmUserID, String platform, String weiboID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                counterService.increaseCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name (), -1);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }
        return -1;
    }

    @Override
    public long setValue (String kmUserID, KMSCounterType counterName, long value) {

        try {
            if (checkKmUserID (kmUserID)) {
                counterService.setCounter (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name (), value);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long setValue (String kmUserID, String platform, String weiboID, KMSCounterType counterName, long value) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                counterService.setCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name (), value);
                return 1;
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public String getValue (String kmUserID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID)) {
                return counterService.getValue (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name ());
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return null;
    }

    @Override
    public String getValue (String kmUserID, String platform, String weiboID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                return counterService.getValue (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name ());
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return null;
    }

    @Override
    public Map <String, String> getAllCounterValue (String kmUserID) {

        try {
            if (checkKmUserID (kmUserID)) {
                return counterService.getValue (RedisKeyGen.generateKMSCounterKey (kmUserID));
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return null;
    }

    @Override
    public Map <String, String> getAllCounterValue (String kmUserID, String platform, String weiboID) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                return counterService.getValue (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID));
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return null;
    }

    @Override
    public long clearCounter (String kmUserID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID)) {
                return counterService.clearCounter (RedisKeyGen.generateKMSCounterKey (kmUserID), counterName.name ());
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long clearCounter (String kmUserID, String platform, String weiboID, KMSCounterType counterName) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                return counterService.clearCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform, weiboID),
                        counterName.name ());
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long clearAllCounter (String kmUserID) {

        try {
            if (checkKmUserID (kmUserID)) {
                long reply = counterService.clearCounter (RedisKeyGen.generateKMSCounterKey (kmUserID));
                if (reply > 0) {
                    return 1;
                }
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    @Override
    public long clearAllCounter (String kmUserID, String platform, String weiboID) {

        try {
            if (checkKmUserID (kmUserID) && checkPlatform (platform) && checkWeiboID (weiboID)) {
                long reply = counterService.clearCounter (RedisKeyGen.generateKMSCounterKey (kmUserID, platform,
                        weiboID));
                if (reply > 0) {
                    return 1;
                }
            }
        }
        catch (Exception e) {
            Logger.getLogger (CircleLineineCounterServlet.class).error (e, e);
        }

        return -1;
    }

    private boolean checkKmUserID (String str) {

        if (Preconditions.isNotNull (str)) {
            return true;
        }
        else {
            Logger.getLogger (CircleLineineCounterServlet.class).error ("kmUserID is null");
            return false;
        }
    }

    private boolean checkPlatform (String str) {

        if (Preconditions.isNotNull (str)) {
            return true;
        }
        else {
            Logger.getLogger (CircleLineineCounterServlet.class).error ("platform is null");
            return false;
        }
    }

    private boolean checkWeiboID (String str) {

        if (Preconditions.isNotNull (str)) {
            return true;
        }
        else {
            Logger.getLogger (CircleLineineCounterServlet.class).error ("weiboID is null");
            return false;
        }
    }

}
