/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *
 * @author MyPC
 */
public class DataProcess {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Preferences database = Helper.readConfig("database");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + database.get("dbhost", null) + ":" + database.get("dbport", null) + ";databaseName=" + database.get("dbname", null);
            conn = DriverManager.getConnection(url, database.get("dbuser", null), database.get("dbpass", null));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
