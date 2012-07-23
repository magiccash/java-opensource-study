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
import com.magic.lineone.db.DbComment;

public class CommentCreateServlet extends AbstractServlet {

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

        String content = request.getParameter ("content");
        String lineoneID = request.getParameter ("lineoneID");
        String commentID = request.getParameter ("commentID");

        try {

            int cmtID = dbComment.insert (content, lineoneID, userid, commentID);

            Map <String, String> comment = dbComment.getComment (cmtID);

            if (comment != null) {
                JSONObject object = new JSONObject ();
                object.put ("id", comment.get ("id"));
                object.put ("content", comment.get ("content"));
                object.put ("createTime", comment.get ("createTime"));
                object.put ("nick", comment.get ("nick"));

                ret.put ("result", "true");
                ret.put ("rst", object);
                ret.put ("errno", 0);
                ret.put ("err", "");
            }
            else {
                Logger.getLogger (this.getClass ()).error (
                        "insert comment error! userid:" + userid + " lineoneID:" + lineoneID + " commentID:"
                                + commentID);
                failureReason = "insert comment error!";
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
