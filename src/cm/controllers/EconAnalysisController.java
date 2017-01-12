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

import static cm.models.Model.DESIGNS;

/**
 * Created by royg59 on 9/21/16.
 */
public class EconAnalysisController {

    @FXML
    public TreeView treeView = new TreeView();
    @FXML
    public ComboBox ComboBox_PType;
    @FXML
    public ComboBox ComboBox_Init_Filter1;
    @FXML
    public ComboBox ComboBox_Init_Filter2;
    @FXML
    public TableView<CostItems> tableView;
    @FXML
    public TableColumn<CostItems, CheckBox> column_Selected;
    @FXML
    public TableColumn<CostItems,String> column_Dtype;
    @FXML
    public TableColumn<CostItems,String> column_ItemDescription;
    @FXML
    public TableColumn<CostItems, String> column_OccurYear;
    @FXML
    public TableColumn<CostItems, String> column_Quantity;
    @FXML
    public Button Button_CostAnalysis;

    private List<CostItems> initCostItems;

    @FXML
    public void initialize(){
        treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                //set items for ComboBox_PavementType
                String pavementType;
                if (selectedItem.getValue().contains("Rigid")){
                    pavementType = "Rigid";
                }
                else if ((selectedItem.getValue().contains("Flexible"))) {
                    pavementType = "Flexible";
                }
                else {pavementType = "";}
                try {
                    showPavementType(pavementType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        ComboBox_PType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Object selectedItem = (Object)newValue;
                try {
                    ComboBox_Init_Filter1.getSelectionModel().clearSelection();
                    ComboBox_Init_Filter2.getSelectionModel().clearSelection();
                    showInitFilter1(ComboBox_PType.getValue().toString());
                    initCostItems = new CostDatabase().getInitialItems(ComboBox_PType.getValue().toString());
                    showTableData(initCostItems);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        ComboBox_Init_Filter1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Object selectedItem = (Object)newValue;
                try {
                    ComboBox_Init_Filter2.getSelectionModel().clearSelection();
                    showInitFilter2(
                            ComboBox_PType.getValue().toString(),
                            ComboBox_Init_Filter1.getValue().toString());
                    List<CostItems> costItems_Filter1 = getInitCostItems_Filter1(initCostItems);
                    showTableData(costItems_Filter1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        ComboBox_Init_Filter2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Object selectedItem = (Object)newValue;
                try {
                    showTableData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<CostItems> getInitCostItems_Filter1(List<CostItems> initCostItems) {
        List<CostItems> result = new ArrayList<>();
        if (initCostItems == null){
            return null;
        }
        for (CostItems temp: initCostItems){
            String a = temp.getFilter1();
            String b = ComboBox_Init_Filter1.getValue().toString();
            if (a.equals(b)){
                result.add(temp);
            }
        }
        return result;
    }

    private void showTableData(List<CostItems> initCostItems) {
        if (initCostItems == null){
            return;
        }
        tableView.setEditable(true);
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
        column_Dtype.setCellValueFactory(new PropertyValueFactory<CostItems, String>("itemType"));
        column_ItemDescription.setCellValueFactory(new PropertyValueFactory<CostItems, String>("itemDescription"));
        column_OccurYear.setCellValueFactory(new PropertyValueFactory<CostItems, String>("occurYear"));
        column_OccurYear.setCellFactory(TextFieldTableCell.<CostItems>forTableColumn());
        column_OccurYear.setOnEditCommit(
                (TableColumn.CellEditEvent<CostItems,String> t) -> {
                    ((CostItems) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setOccurYear(t.getNewValue());
                });
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

    private void showInitFilter1(String designType) throws SQLException {
        ObservableList<String> filter1Item = new CostDatabase().getInitFilter1Item(designType);
        ComboBox_Init_Filter1.setItems(filter1Item);
    }

    private void showInitFilter2(String dType, String filter1) throws SQLException {
        ObservableList<String> filter1Item = new CostDatabase().getInitFilter2Item(dType,filter1);
        ComboBox_Init_Filter2.setItems(filter1Item);
    }

    public void loadTreeList(){
        // create a treeView Helper
        TreeViewHelper helper = new TreeViewHelper();
        ArrayList<TreeItem> items = helper.getItems();
        TreeItem rootItem = new TreeItem("Alternates");
        rootItem.getChildren().addAll(items);
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
                designs.getChildren().addAll(getLayers(design_temp));
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

    private void showPavementType(String pavementType) throws SQLException {
        ObservableList<String> PT = new CostDatabase().getDesignType(pavementType);
        ComboBox_PType.setItems(PT);
        ComboBox_PType.getSelectionModel().selectFirst();
    }

    public void showTableData() throws SQLException {
        String pType="";
        String filter1="";
        String filter2="";
        if (ComboBox_PType.getValue() != null) {
            pType = ComboBox_PType.getValue().toString();
        }
        if (ComboBox_Init_Filter1.getValue() != null){
            filter1 = ComboBox_Init_Filter1.getValue().toString();
        }
        if (ComboBox_Init_Filter2.getValue() != null){
            filter2 = ComboBox_Init_Filter2.getValue().toString();
        }

        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<CostItems> data = FXCollections.observableArrayList();
//        List<CostItems> costItemsList = new CostDatabase().getCostItems(pType,filter1,filter2);
        List<CostItems> costItemsList = new CostDatabase().getInitialItems(pType);
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
        column_Dtype.setCellValueFactory(new PropertyValueFactory<CostItems, String>("itemType"));
        column_ItemDescription.setCellValueFactory(new PropertyValueFactory<CostItems, String>("itemDescription"));
        column_OccurYear.setCellValueFactory(new PropertyValueFactory<CostItems, String>("occurYear"));
        column_OccurYear.setCellFactory(TextFieldTableCell.<CostItems>forTableColumn());
        column_OccurYear.setOnEditCommit(
                (TableColumn.CellEditEvent<CostItems,String> t) -> {
                    ((CostItems) t.getTableView().getItems()
                    .get(t.getTablePosition().getRow()))
                    .setOccurYear(t.getNewValue());
        });
        column_Quantity.setCellValueFactory(new PropertyValueFactory<CostItems, String>("quantity"));
        column_Quantity.setCellFactory(TextFieldTableCell.<CostItems>forTableColumn());
        column_Quantity.setOnEditCommit(
                (TableColumn.CellEditEvent<CostItems,String> t) -> {
                    ((CostItems) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setQuantity(t.getNewValue());
                });
        for (CostItems item: costItemsList){
            data.add(item);
        }
        tableView.setItems(data);
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
//        List<CostItems> costItemsList = tableView.getSelectionModel().getSelectedItems();
        List<CostItems> costItemsList = new ArrayList<>();
        Double totCost = 0.0;
        for (CostItems temp: initCostItems){
            if (temp.getSelected()){
                Double itemCost = temp.getPrice()*Double.parseDouble(temp.getQuantity());
                System.out.println(temp.getItemDescription()+" "+temp.getPrice());
                totCost +=itemCost;
            }
        }
        System.out.println("Total cost for this design is: "+totCost+"USD");
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
    }

    App main;
    public void showTable() throws IOException {

    }
}
