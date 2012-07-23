package com.messagecenter.webservice;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.caucho.hessian.client.HessianProxyFactory;
import com.messagecenter.counter.ICircleLineoneCounter;
import com.messagecenter.message.IKMSMessage;
import com.messagecenter.message.IKMSNotify;

public class MessageCenter {

    private static final Map <String, MessageCenter> instanceMap    = new HashMap <String, MessageCenter> ();

    private static final Object                      singlentonLock = new Object ();

    private final HessianProxyFactory                factory        = new HessianProxyFactory ();

    private String                                   serviceUrl     = null;

    private MessageCenter (String url) {

        this.serviceUrl = url;
    }

    public static MessageCenter getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singlentonLock) {
                if (!instanceMap.containsKey (url)) {
                    MessageCenter instance = new MessageCenter (url);
                    instanceMap.put (url, instance);
                }
            }
        }
        return instanceMap.get (url);
    }

    public ICircleLineoneCounter getKMSCounterWebService () {

        try {
            return (ICircleLineoneCounter) factory.create (ICircleLineoneCounter.class, serviceUrl + "/" + Interface.kms_counter.name ());
        }
        catch (MalformedURLException e) {
            Logger.getLogger (MessageCenter.class).error (e, e);
        }
        return null;
    }

    public IKMSMessage getKMSMessageWebService () {

        try {
            return (IKMSMessage) factory.create (IKMSMessage.class, serviceUrl + "/" + Interface.kms_message);
        }
        catch (MalformedURLException e) {
            Logger.getLogger (MessageCenter.class).error (e, e);
        }
        return null;
    }

    public IKMSNotify getKMSNofityWebService () {

        try {
            return (IKMSNotify) factory.create (IKMSNotify.class, serviceUrl + "/" + Interface.kms_notify);
        }
        catch (MalformedURLException e) {
            Logger.getLogger (MessageCenter.class).error (e, e);
        }
        return null;
    }

}
