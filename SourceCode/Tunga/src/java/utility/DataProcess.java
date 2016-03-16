/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 *
 * @author notte
 */
public class DataProcess {
    public static void main(String[] args) {
        DataProcess dt = new DataProcess();
        dt.getConnection();
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Preferences database = this.readConfig("database");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + database.get("dbhost", null) + ":" + database.get("dbport", null) + ";databaseName=" + database.get("dbname", null);
            conn = DriverManager.getConnection(url, database.get("dbuser", null), database.get("dbpass", null));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public Preferences readConfig(String node) {
        Preferences pref = null;
        try {
            Ini ini = new Ini(new File(this.appPath() + "config/main.ini"));
            java.util.prefs.Preferences prefs = new IniPreferences(ini);
            pref = prefs.node(node);
        } catch (IOException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pref;
    }

    public String appPath() {
        String reponsePath = "";
        try {
            String path = DataProcess.class.getResource("").getPath();
            String fullPath = URLDecoder.decode(path, "UTF-8");
            String pathArr[] = fullPath.split("/WEB-INF/classes");
            fullPath = pathArr[0];
            reponsePath = new File(fullPath).getPath() + File.separatorChar;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponsePath;
    }

    public static boolean login(String u, String p) {
        DataProcess dt = new DataProcess();
        Preferences login = dt.readConfig("login");
        return (u.equals(login.get("username", null)) && p.equals(login.get("password", null)));
    }
}
