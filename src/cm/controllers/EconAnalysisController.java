package cm.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import cm.models.CostDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by royg59 on 9/21/16.
 */
public class EconAnalysisController {
    @FXML
    public TreeView treeView = new TreeView();

    @FXML
    public void initialize(){
        // create a treeView Helper
        TreeViewHelper helper = new TreeViewHelper();
        ArrayList<TreeItem> items = helper.getItems();
        TreeItem rootItem = new TreeItem("Pavement");
        rootItem.getChildren().addAll(items);
        treeView.setRoot(rootItem);
    }

    public class TreeViewHelper{

        public TreeViewHelper(){}

        public ArrayList<TreeItem> getItems(){
            ArrayList<TreeItem> items = new ArrayList<>();
            TreeItem rigidPavement = new TreeItem("Rigid Pavement");
            rigidPavement.getChildren().addAll(getRigidPavement());
            TreeItem flexiblePavement = new TreeItem("Flexible Pavement");
            flexiblePavement.getChildren().addAll(getFlexiblePavement());
            items.add(rigidPavement);
            items.add(flexiblePavement);
            return items;
        }
        //this method create an arrayList of treeItems (Rigid pavement)
        private ArrayList<TreeItem> getRigidPavement(){
            ArrayList<TreeItem> rigidPavementItems = new ArrayList<>();
            TreeItem CRCP = new TreeItem("CRCP");
            TreeItem JPCP = new TreeItem("JPCP");
            TreeItem PCC = new TreeItem("PCC");
            TreeItem RUBB = new TreeItem("RUBB");
            rigidPavementItems.add(CRCP);
            rigidPavementItems.add(JPCP);
            rigidPavementItems.add(PCC);
            rigidPavementItems.add(RUBB);
            return rigidPavementItems;
        }
        //this method create an arrayList of treeItems (flexible pavement)
        private ArrayList<TreeItem> getFlexiblePavement(){
            ArrayList<TreeItem> flexiblePavementItems = new ArrayList<>();
            TreeItem AC = new TreeItem("AC");
            TreeItem HMA = new TreeItem("HMA");
            TreeItem SMA = new TreeItem("SMA");
            TreeItem Emulsion = new TreeItem("Emulsion");
            TreeItem SuperPave = new TreeItem("SuperPave");
            flexiblePavementItems.add(AC);
            flexiblePavementItems.add(HMA);
            flexiblePavementItems.add(SMA);
            flexiblePavementItems.add(Emulsion);
            flexiblePavementItems.add(SuperPave);
            return flexiblePavementItems;
        }
    }
}
