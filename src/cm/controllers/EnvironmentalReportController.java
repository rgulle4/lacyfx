package cm.controllers;

import cm.models.Design;
import cm.models.Layer;
import cm.models.Mix;
import cm.models.ShowChartData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;
import java.util.List;

import static cm.models.Model.*;

//import static cm.App.designMap;


/**
 * Created by Administrator on 2016/10/6.
 */
public final class EnvironmentalReportController {

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
    @FXML
    private XYChart.Series<String, Number> serie_Overall = new XYChart.Series<>();
//
//    //summary chart
//    @FXML
//    private XYChart.Series<String, Number> serie_envPerf_EPDScore = new XYChart.Series<>();
//    @FXML
//    private XYChart.Series<String, Number> serie_envPerf_TransportationScore = new XYChart.Series<>();

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
    private ComboBox impactCategory_ComboBox;

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
        //hide bar chart and stack chart
        bc.setVisible(false);
        sbc.setVisible(false);

    }

    private String perfType;
    private String envImpactType;
    private String valueType;
    private String impactCategory;      //GWP, ODP, AP, EP, POCP, Energy Consumption...

    public void showOutputChart() {
        //Environmental analysis in details
        // get design key which is same as design
        String designID = design_ComboBox.getSelectionModel().getSelectedItem().toString();
        String layerID = layer_ComboBox.getSelectionModel().getSelectedItem().toString();
        String mixID = mix_ComboBox.getSelectionModel().getSelectedItem().toString();
        Design designTemp = DESIGNS.get(designID);
        int layerIndex = layer_ComboBox.getSelectionModel().getSelectedIndex();
        Layer layerTemp = designTemp.getLayer(layerIndex);
        int mixIndex = mix_ComboBox.getSelectionModel().getSelectedIndex();
//        Mix mixTemp = layerTemp.getMaterial(mixIndex);

        List<Mix> mixsTemp = new ArrayList<Mix>();
        String selectedMixItem = mix_ComboBox.getValue().toString();
        if(selectedMixItem == "All Mixes"){
                mixsTemp = layerTemp.getMixes();
        }else{
            Mix selectedMix = layerTemp.getMaterial(mixIndex);
            mixsTemp.add(selectedMix);
        }
        perfType = performanceType_ComboBox.getSelectionModel().getSelectedItem().toString();
        envImpactType = environmentalImpact_ComboBox.getSelectionModel().getSelectedItem().toString();
        valueType = rawValue_ComboBox.getSelectionModel().getSelectedItem().toString();
        impactCategory = impactCategory_ComboBox.getSelectionModel().getSelectedItem().toString();
        String alternative_ID = new StringBuilder(designID).append(layerID).toString();
        this.showBarChart("Alternatives","Value","Environmental Analysis",alternative_ID, mixsTemp);
    }
    public void showBarChart(String xLabel, String yLabel, String chartTitle, String incompletedAlternative_ID, List<Mix> mixs){
        bc.getData().clear();
        bc.layout();
        bc.setVisible(true);
        // set up bar chart
        serie_GWP.setName("GWP");
        serie_ODP.setName("ODP");
        serie_AP.setName("AP");
        serie_EP.setName("EP");
        serie_POCP.setName("POCP");
        serie_TotalWater.setName("TotalWater");
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_Overall.setName("Overall");
        for (Mix aMix:mixs){
            String mix_ID = aMix.getMixNum();
            StringBuilder sb = new StringBuilder(incompletedAlternative_ID).append(mix_ID);
            String alternative_ID = sb.toString();
            if(impactCategory == "GWP"){
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if(impactCategory == "ODP"){
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if(impactCategory == "AP"){
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if(impactCategory == "EP"){
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if(impactCategory == "POCP"){
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if(impactCategory == "TotalWater"){
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if (impactCategory == "PrimaryEnergyConsumption"){
                serie_TotalWater.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            }
            if (impactCategory == "Overall"){
                serie_Overall.getData().add(new XYChart.Data<>(alternative_ID,getDataValue(aMix)));
            }
        }
        if(impactCategory == "GWP"){
            bc.getData().add(serie_GWP);
        }
        if(impactCategory == "ODP"){
            bc.getData().add(serie_ODP);
        }
        if(impactCategory == "AP"){
            bc.getData().add(serie_AP);
        }
        if(impactCategory == "EP"){
            bc.getData().add(serie_EP);
        }
        if(impactCategory == "POCP"){
            bc.getData().add(serie_POCP);
        }
        if(impactCategory == "TotalWater"){
            bc.getData().add(serie_TotalWater);
        }
        if (impactCategory == "PrimaryEnergyConsumption"){
            bc.getData().add(serie_TotalPrimaryEnergyConsumption);
        }
        if (impactCategory == "Overall"){
            bc.getData().add(serie_Overall);
        }
    }

    public double getDataValue(Mix mix){
        double dataTemp = 0.0;
        if (perfType == "Environmental Performance"){
            System.out.println("Accessing environmental performance data");
            if(envImpactType == "EPD"){
                System.out.println("Accessing EPD data");
                if(valueType == "Raw impact per functional unit"){
                    System.out.println("Accessing data of raw impact per functional unit");
                    if(impactCategory == "GWP"){
                        System.out.println("Accessing data of GWP");
                        dataTemp = mix.getGWP_EPD_Ctb();
                    }else if(impactCategory == "ODP"){
                        System.out.println("Accessing data of ODP");
                        dataTemp = mix.getODP_EPD_Ctb();
                    }else if(impactCategory == "AP"){
                        System.out.println("Accessing data of AP");
                        dataTemp = mix.getAP_EPD_Ctb();
                    }else if(impactCategory == "EP"){
                        System.out.println("Accessing data of EP");
                        dataTemp = mix.getEP_EPD_Ctb();
                    }else if(impactCategory == "POCP"){
                        System.out.println("Accessing data of POCP");
                        dataTemp = mix.getPOCP_EPD_Ctb();
                    }else if(impactCategory == "TotalWater"){
                        System.out.println("Accessing data of TotalWater");
                        dataTemp = mix.getTW_EPD_Ctb();
                    }else if (impactCategory == "PrimaryEnergyConsumption"){
                        System.out.println("Accessing data of PrimaryEnergyConsumption");
                        dataTemp = mix.getTPEC_EPD_Ctb();
                    }else if (impactCategory == "Overall"){
                        System.out.println("Presenting raw value of all impactCategory");
                        dataTemp = mix.getGWP_EPD_Ctb()+mix.getODP_EPD_Ctb()
                                +mix.getAP_EPD_Ctb() +mix.getEP_EPD_Ctb()+mix.getPOCP_EPD_Ctb()
                                +mix.getTW_EPD_Ctb() +mix.getTPEC_EPD_Ctb();
                    }else{
                        System.out.println("Can not identify an impactCategory");
                    }
                }else if(valueType == "Normalized impact per functional unit"){
                    System.out.println("Accessing data of normalized impact per functional unit");
                    if(impactCategory == "GWP"){
                        System.out.println("Accessing data of GWP");
                        dataTemp = mix.getGWP_EPD_NORM();
                    }else if(impactCategory == "ODP"){
                        System.out.println("Accessing data of ODP");
                        dataTemp = mix.getODP_EPD_NORM();
                    }else if(impactCategory == "AP"){
                        System.out.println("Accessing data of AP");
                        dataTemp = mix.getAP_EPD_NORM();
                    }else if(impactCategory == "EP"){
                        System.out.println("Accessing data of EP");
                        dataTemp = mix.getEP_EPD_NORM();
                    }else if(impactCategory == "POCP"){
                        System.out.println("Accessing data of POCP");
                        dataTemp = mix.getPOCP_EPD_NORM();
                    }else if(impactCategory == "TotalWater"){
                        System.out.println("Accessing data of TotalWater");
                        dataTemp = mix.getTW_EPD_NORM();
                    }else if (impactCategory == "PrimaryEnergyConsumption"){
                        System.out.println("Accessing data of PrimaryEnergyConsumption");
                        dataTemp = mix.getTPEC_EPD_NORM();
                    }else if(impactCategory == "Overall"){
                        System.out.println("Presenting Normalized value of all impactCategory");
                        dataTemp = mix.getGWP_EPD_NORM()+mix.getODP_EPD_NORM()
                                +mix.getAP_EPD_NORM() +mix.getEP_EPD_NORM()+mix.getPOCP_EPD_NORM()
                                +mix.getTW_EPD_NORM() +mix.getTPEC_EPD_NORM();
                    }else{
                        System.out.println("Can not identify an impactCategory");
                    }
                }else if (valueType == "Weighted impact per functional unit"){
                    System.out.println("Accessing data of weighted impact per functional unit");
                    if(impactCategory == "GWP"){
                        System.out.println("Accessing data of GWP");
                        dataTemp = mix.getGWP_EPD_SubScore();
                    }else if(impactCategory == "ODP"){
                        System.out.println("Accessing data of ODP");
                        dataTemp = mix.getODP_EPD_SubScore();
                    }else if(impactCategory == "AP"){
                        System.out.println("Accessing data of AP");
                        dataTemp = mix.getAP_EPD_SubScore();
                    }else if(impactCategory == "EP"){
                        System.out.println("Accessing data of EP");
                        dataTemp = mix.getEP_EPD_SubScore();
                    }else if(impactCategory == "POCP"){
                        System.out.println("Accessing data of POCP");
                        dataTemp = mix.getPOCP_EPD_SubScore();
                    }else if(impactCategory == "TotalWater"){
                        System.out.println("Accessing data of TotalWater");
                        dataTemp = mix.getTW_EPD_SubScore();
                    }else if (impactCategory == "PrimaryEnergyConsumption"){
                        System.out.println("Accessing data of PrimaryEnergyConsumption");
                        dataTemp = mix.getTPEC_EPD_SubScore();
                    }else if (impactCategory == "Overall"){
                        System.out.println("Presenting Weighted value of all impactCategory");
                        dataTemp = mix.getGWP_EPD_SubScore()+mix.getODP_EPD_SubScore()
                                +mix.getAP_EPD_SubScore() +mix.getEP_EPD_SubScore()+mix.getPOCP_EPD_SubScore()
                                +mix.getTW_EPD_SubScore() +mix.getTPEC_EPD_SubScore();
                    }else{
                        System.out.println("Can not identify an impactCategory");
                    }
                }else{
                    System.out.println("Can not identify an valueType");
                }

            }else if(envImpactType == "TSP"){
                System.out.println("Accessing TSP data");
                if(valueType == "Raw impact per functional unit"){
                    System.out.println("Accessing data of raw impact per functional unit");
                    if(impactCategory == "GWP"){
                        System.out.println("Accessing data of GWP");
                        dataTemp = mix.getGWP_Transportation_Ctb();
                    }else if(impactCategory == "ODP"){
                        System.out.println("Accessing data of ODP");
                        dataTemp = mix.getODP_Transportation_Ctb();
                    }else if(impactCategory == "AP"){
                        System.out.println("Accessing data of AP");
                        dataTemp = mix.getAP_Transportation_Ctb();
                    }else if(impactCategory == "EP"){
                        System.out.println("Accessing data of EP");
                        dataTemp = mix.getEP_Transportation_Ctb();
                    }else if(impactCategory == "POCP"){
                        System.out.println("Accessing data of POCP");
                        dataTemp = mix.getPOCP_Transportation_Ctb();
                    }else if(impactCategory == "TotalWater"){
                        System.out.println("Accessing data of TotalWater");
                        dataTemp = mix.getTW_Transportation_Ctb();
                    }else if (impactCategory == "PrimaryEnergyConsumption"){
                        System.out.println("Accessing data of PrimaryEnergyConsumption");
                        dataTemp = mix.getTPEC_Transportation_Ctb();
                    }else if (impactCategory == "Overall"){
                        System.out.println("Presenting raw value of all impactCategory");
                    }else{
                        System.out.println("Can not identify an impactCategory");
                    }
                }else if(valueType == "Normalized impact per functional unit"){
                    System.out.println("Accessing data of normalized impact per functional unit");
                    if(impactCategory == "GWP"){
                        System.out.println("Accessing data of GWP");
                        dataTemp = mix.getGWP_Transportation_NORM();
                    }else if(impactCategory == "ODP"){
                        System.out.println("Accessing data of ODP");
                        dataTemp = mix.getODP_Transportation_NORM();
                    }else if(impactCategory == "AP"){
                        System.out.println("Accessing data of AP");
                        dataTemp = mix.getAP_Transportation_NORM();
                    }else if(impactCategory == "EP"){
                        System.out.println("Accessing data of EP");
                        dataTemp = mix.getEP_Transportation_NORM();
                    }else if(impactCategory == "POCP"){
                        System.out.println("Accessing data of POCP");
                        dataTemp = mix.getPOCP_Transportation_NORM();
                    }else if(impactCategory == "TotalWater"){
                        System.out.println("Accessing data of TotalWater");
                        dataTemp = mix.getTW_Transportation_NORM();
                    }else if (impactCategory == "PrimaryEnergyConsumption"){
                        System.out.println("Accessing data of PrimaryEnergyConsumption");
                        dataTemp = mix.getTPEC_Transportation_NORM();
                    }else if(impactCategory == "Overall"){
                        System.out.println("Presenting Normalized value of all impactCategory");
                    }else{
                        System.out.println("Can not identify an impactCategory");
                    }
                }else if (valueType == "Weighted impact per functional unit"){
                    System.out.println("Accessing data of weighted impact per functional unit");
                    if(impactCategory == "GWP"){
                        System.out.println("Accessing data of GWP");
                        dataTemp = mix.getGWP_Transportation_SubScore();
                    }else if(impactCategory == "ODP"){
                        System.out.println("Accessing data of ODP");
                        dataTemp = mix.getODP_Transportation_SubScore();
                    }else if(impactCategory == "AP"){
                        System.out.println("Accessing data of AP");
                        dataTemp = mix.getAP_Transportation_SubScore();
                    }else if(impactCategory == "EP"){
                        System.out.println("Accessing data of EP");
                        dataTemp = mix.getEP_Transportation_SubScore();
                    }else if(impactCategory == "POCP"){
                        System.out.println("Accessing data of POCP");
                        dataTemp = mix.getPOCP_Transportation_SubScore();
                    }else if(impactCategory == "TotalWater"){
                        System.out.println("Accessing data of TotalWater");
                        dataTemp = mix.getTW_Transportation_SubScore();
                    }else if (impactCategory == "PrimaryEnergyConsumption"){
                        System.out.println("Accessing data of PrimaryEnergyConsumption");
                        dataTemp = mix.getTPEC_Transportation_SubScore();
                    }else if (impactCategory == "Overall"){
                        System.out.println("Presenting Weighted value of all impactCategory");
                    }else{
                        System.out.println("Can not identify an impactCategory");
                    }
                }else{
                    System.out.println("Can not identify an valueType");
                }
            }else if(envImpactType == "Overall"){
                System.out.println("Accessing OVERALL Data_envImpact");

            }else {
                System.out.println("Can not identify an envImpactType");
            }
        }else if (perfType == "Economical Performance"){

        }
        else if (perfType == "Overall"){

        } else {
            System.out.println("Can not identify a performanceType");
            return 0.0;
        }
        // return data value
        return dataTemp;
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
        impactCategory_ComboBox.setItems(impacyCategoryName);
        impactCategory_ComboBox.setValue(impacyCategoryName.get(0));
    }

    private void setupDesignNumber_ComboBox(){
        for (String dKey:DESIGNS.keySet()){
            designNum.add(dKey);
        }
        if (DESIGNS.size()>0){
            designNum.addAll("All designs");
        }
        design_ComboBox.setItems(designNum);
        design_ComboBox.setValue(designNum.get(0));
    }

    public void setupLayerNumber_ComboBox(){
        // clean items in the layerNumber_ComboBox
        layerNum.clear();
        if (!design_ComboBox.getSelectionModel().isEmpty()){
            String selectedDeisgnKey = design_ComboBox.getValue().toString();
            int layerNumber = DESIGNS.get(selectedDeisgnKey).getNumberOfLayers();
            if(layerNumber>0){
                for (int i = 1; i<= layerNumber;i++){
                    StringBuilder sb = new StringBuilder("Layer ");
                    String layerName= sb.append(i).toString();
                    layerNum.add(layerName);
                }
                layerNum.addAll("All layers");
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
        // clean MixNum_ComBox first
        mixNum.clear();
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
                    mixNum.addAll("All Mixes");
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

    }
    public void cleanChart(){
        // clear old data
//        sbc.getData().clear();
//        sbc.layout();
//        bc.getData().clear();
//        bc.layout();
    }
}
