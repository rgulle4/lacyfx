package cm.models;

import libs.net.efabrika.util.DBTablePrinter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EPDs are "Environmental Product Declarations", and this class represents a database full of them.
 */
public class EPDDatabase {

    /* -- Fields ----------------------------------------------------- */

    private String DEFAULT_DB_FILE_NAME = "rt.db";
    private String DEFAULT_SQL_QUERY = "SELECT * FROM EPD";

    private String dbFileName;
    private Connection conn;
    private Statement s;
    private ResultSet r;

    /* -- Constructor(s) --------------------------------------------- */

    public EPDDatabase(String dbFileName) {
        if (dbFileName.isEmpty())
            this.dbFileName = DEFAULT_DB_FILE_NAME;
        else
            this.dbFileName = dbFileName;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + this.dbFileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EPDDatabase() {
        this("");
    }

    /* -- Methods ---------------------------------------------------- */

    // TODO: figure out what the surface of this thing should be (lists, or maybe do it like Ruby on Rails models (or would that be overengineering?))

    // TODO: figure out if these sql ResultSets play nice with some JavaFX components. If not, then we'll have to go full ORM.

    // TODO: beware of SQL injection (again, overengineering?)

    /**
     * Returns an sql ResultSet, filtered.
     * @param filterClause Example: 'GWP >= 10' has the effect of 'SELECT * FROM EPD WHERE GWP >= 10'.
     * @return
     */
    public List<AlternativeMaterial> getResultsFilteredBy(String filterClause) throws SQLException {
        List<AlternativeMaterial> result = new ArrayList<AlternativeMaterial>();

        String str;
        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        if (filterClause.isEmpty()) {
            str = sb.toString();
        } else {
            str = sb.append(" WHERE ").append(filterClause).toString();
        }
        s = conn.createStatement();
        r = s.executeQuery(str);

        AlternativeMaterial g =null;
        while(r.next()){
            g = new AlternativeMaterial();
            g.setID(r.getString("ID"));
            g.setCS(r.getString("CS"));
            g.setGWP(r.getFloat("GWP"));
            g.setAP(r.getFloat("AP"));
            g.setEP(r.getFloat("EP"));
            g.setODP(r.getFloat("ODP"));
            g.setPOCP(r.getFloat("POCP"));
            g.setZIP(r.getFloat("ZIP"));
            g.setMIXNUMBER(r.getString("MIXNUMBER"));
            g.setNAME(r.getString("NAME"));
            g.setLOCATION(r.getString("LOCATION"));

            result.add(g);
        }
        return result;
    }

    /**
     * Return the entire EPD table.
     * @return A ResultSet of the entire EPD table.
     * @throws SQLException
     */
    public List<AlternativeMaterial> getResults() throws SQLException {
        return getResultsFilteredBy("");
    }

    /**
     * Rough tester
     */
//    public static void main(String[] args) throws SQLException {
//        ResultSet r = new EPDDatabase().getResultsFilteredBy("CS >= '7000'");
//        DBTablePrinter.printResultSet(r);
//    }

}
