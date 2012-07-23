package com.magic.lineone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.magic.lineone.config.WebServiceConfiguration;
import com.magic.lineone.db.DbCircleIndex;

public class CircleIndexServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long   serialVersionUID = 6049615452868842907L;

    private final DbCircleIndex dbCircleIndex    = DbCircleIndex.getInstance (WebServiceConfiguration.getInstance ()
                                                         .getJdbcurl ());

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

        JSONObject ret = new JSONObject ();
        String failureReason = null;

        int pagenum = Integer.parseInt (request.getParameter ("pagenum"));
        int pagesize = Integer.parseInt (request.getParameter ("pagesize"));

        String key = request.getParameter ("key");

        List <Map <String, String>> circleList = dbCircleIndex.getCircles (key, "any", pagenum, pagesize);

        JSONArray array = new JSONArray ();

        try {
            for (Map <String, String> map : circleList) {
                JSONObject circle = new JSONObject ();
                circle.put ("id", map.get ("id"));
                circle.put ("name", map.get ("name"));
                circle.put ("descr", map.get ("descr"));
                circle.put ("totalNum", map.get ("totalNum"));
                circle.put ("ownername", map.get ("ownername"));
                circle.put ("createTime", map.get ("createTime"));
                array.put (circle);
            }

            if (array.length () == pagesize) {
                ret.put ("hasnext", true);
            }
            else {
                ret.put ("hasnext", false);
            }

            ret.put ("result", "true");
            ret.put ("rst", array);
            ret.put ("errno", 0);
            ret.put ("err", "");
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
