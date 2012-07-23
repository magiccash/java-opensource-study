package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbLineone;

public class LineoneCreateServlet extends AbstractServlet {

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

        String content = request.getParameter ("content");

        String selectCircle = request.getParameter ("selectCircle");

        int lineoneID = dbLineone.insert (content, userid);

        dbLineone.insertCircleLineone (String.valueOf (lineoneID), selectCircle);

        Map <String, String> lineone = dbLineone.getLineone (lineoneID);

        JSONObject object = new JSONObject ();

        try {
            object.put ("id", lineone.get ("id"));
            object.put ("content", lineone.get ("content"));
            object.put ("createTime", lineone.get ("createTime"));
            object.put ("nick", lineone.get ("nick"));

            ret.put ("result", "true");
            ret.put ("rst", object);
            ret.put ("errno", 0);
            ret.put ("err", "");
        }
        catch (JSONException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
            failureReason = "make json error";
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
