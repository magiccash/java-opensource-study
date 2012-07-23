package com.magic.lineone.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DbCircleIndex extends DatabaseAdapter {

    private static final Map <String, DbCircleIndex> instanceMap   = new HashMap <String, DbCircleIndex> ();

    private static final Object                      singletonLock = new Object ();

    public DbCircleIndex (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbCircleIndex getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbCircleIndex (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public List <Map <String, String>> getCircles (String key, String mode, int pagenum, int pagesize) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();
        try {

            String sql = "SELECT c.*,u.nick FROM circle_index AS ci,circle AS c,user u WHERE ci.query='" + key
                    + ";mode=" + mode + "' and c.ownerid=u.id and ci.id=c.id and ispublic=1  limit " + (pagenum - 1)
                    * pagesize + "," + pagesize;
            conn = getConnection ();

            stmt = conn.createStatement ();

            rs = stmt.executeQuery (sql);

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("name", rs.getString ("name"));
                map.put ("descr", rs.getString ("descr"));
                map.put ("totalNum", rs.getString ("totalNum"));
                map.put ("createTime", rs.getString ("createTime"));
                map.put ("ownername", rs.getString ("nick"));
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
