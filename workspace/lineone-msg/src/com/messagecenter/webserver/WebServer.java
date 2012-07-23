package com.messagecenter.webserver;

import org.apache.log4j.PropertyConfigurator;

import com.messagecenter.utils.jetty.JettyApplication;

public class WebServer {

    public static void main (String [] args) {

        PropertyConfigurator.configure ("config/log4j.properties");
        JettyApplication.getInstance ().start ();
    }
}
