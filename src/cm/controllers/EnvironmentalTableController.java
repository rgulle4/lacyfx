package cm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Administrator on 2016/11/10.
 */
public class EnvironmentalTableController {
    @FXML
    private TableView dataTable;



    public void showData(){
        addCertainNumTableColumns();
        insertValueToTableView();
    }
    private void addCertainNumTableColumns(){
        TableColumn designColumn1 = new TableColumn("Design1");
        dataTable.getColumns().add(designColumn1);
        TableColumn layerColumn1 = new TableColumn("Layer1");
        designColumn1.getColumns().add(layerColumn1);
        TableColumn mixColunmn = new TableColumn("Mix_Name");
        layerColumn1.getColumns().add(mixColunmn);
    }
    private void insertValueToTableView(){
        insertValueToColumns();
    }
    private void insertValueToColumns(){

    }
}
