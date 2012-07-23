package com.magic.lineone.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class WebServiceConfiguration {

    private static WebServiceConfiguration instance      = null;
    private static Object                  singletonLock = new Object ();

    private final Properties               properties    = new Properties ();

    private static final String            CONFIG_FILE   = "config/config.properties";

    public static final WebServiceConfiguration getInstance () {

        if (instance == null) {
            synchronized (singletonLock) {
                if (instance == null)
                    instance = new WebServiceConfiguration ();
            }
        }
        return instance;
    }

    private WebServiceConfiguration () {

        try {
            BufferedReader br = new BufferedReader (new InputStreamReader (new FileInputStream (CONFIG_FILE), "UTF-8"));
            String line;
            while ((line = br.readLine ()) != null) {
                if (line.startsWith ("#"))
                    continue;
                final int index = line.indexOf ("=");
                if (index == -1)
                    continue;
                properties.put (line.substring (0, index).toLowerCase (), line.substring (index + 1));
            }
            br.close ();
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).error ("Ioexception.", e);
        }
    }

    public synchronized void reconfig () {

        try {
            BufferedReader br = new BufferedReader (new InputStreamReader (new FileInputStream (CONFIG_FILE), "UTF-8"));
            String line;
            while ((line = br.readLine ()) != null) {
                if (line.startsWith ("#"))
                    continue;
                final int index = line.indexOf ("=");
                if (index == -1)
                    continue;
                properties.put (line.substring (0, index).toLowerCase (), line.substring (index + 1));
            }
            br.close ();
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).error ("Ioexception.", e);
        }
    }

    public synchronized int getAdminHttpPort () {

        return Integer.parseInt (properties.getProperty ("adminhttpport"));
    }

    public synchronized String getHttpAddress () {

        return properties.getProperty ("address");
    }

    public synchronized String getJdbcurl () {

        return properties.getProperty ("jdbcurl");
    }

    public synchronized String getBackendJdbcurl () {

        return properties.getProperty ("backendjdbcurl");
    }

    public synchronized String getKyotoTycoonAddress () {

        return properties.getProperty ("kyototycoonaddress");
    }

    public synchronized int getRegisterCookieMaxAge () {

        return Integer.parseInt (properties.getProperty ("registercookiemaxage"));
    }

    public synchronized int getLoginCookieMaxAge () {

        return Integer.parseInt (properties.getProperty ("logincookiemaxage"));
    }

    public synchronized String getMainPage () {

        return properties.getProperty ("mainpage");
    }

    public synchronized String getPublicDomain () {

        return properties.getProperty ("domain");
    }
}
