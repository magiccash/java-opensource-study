package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbUser;
import com.magic.lineone.session.LocalSessionStore;

public class LoginServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long       serialVersionUID = -5004549173387841743L;

    private final DbUser            dbUser           = DbUser.getInstance (WebServiceConfiguration.getInstance ()
                                                             .getJdbcurl ());

    private final LocalSessionStore sessionStore     = LocalSessionStore.getInstance ();

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

    }

    @Override
    public void dealWithoutCookie (HttpServletRequest request, HttpServletResponse response) {

        JSONObject ret = new JSONObject ();

        String username = request.getParameter ("username");
        String password = request.getParameter ("password");

        Map <String, String> map = dbUser.isContainUser (username);
        try {
            if (map != null && map.get ("password").equals (password)) {

                final String sid = sessionStore.newSession (map.get ("email"));
                final Cookie sessionCookie = new Cookie ("session", sid);
                sessionCookie.setMaxAge (WebServiceConfiguration.getInstance ().getLoginCookieMaxAge ());
                sessionCookie.setPath ("/");
                sessionCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
                response.addCookie (sessionCookie);

                final Cookie usernameCookie = new Cookie ("username", map.get ("email"));
                usernameCookie.setMaxAge (WebServiceConfiguration.getInstance ().getLoginCookieMaxAge ());
                usernameCookie.setPath ("/");
                usernameCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
                response.addCookie (usernameCookie);

                final Cookie useridCookie = new Cookie ("userid", map.get ("id"));
                useridCookie.setMaxAge (WebServiceConfiguration.getInstance ().getLoginCookieMaxAge ());
                useridCookie.setPath ("/");
                useridCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
                response.addCookie (useridCookie);

                ret.put ("value", 1);

            }
            else {
                ret.put ("value", 0);
            }
        }
        catch (JSONException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }

        try {
            PrintWriter writer = response.getWriter ();
            writer.println (ret);
            writer.flush ();
            writer.close ();
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }

    }
}
