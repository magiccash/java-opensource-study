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

public class DbLineoneIndex extends DatabaseAdapter {

    private static final Map <String, DbLineoneIndex> instanceMap   = new HashMap <String, DbLineoneIndex> ();

    private static final Object                       singletonLock = new Object ();

    public DbLineoneIndex (String jdbcurl) {

        super (jdbcurl);
    }

    public static DbLineoneIndex getInstance (String url) {

        if (!instanceMap.containsKey (url)) {
            synchronized (singletonLock) {
                if (!instanceMap.containsKey (url)) {
                    instanceMap.put (url, new DbLineoneIndex (url));
                }
            }
        }
        return instanceMap.get (url);
    }

    public List <Map <String, String>> getLineones (String key, String mode, int pagenum, int pagesize) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Map <String, String>> ret = new ArrayList <Map <String, String>> ();
        try {

            String sql = "SELECT lo.*,u.nick FROM lineone_index AS loi,lineone AS lo,user as u " + "WHERE loi.query='"
                    + key + ";mode=" + mode + "' and loi.id=lo.id and u.id=lo.userid limit " + (pagenum - 1) * pagesize
                    + "," + pagesize;
            conn = getConnection ();

            stmt = conn.createStatement ();

            rs = stmt.executeQuery (sql);

            while (rs.next ()) {
                Map <String, String> map = new HashMap <String, String> ();
                map.put ("id", rs.getString ("id"));
                map.put ("createTime", rs.getString ("createTime"));
                map.put ("content", rs.getString ("content"));
                map.put ("userid", rs.getString ("userid"));
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
