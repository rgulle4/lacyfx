package models;

import java.sql.*;
import net.efabrika.util.DBTablePrinter; // https://github.com/htorun/dbtableprinter

public class SQLiteJDBC {
    public static void main(String[] args) throws SQLException {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e);
        }

        Statement s = c.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM EPD");
        System.out.println("ResultSet....");
        DBTablePrinter.printResultSet(r);
    }

}
