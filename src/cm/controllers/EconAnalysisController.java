package cm.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import cm.models.CostDatabase;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by royg59 on 9/21/16.
 */
public class EconAnalysisController {
    ObservableList<String> initialItems_ObsList = FXCollections.observableArrayList();
    ObservableList<String> MRItems_ObsList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> Initial_Item_ListView;
    @FXML
    private ListView<String> MR_Item_ListView;
    @FXML
    private ListView<String> Incurred_Year_ListView;
    @FXML
    private RadioButton CRCP_Rbutton;
    @FXML
    private RadioButton JPCP_Rbutton;


    @FXML
    private void initialize(){
    CRCP_Rbutton.setOnAction((event) -> {
        try {
            presentItems(event);
            if(CRCP_Rbutton.isSelected())
            JPCP_Rbutton.setSelected(false);
            else JPCP_Rbutton.setSelected(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
    JPCP_Rbutton.setOnAction((event) -> {
        try {
            presentItems(event);
            if (JPCP_Rbutton.isSelected())
                CRCP_Rbutton.setSelected(false);
            else CRCP_Rbutton.setSelected(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
    }

    @FXML
    public void presentItems(ActionEvent event) throws SQLException {
        Initial_Item_ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        MR_Item_ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (CRCP_Rbutton.isSelected()){
            List<String> initialItems_List = new CostDatabase().getInitialItems("CRCP","Initial");
            List<String> mrItems_List = new CostDatabase().getInitialItems("CRCP","M&R");
            for (String initialItem:initialItems_List){initialItems_ObsList.add(initialItem);}
            for (String mrItem:mrItems_List){MRItems_ObsList.add(mrItem);}
            Initial_Item_ListView.setItems(initialItems_ObsList);
            MR_Item_ListView.setItems(MRItems_ObsList);
        }else {
            List<String> initialItems_List = new CostDatabase().getInitialItems("JPCP","Initial");
            List<String> mrItems_List = new CostDatabase().getInitialItems("JPCP","M&R");
            for (String initialItem:initialItems_List){initialItems_ObsList.add(initialItem);}
            for (String mrItem:mrItems_List){MRItems_ObsList.add(mrItem);}
            Initial_Item_ListView.setItems(initialItems_ObsList);
            MR_Item_ListView.setItems(MRItems_ObsList);
        }

    }

}
