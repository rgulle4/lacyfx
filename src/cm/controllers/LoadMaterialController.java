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
    @FXML
    private TableColumn<AlternativeMat, String> Location_Column;
    @FXML
    private TableColumn<AlternativeMat, String> MixNum_Column;
    // Reference to the main application.
    private App mainApp;

    final ObservableList<AlternativeMat> MaterialData = FXCollections.observableArrayList(
            new AlternativeMat("4500","IBM","Baton Rouge","Mix01"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4500","IBM","Baton Rouge","Mix01"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02"),
            new AlternativeMat("4600","IBM","Baton Rouge","Mix02")
    );

    // Constructor is called before the initialized method
    public LoadMaterialController(){
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public void initialize() throws SQLException {
        CS_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CS"));
        CM_Name_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CM_name"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("Location"));
        MixNum_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("MixNum"));

    }

    private ObservableList<AlternativeMat> data;

    @FXML
    private void searchbutton() throws SQLException {
        StringBuilder CS_field = new StringBuilder("CS >= ");
        String CS_Textfiled = CS_field.append("'").append(TF_CS.getText()).append("'").toString();

        List<AlternativeMaterial> result = new EPDDatabase().getResultsFilteredBy(CS_Textfiled);

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getID()+
                    ":"+result.get(i).getCS()+" "+result.get(i).getNAME());
        }
        data = FXCollections.observableArrayList();

        for (int i = 0; i < result.size(); i++){
            AlternativeMat cm = new AlternativeMat();
            cm.setCS(result.get(i).getCS());
            cm.setCM_name(result.get(i).getNAME());
            cm.setLocation(result.get(i).getLOCATION());
            cm.setMixNum(result.get(i).getMIXNUMBER());
            data.add(cm);
        }
        MaterialTable.setItems(data);
    }







}
