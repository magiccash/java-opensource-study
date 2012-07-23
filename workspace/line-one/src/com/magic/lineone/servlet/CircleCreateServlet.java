package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbCircle;

public class CircleCreateServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 6049615452868842907L;

    private final DbCircle    dbCircle         = DbCircle.getInstance (WebServiceConfiguration.getInstance ()
                                                       .getJdbcurl ());

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

        JSONObject ret = new JSONObject ();
        String failureReason = null;

        String name = request.getParameter ("name");
        String descr = request.getParameter ("descr");
        String isPublic = request.getParameter ("isPublic");

        try {

            if (dbCircle.insert (name, descr, userid, isPublic)) {
                JSONObject object = new JSONObject ();
                object.put ("value", 1);
                ret.put ("result", "true");
                ret.put ("rst", object);
                ret.put ("errno", 0);
                ret.put ("err", "");
            }
            else {
                Logger.getLogger (this.getClass ()).error (
                        "insert circle error! userid:" + userid + " circleName:" + name);
                failureReason = "insert circle error!";
            }

        }
        catch (JSONException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
            failureReason = "make json error!";
        }

        try {
            if (!ret.has ("result")) {
                ret.put ("result", "false");
                ret.put ("failureReason", failureReason);
            }

            PrintWriter writer = response.getWriter ();
            writer.write (ret.toString ());
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
