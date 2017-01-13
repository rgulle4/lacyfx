package cm.controllers;


import cm.App;
import cm.models.CostDatabase;
import cm.models.Design;
import cm.models.Layer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cm.models.Model.DESIGNS;

/**
 * Created by royg59 on 9/21/16.
 */
public class EconAnalysisController {

    @FXML
    public TreeView treeView = new TreeView();
    @FXML
    public ComboBox ComboBox_DType;

    //elements for TableView(initial)
    @FXML
    public ComboBox ComboBox_MR_Filter1;
    @FXML
    public TableView<CostItems> tableView_MR;
    @FXML
    public TableColumn<CostItems, CheckBox> column_Selected_MR;
    @FXML
    public TableColumn<CostItems,String> column_Unit_MR;
    @FXML
    public TableColumn<CostItems,String> column_ItemDescription_MR;
    @FXML
    public TableColumn<CostItems, String> column_OccurYear_MR;
    @FXML
    public TableColumn<CostItems, String> column_Quantity_MR;
    @FXML
    public TableColumn<CostItems, String> column_Price_MR;
    //elements for TableView(M&R)
    @FXML
    public ComboBox ComboBox_Init_Filter1;
    @FXML
    public TableView<CostItems> tableView;
    @FXML
    public TableColumn<CostItems, CheckBox> column_Selected;
    @FXML
    public TableColumn<CostItems,String> column_Unit;
    @FXML
    public TableColumn<CostItems,String> column_ItemDescription;
    @FXML
    public TableColumn<CostItems, String> column_Quantity;
    @FXML
    public TableColumn<CostItems, String> column_Price;
    @FXML
    public Button Button_CostAnalysis;
    @FXML
    public TextField Start_Year_Textfield;
    @FXML
    public TextField Total_Year_Textfield;
    @FXML
    public TextField Discount_Rate_Textfield;

    private List<CostItems> initCostItems;

    private List<CostItems> maintainanceCostItems;

    @FXML
    public void initialize(){
        treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                if (newValue == null) return;
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                //set items for ComboBox_PavementType
                String designType;
                if (selectedItem.getValue().contains("Rigid")){
                    designType = "Rigid";
                }
                else if ((selectedItem.getValue().contains("Flexible"))) {
                    designType = "Flexible";
                }
                else {designType = "";}
                try {
                    //initialize
                    clearfields();
                    //set up ComboBox_DType;
                    showDesignType(designType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        ComboBox_DType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue == null) return;
                Object selectedItem = (Object)newValue;
                try {
                    String designType = ComboBox_DType.getValue().toString();

                    ComboBox_Init_Filter1.getSelectionModel().clearSelection();
                    ObservableList<String> filter1Item_Init = new CostDatabase().getInitFilter1Item(designType);
                    ComboBox_Init_Filter1.setItems(filter1Item_Init);

                    ComboBox_MR_Filter1.getSelectionModel().clearSelection();
                    ObservableList<String> filter1Item_MR = new CostDatabase().getMaintFilter1Item(designType);
                    ComboBox_MR_Filter1.setItems(filter1Item_MR);

                    initCostItems = new CostDatabase().getInitialItems(ComboBox_DType.getValue().toString());
                    maintainanceCostItems = new CostDatabase().getMaintItems(ComboBox_DType.getValue().toString());
//                    showTableData(initCostItems,tableView);
//                    showMRTableData(maintainanceCostItems,tableView_MR);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        ComboBox_Init_Filter1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue == null) return;
                Object selectedItem = (Object)newValue;
                    List<CostItems> costItems_Filter1 = getCostItems_ByFilter1(initCostItems,ComboBox_Init_Filter1);
                    showTableData(costItems_Filter1,tableView);
            }
        });

        ComboBox_MR_Filter1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue == null) return;
                Object selectedItem = (Object)newValue;
                List<CostItems> costItems_Filter1 = getCostItems_ByFilter1(maintainanceCostItems,ComboBox_MR_Filter1);
                showMRTableData(costItems_Filter1,tableView_MR);
            }
        });
    }

    private void clearfields() {
        ComboBox_DType.getItems().clear();
        ComboBox_Init_Filter1.getItems().clear();
        ComboBox_MR_Filter1.getItems().clear();
        tableView.getItems().clear();
        tableView_MR.getItems().clear();
        if (initCostItems!=null)initCostItems.clear();
        if (maintainanceCostItems!=null)maintainanceCostItems.clear();
    }

    private List<CostItems> getCostItems_ByFilter1(List<CostItems> initCostItems, ComboBox comboBox) {
        List<CostItems> result = new ArrayList<>();
        if (initCostItems == null){
            return null;
        }
        for (CostItems temp: initCostItems){
            String a = temp.getFilter1();
            String b = comboBox.getValue().toString();
            if (a.equals(b)){
                result.add(temp);
            }
        }
        return result;
    }

    private void showTableData(List<CostItems> initCostItems,TableView tableView) {
        if (initCostItems == null){
            return;
        }
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        column_Selected.setCellValueFactory(new PropertyValueFactory<CostItems, CheckBox>("isSelected"));
        column_Selected.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CostItems, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<CostItems, CheckBox> arg0) {
                CostItems user = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(user.getSelected());

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {

                        user.setSelected(new_val);
                        updateSelection();
                    }
                });

                return new SimpleObjectProperty<CheckBox>(checkBox);

            }

        });
        column_Unit.setCellValueFactory(new PropertyValueFactory<CostItems, String>("unit"));
        column_Price.setCellValueFactory(new PropertyValueFactory<CostItems, String>("price"));
        column_ItemDescription.setCellValueFactory(new PropertyValueFactory<CostItems, String>("filter2"));
        column_Quantity.setCellValueFactory(new PropertyValueFactory<CostItems, String>("quantity"));
        column_Quantity.setCellFactory(TextFieldTableCell.<CostItems>forTableColumn());
        column_Quantity.setOnEditCommit(
                (TableColumn.CellEditEvent<CostItems,String> t) -> {
                    ((CostItems) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setQuantity(t.getNewValue());
                });
        ObservableList<CostItems> items = FXCollections.observableArrayList();
        for (CostItems temp: initCostItems){
            items.add(temp);
        }
        tableView.setItems(items);
    }

    private void showMRTableData(List<CostItems> initCostItems,TableView tableView) {
        if (initCostItems == null){
            return;
        }

        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        column_Selected_MR.setCellValueFactory(new PropertyValueFactory<CostItems, CheckBox>("isSelected"));
        column_Selected_MR.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CostItems, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<CostItems, CheckBox> arg0) {
                CostItems user = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(user.getSelected());

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {

                        user.setSelected(new_val);
                        updateSelection();
                    }
                });

                return new SimpleObjectProperty<CheckBox>(checkBox);

            }

        });
        column_Unit_MR.setCellValueFactory(new PropertyValueFactory<CostItems, String>("unit"));
        column_Price_MR.setCellValueFactory(new PropertyValueFactory<CostItems, String>("price"));
        column_ItemDescription_MR.setCellValueFactory(new PropertyValueFactory<CostItems, String>("filter2"));
        column_OccurYear_MR.setCellValueFactory(new PropertyValueFactory<CostItems, String>("occurYear"));
        column_OccurYear_MR.setCellFactory(TextFieldTableCell.<CostItems>forTableColumn());
        column_OccurYear_MR.setOnEditCommit(
                (TableColumn.CellEditEvent<CostItems,String> t) -> {
                    ((CostItems) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setOccurYear(t.getNewValue());
                });
        column_Quantity_MR.setCellValueFactory(new PropertyValueFactory<CostItems, String>("quantity"));
        column_Quantity_MR.setCellFactory(TextFieldTableCell.<CostItems>forTableColumn());
        column_Quantity_MR.setOnEditCommit(
                (TableColumn.CellEditEvent<CostItems,String> t) -> {
                    ((CostItems) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setQuantity(t.getNewValue());
                });
        ObservableList<CostItems> items = FXCollections.observableArrayList();
        for (CostItems temp: initCostItems){
            items.add(temp);
        }
        tableView.setItems(items);
    }

    public void loadTreeList(){
        // create a treeView Helper
        TreeViewHelper helper = new TreeViewHelper();
        ArrayList<TreeItem> items = helper.getItems();
        TreeItem rootItem = new TreeItem("Alternates");
        rootItem.getChildren().addAll(items);
        // initialize

        treeView.getSelectionModel().clearSelection();
        treeView.setRoot(null);
        treeView.setRoot(rootItem);
    }
    public class TreeViewHelper{

        public TreeViewHelper(){}

        public ArrayList<TreeItem> getItems(){
            ArrayList<TreeItem> items = new ArrayList<>();
            for (int i = 1 ; i <= DESIGNS.size();i ++){
                StringBuilder sb = new StringBuilder("Design "+i);
                String key = sb.toString();
                Design design_temp = DESIGNS.get(key);
                String design_type = design_temp.getDesignType();
                String pavement_type = design_temp.getPavementType();
                StringBuilder sb_Item_Name = new StringBuilder(key);
                sb_Item_Name.append(": ").append(pavement_type)
                        .append("(").append(design_type).append(")");
                String item_Name = sb_Item_Name.toString();
                TreeItem designs = new TreeItem(item_Name);
//                designs.getChildren().addAll(getLayers(design_temp));
                items.add(designs);
            }
            return items;
        }
        //this method create an arrayList of treeItems (Rigid pavement)
        private ArrayList<TreeItem> getLayers(Design design){
            ArrayList<TreeItem> layerItems = new ArrayList<>();
            int layerNum = 1;
            for (Layer layer_temp: design.getLayers()){
                StringBuilder layerID_sb = new StringBuilder("Layer "+layerNum);
                String layer_ID = layerID_sb.toString();
                layerNum ++;
                String layer_Type = layer_temp.getLayerType();
                StringBuilder sb = new StringBuilder(
                        layer_ID+ ": "+layer_Type);
                String item_Name = sb.toString();
                TreeItem layer = new TreeItem(item_Name);
                layerItems.add(layer);
            }
            return layerItems;
        }
    }

    private void showDesignType(String pavementType) throws SQLException {
        ObservableList<String> PT = new CostDatabase().getDesignType(pavementType);
        ComboBox_DType.getItems().clear();
        ComboBox_DType.setItems(PT);
//        ComboBox_DType.getSelectionModel().selectFirst();
    }

    private void updateSelection() {
        List<CostItems> costItemsList = tableView.getItems();
        int index = 0;
        for (CostItems costItems:costItemsList){
            if (costItems.isSelected){
                tableView.getSelectionModel().select(index);
            }else{
                tableView.getSelectionModel().clearSelection(index);
            }
            index ++;
        }
    }

    public void saveButton(){
        List<CostItems> initCostItemsList = new ArrayList<>();
        List<CostItems> maintainCostItemsList = new ArrayList<>();

        Double totCost = 0.0;
        if (allTextfieldEmptyStatus()){
            //To DO List: pop up a dialog
            System.out.println("Make sure all the textfiled is filled!!");
            //To Do List: check all the TextField's content
            return;
        }

        if (initCostItems !=null && maintainanceCostItems != null){
            for (CostItems temp: initCostItems){
                if (temp.getSelected()){
                    initCostItemsList.add(temp);
                    Double itemCost = temp.getPrice()*Double.parseDouble(temp.getQuantity());
                    int occurYear = Integer.parseInt(temp.getOccurYear());
                    Double itemCost_PV = getPresentValue(itemCost,occurYear);
                    totCost +=itemCost_PV;
                }
            }
            for (CostItems temp: maintainanceCostItems){
                if (temp.getSelected()){
                    maintainCostItemsList.add(temp);
                    Double itemCost = temp.getPrice()*Double.parseDouble(temp.getQuantity());
                    int occurYear = Integer.parseInt(temp.getOccurYear());
                    Double itemCost_PV = getPresentValue(itemCost,occurYear);
                    totCost +=itemCost_PV;
                }
            }
        }
        if (treeView.getRoot()==null) return;
        int index = treeView.getSelectionModel().getSelectedIndex();
        if (index > 0){
            String key = new StringBuilder("Design ").append(index).toString();
            //save costItem information to current design
            DESIGNS.get(key).setTotalCost(totCost);
            DESIGNS.get(key).setInitCostItemsList(initCostItemsList);
            DESIGNS.get(key).setMaintainCostItemsList(maintainCostItemsList);
            System.out.println("Total cost for this design is: "+DESIGNS.get(key).getTotalCost()+" USD");
        }

    }

    private Double getPresentValue(Double itemCost, int occurYear) {
        int startYear = Integer.parseInt(Start_Year_Textfield.getText());
        int totYear = Integer.parseInt(Total_Year_Textfield.getText());
        Double discRate = Double.valueOf(Discount_Rate_Textfield.getText());
        Double cost = itemCost
                // The equation below shows how to calculate present value with a discount rate
                //2.5% is avg inflation rate from 2008 to start Year
                // 2008 is the year of our costItem database
                * Math.pow((double)(1+0.025),(double)(startYear-2008))
                / Math.pow((double)(1+discRate/100),(double)(occurYear))
                ;
        return cost;
    }

    private boolean allTextfieldEmptyStatus() {
        return Total_Year_Textfield.getText().isEmpty()
                ||Start_Year_Textfield.getText().isEmpty()
                ||Discount_Rate_Textfield.getText().isEmpty();
    }

    public static final class CostItems {
        //fields
        private Boolean isSelected = false;
        private String itemType;
        private String itemDescription;
        private String occurYear = Integer.toString(0);
        private String quantity = Integer.toString(1);
        private Double price;
        private String filter1;
        private String filter2;
        private String unit;

        //methods

        public Boolean getSelected() {
            return isSelected;
        }

        public void setSelected(Boolean selected) {
            isSelected = selected;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        public String getOccurYear() {
            return occurYear;
        }

        public void setOccurYear(String occurYear) {
            this.occurYear = occurYear;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getFilter1() {
            return filter1;
        }

        public void setFilter1(String filter1) {
            this.filter1 = filter1;
        }

        public String getFilter2() {
            return filter2;
        }

        public void setFilter2(String filter2) {
            this.filter2 = filter2;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    App main;
    public void showTable() throws IOException {

    }
}
