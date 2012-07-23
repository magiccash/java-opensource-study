package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbLineone;

public class LineoneNumServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 6049615452868842907L;

    private final DbLineone   dbLineone        = DbLineone.getInstance (WebServiceConfiguration.getInstance ()
                                                       .getJdbcurl ());

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

        JSONObject ret = new JSONObject ();
        String failureReason = null;

        String type = request.getParameter ("type");

        String circleID = null;

        String num = null;

        if (type.equalsIgnoreCase ("all")) {
            num = dbLineone.getLineoneNum (userid);
        }
        else {
            circleID = request.getParameter ("circleid");
            num = dbLineone.getLineoneNumByCircle (userid, circleID);
        }

        try {
            if (num != null) {
                JSONObject object = new JSONObject ();
                object.put ("num", num);
                ret.put ("result", "true");
                ret.put ("rst", object);
                ret.put ("errno", 0);
                ret.put ("err", "");
            }
            else {
                Logger.getLogger (this.getClass ()).error (
                        "get circle num error! username:" + username + " circleid:" + circleID);
                failureReason = "get circle num error!";
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
