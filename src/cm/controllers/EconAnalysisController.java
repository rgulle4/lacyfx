package cm.controllers;


import cm.models.Design;
import cm.models.Layer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

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
    public void initialize(){
        treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                if (selectedItem.getValue().contains("Rigid"))
                    showRigidPavementType();
                else if (selectedItem.getValue().contains("Flexible"))
                    showFlexiblePavementType();
                else {ComboBox_PType.setItems(null);}
            }

        });
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

    private void showRigidPavementType(){
        ObservableList<String> PT = FXCollections.observableArrayList
                ("CRCP","JPCP","PCC","RUBB","Latex Modified Concrete");
        ComboBox_PType.setItems(PT);
        ComboBox_PType.getSelectionModel().selectFirst();
    }

    private void showFlexiblePavementType(){
        ObservableList<String> PT = FXCollections.observableArrayList
                ("AC","HMA","SMA","SuperPave");
        ComboBox_PType.setItems(PT);
        ComboBox_PType.getSelectionModel().selectFirst();
    }
}
