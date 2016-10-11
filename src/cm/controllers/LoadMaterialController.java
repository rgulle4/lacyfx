package cm.controllers;

import cm.App;
import cm.models.EnvAnalysisCalc;
import cm.models.AlternativeMat;
import cm.models.EPDDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMaterialController {

    ObservableList<String> DistanceList = FXCollections.observableArrayList("<25 miles","<50 miles","<100 miles",">100 miles");
    @FXML
    private ChoiceBox distanceChoice;
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
        //set item for distance choiceBox

        distanceChoice.setValue("<25 miles");
        distanceChoice.setItems(DistanceList);

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
            cm.setTPEC(result.get(i).getTPEC());
            cm.setNRE(result.get(i).getDNER());
            cm.setRE(result.get(i).getRPEU());
            cm.setNRM(result.get(i).getDNMR());
            cm.setRM(result.get(i).getRMRU());
            cm.setTWC(result.get(i).getTWC());
            cm.setCHW(result.get(i).getCHW());
            cm.setCNHW(result.get(i).getCNHW());
            data.add(cm);
        }
        MaterialTable.setItems(data);
    }

    ObservableList<AlternativeMat> AlterMaterials = FXCollections.observableArrayList();

    //update conversion factor
    public void updatecvf(){
        for (int i = 0; i < AlterMaterials.size(); i++){
            if (AlterMaterials.get(i).getUnit().equals("m3")){
                EnvAnalysisCalc.setConFc(EnvAnalysisCalc.getTotV());
            }
            if (AlterMaterials.get(i).getUnit().equals("y3")){
                EnvAnalysisCalc.setConFc(EnvAnalysisCalc.getTotV()*1.30795);        //1 m^3 = 1 yd^3
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
            EnvAnalysisCalc.setGwp(AlterMaterials.get(i).getGWP());
            EnvAnalysisCalc.setOdp(AlterMaterials.get(i).getODP());
            EnvAnalysisCalc.setAp(AlterMaterials.get(i).getAP());
            EnvAnalysisCalc.setEp(AlterMaterials.get(i).getEP());
            EnvAnalysisCalc.setPocp(AlterMaterials.get(i).getPOCP());
            EnvAnalysisCalc.setChw(AlterMaterials.get(i).getCHW());
            EnvAnalysisCalc.setCnhw(AlterMaterials.get(i).getCNHW());
            EnvAnalysisCalc.setTwc(AlterMaterials.get(i).getTWC());
            EnvAnalysisCalc.setRpeu(AlterMaterials.get(i).getRPEU());
            EnvAnalysisCalc.setDner(AlterMaterials.get(i).getDNER());//wrong
            EnvAnalysisCalc.setRmru(AlterMaterials.get(i).getRMRU());
            EnvAnalysisCalc.setDnmr(AlterMaterials.get(i).getDNMR());//wrong
            EnvAnalysisCalc.setTpeu(AlterMaterials.get(i).getTPEC());//wrong

            System.out.println(EnvAnalysisCalc.getGwp()+" " + EnvAnalysisCalc.getOdp()+" "+ EnvAnalysisCalc.getAp()+" " + EnvAnalysisCalc.getEp()+ " "+
                                EnvAnalysisCalc.getPocp()+" " + EnvAnalysisCalc.getTwc()+" "+ EnvAnalysisCalc.getChw()+" " + EnvAnalysisCalc.getCnhw()+ " "+
                                EnvAnalysisCalc.getDner()+ " "+ EnvAnalysisCalc.getRpeu()+" "+ EnvAnalysisCalc.getRmru()+ " "+ EnvAnalysisCalc.getDnmr()+"\nTPEC: "+EnvAnalysisCalc.getTpeu());
        }
    }



}
