package com.magic.lineone.servlet;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.session.LocalSessionStore;

public class LogoutServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long       serialVersionUID = -1481631558008162043L;

    private final LocalSessionStore sessionStore     = LocalSessionStore.getInstance ();

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

        final Cookie sessionCookie = new Cookie ("session", sid);
        sessionCookie.setMaxAge (0);
        sessionCookie.setPath ("/");
        sessionCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
        response.addCookie (sessionCookie);
        final Cookie usernameCookie = new Cookie ("username", username);
        usernameCookie.setMaxAge (0);
        usernameCookie.setPath ("/");
        usernameCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
        response.addCookie (usernameCookie);
        final Cookie useridCookie = new Cookie ("userid", userid);
        useridCookie.setMaxAge (0);
        useridCookie.setPath ("/");
        useridCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
        response.addCookie (useridCookie);

        sessionStore.removeSession (username, sid);
        try {
            response.sendRedirect (WebServiceConfiguration.getInstance ().getMainPage () + ":8082/login.html");
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).error ("IOException", e);
        }
    }

}
