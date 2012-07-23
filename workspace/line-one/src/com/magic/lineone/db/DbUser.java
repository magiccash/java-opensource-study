package com.magic.lineone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DbUser extends DatabaseAdapter {

    private static final Map <String, DbUser> instanceMap   = new HashMap <String, DbUser> ();

    private static final Object               singletonLock = new Object ();

    private DbUser (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbUser getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbUser (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public boolean isExistNick (String nick) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("select nick from user where nick=?");

            stmt.setString (1, nick);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                return true;
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
        return false;
    }

    public boolean isExistEmail (String email) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("select email from user where email=?");

            stmt.setString (1, email);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                return true;
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
        return false;
    }

    public Map <String, String> isContainUser (String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("select id,email,nick,password from user where email=? or nick=?");

            stmt.setString (1, username);
            stmt.setString (2, username);

            rs = stmt.executeQuery ();

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("email", rs.getString ("email"));
                map.put ("nick", rs.getString ("nick"));
                map.put ("password", rs.getString ("password"));
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

    public int insert (String nick, String password, String email, String phone) {

        int userid = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement (
                    "insert into user(nick,password,email,phone,createTime) values(?,?,?,?,now())",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString (1, nick);
            stmt.setString (2, password);
            stmt.setString (3, email);

            if (phone == null) {
                stmt.setNull (4, Types.VARCHAR);
            }
            else {
                stmt.setString (4, phone);
            }

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("insert user success! " + "nick:" + nick);

                rs = stmt.getGeneratedKeys ();

                while (rs.next ()) {
                    userid = rs.getInt (1);
                    return userid;
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
        return userid;
    }

    public boolean update (String id, String nick, String email, String phone) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("update user set nick=?,email=?,phone=? where id=?");
            stmt.setString (1, nick);

            if (email == null) {
                stmt.setNull (2, Types.VARCHAR);
            }
            else {
                stmt.setString (2, email);
            }

            if (phone == null) {
                stmt.setNull (3, Types.VARCHAR);
            }
            else {
                stmt.setString (3, phone);
            }

            stmt.setString (4, id);

            int affected = stmt.executeUpdate ();
            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("update user success! " + "nick:" + nick);
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

    public boolean update (String id, String password) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("update user set password=? where id=?");
            stmt.setString (1, password);

            stmt.setString (2, id);

            int affected = stmt.executeUpdate ();
            if (affected == 1) {
                Logger.getLogger (this.getClass ()).info ("change user password success! " + "userid:" + id);
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

    public static void main (String [] args) {

    }
}
