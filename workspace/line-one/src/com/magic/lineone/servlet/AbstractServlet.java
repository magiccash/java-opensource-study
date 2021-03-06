package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.session.ISessionStore.SessionState;
import com.magic.lineone.session.LocalSessionStore;

public abstract class AbstractServlet extends HttpServlet {

    /**
     * 
     */
    private static final long       serialVersionUID = 1282773743222474190L;

    private final LocalSessionStore sessionStore     = LocalSessionStore.getInstance ();
    private static final long       EXPIRED_TIME     = 1000l * WebServiceConfiguration.getInstance ()
                                                             .getLoginCookieMaxAge ();

    @Override
    protected void service (HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding ("UTF-8");
            response.setCharacterEncoding ("UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            Logger.getLogger (getClass ()).error (e, e);
        }

        Cookie [] cookies = request.getCookies ();
        String username = null;
        String userid = null;
        String sid = null;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                final String name = cookie.getName ();
                final String value = cookie.getValue ();
                if ("session".equals (name))
                    sid = value;
                else if ("username".equals (name))
                    username = value;
                else if ("userid".equals (name))
                    userid = value;
            }

        long sessionstart = System.currentTimeMillis ();
        SessionState state = sessionStore.getSessionState (username, sid, EXPIRED_TIME);
        long sessionend = System.currentTimeMillis ();
        Logger.getLogger (this.getClass ()).info ("Session time:" + (sessionend - sessionstart) + " ms.");

        if (state.equals (SessionState.ILLEGAL)) {
            dealWithoutCookie (request, response);
        }
        else if (state.equals (SessionState.EXPIRED)) {
            sessionStore.removeSession (username, sid);
            dealWithoutCookie (request, response);
        }
        else if (state.equals (SessionState.LEGAL)) {
            dealWithCookie (request, response, sid, username, userid);
        }
    }

    public abstract void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid,
            String username, String userid);

    public void dealWithoutCookie (HttpServletRequest request, HttpServletResponse response) {

        final Cookie sessionCookie = new Cookie ("session", null);
        sessionCookie.setMaxAge (0);
        sessionCookie.setPath ("/");
        sessionCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
        response.addCookie (sessionCookie);
        final Cookie usernameCookie = new Cookie ("username", null);
        usernameCookie.setMaxAge (0);
        usernameCookie.setPath ("/");
        usernameCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
        response.addCookie (usernameCookie);
        final Cookie useridCookie = new Cookie ("userid", null);
        useridCookie.setMaxAge (0);
        useridCookie.setPath ("/");
        useridCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
        response.addCookie (useridCookie);

        try {
            JSONObject ret = new JSONObject ();
            PrintWriter writer = response.getWriter ();
            writer = response.getWriter ();
            ret.put ("errno", 1);
            ret.put ("err", "Session expired!");
            ret.put ("rst", "");
            writer.println (ret);
            writer.flush ();
            writer.close ();
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }
        catch (JSONException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }

    }
}
