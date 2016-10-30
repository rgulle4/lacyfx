package cm.models;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cm.models.Model.*;

/**
 * EPDs are "Environmental Product Declarations", and this class represents a database full of them.
 */
public class EPDDatabase {

    /* -- Fields ----------------------------------------------------- */

    private String DEFAULT_DB_FILE_NAME = "rt.db";
    private String DEFAULT_SQL_QUERY = "SELECT * FROM EPD WHERE 1";

    private String dbFileName;
    private Connection conn;
    private Statement s;
    private ResultSet rs;

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
    /* -- new test code -------------------------------------------------------

    * */
    private  String sql;
    public List<Material> getResultsFilteredBy(String zipCode, String cs, String companyName) throws SQLException, ParseException {
        List<Material> result = new ArrayList<Material>();

        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        PreparedStatement ptmt = null;
        if (zipCode.isEmpty() && companyName.isEmpty() && cs.isEmpty()) {
            ptmt = conn.prepareStatement(sb.toString());
        }
        else {
            if (!zipCode.isEmpty()) {
                sql = sb.append(" AND ZIP in ?").toString();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, zipCode);
            }
            if (!companyName.isEmpty()) {
                sql = sb.append(" AND NAME =?").toString();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, companyName);
            }
            if (!cs.isEmpty()) {
                sql = sb.append(" AND CS >= ?").toString();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, cs);
            }
        }
        rs = ptmt.executeQuery();

        Material g;
        while(rs.next()){
            g = new Material();

            g.setCS(rs.getString("CS"));
            g.setCompany_Name(rs.getString("NAME"));
            g.setLocation(rs.getString("LOCATION"));
            g.setMixNum(rs.getString("MIXNUMBER"));
            g.setZipCode(rs.getString("ZIP"));
            g.setGWP(rs.getDouble("GWP"));
            g.setODP(rs.getDouble("ODP"));
            g.setAP(rs.getDouble("AP"));
            g.setEP(rs.getDouble("EP"));
            g.setPOCP(rs.getDouble("POCP"));
            g.setUnit(rs.getString("UNITS"));
            g.setConcreteHazardousWaste(rs.getDouble("CHW"));
            g.setConcreteNonHazardousWaste(rs.getDouble("CNHW"));
            g.setTotalWaterConsumption(rs.getDouble("TW"));
            g.setTotalPrimaryEnergyConsumption(rs.getString("PEC"));
            g.setRenewablePrimaryEnergyUse(rs.getDouble("RE"));
            g.setNonRenewableEnergyUse(rs.getDouble("NRE"));
            g.setRenewableMaterialResourcesUse(rs.getDouble("RM"));
            g.setNonRenewableMaterialResource(rs.getDouble("NRM"));

            result.add(g);
        }
        return result;
    }

    public List<String> getAllZipcode() throws SQLException {
        List<String> zipcodeList = new ArrayList<>();
        String sql = "SELECT DISTINCT ZIP FROM EPD";
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        String zip;
        while (rs.next()){
            zip = rs.getString("ZIP");
            zipcodeList.add(zip);
        }
        return zipcodeList;
    }


    /**
     * Return the entire EPD table.
     * @return A ResultSet of the entire EPD table.
     * @throws SQLException
     */
    public List<Material> getResults() throws SQLException, ParseException {
        return getResultsFilteredBy("","","");
    }

    /**
     * Rough tester
     */
//    public static void main(String[] args) throws SQLException {
//        ResultSet rs = new EPDDatabase().getResultsFilteredBy("CS >= '7000'");
//        DBTablePrinter.printResultSet(rs);
//    }

}
