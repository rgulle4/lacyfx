package cm.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public final class CostDatabase {
    /* -- Fields ----------------------------------------------------- */

    private String DEFAULT_DB_FILE_NAME = "costAnalysis.db";
    private String DEFAULT_SQL_QUERY = "SELECT DISTINCT Item_Description FROM Rigid_Pavement WHERE 1";

    private String dbFileName;
    private Connection conn;
    private Statement s;
    private ResultSet rs;

    /* -- Constructor(s) --------------------------------------------- */

    public CostDatabase(String dbFileName) throws SQLException {
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

    public CostDatabase() throws SQLException {
        this("");
    }
    /* -- Methods ---------------------------------------------------- */
    public List<String> getInitialItems(String dtype,String itemType) throws SQLException {
        List<String> initialItems = new ArrayList<>();
        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        if (dtype != "") sb.append(" AND Design_Type = ").append("'").append(dtype).append("'");
        if (itemType != "") sb.append(" AND Item_Type = ").append("'").append(itemType).append("'");
        String sql = sb.toString();
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        String initial_Item;
        while(rs.next()){
            initial_Item = rs.getString("Item_Description");
            initialItems.add(initial_Item);
        }
        return initialItems;
    }

    public static void main(String[] args) throws SQLException {
        CostDatabase costDatabase = new CostDatabase();
        String designType = "CRCP";
        String itemType = "M&R";
        List<String> result =costDatabase.getInitialItems(designType,itemType);
        for (int i =0; i < result.size(); i++){
            System.out.println(result.get(i));
        }
        System.out.println(result.size());
    }
}