package cm.models;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

/**
 * EPDs are "Environmental Product Declarations", and this class represents a database full of them.
 */
public final class EPDDatabase {

    /* -- Fields ----------------------------------------------------- */

    private String DEFAULT_DB_FILE_NAME = "epds.db";
    private String DEFAULT_SQL_QUERY = "SELECT * FROM concrete_epds WHERE 1";

    private String dbFileName;
    private Connection conn;
    private Statement s;
    private ResultSet rs;

    /* -- Constructor(s) --------------------------------------------- */

    public EPDDatabase(String dbFileName) throws SQLException {
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

    public EPDDatabase() throws SQLException {
        this("");
    }

    /* -- Methods ---------------------------------------------------- */

    // TODO: figure out what the surface of this thing should be (lists, or maybe do it like Ruby on Rails models (or would that be overengineering?))

    // TODO: figure out if these sql ResultSets play nice with some JavaFX components. If not, then we'll have to go full ORM.

    // TODO: beware of SQL injection (again, overengineering?)
    /* -- new test code -------------------------------------------------------

    * */
    private  String sql;
    public List<Mix> getResultsFilteredBy(Map<String, Double> filteredZipcodeMap, Double cs, String companyName) throws SQLException, ParseException {
        List<Mix> result = new ArrayList<Mix>();

        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        PreparedStatement ptmt = null;

        if (filteredZipcodeMap.isEmpty() && companyName.isEmpty() && cs !=0.0){
            ptmt = conn.prepareStatement(sb.toString());
        }
        else {
            // convert set into a String by a format like ('70820','70821','70822')
            StringBuilder sb_ContentofIn = new StringBuilder("(");
            int count = 0;//to indicate if it is first time to append
            for (Iterator<String> it = filteredZipcodeMap.keySet().iterator(); it.hasNext(); ) {
                String f = it.next();
                if (count == 0)
                    sb_ContentofIn.append("'").append(f).append("'");
                else {
                    sb_ContentofIn.append(",'").append(f).append("'");
                }
                count++;
            }
            sb_ContentofIn.append(")");

            if (!filteredZipcodeMap.isEmpty()) {
                sql = sb.append(" AND ZIP_CODE in").append(sb_ContentofIn).toString();
                ptmt = conn.prepareStatement(sql);
                if (cs !=0.0){
                    sql = sb.append(" AND COMPRESSIVE_STRENGTH >= ?").toString();
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, cs.toString());
                }
            }

//            if (!companyName.isEmpty() && !cs.isEmpty()) {
//                sql = sb.append(" AND COMPANY_NAME =? AND CS >= ? ").toString();
//                ptmt = conn.prepareStatement(sql);
//                ptmt.setString(1, companyName);
//                ptmt.setString(2, cs);
//            }else if (!cs.isEmpty()) {
//                sql = sb.append(" AND COMPRESSIVE_STRENGTH >= ?").toString();
//                ptmt = conn.prepareStatement(sql);
//                ptmt.setString(1, cs);
//            }else {sql = sb.append(" AND COMPANY_NAME =?").toString();
//                ptmt = conn.prepareStatement(sql);
//                ptmt.setString(1, companyName);}
        }
        rs = ptmt.executeQuery();

        Mix g;
        while(rs.next()){
            g = new Mix();
            g.setProduct_ID(rs.getString("Product_ID"));
            g.setCS(rs.getString("COMPRESSIVE_STRENGTH"));
            g.setCompany_Name(rs.getString("COMPANY_NAME"));
            g.setLocation(rs.getString("LOCATION"));
            g.setMixNum(rs.getString("MIX_NUMBER"));
            g.setZipCode(rs.getString("ZIP_CODE"));
            g.setGWP(rs.getDouble("GLOBAL_WARMING_POTENTIAL"));
            g.setODP(rs.getDouble("ODP"));
            g.setAP(rs.getDouble("AP"));
            g.setEP(rs.getDouble("EP"));
            g.setPOCP(rs.getDouble("POCP"));
            g.setUnit(rs.getString("UNITS_OF_VOLUME"));
            g.setConcreteHazardousWaste(rs.getDouble("CONCRETE_HAZARDOUS_WASTE"));
            g.setConcreteNonHazardousWaste(rs.getDouble("CONCRETE_NON_HAZARDOUS_WASTE"));
            g.setTotalWaterConsumption(rs.getDouble("TOTAL_WATER_CONSUMPTION"));
            g.setConcreteBatchingWaterConsumption(rs.getDouble("CONCRETE_BATCHING_WATER_CONSUMPTION"));
            g.setConcreteBatchingWaterConsumption(rs.getDouble("CONCRETE_WASHING_WATER_CONSUMPTION"));
            g.setTotalPrimaryEnergyConsumption(rs.getString("TOTAL_PRIMARY_ENERGY_CONSUMPTION"));
            g.setRenewablePrimaryEnergyUse(rs.getDouble("RENEWABLE_PRIMARY_ENERGY_USE"));
            g.setNonRenewableEnergyUse(rs.getDouble("NON_RENWABLE_ENERGY_CONSUMPTION"));
            g.setRenewableMaterialResourcesUse(rs.getDouble("RENEWABLE_MIX_RESOURCES_USE"));
            g.setNonRenewableMaterialResource(rs.getDouble("NON_RENEWABLE_MIX_RESOURCES"));
            //get distance from filterzips map
            String zip = g.getZipCode();
            Double distance = filteredZipcodeMap.get(zip);//return distance in meter
            g.setDistance(distance/1000.0/1.609344); // convert meter to mile
            System.out.println("From "+zip+" to destination is "+distance/1000.0+ " Km");
            result.add(g);
        }
        return result;
    }

    public List<Mix> getResultsFilteredBy_Test(String cs, String companyName) throws SQLException, ParseException {
        List<Mix> result = new ArrayList<Mix>();

        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        PreparedStatement ptmt = null;

        if (companyName.isEmpty() && cs.isEmpty()) {
            ptmt = conn.prepareStatement(sb.toString());
        }
        else {
            if (!companyName.isEmpty() && !cs.isEmpty()) {
                sql = sb.append(" AND Company_Name like ? AND Compressive_Strength >= ? ").toString();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, "%"+companyName+"%");
                ptmt.setString(2, cs);
            }
            else if (!cs.isEmpty()) {
                sql = sb.append(" AND Compressive_Strength >= ?").toString();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, cs);
            }
            else {sql = sb.append(" AND Company_Name like ?").toString();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, "%"+companyName+"%");}
        }
        rs = ptmt.executeQuery();

        Mix g;
        while(rs.next()){
            g = new Mix();
            g.setProduct_ID(rs.getString("Product_ID"));
            g.setCS(rs.getString("COMPRESSIVE_STRENGTH"));
            g.setCompany_Name(rs.getString("COMPANY_NAME"));
            g.setLocation(rs.getString("LOCATION"));
            g.setMixNum(rs.getString("MIX_NUMBER"));
            g.setZipCode(rs.getString("ZIP_CODE"));
            g.setGWP(rs.getDouble("GWP"));
            g.setODP(rs.getDouble("ODP"));
            g.setAP(rs.getDouble("AP"));
            g.setEP(rs.getDouble("EP"));
            g.setPOCP(rs.getDouble("POCP"));
            g.setUnit(rs.getString("UNITS_OF_VOLUME"));
            g.setConcreteHazardousWaste(rs.getDouble("CONCRETE_HAZARDOUS_WASTE"));
            g.setConcreteNonHazardousWaste(rs.getDouble("CONCRETE_NON_HAZARDOUS_WASTE"));
            g.setTotalWaterConsumption(rs.getDouble("TOTAL_WATER_CONSUMPTION"));
            g.setConcreteBatchingWaterConsumption(rs.getDouble("CONCRETE_BATCHING_WATER_CONSUMPTION"));
            g.setConcreteBatchingWaterConsumption(rs.getDouble("CONCRETE_WASHING_WATER_CONSUMPTION"));
            g.setTotalPrimaryEnergyConsumption(rs.getString("TOTAL_PRIMARY_ENERGY_CONSUMPTION"));
            g.setRenewablePrimaryEnergyUse(rs.getDouble("RENEWABLE_PRIMARY_ENERGY_CONSUMPTION"));
            g.setNonRenewableEnergyUse(rs.getDouble("NON_RENEWABLE_ENERGY_CONSUMPTION"));
            g.setRenewableMaterialResourcesUse(rs.getDouble("RENEWABLE_MIX_RESOURCES_CONSUMPTION"));
            g.setNonRenewableMaterialResource(rs.getDouble("NON_RENEWABLE_MIX_RESOURCES_CONSUMPTION"));
            result.add(g);
        }
        return result;
    }
    /**
     * Returns the list of distinct zip codes in the epd database.
     * @return List of distinct zip codes.
     * @throws SQLException
     */
    public List<String> getDistinctZipCodes() throws SQLException {
        List<String> zipcodeList = new ArrayList<>();
        String sql = "SELECT DISTINCT ZIP_CODE FROM CONCRETE_EPDS";
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        String zip;
        while (rs.next()){
            zip = rs.getString("ZIP_CODE");
            zipcodeList.add(zip);
        }
        return zipcodeList;
    }


    /**
     * TODO: add documentation... and also probably change this name?
     */
    public List<Mix> getResults(Map filteredZipcodeMap, Double cs, String companyName) throws SQLException, ParseException {
        return getResultsFilteredBy(filteredZipcodeMap,cs,companyName);
    }

    /**
     * Returns an sql ResultSet, filtered.
     * @param filterClause Example: 'GWP >= 10' has the effect of 'SELECT * FROM EPD WHERE GWP >= 10'.
     * @return
     */
    public ResultSet getResultsFilteredBy(String filterClause) throws SQLException {
        String str;
        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        if (filterClause.isEmpty())
            str = sb.toString();
        else
            str = sb.append(" WHERE ").append(filterClause).toString();
        s = conn.createStatement();
        return s.executeQuery(str);
    }

    /**
     * Return the entire EPD table.
     * @return A ResultSet of the entire EPD table.
     * @throws SQLException
     */
    public ResultSet getResults() throws SQLException {
        return getResultsFilteredBy("");
    }

    /**
     * Rough tester
     */

    public static void main(String[] args) throws SQLException, ParseException {
        Set<String> zips = new HashSet<String>();
        zips.add("70821");
        zips.add("70822");
        zips.add("70823");
        zips.add("70824");
        zips.add("70825");
        zips.add("70826");
        StringBuilder sb0 = new StringBuilder("(");
        int count = 0;
        for (Iterator<String> it = zips.iterator(); it.hasNext(); ) {
            String f = it.next();
            if (count == 0)
            sb0.append("'").append(f).append("'");
            else {
                sb0.append(",'").append(f).append("'");
            }
            count++;
        }
        sb0.append(")");
        System.out.println(sb0.toString());

        List<Mix> result = new ArrayList<Mix>();
        String dbFileName = "rt.db";
        Connection conn;
        Statement s;
        ResultSet rs;
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
        StringBuilder sb = new StringBuilder("SELECT * FROM CONCRETE_EPD WHERE 1");
        PreparedStatement ptmt;
        String sql = sb.toString();
        ptmt = conn.prepareStatement(sql);
        rs = ptmt.executeQuery();
        Mix g;
        while(rs.next()) {
            g = new Mix();

            g.setCS(rs.getString("Compressive Strength"));
            g.setCompany_Name(rs.getString("NAME"));
            g.setLocation(rs.getString("LOCATION"));
            g.setMixNum(rs.getString("MIX NUMBER"));
            g.setZipCode(rs.getString("ZIP CODE"));
            g.setGWP(rs.getDouble("Global   Warming Potential"));
            g.setODP(rs.getDouble("Ozone    Depletion Potential"));
            g.setAP(rs.getDouble("Acidification potential"));
            g.setEP(rs.getDouble("Eutrophication potential"));
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
        System.out.println(result.size());
    }
}
