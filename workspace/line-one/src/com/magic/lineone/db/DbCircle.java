package com.magic.lineone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DbCircle extends DatabaseAdapter {

    private static final Map <String, DbCircle> instanceMap   = new HashMap <String, DbCircle> ();

    private static final Object                 singletonLock = new Object ();

    public DbCircle (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbCircle getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbCircle (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public boolean insert (String name, String descr, String owerID, String isPublic) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("insert into circle(name,descr,ownerid,ispublic,createTime) values(?,?,?,?,now())");

            stmt.setString (1, name);

            if (descr == null) {
                stmt.setNull (2, Types.VARCHAR);
            }
            else {
                stmt.setString (2, descr);
            }

            stmt.setString (3, owerID);

            if (isPublic.equals ("1")) {
                stmt.setBoolean (4, true);
            }
            else {
                stmt.setBoolean (4, false);
            }

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).error ("insert circle success! " + "name:" + name);
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

    public boolean hasjoined (String circleID, String userID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("select id from user_circle where circleid=? and userid=?");

            stmt.setString (1, circleID);

            stmt.setString (2, userID);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Logger.getLogger (this.getClass ()).info (
                        "has joined circle! " + "circleID:" + circleID + " userID:" + userID);
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

    public boolean join (String circleID, String userID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("insert into user_circle(circleid,userid,createTime) values(?,?,now())");

            stmt.setString (1, circleID);

            stmt.setString (2, userID);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info (
                        "join circle success! " + "circleID:" + circleID + " userID:" + userID);
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

    public boolean quit (String circleID, String userID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("delete from user_circle where circleid=? and userid=?");

            stmt.setString (1, circleID);

            stmt.setString (2, userID);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info (
                        "quit circle success! " + "circleID:" + circleID + " userID:" + userID);
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

    public boolean delete (String id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("delete from circle where id=?");

            stmt.setString (1, id);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("delete circle success! " + "id:" + id);
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

    public boolean dissolve (String id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("delete from user_circle where circleid=?");

            stmt.setString (1, id);

            int affected = stmt.executeUpdate ();

            Logger.getLogger (this.getClass ()).info (
                    "dissolve circle success! " + "id:" + id + " dissolve affected:" + affected);
            return true;
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

    public boolean upate (String id, String name, String descr, boolean ispublic) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("update circle set name=?,descr=?,ispublic=? where id=?");

            stmt.setString (1, name);

            if (descr == null) {
                stmt.setNull (2, Types.VARCHAR);
            }
            else {
                stmt.setString (2, descr);
            }

            stmt.setBoolean (3, ispublic);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("update circle success! " + "name:" + name);
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

    public boolean upateTotalNumA (String id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("update circle set totalNum=totalNum+1 where id=?");

            stmt.setString (1, id);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("update circle totalNum add success ! " + "circleid:" + id);
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

    public boolean upateTotalNumM (String id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("update circle set totalNum=totalNum-1 where id=?");

            stmt.setString (1, id);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("update circle totalNum m success! " + "circleid:" + id);
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

    public List <Map <String, String>> getCircle (String ownerID, int pagenum, int pagesize) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("select * from circle where ownerid=? order by createTime DESC limit ?,?");

            stmt.setString (1, ownerID);
            stmt.setInt (2, (pagenum - 1) * pagesize);
            stmt.setInt (3, pagesize);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("name", rs.getString ("name"));
                map.put ("descr", rs.getString ("descr"));
                map.put ("totalNum", rs.getString ("totalNum"));
                map.put ("createTime", rs.getString ("createTime"));
                map.put ("ispublic", rs.getString ("ispublic"));
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

    public List <Map <String, String>> getCircle (int pagenum, int pagesize) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();
        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select c.id,c.name,c.descr,c.totalNum,c.createTime,u.nick from circle c,user u where c.ownerid=u.id order by createTime DESC  limit ?,?");

            stmt.setInt (1, (pagenum - 1) * pagesize);
            stmt.setInt (2, pagesize);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("name", rs.getString ("name"));
                map.put ("descr", rs.getString ("descr"));
                map.put ("totalNum", rs.getString ("totalNum"));
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

    public List <Map <String, String>> getJoinCircle (String userID, int pagenum, int pagesize) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();
        try {
            conn = getConnection ();
            stmt = conn
                    .prepareStatement ("select c.id,c.name,c.descr,c.totalNum,c.createTime,u.nick from circle c,user u,user_circle uc where c.id=uc.circleid and u.id=c.ownerid and uc.userid=? order by uc.createTime DESC limit ?,?");

            stmt.setString (1, userID);
            stmt.setInt (2, (pagenum - 1) * pagesize);
            stmt.setInt (3, pagesize);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("name", rs.getString ("name"));
                map.put ("descr", rs.getString ("descr"));
                map.put ("totalNum", rs.getString ("totalNum"));
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
}
