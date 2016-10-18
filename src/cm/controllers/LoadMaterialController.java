package cm.controllers;

import cm.App;
import cm.models.EnvAnalysisCalc;
import cm.models.AlternativeMat;
import cm.models.EPDDatabase;
import cm.models.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static cm.App.materialMap;

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
            cm.setTotalPrimaryEnergyConsumption(result.get(i).getTotalPrimaryEnergyConsumption());
            cm.setNonRenewableEnergyUse(result.get(i).getNonRenewableEnergyUse());
            cm.setRenewablePrimaryEnergyUse(result.get(i).getRenewablePrimaryEnergyUse());
            cm.setNonRenewableMaterialResource(result.get(i).getNonRenewableMaterialResource());
            cm.setRenewableMaterialResourcesUse(result.get(i).getRenewableMaterialResourcesUse());
            cm.setTotalWaterConsumption(result.get(i).getTotalWaterConsumption());
            cm.setConcreteHazardousWaste(result.get(i).getConcreteHazardousWaste());
            cm.setConcreteNonHazardousWaste(result.get(i).getConcreteNonHazardousWaste());
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

    public void nextButton() throws ParseException {
        updatecvf();        //update conversion factor
        //test the consistency of data
        for (int i = 0; i < AlterMaterials.size(); i++) {
            Material material = new Material();
            material.setGWP(AlterMaterials.get(i).getGWP());
            material.setODP(AlterMaterials.get(i).getODP());
            material.setAP(AlterMaterials.get(i).getAP());
            material.setEP(AlterMaterials.get(i).getEP());
            material.setPOCP(AlterMaterials.get(i).getPOCP());
            material.setConcreteHazardousWaste(AlterMaterials.get(i).getConcreteHazardousWaste());
            material.setConcreteNonHazardousWaste(AlterMaterials.get(i).getConcreteNonHazardousWaste());
            material.setTotalWaterConsumption(AlterMaterials.get(i).getTotalWaterConsumption());
            material.setRenewablePrimaryEnergyUse(AlterMaterials.get(i).getRenewablePrimaryEnergyUse());
            material.setNonRenewableEnergyUse(AlterMaterials.get(i).getNonRenewableEnergyUse());
            material.setRenewableMaterialResourcesUse(AlterMaterials.get(i).getRenewableMaterialResourcesUse());
            material.setNonRenewableMaterialResource(AlterMaterials.get(i).getNonRenewableMaterialResource());
            material.setTotalPrimaryEnergyConsumption(AlterMaterials.get(i).getTotalPrimaryEnergyConsumption());
            material.setMixNum(AlterMaterials.get(i).getMixNum());
            materialMap.put(material.getMixNum(),material);



//            EnvAnalysisCalc.setGwp(AlterMaterials.get(i).getGWP());
//            EnvAnalysisCalc.setOdp(AlterMaterials.get(i).getODP());
//            EnvAnalysisCalc.setAp(AlterMaterials.get(i).getAP());
//            EnvAnalysisCalc.setEp(AlterMaterials.get(i).getEP());
//            EnvAnalysisCalc.setPocp(AlterMaterials.get(i).getPOCP());
//            EnvAnalysisCalc.setChw(AlterMaterials.get(i).getConcreteHazardousWaste());
//            EnvAnalysisCalc.setCnhw(AlterMaterials.get(i).getConcreteNonHazardousWaste());
//            EnvAnalysisCalc.setTwc(AlterMaterials.get(i).getTotalWaterConsumption());
//            EnvAnalysisCalc.setRpeu(AlterMaterials.get(i).getRenewablePrimaryEnergyUse());
//            EnvAnalysisCalc.setDner(AlterMaterials.get(i).getNonRenewableEnergyUse());//wrong
//            EnvAnalysisCalc.setRmru(AlterMaterials.get(i).getRenewableMaterialResourcesUse());
//            EnvAnalysisCalc.setDnmr(AlterMaterials.get(i).getNonRenewableMaterialResource());//wrong
//            EnvAnalysisCalc.setTpeu(AlterMaterials.get(i).getTotalPrimaryEnergyConsumption());//wrong

//            System.out.println(EnvAnalysisCalc.getGwp()+" " + EnvAnalysisCalc.getOdp()+" "+ EnvAnalysisCalc.getAp()+" " + EnvAnalysisCalc.getEp()+ " "+
//                                EnvAnalysisCalc.getPocp()+" " + EnvAnalysisCalc.getTwc()+" "+ EnvAnalysisCalc.getChw()+" " + EnvAnalysisCalc.getCnhw()+ " "+
//                                EnvAnalysisCalc.getDner()+ " "+ EnvAnalysisCalc.getRpeu()+" "+ EnvAnalysisCalc.getRmru()+ " "+ EnvAnalysisCalc.getDnmr()+"\nTPEC: "+EnvAnalysisCalc.getTpeu());
        }
    }



}
