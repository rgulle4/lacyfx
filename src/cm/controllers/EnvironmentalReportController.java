package cm.controllers;

import cm.models.Design;
import cm.models.Layer;
import cm.models.Mix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import sun.java2d.pipe.AlphaPaintPipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cm.models.Model.*;
import cm.App;
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
    private XYChart.Series<String, Number> serie_TotalPrimaryEnergyConsumption = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_allAlternatives = new XYChart.Series<>();
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
        //Get the whole selected Mix lists
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
        showBarChart("Alternatives","Value","Environmental Analysis",alternative_ID, mixsTemp);
    }

    public void showBarChart(String xLabel, String yLabel, String chartTitle, String incompletedAlternative_ID, List<Mix> mixs){
        bc.getData().clear();
        bc.layout();
        bc.setVisible(true);
        // set up bar chart
        bc.setTitle(chartTitle);
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
        serie_GWP.setName("GWP");
        serie_ODP.setName("ODP");
        serie_AP.setName("AP");
        serie_EP.setName("EP");
        serie_POCP.setName("POCP");
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_allAlternatives.setName("Impact Analysis of All Alternatives");
        Double Sum_SubScore=0.0;
        for (Mix mix:mixs){
            if (impactCategory == "Impact Analysis Comparison of All Alternatives"){
                Sum_SubScore = Sum_SubScore + getSingleDataValue(mix);
            }
        }
        for (Mix aMix:mixs){
            String mix_ID = aMix.getZipCode();
            String product_ID =aMix.getProduct_ID();
            StringBuilder sb = new StringBuilder(incompletedAlternative_ID).append(mix_ID).append(product_ID);
            String alternative_ID = sb.toString();
            if(impactCategory == "GWP"){
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, getSingleDataValue(aMix)));
            }
            if(impactCategory == "ODP"){
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, getSingleDataValue(aMix)));
            }
            if(impactCategory == "AP"){
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, getSingleDataValue(aMix)));
            }
            if(impactCategory == "EP"){
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, getSingleDataValue(aMix)));
            }
            if(impactCategory == "POCP"){
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, getSingleDataValue(aMix)));
            }
            if (impactCategory == "PrimaryEnergyConsumption"){
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, getSingleDataValue(aMix)));
            }
            if (impactCategory == "Impact Analysis Comparison of All Alternatives"){
                Double averageScore = getSingleDataValue(aMix)/Sum_SubScore;
                serie_allAlternatives.getData().add(new XYChart.Data<>(alternative_ID,averageScore));
            }
            if (impactCategory == "Impact Analysis per Alternative"){
                setupSerieForImpactAnalysisPerAlternative(alternative_ID,aMix);
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
        if (impactCategory == "PrimaryEnergyConsumption"){
            bc.getData().add(serie_TotalPrimaryEnergyConsumption);
        }
        if (impactCategory == "Impact Analysis Comparison of All Alternatives"){
            bc.getData().add(serie_allAlternatives);
        }
        if (impactCategory == "Impact Analysis per Alternative"){
            bc.getData().addAll(serie_GWP,serie_ODP,serie_AP,serie_EP,serie_POCP,serie_TotalPrimaryEnergyConsumption);
        }
    }
    public void setupSerieForImpactAnalysisPerAlternative(String alternative_ID, Mix aMix){
        if(envImpactType =="EPD"){
            if(valueType == "Raw impact per functional unit") {
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getGWP_EPD_Ctb()));
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getODP_EPD_Ctb()));
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getAP_EPD_Ctb()));
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getEP_EPD_Ctb()));
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getPOCP_EPD_Ctb()));
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, aMix.getTPEC_EPD_Ctb()));
            }else if(valueType == "Normalized impact per functional unit"){
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getGWP_EPD_NORM()));
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getODP_EPD_NORM()));
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getAP_EPD_NORM()));
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getEP_EPD_NORM()));
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getPOCP_EPD_NORM()));
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, aMix.getTPEC_EPD_NORM()));
            }else{
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getGWP_EPD_SubScore()));
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getODP_EPD_SubScore()));
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getAP_EPD_SubScore()));
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getEP_EPD_SubScore()));
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getPOCP_EPD_SubScore()));
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, aMix.getTPEC_EPD_SubScore()));
            }
        }else if(envImpactType == "TSP"){
            if(valueType == "Raw impact per functional unit") {
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getGWP_Transportation_Ctb()));
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getODP_Transportation_Ctb()));
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getAP_Transportation_Ctb()));
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getEP_Transportation_Ctb()));
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getPOCP_Transportation_Ctb()));
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, aMix.getTPEC_Transportation_Ctb()));
            }else if(valueType == "Normalized impact per functional unit"){
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getGWP_Transportation_NORM()));
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getODP_Transportation_NORM()));
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getAP_Transportation_NORM()));
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getEP_Transportation_NORM()));
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getPOCP_Transportation_NORM()));
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, aMix.getTPEC_Transportation_NORM()));
            }else{
                serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getGWP_Transportation_SubScore()));
                serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getODP_Transportation_SubScore()));
                serie_AP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getAP_Transportation_SubScore()));
                serie_EP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getEP_Transportation_SubScore()));
                serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, aMix.getPOCP_Transportation_SubScore()));
                serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, aMix.getTPEC_Transportation_SubScore()));
            }
        }else{
            //update stack bar chart here!!
        }
    }
    public double getSingleDataValue(Mix aMix){
        if (perfType == "Environmental Performance"){
            System.out.println("Accessing environmental performance data");
            return getSingleEnvPerfValue(aMix);
        }
        else if(perfType == "Economical Performance"){
            System.out.println("Accessing economical performance data");
            return getSingleEncPerfValue(aMix);
        }else {
            System.out.println("Accessing overall performance data");
            return getSingleOverallPerfValue(aMix);
        }
    }
    public double getSingleEnvPerfValue(Mix aMix){
        if(envImpactType == "EPD"){
            System.out.println("Accessing EPD data");
            return getSingleEPDValue(aMix);
        }else if (envImpactType == "TSP"){
            System.out.println("Accessing TSP data");
            return getSingleTSPValue(aMix);
        }else {
            System.out.println("Accessing overall environmental impact data");
            return getOverallSingleEnvImpactValue(aMix);
        }
    }
    public double getSingleEPDValue(Mix aMix) {
        if (valueType == "Raw impact per functional unit") {
            System.out.println("Accessing data of raw impact per functional unit");
            return getSingleRawFunctionalUnitValue_EPD(aMix);
        } else if (valueType == "Normalized impact per functional unit") {
            System.out.println("Accessing data of normalized impact per functional unit");
            return getSingleNormFunctionalUnitValue_EPD(aMix);
        }else{
            System.out.println("Accessing data of weighted impact per functional unit");
            return getSingleWeightedFunctionUnitValue_EPD(aMix);
        }
    }
    public double getSingleTSPValue(Mix aMix) {
        if (valueType == "Raw impact per functional unit") {
            System.out.println("Accessing data of raw impact per functional unit");
            return getSingleRawFunctionalUnitValue_TSP(aMix);
        } else if (valueType == "Normalized impact per functional unit") {
            System.out.println("Accessing data of normalized impact per functional unit");
            return getSingleNormFunctionalUnitValue_TSP(aMix);
        }else{
            System.out.println("Accessing data of weighted impact per functional unit");
            return getSingleWeightedFunctionUnitValue_TSP(aMix);
        }
    }
    public double getOverallSingleEnvImpactValue(Mix aMix) {
        if (valueType == "Raw impact per functional unit") {
            System.out.println("Accessing data of raw impact per functional unit");
            return getSingleRawFunctionalUnitValue_Overall(aMix);
        } else if (valueType == "Normalized impact per functional unit") {
            System.out.println("Accessing data of normalized impact per functional unit");
            return getSingleNormFunctionalUnitValue_Overall(aMix);
        }else{
            System.out.println("Accessing data of weighted impact per functional unit");
            return getSingleWeightedFunctionUnitValue_Overall(aMix);
        }
    }
    public double getSingleRawFunctionalUnitValue_EPD(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_EPD_Ctb();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_EPD_Ctb();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_EPD_Ctb();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return mix.getEP_EPD_Ctb();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_EPD_Ctb();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_EPD_Ctb();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_EPD_Ctb();
        } else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleNormFunctionalUnitValue_EPD(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_EPD_NORM();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_EPD_NORM();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_EPD_NORM();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return mix.getEP_EPD_NORM();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_EPD_NORM();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_EPD_NORM();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_EPD_NORM();
        } else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleWeightedFunctionUnitValue_EPD(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_EPD_SubScore();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_EPD_SubScore();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_EPD_SubScore();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return mix.getEP_EPD_SubScore();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_EPD_SubScore();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_EPD_SubScore();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_EPD_SubScore();
        }else if (impactCategory == "Impact Analysis Comparison of All Alternatives"){
            System.out.println("Comparison of All Alternatives");
            return mix.getSum_EPD_SubScore();
        } else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleRawFunctionalUnitValue_TSP(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_Transportation_Ctb();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_Transportation_Ctb();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_Transportation_Ctb();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return mix.getEP_Transportation_Ctb();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_Transportation_Ctb();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_Transportation_Ctb();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_Transportation_Ctb();
        }else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleNormFunctionalUnitValue_TSP(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_Transportation_NORM();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_Transportation_NORM();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_Transportation_NORM();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return mix.getEP_Transportation_NORM();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_Transportation_NORM();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_Transportation_NORM();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_Transportation_NORM();
        }else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleWeightedFunctionUnitValue_TSP(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_Transportation_SubScore();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_Transportation_SubScore();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_Transportation_SubScore();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return mix.getEP_Transportation_SubScore();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_Transportation_SubScore();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_Transportation_SubScore();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_Transportation_SubScore();
        }else if (impactCategory == "Impact Analysis Comparison of All Alternatives") {
            System.out.println("Comparison of All Alternatives");
            return mix.getSum_Transportation_SubScore();
        }else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleRawFunctionalUnitValue_Overall(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_EPD_Ctb()+mix.getGWP_Transportation_Ctb();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_EPD_Ctb()+mix.getODP_Transportation_Ctb();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_EPD_Ctb()+mix.getAP_Transportation_Ctb();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return  mix.getEP_EPD_Ctb()+mix.getEP_Transportation_Ctb();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_EPD_Ctb()+mix.getPOCP_Transportation_Ctb();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_EPD_Ctb()+mix.getTW_Transportation_Ctb();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_EPD_Ctb()+mix.getTPEC_Transportation_Ctb();
        }else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleNormFunctionalUnitValue_Overall(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_EPD_NORM()+mix.getGWP_Transportation_NORM();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_EPD_NORM()+mix.getODP_Transportation_NORM();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_EPD_NORM()+mix.getAP_Transportation_NORM();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return  mix.getEP_EPD_NORM()+mix.getEP_Transportation_NORM();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_EPD_NORM()+mix.getPOCP_Transportation_NORM();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_EPD_NORM()+mix.getTW_Transportation_NORM();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_EPD_NORM() + mix.getTPEC_Transportation_NORM();
        }else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }
    public double getSingleWeightedFunctionUnitValue_Overall(Mix mix) {
        if (impactCategory == "GWP") {
            System.out.println("Accessing data of GWP");
            return mix.getGWP_EPD_SubScore()+mix.getGWP_Transportation_SubScore();
        } else if (impactCategory == "ODP") {
            System.out.println("Accessing data of ODP");
            return mix.getODP_EPD_SubScore()+mix.getODP_Transportation_SubScore();
        } else if (impactCategory == "AP") {
            System.out.println("Accessing data of AP");
            return mix.getAP_EPD_SubScore()+mix.getAP_Transportation_SubScore();
        } else if (impactCategory == "EP") {
            System.out.println("Accessing data of EP");
            return  mix.getEP_EPD_SubScore()+mix.getEP_Transportation_SubScore();
        } else if (impactCategory == "POCP") {
            System.out.println("Accessing data of POCP");
            return mix.getPOCP_EPD_SubScore()+mix.getPOCP_Transportation_SubScore();
        } else if (impactCategory == "TotalWater") {
            System.out.println("Accessing data of TotalWater");
            return mix.getTW_EPD_SubScore()+mix.getTW_Transportation_SubScore();
        } else if (impactCategory == "PrimaryEnergyConsumption") {
            System.out.println("Accessing data of PrimaryEnergyConsumption");
            return mix.getTPEC_EPD_SubScore()+mix.getTPEC_Transportation_SubScore();
        }else if (impactCategory == "Impact Analysis Comparison of All Alternatives") {
            System.out.println("Comparison of All Alternatives");
            return mix.getSum_EPD_SubScore()+mix.getSum_Transportation_SubScore();
        }else {
            System.out.println("Can not identify an impactCategory");
            return 0.0;
        }
    }

    public double getSingleEncPerfValue(Mix aMix){return 0.0;}
    public double getSingleOverallPerfValue(Mix aMix){return 0.0;}

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

    public void setupImpactCategory_ComboBox(){
        valueType = rawValue_ComboBox.getSelectionModel().getSelectedItem().toString();
        // Impact Category
        ObservableList<String> impactCategoryName =FXCollections
                .observableArrayList(
                        "Impact Analysis Comparison of All Alternatives",
                        "Impact Analysis per Alternative",
                        "GWP","ODP","AP","EP", "POCP",
                        "PrimaryEnergyConsumption"
                );
        if(valueType == "Weighted impact per functional unit"){
            impactCategory_ComboBox.setItems(impactCategoryName);
        }else{
            impactCategoryName.remove(0);
            impactCategory_ComboBox.setItems(impactCategoryName);
        }

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
        sbc.getData().clear();
        sbc.layout();
        bc.getData().clear();
        bc.layout();
        serie_GWP.getData().clear();
        serie_ODP.getData().clear();
        serie_AP.getData().clear();
        serie_EP.getData().clear();
        serie_POCP.getData().clear();
        serie_TotalPrimaryEnergyConsumption.getData().clear();
    }
    App main;
    public void showTable() throws IOException {
        main.showEnvironmentalDataTable();
    }
}
