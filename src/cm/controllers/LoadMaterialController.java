package cm.controllers;

import cm.App;
import cm.models.AlternativeMat;
import cm.models.AlternativeMaterial;
import cm.models.EPDDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import libs.net.efabrika.util.DBTablePrinter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMaterialController {

    // To DO: select alternative materials from database for calculating and comparing
    @FXML
    private TextField TF_CS;
    @FXML
    private TableView<AlternativeMat> MaterialTable;
    @FXML
    private TableColumn<AlternativeMat, String> CS_Column;
    @FXML
    private TableColumn<AlternativeMat, String> CM_Name_Column;
    // Reference to the main application.
    private App mainApp;
    // Constructor is called before the initialized method
    public LoadMaterialController(){
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
//    @FXML
//    private void initialize(){
//        // Initialize the AlternativeMat table with the two columns.
//        CS_Column.setCellValueFactory(cellData-> cellData.getValue().CSProperty());
//        CM_Name_Column.setCellValueFactory(cellData-> cellData.getValue().CM_nameProperty());
//    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        MaterialTable.setItems(mainApp.getMaterialData());
    }



    @FXML
    private void searchbutton() throws SQLException {
        StringBuilder CS_field = new StringBuilder("CS >= ");
        String CS_Textfiled = CS_field.append("'").append(TF_CS.getText()).append("'").toString();

        List<AlternativeMaterial> result = new EPDDatabase().getResultsFilteredBy(CS_Textfiled);

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getID()+
                    ":"+result.get(i).getCS()+" "+result.get(i).getNAME());

        }
    }
}
