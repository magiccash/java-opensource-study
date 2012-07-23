package com.messagecenter.counter;

import java.util.Map;

/**
 * 
 * @author magiccash
 *
 */

public interface ICircleLineoneCounter {

    /**
     * 某个账号某个计数器增加n
     *  
     */
    public long plus (String circleID, long increaseBy);

    /**
     * 某个账号某个计数器增加1
     * 
     */
    public long increment (String circleID);

    /**
     * 某个账号某个计数器减少n
     * 
     */
    public long minus (String circleID, long reduceBy);

    /**
     * 某个账号某个计数器减少1
     * 
     */
    public long decrement (String circleID);

    /**
     * 设置某个账号下某个计数器的值
     * 
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is returned, otherwise if a new field is created 1 is returned.
     */
    public long setValue (String circleID, long value);

    /**
     *  得到某个账号下某个计数器的值
     *  
     */
    public String getValue (String circleID);

    /**
     *   得到某个账号下所有计数器的值
     */
    public Map <String, String> getAllCounterValue (String kmUserID);

    public Map <String, String> getAllCounterValue (String kmUserID, String platform, String weiboID);

    /**
     *   某个计数器清零
     *   
     * @param kmUserID
     * @param counterName
     * @return
     */
    public long clearCounter (String kmUserID, KMSCounterType counterName);

    public long clearCounter (String kmUserID, String platform, String weiboID, KMSCounterType counterName);

    /**
     *  某个账号所有计数器清零
     *  
     * @param kmUserID   
     * @return
     */
    public long clearAllCounter (String kmUserID);

    public long clearAllCounter (String kmUserID, String platform, String weiboID);
}
