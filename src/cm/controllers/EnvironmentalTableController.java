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
        TableColumn designColumn = new TableColumn("Design");
        dataTable.getColumns().add(designColumn);
    }


}
