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

public class DbLineone extends DatabaseAdapter {

    private static final Map <String, DbLineone> instanceMap   = new HashMap <String, DbLineone> ();

    private static final Object                  singletonLock = new Object ();

    public DbLineone (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbLineone getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbLineone (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public List <Map <String, String>> getMyLineone (String userID, int pagenum, int pagesize) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select id,content,createTime from lineone where userid=? order by createTime DESC limit ?,?");

            stmt.setString (1, userID);

            stmt.setInt (2, pagesize * (pagenum - 1));
            stmt.setInt (3, pagesize);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("content", rs.getString ("content"));
                map.put ("createTime", rs.getString ("createTime"));
                ret.add (map);
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
        return ret;
    }

    public Map <String, String> getLineone (int lineoneID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select lo.id,lo.content,lo.createTime,u.nick from lineone lo,user u where lo.userid=u.id and lo.id=?");

            stmt.setInt (1, lineoneID);

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

    public int insert (String content, String userid) {

        int lineoneID = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("insert into lineone(content,userid,createTime) values(?,?,now())",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString (1, content);
            stmt.setString (2, userid);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("insert user lineone success! " + "userid:" + userid);

                rs = stmt.getGeneratedKeys ();

                while (rs.next ()) {
                    lineoneID = rs.getInt (1);
                    return lineoneID;
                }
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
        return lineoneID;
    }

    public boolean insertCircleLineone (String lineoneID, String cricleID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("insert into circle_lineone(lineoneid,circleid) values(?,?)");

            stmt.setString (1, lineoneID);
            stmt.setString (2, cricleID);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info (
                        "insert user circle_lineone success! " + "lineoneid:" + lineoneID + " circleid:" + cricleID);
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

    public boolean update (String lineoneID, String content) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("update lineone set content=? where id=?", Statement.RETURN_GENERATED_KEYS);

            stmt.setString (1, content);
            stmt.setString (2, lineoneID);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("update user lineone success! " + "lineoneID:" + lineoneID);

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

    public boolean delete (String lineoneID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("delete from lineone where id=?");

            stmt.setString (1, lineoneID);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("delete user lineone success! " + "lineoneID:" + lineoneID);
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

    public List <Map <String, String>> getLineoneByCircle (String circleID, int pagenum, int pagesize) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select lo.id,lo.content,lo.createTime,u.nick from lineone lo,circle_lineone clo,user u where lo.id=clo.lineoneid and lo.userid=u.id and clo.circleid=? order by lo.createTime DESC limit ?,?");

            stmt.setString (1, circleID);

            stmt.setInt (2, pagesize * (pagenum - 1));
            stmt.setInt (3, pagesize);

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
        return ret;
    }

    public List <Map <String, String>> getAllLineone (String userID, int pagenum, int pagesize) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select lo.id,lo.content,lo.createTime,u.nick from lineone lo,circle_lineone clo,user_circle uc,user u where lo.userid=u.id and lo.id=clo.lineoneid and uc.circleid=clo.circleid and uc.userid=? order by lo.createTime DESC limit ?,?");

            stmt.setString (1, userID);

            stmt.setInt (2, pagesize * (pagenum - 1));
            stmt.setInt (3, pagesize);

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
        return ret;
    }

    public String getLineoneNum (String userID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select count(*) as count from user u,circle_lineone clo,user_circle uc where u.id=uc.userid and uc.circleid=clo.circleid and u.id=?");

            stmt.setString (1, userID);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                return rs.getString ("count");
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

    public String getLineoneNumByCircle (String userID, String circleID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select count(*) as count from user u,circle_lineone clo,user_circle uc where u.id=uc.userid and uc.circleid=clo.circleid and u.id=? and clo.circleid=?");

            stmt.setString (1, userID);
            stmt.setString (2, circleID);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                return rs.getString ("count");
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
}
