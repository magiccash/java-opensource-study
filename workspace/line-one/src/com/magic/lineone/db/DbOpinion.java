package com.magic.lineone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DbOpinion extends DatabaseAdapter {

    private static final Map <String, DbOpinion> instanceMap   = new HashMap <String, DbOpinion> ();

    private static final Object                  singletonLock = new Object ();

    public DbOpinion (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbOpinion getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbOpinion (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public boolean insert (String content, String userID) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("insert into opinion(content,userid,createTime,status) values(?,?,now(),?)");

            stmt.setString (1, content);
            stmt.setString (2, userID);
            stmt.setInt (3, 0);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).error ("insert opinion success!");
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
            stmt = conn.prepareStatement ("delete from opinion where id=?");

            stmt.setString (1, id);

            int affected = stmt.executeUpdate ();

            if (affected == 1) {
                Logger.getLogger (this.getClass ()).error ("insert opinion success!");
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

    public boolean getOpinion (int pagesize, int pagenum) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection ();
            stmt = conn.prepareStatement ("select * from opinion limit ?,?");

            // int affected = stmt.executeUpdate ();

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

}
