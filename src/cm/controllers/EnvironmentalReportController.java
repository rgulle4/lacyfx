package cm.controllers;

import cm.models.Design;
import cm.models.Layer;
import cm.models.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import java.util.Arrays;

import static cm.models.Model.*;

//import static cm.App.designMap;


/**
 * Created by Administrator on 2016/10/6.
 */
public class EnvironmentalReportController {

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    @FXML
    private StackedBarChart<String, Number> sbc = new StackedBarChart<>(xAxis, yAxis);
    @FXML
    private BarChart<String, Number> bc = new BarChart<>(xAxis,yAxis);
    @FXML
    private XYChart.Series<String, Number> serie_GWP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_ODP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_AP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_EP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_POCP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_TotalWater = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_TotalPrimaryEnergyConsumption = new XYChart.Series<>();

    //summary chart
    @FXML
    private XYChart.Series<String, Number> serie_envPerf_EPDScore = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_envPerf_TransportationScore = new XYChart.Series<>();

    @FXML
    private ComboBox performanceType_ComboBox;
    @FXML
    private ComboBox environmentalImpact_ComboBox;
    @FXML
    private ComboBox rawValue_ComboBox;
    @FXML
    private ComboBox design_ComboBox;
    @FXML
    private ComboBox layer_ComboBox;
    @FXML
    private ComboBox mix_ComboBox;
    @FXML
    private ComboBox impactCategory;

    @FXML
    private RadioButton sumGraph_RadioButton;
    @FXML
    private  RadioButton sumTable_RadioButton;

    // Type of performance
    ObservableList<String> performanceType = FXCollections.
            observableArrayList(
                    "Environmental Performance",
                    "Economical Performance",
                    "Overall Performance");
    // EnvironmentalImpact type
    ObservableList<String> impactType = FXCollections.
            observableArrayList(
                    "EPD",
                    "TSP",
                    "Overall"
            );
    // raw Value type
    ObservableList<String> rawValueType = FXCollections.
            observableArrayList(
                    "Raw impact per functional unit",
                    "Normalized impact per functional unit",
                    "Weighted impact per functional unit"
            );

    // Number of designs
    ObservableList<String> designNum = FXCollections.
            observableArrayList();
    // Number of layers in this design
    ObservableList<String> layerNum = FXCollections
            .observableArrayList();
    // Number of layers in this design
    ObservableList<String> mixNum = FXCollections
            .observableArrayList();
    // Impact Category
    ObservableList<String> impacyCategoryName =FXCollections
            .observableArrayList(
                    "Overall","GWP","ODP","AP","EP",
                    "POCP","TotalWater","PrimaryEnergyConsumption"
            );

    @FXML
    public void initialize() {

        //set up default elements
        setupPerformanceType_ComboBox();
        setupEnvironmentalImpact_ComboBox();
        setupRawValue_ComboBox();
        setupImpactCategory_ComboBox();

        //set up design number
        setupDesignNumber_ComboBox();

    }

    public void showOutputChart() {
        //Environmental analysis in details
        // get design key which is same as design
        sbc.setVisible(false);
        bc.setVisible(false);
        String designID = design_ComboBox.getSelectionModel().getSelectedItem().toString();
        Design designTemp = DESIGNS.get(designID);
        int layerIndex = layer_ComboBox.getSelectionModel().getSelectedIndex();
        Layer layerTemp = designTemp.getLayer(layerIndex);
        int materialIndex = mix_ComboBox.getSelectionModel().getSelectedIndex();
        Material materialTemp = layerTemp.getMaterial(materialIndex);

        // setValue according to performanceType_ComboBox,
        // environmentalImpact_ComboBox and rawValue_ComboBox
        if (performanceType_ComboBox.getSelectionModel().isSelected(0)){
            // Environmental Performance is selected
            if (environmentalImpact_ComboBox.getSelectionModel().isSelected(0)){
                // EPD is selected
                if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
                    //ctb is selected
                    barChart_EPD_Ctr_Alternative(designID,materialTemp);
                }
                if(rawValue_ComboBox.getSelectionModel().isSelected(1)){
                    //Norm is selected
                    barChart_EPD_Norm_Alternative(designID,materialTemp);
                }
                if(rawValue_ComboBox.getSelectionModel().isSelected(2)){
                    //SubScore is selected
                    barChart_EPD_SubScore_Alternative(designID,materialTemp);
                }
            }
            // TSP is selected
            else{
                if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
                    //ctb is selected
                    barChart_Transportation_Ctr_Alternative(designID,materialTemp);
                }
                if(rawValue_ComboBox.getSelectionModel().isSelected(1)){
                    //Norm is selected
                    barChart_Transportation_Norm_Alternative(designID,materialTemp);
                }
                if(rawValue_ComboBox.getSelectionModel().isSelected(2)){
                    //SubScore is selected
                    barChart_Transportation_SubScore_Alternative(designID,materialTemp);
                }
            }
        }
    }

    private void setupPerformanceType_ComboBox(){
        performanceType_ComboBox.setItems(performanceType);
        performanceType_ComboBox.setValue(performanceType.get(0));
    }

    private void setupEnvironmentalImpact_ComboBox(){
        environmentalImpact_ComboBox.setItems(impactType);
        environmentalImpact_ComboBox.setValue(impactType.get(0));
    }

    private void setupRawValue_ComboBox(){
        rawValue_ComboBox.setItems(rawValueType);
        rawValue_ComboBox.setValue(rawValueType.get(0));
    }

    private void setupImpactCategory_ComboBox(){
        impactCategory.setItems(impacyCategoryName);
        impactCategory.setValue(impacyCategoryName.get(0));
    }

    private void setupDesignNumber_ComboBox(){
        for (String dKey:DESIGNS.keySet()){
            designNum.add(dKey);
        }
        if (DESIGNS.size()>0){
            designNum.addAll("Overall");
        }
        design_ComboBox.setItems(designNum);
        design_ComboBox.setValue(designNum.get(0));
    }

    public void setupLayerNumber_ComboBox(){
        if (!design_ComboBox.getSelectionModel().isEmpty()){
            String selectedDeisgnKey = design_ComboBox.getValue().toString();
            int layerNumber = DESIGNS.get(selectedDeisgnKey).getNumberOfLayers();
            if(layerNumber>0){
                for (int i = 1; i<= layerNumber;i++){
                    StringBuilder sb = new StringBuilder("Layer ");
                    String layerName= sb.append(i).toString();
                    layerNum.add(layerName);
                }
                layerNum.addAll("Overall");
                layer_ComboBox.setItems(layerNum);
            }
            else {
                System.out.println("No layer was added for this design!!");
            }
        }
        else{
            System.out.println("Select a certain design first!!");
        }

    }

    public void setupMixNum_ComboBox(){
        if(!design_ComboBox.getSelectionModel().isEmpty()){
            String selectedDeisgnKey = design_ComboBox.getValue().toString();
            if(!layer_ComboBox.getSelectionModel().isEmpty()){
                int layerOfIndex = layer_ComboBox.getSelectionModel().getSelectedIndex();
                int mixNumber = DESIGNS.get(selectedDeisgnKey)
                                        .getLayer(layerOfIndex)
                                        .getNumberofMaterials();
                if(mixNumber > 0){
                    for (int i = 1; i <= mixNumber;i ++){
                        StringBuilder sb = new StringBuilder("Mix ");
                        String mixName = sb.append(i).toString();
                        mixNum.add(mixName);
                    }
                    mixNum.addAll("Overall");
                    mix_ComboBox.setItems(mixNum);
                }else{
                    System.out.println("No mix was added!!");
                }
            }else{
                System.out.println("Select a certain layer first!!");
            }
        }else{
            System.out.println("Select a certain design first!!");
        }
    }

    public static void main(String[] args) {
        ObservableList<String> designNum = FXCollections.
                observableArrayList();
        DESIGNS.put("Design 1", new Design());
        DESIGNS.put("Design 2", new Design());
        DESIGNS.put("Design 3", new Design());
        for (String dKey:DESIGNS.keySet()){
            designNum.add(dKey);
        }
        if (DESIGNS.size()>0){
            designNum.addAll("Overall");
        }
        System.out.println(designNum);

    }

    public void stackBarChart_EPD_Ctb_Alternative(String designID, Layer layerTemp){
        // clear old data
        bc.getData().clear();
        bc.setVisible(false);
        bc.layout();
        sbc.getData().clear();
        sbc.setVisible(true);
        sbc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(
                        "mix 1",
                        "mix 2",
                        "mix 3")));

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getGWP_EDP_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getODP_EDP_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getAP_EDP_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getEP_EDP_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getPOCP_EDP_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", layerTemp.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", layerTemp.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", layerTemp.getTW_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", layerTemp.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", layerTemp.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", layerTemp.getTPEC_EDP_Ctb()));
        sbc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_EPD_Ctr_Alternative(String designID, Material material){
        // clear old data
        sbc.getData().clear();
        sbc.setVisible(false);
        sbc.layout();
        bc.getData().clear();
        bc.setVisible(true);
        bc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", material.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", material.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", material.getGWP_EDP_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", material.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", material.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", material.getODP_EDP_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", material.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", material.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", material.getAP_EDP_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", material.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", material.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", material.getEP_EDP_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", material.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", material.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", material.getPOCP_EDP_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", material.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", material.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", material.getTW_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", material.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", material.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", material.getTPEC_EDP_Ctb()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_EPD_Norm_Alternative(String designID, Material material){
        // clear old data
        sbc.getData().clear();
        sbc.setVisible(false);
        sbc.layout();
        bc.getData().clear();
        bc.setVisible(true);
        bc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", material.getGWP_EDP_NORM()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", material.getGWP_EDP_NORM()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", material.getGWP_EDP_NORM()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", material.getODP_EDP_NORM()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", material.getODP_EDP_NORM()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", material.getODP_EDP_NORM()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", material.getAP_EDP_NORM()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", material.getAP_EDP_NORM()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", material.getAP_EDP_NORM()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", material.getEP_EDP_NORM()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", material.getEP_EDP_NORM()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", material.getEP_EDP_NORM()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", material.getPOCP_EDP_NORM()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", material.getPOCP_EDP_NORM()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", material.getPOCP_EDP_NORM()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", material.getTW_EDP_NORM()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", material.getTW_EDP_NORM()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", material.getTW_EDP_NORM()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", material.getTPEC_EDP_NORM()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", material.getTPEC_EDP_NORM()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", material.getTPEC_EDP_NORM()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_EPD_SubScore_Alternative(String designID, Material material){
        // clear old data
        sbc.getData().clear();
        sbc.setVisible(false);
        sbc.layout();
        bc.getData().clear();
        bc.setVisible(true);
        bc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", material.getGWP_EDP_Subsore()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", material.getGWP_EDP_Subsore()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", material.getGWP_EDP_Subsore()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", material.getODP_EDP_Subsore()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", material.getODP_EDP_Subsore()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", material.getODP_EDP_Subsore()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", material.getAP_EDP_Subsore()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", material.getAP_EDP_Subsore()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", material.getAP_EDP_Subsore()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", material.getEP_EDP_Subsore()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", material.getEP_EDP_Subsore()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", material.getEP_EDP_Subsore()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", material.getPOCP_EDP_Subsore()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", material.getPOCP_EDP_Subsore()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", material.getPOCP_EDP_Subsore()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", material.getTW_EDP_Subsore()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", material.getTW_EDP_Subsore()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", material.getTW_EDP_Subsore()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", material.getTPEC_EDP_Subsore()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", material.getTPEC_EDP_Subsore()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", material.getTPEC_EDP_Subsore()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    //TSP PART
    public void stackBarChart_Transportation_Ctb_Alternative(String designID, Layer layerTemp){
        // clear old data
        bc.getData().clear();
        bc.setVisible(false);
        bc.layout();
        sbc.getData().clear();
        sbc.setVisible(true);
        sbc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(
                        "mix 1",
                        "mix 2",
                        "mix 3")));

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getGWP_Transportation_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getGWP_Transportation_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getGWP_Transportation_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getODP_Transportation_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getODP_Transportation_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getODP_Transportation_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getAP_Transportation_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getAP_Transportation_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getAP_Transportation_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getEP_Transportation_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getEP_Transportation_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getEP_Transportation_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", layerTemp.getPOCP_Transportation_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", layerTemp.getPOCP_Transportation_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", layerTemp.getPOCP_Transportation_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", layerTemp.getTW_Transportation_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", layerTemp.getTW_Transportation_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", layerTemp.getTW_Transportation_Ctb()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", layerTemp.getTPEC_Transportation_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", layerTemp.getTPEC_Transportation_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", layerTemp.getTPEC_Transportation_Ctb()));
        sbc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_Transportation_Ctr_Alternative(String designID, Material material){
        // clear old data
        sbc.getData().clear();
        sbc.setVisible(false);
        sbc.layout();
        bc.getData().clear();
        bc.setVisible(true);
        bc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", material.getGWP_Transportation_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", material.getGWP_Transportation_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", material.getGWP_Transportation_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", material.getODP_Transportation_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", material.getODP_Transportation_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", material.getODP_Transportation_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", material.getAP_Transportation_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", material.getAP_Transportation_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", material.getAP_Transportation_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", material.getEP_Transportation_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", material.getEP_Transportation_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", material.getEP_Transportation_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", material.getPOCP_Transportation_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", material.getPOCP_Transportation_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", material.getPOCP_Transportation_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", material.getTW_Transportation_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", material.getTW_Transportation_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", material.getTW_Transportation_Ctb()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", material.getTPEC_Transportation_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", material.getTPEC_Transportation_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", material.getTPEC_Transportation_Ctb()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_Transportation_Norm_Alternative(String designID, Material material){
        // clear old data
        sbc.getData().clear();
        sbc.setVisible(false);
        sbc.layout();
        bc.getData().clear();
        bc.setVisible(true);
        bc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", material.getGWP_Transportation_NORM()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", material.getGWP_Transportation_NORM()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", material.getGWP_Transportation_NORM()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", material.getODP_Transportation_NORM()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", material.getODP_Transportation_NORM()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", material.getODP_Transportation_NORM()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", material.getAP_Transportation_NORM()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", material.getAP_Transportation_NORM()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", material.getAP_Transportation_NORM()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", material.getEP_Transportation_NORM()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", material.getEP_Transportation_NORM()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", material.getEP_Transportation_NORM()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", material.getPOCP_Transportation_NORM()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", material.getPOCP_Transportation_NORM()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", material.getPOCP_Transportation_NORM()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", material.getTW_Transportation_NORM()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", material.getTW_Transportation_NORM()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", material.getTW_Transportation_NORM()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", material.getTPEC_Transportation_NORM()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", material.getTPEC_Transportation_NORM()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", material.getTPEC_Transportation_NORM()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_Transportation_SubScore_Alternative(String designID, Material material){
        // clear old data
        sbc.getData().clear();
        sbc.setVisible(false);
        sbc.layout();
        bc.getData().clear();
        bc.setVisible(true);
        bc.layout();
        // set up axis label
        xAxis.setLabel("Alternative");
        yAxis.setLabel("Score");

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("mix 1", material.getGWP_Transportation_Subsore()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 2", material.getGWP_Transportation_Subsore()));
        serie_GWP.getData().add(new XYChart.Data<>("mix 3", material.getGWP_Transportation_Subsore()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("mix 1", material.getODP_Transportation_Subsore()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 2", material.getODP_Transportation_Subsore()));
        serie_ODP.getData().add(new XYChart.Data<>("mix 3", material.getODP_Transportation_Subsore()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("mix 1", material.getAP_Transportation_Subsore()));
        serie_AP.getData().add(new XYChart.Data<>("mix 2", material.getAP_Transportation_Subsore()));
        serie_AP.getData().add(new XYChart.Data<>("mix 3", material.getAP_Transportation_Subsore()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("mix 1", material.getEP_Transportation_Subsore()));
        serie_EP.getData().add(new XYChart.Data<>("mix 2", material.getEP_Transportation_Subsore()));
        serie_EP.getData().add(new XYChart.Data<>("mix 3", material.getEP_Transportation_Subsore()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("mix 1", material.getPOCP_Transportation_Subsore()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 2", material.getPOCP_Transportation_Subsore()));
        serie_POCP.getData().add(new XYChart.Data<>("mix 3", material.getPOCP_Transportation_Subsore()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 1", material.getTW_Transportation_Subsore()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 2", material.getTW_Transportation_Subsore()));
        serie_TotalWater.getData().add(new XYChart.Data<>("mix 3", material.getTW_Transportation_Subsore()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 1", material.getTPEC_Transportation_Subsore()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 2", material.getTPEC_Transportation_Subsore()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("mix 3", material.getTPEC_Transportation_Subsore()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void cleanChart(){
        // clear old data
        sbc.getData().clear();
        sbc.layout();
        bc.getData().clear();
        bc.layout();
    }
}
