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
import com.magic.lineone.db.DbComment;

public class CommentListServlet extends AbstractServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 6049615452868842907L;

    private final DbComment   dbComment        = DbComment.getInstance (WebServiceConfiguration.getInstance ()
                                                       .getJdbcurl ());

    @Override
    public void dealWithCookie (HttpServletRequest request, HttpServletResponse response, String sid, String username,
            String userid) {

        JSONObject ret = new JSONObject ();
        String failureReason = null;

        String lineoneID = request.getParameter ("lineoneID");

        List <Map <String, String>> commentList = dbComment.getComment (lineoneID);

        JSONArray array = new JSONArray ();

        try {
            for (Map <String, String> map : commentList) {
                JSONObject comment = new JSONObject ();
                comment.put ("id", map.get ("id"));
                comment.put ("content", map.get ("content"));
                comment.put ("createTime", map.get ("createTime"));
                comment.put ("nick", map.get ("nick"));
                array.put (comment);
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
