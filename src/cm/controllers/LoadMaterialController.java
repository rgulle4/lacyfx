package cm.controllers;

import cm.App;
import cm.models.AlternativeMat;
import cm.models.AlternativeMaterial;
import cm.models.EPDDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.net.efabrika.util.DBTablePrinter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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

    final ObservableList<AlternativeMat> MaterialData = FXCollections.observableArrayList(
            new AlternativeMat("4500","IBM"),
            new AlternativeMat("4600","APPLE"),
            new AlternativeMat("4700","GOOGLE")
    );

    // Constructor is called before the initialized method
    public LoadMaterialController(){
    }


    public void initialize(){
        CS_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CS"));
        CM_Name_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CM_name"));
        MaterialTable.setItems(MaterialData);
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
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
