package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbUser;

public class CheckServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -7850644991820718815L;

    private final DbUser      dbUser           = DbUser.getInstance (WebServiceConfiguration.getInstance ()
                                                       .getJdbcurl ());

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

    }

    @Override
    public void dealWithoutCookie (HttpServletRequest request, HttpServletResponse response) {

        final String fieldId = request.getParameter ("fieldId");
        final String fieldValue = request.getParameter ("fieldValue");
        final String callback = request.getParameter ("callback");

        String type = request.getParameter ("type");

        try {
            JSONArray ret = new JSONArray ();
            ret.put (fieldId);
            if (type.equalsIgnoreCase ("email")) {
                if (fieldId.equals ("email")) {
                    ret.put (!dbUser.isExistEmail (fieldValue));
                }
            }
            else if (type.equalsIgnoreCase ("nick")) {
                if (fieldId.equals ("nick")) {
                    ret.put (!dbUser.isExistNick (fieldValue));
                }
            }

            if (ret != null) {
                PrintWriter writer = response.getWriter ();
                if (callback == null)
                    writer.write (ret.toString ());
                else
                    writer.write (callback + "(" + ret.toString () + ")");
                writer.flush ();
                writer.close ();
            }
        }
        catch (IOException e) {
            Logger.getLogger (this.getClass ()).error ("IOException", e);
        }
    }
}
