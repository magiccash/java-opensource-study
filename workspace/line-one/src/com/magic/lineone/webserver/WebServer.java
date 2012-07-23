package com.magic.lineone.webserver;

import org.apache.log4j.PropertyConfigurator;

import com.kmsocial.utils.jetty.JettyApplication;

public class WebServer {

    public static void main (String [] args) {

        PropertyConfigurator.configure ("config/log4j.properties");
        JettyApplication.getInstance ().start ();
    }
}
