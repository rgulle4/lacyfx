package cm.models;

import cm.controllers.EconAnalysisController.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public final class CostDatabase {
    /* -- Fields ----------------------------------------------------- */

    private String DEFAULT_DB_FILE_NAME = "costAnalysis.db";
    private String DEFAULT_SQL_QUERY = "SELECT * FROM Pavements WHERE 1";

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
    public List<CostItems> getInitialItems(String designType) throws SQLException {
        List<CostItems> initialItems = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM Pavements WHERE Item_Type = 'Initial'");
        if (designType != "") sb.append(" AND DesignType = ").append("'").append(designType).append("'");
        String sql = sb.toString();
        s = conn.createStatement();
        rs = s.executeQuery(sql);

        while(rs.next()){
            CostItems temp = new CostItems();
            temp.setItemDescription(rs.getString("Item_Description"));
            temp.setItemType(rs.getString("item_Type"));
            temp.setPrice(rs.getDouble("Weighted_average_unit_price"));
            temp.setFilter1(rs.getString("Filter1"));
            temp.setFilter2(rs.getString("Filter2"));
            temp.setUnit(rs.getString("Unit"));
            initialItems.add(temp);
        }
        return initialItems;
    }

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

    public List<CostItems> getCostItems(String dtype, String f1, String f2) throws SQLException {
        List<CostItems> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder(DEFAULT_SQL_QUERY);
        if (dtype != "") sb.append(" AND DesignType = ").append("'").append(dtype).append("'");
        if (f1 != "") sb.append(" AND Filter1 = ").append("'").append(f1).append("'");
        if (f2 != "") sb.append(" AND Filter2 = ").append("'").append(f2).append("'");

        String sql = sb.toString();
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        CostItems g;
        while(rs.next()){
            g = new CostItems();
            g.setItemDescription(rs.getString("Item_Description"));
            g.setItemType(rs.getString("item_Type"));
            g.setPrice(rs.getDouble("Weighted_average_unit_price"));
            items.add(g);
        }
        return items;
    }

    public ObservableList<String> getDesignType(String pavementType) throws SQLException {
        ObservableList<String> types = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT DESIGNTYPE FROM Pavements WHERE 1";
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" AND").append(" Pavement_Type = ")
                .append("'").append(pavementType).append("'");
        s = conn.createStatement();
        rs = s.executeQuery(sb.toString());
        while(rs.next()){
            types.add(rs.getString("DesignType"));
        }
        return types;
    }

    public static void main(String[] args) throws SQLException {
        CostDatabase costDatabase = new CostDatabase();
        String designType = "CRCP";
        String itemType = "M&R";
        List<CostItems> result =costDatabase.getInitialItems(designType);
        for (int i =0; i < result.size(); i++){
            System.out.println(result.get(i));
        }
        System.out.println(result.size());
    }

    public ObservableList<String> getInitFilter1Item(String designType) throws SQLException {
        ObservableList<String> initFilter1 = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Filter1 FROM Pavements WHERE Item_Type = 'Initial'";
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" AND").append(" DesignType = ")
                .append("'").append(designType).append("'");
        s = conn.createStatement();
        rs = s.executeQuery(sb.toString());
        while(rs.next()){
            initFilter1.add(rs.getString("Filter1"));
        }
        return initFilter1;
    }

    public ObservableList<String> getMaintFilter1Item(String designType) throws SQLException {
        ObservableList<String> initFilter1 = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Filter1 FROM Pavements WHERE Item_Type = 'M&R'";
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" AND").append(" DesignType = ")
                .append("'").append(designType).append("'");
        s = conn.createStatement();
        rs = s.executeQuery(sb.toString());
        while(rs.next()){
            initFilter1.add(rs.getString("Filter1"));
        }
        return initFilter1;
    }

    public ObservableList<String> getInitFilter2Item(String designType, String filter1) throws SQLException {
        ObservableList<String> initFilter2 = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Filter2 FROM Pavements WHERE 1";
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" AND").append(" DesignType = ")
                .append("'").append(designType).append("'");
        sb.append(" AND").append(" Filter1 = ")
                .append("'").append(filter1).append("'");

        s = conn.createStatement();
        rs = s.executeQuery(sb.toString());
        while(rs.next()){
            initFilter2.add(rs.getString("Filter2"));
        }
        return initFilter2;
    }

    public List<CostItems> getMaintItems(String designType) throws SQLException {
        List<CostItems> maintItems = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM Pavements WHERE Item_Type = 'M&R'");
        if (designType != "") sb.append(" AND DesignType = ").append("'").append(designType).append("'");
        String sql = sb.toString();
        s = conn.createStatement();
        rs = s.executeQuery(sql);

        while(rs.next()){
            CostItems temp = new CostItems();
            temp.setItemDescription(rs.getString("Item_Description"));
            temp.setItemType(rs.getString("item_Type"));
            temp.setPrice(rs.getDouble("Weighted_average_unit_price"));
            temp.setFilter1(rs.getString("Filter1"));
            temp.setFilter2(rs.getString("Filter2"));
            temp.setUnit(rs.getString("Unit"));
            maintItems.add(temp);
        }
        return maintItems;
    }
}
