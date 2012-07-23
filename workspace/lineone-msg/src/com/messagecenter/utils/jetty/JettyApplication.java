package com.messagecenter.utils.jetty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mortbay.http.SocketListener;
import org.mortbay.http.handler.NotFoundHandler;
import org.mortbay.http.handler.ResourceHandler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Default;
import org.mortbay.jetty.servlet.HashSessionManager;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.servlet.SessionManager;
import org.mortbay.jetty.servlet.WebApplicationContext;
import org.mortbay.jetty.servlet.WebApplicationHandler;

import com.messagecenter.config.webservice.ServletConfiguration;
import com.messagecenter.config.webservice.WebServerConfiguration;

public class JettyApplication {

    private final Server                  httpServer   = new Server ();

    private WebApplicationContext         queryContext = null;

    private static final JettyApplication instance     = new JettyApplication ();

    public static JettyApplication getInstance () {

        return instance;
    }

    private JettyApplication () {

    }

    public void initServer () throws IllegalAccessException, InstantiationException, ClassNotFoundException,
            UnknownHostException {

        final SocketListener querySocketListener = new SocketListener ();
        querySocketListener.setPort (WebServerConfiguration.getInstance ().getPort ());
        querySocketListener
                .setInetAddress (InetAddress.getByName (WebServerConfiguration.getInstance ().getAddress ()));

        querySocketListener.setTcpNoDelay (true);
        querySocketListener.setName ("StatsThreads");
        querySocketListener.setLingerTimeSecs (10);
        httpServer.addListener (querySocketListener);

        SessionManager sessionManager;

        queryContext = new WebApplicationContext ();
        queryContext.setContextPath ("/");
        // following will remove jsp servlet
        queryContext.setDefaultsDescriptor (null);

        final WebApplicationHandler webapp = new WebApplicationHandler ();
        webapp.initialize (queryContext);

        // Default Servlet
        initDefaultServlet (queryContext);

        addServlet (webapp);

        webapp.setAutoInitializeServlets (true);

        // Session handling
        sessionManager = new HashSessionManager ();
        // sessionManager.setMaxInactiveInterval (sessionTimeout);
        webapp.setSessionManager (sessionManager);

        queryContext.addHandler (webapp);

        final ResourceHandler zapResHandler = new ResourceHandler ();

        zapResHandler.setDirAllowed (false);
        zapResHandler.setAcceptRanges (true);
        queryContext.addHandler (zapResHandler);
        queryContext.addHandler (new NotFoundHandler ());
        httpServer.addContext (queryContext);
    }

    private void addServlet (WebApplicationHandler webapp) {

        Map <String, String> servletMap = ServletConfiguration.getInstance ().getServletMap ();
        for (Map.Entry <String, String> entry : servletMap.entrySet ()) {
            webapp.addServlet (entry.getKey (), entry.getValue ());
        }
    }

    private void initDefaultServlet (WebApplicationContext actContext) throws IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        final ServletHolder defaultServlet = actContext.addServlet ("default-serlvet", WebServerConfiguration
                .getInstance ().getDefault_servlet (), Default.class.getName ());
        Map <String, String> params = WebServerConfiguration.getInstance ().getParams ();
        for (Map.Entry <String, String> entry : params.entrySet ()) {
            defaultServlet.setInitParameter (entry.getKey (), entry.getValue ());
        }
    }

    public boolean start () {

        try {
            initServer ();
        }
        catch (ClassNotFoundException e) {
            Logger.getLogger (this.getClass ()).error ("ClassNotFoundException when initializing webservice server", e);
            System.exit (1);
        }
        catch (IllegalAccessException e) {
            Logger.getLogger (this.getClass ()).error ("IllegalAccessException when initializing webservice server", e);
            System.exit (1);
        }
        catch (InstantiationException e) {
            Logger.getLogger (this.getClass ()).error ("InstantiationException when initializing webservice server", e);
            System.exit (1);
        }
        catch (UnknownHostException e) {
            Logger.getLogger (this.getClass ()).error ("UnknownHostException when initializing webservice server", e);
            System.exit (1);
        }

        try {
            this.httpServer.start ();
            this.httpServer.join ();
        }
        catch (Exception e) {
            Logger.getLogger (this.getClass ()).error ("Exception when starting webservice server", e);
            System.exit (1);
        }

        Logger.getLogger (this.getClass ()).info (
                "webservice server servlet started, listening on "
                        + WebServerConfiguration.getInstance ().getAddress () + ":"
                        + WebServerConfiguration.getInstance ().getPort ());

        return true;
    }

}
