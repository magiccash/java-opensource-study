package com.magic.lineone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DbComment extends DatabaseAdapter {

    private static final Map <String, DbComment> instanceMap   = new HashMap <String, DbComment> ();

    private static final Object                  singletonLock = new Object ();

    public DbComment (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbComment getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbComment (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public int insert (String content, String lineoneID, String userID, String commentID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection ();
            stmt = conn.prepareStatement (
                    "insert into comment(content,lineoneid,userid,commentid,createTime) values(?,?,?,?,now())",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString (1, content);

            if (lineoneID == null) {
                stmt.setInt (2, 0);
            }
            else {
                stmt.setString (2, lineoneID);
            }

            stmt.setString (3, userID);

            if (commentID == null) {
                stmt.setInt (4, 0);
            }
            else {
                stmt.setString (4, commentID);
            }

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info (
                        "insert comment success! " + "userid:" + userID + " lineoneid:" + lineoneID + " commentid:"
                                + commentID);
            }

            rs = stmt.getGeneratedKeys ();

            while (rs.next ()) {
                return rs.getInt (1);
            }

        }
        catch (SQLException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
        }
        return 0;
    }

    public boolean delete (String id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("delete from comment where id=?");

            stmt.setString (1, id);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("delete comment success! " + "id:" + id);
                return true;
            }
        }
        catch (SQLException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
        }
        return false;
    }

    public List <Map <String, String>> getComment (String lineoneid) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select c.content,c.createTime,c.id,u.nick from comment c,user u where u.id=c.userid and c.lineoneid=? order by c.createTime DESC");

            stmt.setString (1, lineoneid);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("content", rs.getString ("content"));
                map.put ("createTime", rs.getString ("createTime"));
                map.put ("nick", rs.getString ("nick"));
                ret.add (map);
            }
        }
        catch (SQLException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
        }
        return ret;
    }

    public Map <String, String> getComment (int commentID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select c.content,c.createTime,c.id,u.nick from comment c,user u where u.id=c.userid and c.id=?");

            stmt.setInt (1, commentID);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("content", rs.getString ("content"));
                map.put ("createTime", rs.getString ("createTime"));
                map.put ("nick", rs.getString ("nick"));
                return map;
            }
        }
        catch (SQLException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
        }
        return null;
    }

    public List <Map <String, String>> getReply (String commentID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select r.id,r.content,r.createTime,u.nick from comment r,user u where u.id=r.userid and r.commentid=?");

            stmt.setString (1, commentID);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("content", rs.getString ("content"));
                map.put ("createTime", rs.getString ("createTime"));
                map.put ("nick", rs.getString ("nick"));
                ret.add (map);
            }
        }
        catch (SQLException e) {
            Logger.getLogger (this.getClass ()).error (e, e);
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (SQLException e) {
                    Logger.getLogger (this.getClass ()).error (e, e);
                }
            }
        }
        return ret;
    }
}
