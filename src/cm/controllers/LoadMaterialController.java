package cm.controllers;

import cm.App;
import cm.EnvAnalysis_cal;
import cm.models.AlternativeMat;
import cm.models.EPDDatabase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<AlternativeMat> SelectedTable;
    @FXML
    private TableColumn<AlternativeMat, String> CS_Column;
    @FXML
    private TableColumn<AlternativeMat, String> CM_Name_Column;
    @FXML
    private TableColumn<AlternativeMat, String> Location_Column;
    @FXML
    private TableColumn<AlternativeMat, String> MixNum_Column;
    @FXML
    private TableColumn<AlternativeMat,String> ZipCode_Column;
    @FXML
    private TableColumn<AlternativeMat, String> CS_Column_selected;
    @FXML
    private TableColumn<AlternativeMat, String> CM_Name_Column_selected;
    @FXML
    private TableColumn<AlternativeMat, String> Location_Column_selected;
    @FXML
    private TableColumn<AlternativeMat, String> MixNum_Column_selected;
    @FXML
    private TableColumn<AlternativeMat,String> ZipCode_Column_selected;

    // Reference to the main application.
    private App mainApp;

    // Constructor is called before the initialized method
    public LoadMaterialController(){
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public void initialize() throws SQLException {
        //Alternative materials
        CS_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CS"));
        CM_Name_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CM_name"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("Location"));
        MixNum_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("MixNum"));
        ZipCode_Column.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("ZipCode"));

        //Selected materials
        CS_Column_selected.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CS"));
        CM_Name_Column_selected.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("CM_name"));
        Location_Column_selected.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("Location"));
        MixNum_Column_selected.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("MixNum"));
        ZipCode_Column_selected.setCellValueFactory(new PropertyValueFactory<AlternativeMat, String>("ZipCode"));

    }

    private ObservableList<AlternativeMat> data;

    @FXML
    public void searchbutton() throws SQLException {
        StringBuilder CS_field = new StringBuilder("CS >= ");
        String CS_Textfiled = CS_field.append("'").append(TF_CS.getText()).append("'").toString();

        List<AlternativeMat> result = new EPDDatabase().getResultsFilteredBy(CS_Textfiled);

//        //test the consistency of data
//        for (int i = 0; i < result.size(); i++){
//            System.out.println(result.get(i).getCS()+
//                    ":"+result.get(i).getODP()+" "+result.get(i).getAP());
//        }
        data = FXCollections.observableArrayList();

        for (int i = 0; i < result.size(); i++){
            AlternativeMat cm = new AlternativeMat();

            cm.setCS(result.get(i).getCS());
            cm.setCM_name(result.get(i).getCM_name());
            cm.setLocation(result.get(i).getLocation());
            cm.setMixNum(result.get(i).getMixNum());
            cm.setZipCode(result.get(i).getZipCode());
            cm.setGWP(result.get(i).getGWP());
            cm.setODP(result.get(i).getODP());
            cm.setAP(result.get(i).getAP());
            cm.setEP(result.get(i).getEP());
            cm.setPOCP(result.get(i).getPOCP());
            cm.setUnit(result.get(i).getUnit());
            data.add(cm);
        }
        MaterialTable.setItems(data);
    }

    ObservableList<AlternativeMat> AlterMaterials = FXCollections.observableArrayList();

    //update conversion factor
    public void updatecvf(){
        for (int i = 0; i < AlterMaterials.size(); i++){
            if (AlterMaterials.get(i).getUnit().equals("m3")){
                EnvAnalysis_cal.setConFc(EnvAnalysis_cal.getTotV());
            }
            if (AlterMaterials.get(i).getUnit().equals("y3")){
                EnvAnalysis_cal.setConFc(EnvAnalysis_cal.getTotV()*1.30795);        //1 m^3 = 1 yd^3
            }
        }

    }
    @FXML
    public void selectButton(){

        AlternativeMat SelectedMat = MaterialTable.getSelectionModel().getSelectedItem();
        AlterMaterials.add(SelectedMat);

        SelectedTable.setItems(AlterMaterials);
    }

    public void removeButton(){
        if (AlterMaterials != null){

            AlternativeMat SelectedMat = SelectedTable.getSelectionModel().getSelectedItem();
            AlterMaterials.remove(SelectedMat);
            SelectedTable.setItems(AlterMaterials);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No alternative material selected");
            alert.setContentText("Please selected an alternative material first");

            alert.showAndWait();
        }
    }

    public void nextButton() {
        updatecvf();        //update conversion factor
        //test the consistency of data
        for (int i = 0; i < AlterMaterials.size(); i++) {
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getGWP());
            EnvAnalysis_cal.setOdp(AlterMaterials.get(i).getODP());
            EnvAnalysis_cal.setAp(AlterMaterials.get(i).getAP());
            EnvAnalysis_cal.setEp(AlterMaterials.get(i).getEP());
            EnvAnalysis_cal.setPocp(AlterMaterials.get(i).getPOCP());
            EnvAnalysis_cal.setChw(AlterMaterials.get(i).getCHW());
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getCNHW());
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getTWC());
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getRPEU());
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getDNER());
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getRMRU());
            EnvAnalysis_cal.setGwp(AlterMaterials.get(i).getDNMR());
        }
    }



}
