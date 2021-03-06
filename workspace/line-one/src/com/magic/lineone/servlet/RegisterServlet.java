package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbUser;
import com.magic.lineone.session.LocalSessionStore;

public class RegisterServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long       serialVersionUID = -7850644991820718815L;

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

        String email = request.getParameter ("email");
        String nick = request.getParameter ("nick");

        String password = request.getParameter ("password");

        try {
            int userid = dbUser.insert (nick, password, email, null);
            if (userid > 0) {
                ret.put ("value", 1);

                final String sid = sessionStore.newSession (email);
                final Cookie sessionCookie = new Cookie ("session", sid);
                sessionCookie.setMaxAge (WebServiceConfiguration.getInstance ().getLoginCookieMaxAge ());
                sessionCookie.setPath ("/");
                sessionCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
                response.addCookie (sessionCookie);

                final Cookie useridCookie = new Cookie ("userid", String.valueOf (userid));
                useridCookie.setMaxAge (WebServiceConfiguration.getInstance ().getLoginCookieMaxAge ());
                useridCookie.setPath ("/");
                useridCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
                response.addCookie (useridCookie);

                final Cookie usernameCookie = new Cookie ("username", email);
                usernameCookie.setMaxAge (WebServiceConfiguration.getInstance ().getLoginCookieMaxAge ());
                usernameCookie.setPath ("/");
                usernameCookie.setDomain (WebServiceConfiguration.getInstance ().getPublicDomain ());
                response.addCookie (usernameCookie);
            }
            else {
                ret.put ("value", 0);
            }
        }
        catch (JSONException e) {
            Logger.getLogger (this.getClass ()).info (e, e);
        }

        try {
            PrintWriter writer = response.getWriter ();
            writer.write (ret.toString ());
            writer.flush ();
            writer.close ();
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).info (e, e);
        }

    }
}
