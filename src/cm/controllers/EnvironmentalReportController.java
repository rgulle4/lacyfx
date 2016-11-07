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

//    @FXML
//    private CategoryAxis xAxis = new CategoryAxis();
//    @FXML
//    private NumberAxis yAxis = new NumberAxis();
//    @FXML
//    private StackedBarChart<String, Number> sbc = new StackedBarChart<>(xAxis, yAxis);
//    @FXML
//    private BarChart<String, Number> bc = new BarChart<>(xAxis,yAxis);
//    @FXML
//    private XYChart.Series<String, Number> serie_GWP = new XYChart.Series<>();
//    @FXML
//    private XYChart.Series<String, Number> serie_ODP = new XYChart.Series<>();
//    @FXML
//    private XYChart.Series<String, Number> serie_AP = new XYChart.Series<>();
//    @FXML
//    private XYChart.Series<String, Number> serie_EP = new XYChart.Series<>();
//    @FXML
//    private XYChart.Series<String, Number> serie_POCP = new XYChart.Series<>();
////    @FXML
//    private XYChart.Series<String, Number> serie_TotalWater = new XYChart.Series<>();
//    @FXML
//    private XYChart.Series<String, Number> serie_TotalPrimaryEnergyConsumption = new XYChart.Series<>();
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
        String designID = design_ComboBox.getSelectionModel().getSelectedItem().toString();
        String layerID = layer_ComboBox.getSelectionModel().getSelectedItem().toString();
        String mixID = mix_ComboBox.getSelectionModel().getSelectedItem().toString();
        Design designTemp = DESIGNS.get(designID);
        int layerIndex = layer_ComboBox.getSelectionModel().getSelectedIndex();
        Layer layerTemp = designTemp.getLayer(layerIndex);
        int mixIndex = mix_ComboBox.getSelectionModel().getSelectedIndex();
//        Mix mixTemp = layerTemp.getMaterial(mixIndex);
        /**
         * >>>>>>>>>>>>>>>>>>>>New code>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
         */
        List<Mix> mixsTemp = new ArrayList<Mix>();
        String selectedMixItem = mix_ComboBox.getValue().toString();
        if(selectedMixItem == "All Mixes"){
                mixsTemp = layerTemp.getMixes();
        }else{
            Mix selectedMix = layerTemp.getMaterial(mixIndex);
            mixsTemp.add(selectedMix);
        }
        String perfType = performanceType_ComboBox.getSelectionModel().getSelectedItem().toString();
        String envImpactType = environmentalImpact_ComboBox.getSelectionModel().getSelectedItem().toString();
        String valueType = rawValue_ComboBox.getSelectionModel().getSelectedItem().toString();
        String impactCategory = this.impactCategory.getSelectionModel().getSelectedItem().toString();
        ShowChartData chart1 = new ShowChartData(perfType,envImpactType,valueType,impactCategory);
        String alternative_ID = new StringBuilder(designID).append(layerID).toString();
        chart1.showBarChart("Alternatives","Value","Environmental Analysis",alternative_ID, mixsTemp);

        /**
         * >>>>>>>>>>>>>>>>>>>>Old code>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
         */
//        // setValue according to performanceType_ComboBox,
//        // environmentalImpact_ComboBox and rawValue_ComboBox
//        if (performanceType_ComboBox.getSelectionModel().isSelected(0)) {
//            // Environmental Performance is selected
//            if (environmentalImpact_ComboBox.getSelectionModel().isSelected(0)) {
//                // EPD is selected
//                int overallIndex = layerTemp.getNumberofMaterials();
//                if (materialIndex == overallIndex) {
//                    if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
//                        //ctb is selected
//                        barChart_EPD_Ctb_Mix(designID, layerID, layerTemp);
//                    }
//                    if(rawValue_ComboBox.getSelectionModel().isSelected(1)){
//                        //Norm is selected
//                        barChart_EPD_Norm_Mix(designID, layerID, layerTemp);
//                    }
//                    if(rawValue_ComboBox.getSelectionModel().isSelected(2)){
//                        //SubScore is selected
//                        barChart_EPD_SubScore_Mix(designID, layerID, layerTemp);
//                    }
//                } else {
//                    Mix mixTemp = layerTemp.getMaterial(materialIndex);
//                    if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
//                        //ctb is selected
//                        barChart_EPD_Ctb_Mix(designID, layerID, mixID, mixTemp);
//                    }
//                    if (rawValue_ComboBox.getSelectionModel().isSelected(1)) {
//                        //ctb is selected
//                        barChart_EPD_NORM_Mix(designID, layerID, mixID, mixTemp);
//                    }
//                    if (rawValue_ComboBox.getSelectionModel().isSelected(2)) {
//                        //ctb is selected
//                        barChart_EPD_SubScore_Mix(designID, layerID, mixID, mixTemp);
//                    }
//                }
//
//            }
//            // TSP is selected
//            else {
//                int overallIndex = layerTemp.getNumberofMaterials();
//                if (materialIndex == overallIndex) {
//                    if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
//                        //ctb is selected
//                        barChart_Transportation_Ctr_Mix(designID, layerID, layerTemp);
//                    }
//                    if(rawValue_ComboBox.getSelectionModel().isSelected(1)){
//                        //Norm is selected
//                        barChart_Transportation_Norm_Mix(designID, layerID, layerTemp);
//                    }
//                    if(rawValue_ComboBox.getSelectionModel().isSelected(2)){
//                        //SubScore is selected
//                        barChart_Transportation_SubScore_Mix(designID, layerID, layerTemp);
//                    }
//                } else {
//                    Mix mixTemp = layerTemp.getMaterial(materialIndex);
//                    if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
//                        //ctb is selected
//                        barChart_Transportation_Ctr_Mix(designID, layerID, mixID, mixTemp);
//                    }
//                    if(rawValue_ComboBox.getSelectionModel().isSelected(1)){
//                        //Norm is selected
//                        barChart_Transportation_NORM_Mix(designID, layerID, mixID, mixTemp);
//
//                    }
//                    if(rawValue_ComboBox.getSelectionModel().isSelected(2)){
//                        //SubScore is selected
//                        barChart_Transportation_SubScore_Mix(designID, layerID, mixID, mixTemp);
//                    }
//                }
//            }
//        }else{
//            System.out.println("Don't worry, economical analysis is coming soon!");
//        }
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

//    public void barChart_EPD_Ctb_Mix(String designID, String layerID, Layer layer){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        int mixNum = layer.getNumberofMaterials();
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//
//        for (int i = 1; i <= mixNum; i ++){
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append("Mix "+i);
//            final String mixID = sb.toString();
//            Mix mix = layer.getMaterial(i-1);
//            serie_GWP.getData().add(new XYChart.Data<>(mixID, mix.getGWP_EPD_Ctb()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixID, mix.getODP_EPD_Ctb()));
//            serie_AP.getData().add(new XYChart.Data<>(mixID, mix.getAP_EPD_Ctb()));
//            serie_EP.getData().add(new XYChart.Data<>(mixID, mix.getEP_EPD_Ctb()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixID, mix.getPOCP_EPD_Ctb()));
//            serie_TotalWater.getData().add(new XYChart.Data<>(mixID, mix.getTW_EPD_Ctb()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixID, mix.getTPEC_EPD_Ctb()));
//        }
//
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_EPD_Ctb_Mix(String designID, String layerID, String mixID, Mix mix){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append(mixID);
//            final String mixid = sb.toString();
//            serie_GWP.getData().add(new XYChart.Data<>(mixid, mix.getGWP_EPD_Ctb()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixid, mix.getODP_EPD_Ctb()));
//            serie_AP.getData().add(new XYChart.Data<>(mixid, mix.getAP_EPD_Ctb()));
//            serie_EP.getData().add(new XYChart.Data<>(mixid, mix.getEP_EPD_Ctb()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixid, mix.getPOCP_EPD_Ctb()));
//            serie_TotalWater.getData().add(new XYChart.Data<>(mixid, mix.getTW_EPD_Ctb()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixid, mix.getTPEC_EPD_Ctb()));
//
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_EPD_Norm_Mix(String designID, String layerID, Layer layer){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        int mixNum = layer.getNumberofMaterials();
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//
//        for (int i = 1; i <= mixNum; i ++){
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append("Mix "+i);
//            final String mixID = sb.toString();
//            Mix mix = layer.getMaterial(i-1);
//            serie_GWP.getData().add(new XYChart.Data<>(mixID, mix.getGWP_EPD_NORM()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixID, mix.getODP_EPD_NORM()));
//            serie_AP.getData().add(new XYChart.Data<>(mixID, mix.getAP_EPD_NORM()));
//            serie_EP.getData().add(new XYChart.Data<>(mixID, mix.getEP_EPD_NORM()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixID, mix.getPOCP_EPD_NORM()));
//            serie_TotalWater.getData().add(new XYChart.Data<>(mixID, mix.getTW_EPD_NORM()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixID, mix.getTPEC_EPD_NORM()));
//        }
//
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_EPD_NORM_Mix(String designID, String layerID, String mixID, Mix mix){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        StringBuilder sb = new StringBuilder().append(designID).append(layerID).append(mixID);
//        final String mixid = sb.toString();
//        serie_GWP.getData().add(new XYChart.Data<>(mixid, mix.getGWP_EPD_NORM()));
//        serie_ODP.getData().add(new XYChart.Data<>(mixid, mix.getODP_EPD_NORM()));
//        serie_AP.getData().add(new XYChart.Data<>(mixid, mix.getAP_EPD_NORM()));
//        serie_EP.getData().add(new XYChart.Data<>(mixid, mix.getEP_EPD_NORM()));
//        serie_POCP.getData().add(new XYChart.Data<>(mixid, mix.getPOCP_EPD_NORM()));
//        serie_TotalWater.getData().add(new XYChart.Data<>(mixid, mix.getTW_EPD_NORM()));
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixid, mix.getTPEC_EPD_NORM()));
//
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_EPD_SubScore_Mix(String designID, String layerID, Layer layer){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        int mixNum = layer.getNumberofMaterials();
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
////        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//
//        for (int i = 1; i <= mixNum; i ++){
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append("Mix "+i);
//            final String mixID = sb.toString();
//            Mix mix = layer.getMaterial(i-1);
//            serie_GWP.getData().add(new XYChart.Data<>(mixID, mix.getGWP_EPD_SubScore()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixID, mix.getODP_EPD_SubScore()));
//            serie_AP.getData().add(new XYChart.Data<>(mixID, mix.getAP_EPD_SubScore()));
//            serie_EP.getData().add(new XYChart.Data<>(mixID, mix.getEP_EPD_SubScore()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixID, mix.getPOCP_EPD_SubScore()));
////            serie_TotalWater.getData().add(new XYChart.Data<>(mixID, mix.getTW_EPD_SubScore()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixID, mix.getTPEC_EPD_SubScore()));
//        }
//
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_EPD_SubScore_Mix(String designID, String layerID, String mixID, Mix mix){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        StringBuilder sb = new StringBuilder().append(designID).append(layerID).append(mixID);
//        final String mixid = sb.toString();
//        serie_GWP.getData().add(new XYChart.Data<>(mixid, mix.getGWP_EPD_SubScore()));
//        serie_ODP.getData().add(new XYChart.Data<>(mixid, mix.getODP_EPD_SubScore()));
//        serie_AP.getData().add(new XYChart.Data<>(mixid, mix.getAP_EPD_SubScore()));
//        serie_EP.getData().add(new XYChart.Data<>(mixid, mix.getEP_EPD_SubScore()));
//        serie_POCP.getData().add(new XYChart.Data<>(mixid, mix.getPOCP_EPD_SubScore()));
////        serie_TotalWater.getData().add(new XYChart.Data<>(mixid, mix.getTW_EPD_SubScore()));
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixid, mix.getTPEC_EPD_SubScore()));
//
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
////        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    //TSP PART
//
//    public void barChart_Transportation_Ctr_Mix(String designID, String layerID, Layer layer){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        int mixNum = layer.getNumberofMaterials();
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//
//        for (int i = 1; i <= mixNum; i ++){
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append("Mix "+i);
//            final String mixID = sb.toString();
//            Mix mix = layer.getMaterial(i-1);
//            serie_GWP.getData().add(new XYChart.Data<>(mixID, mix.getGWP_Transportation_Ctb()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixID, mix.getODP_Transportation_Ctb()));
//            serie_AP.getData().add(new XYChart.Data<>(mixID, mix.getAP_Transportation_Ctb()));
//            serie_EP.getData().add(new XYChart.Data<>(mixID, mix.getEP_Transportation_Ctb()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixID, mix.getPOCP_Transportation_Ctb()));
//            serie_TotalWater.getData().add(new XYChart.Data<>(mixID, mix.getTW_Transportation_Ctb()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixID, mix.getTPEC_Transportation_Ctb()));
//        }
//
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_Transportation_Ctr_Mix(String designID, String layerID, String mixID, Mix mix){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        StringBuilder sb = new StringBuilder().append(designID).append(layerID).append(mixID);
//        final String mixid = sb.toString();
//        serie_GWP.getData().add(new XYChart.Data<>(mixid, mix.getGWP_Transportation_Ctb()));
//        serie_ODP.getData().add(new XYChart.Data<>(mixid, mix.getODP_Transportation_Ctb()));
//        serie_AP.getData().add(new XYChart.Data<>(mixid, mix.getAP_Transportation_Ctb()));
//        serie_EP.getData().add(new XYChart.Data<>(mixid, mix.getEP_Transportation_Ctb()));
//        serie_POCP.getData().add(new XYChart.Data<>(mixid, mix.getPOCP_Transportation_Ctb()));
//        serie_TotalWater.getData().add(new XYChart.Data<>(mixid, mix.getTW_Transportation_Ctb()));
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixid, mix.getTPEC_Transportation_Ctb()));
//
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_Transportation_Norm_Mix(String designID, String layerID, Layer layer){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        int mixNum = layer.getNumberofMaterials();
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//
//        for (int i = 1; i <= mixNum; i ++){
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append("Mix "+i);
//            final String mixID = sb.toString();
//            Mix mix = layer.getMaterial(i-1);
//            serie_GWP.getData().add(new XYChart.Data<>(mixID, mix.getGWP_Transportation_NORM()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixID, mix.getODP_Transportation_NORM()));
//            serie_AP.getData().add(new XYChart.Data<>(mixID, mix.getAP_Transportation_NORM()));
//            serie_EP.getData().add(new XYChart.Data<>(mixID, mix.getEP_Transportation_NORM()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixID, mix.getPOCP_Transportation_NORM()));
//            serie_TotalWater.getData().add(new XYChart.Data<>(mixID, mix.getTW_Transportation_NORM()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixID, mix.getTPEC_Transportation_NORM()));
//        }
//
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_Transportation_NORM_Mix(String designID, String layerID, String mixID, Mix mix){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        StringBuilder sb = new StringBuilder().append(designID).append(layerID).append(mixID);
//        final String mixid = sb.toString();
//        serie_GWP.getData().add(new XYChart.Data<>(mixid, mix.getGWP_Transportation_NORM()));
//        serie_ODP.getData().add(new XYChart.Data<>(mixid, mix.getODP_Transportation_NORM()));
//        serie_AP.getData().add(new XYChart.Data<>(mixid, mix.getAP_Transportation_NORM()));
//        serie_EP.getData().add(new XYChart.Data<>(mixid, mix.getEP_Transportation_NORM()));
//        serie_POCP.getData().add(new XYChart.Data<>(mixid, mix.getPOCP_Transportation_NORM()));
//        serie_TotalWater.getData().add(new XYChart.Data<>(mixid, mix.getTW_Transportation_NORM()));
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixid, mix.getTPEC_Transportation_NORM()));
//
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_Transportation_SubScore_Mix(String designID, String layerID, Layer layer){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        int mixNum = layer.getNumberofMaterials();
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
////        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//
//        for (int i = 1; i <= mixNum; i ++){
//            StringBuilder sb = new StringBuilder().append(designID).append(layerID).append("Mix "+i);
//            final String mixID = sb.toString();
//            Mix mix = layer.getMaterial(i-1);
//            serie_GWP.getData().add(new XYChart.Data<>(mixID, mix.getGWP_Transportation_SubScore()));
//            serie_ODP.getData().add(new XYChart.Data<>(mixID, mix.getODP_Transportation_SubScore()));
//            serie_AP.getData().add(new XYChart.Data<>(mixID, mix.getAP_Transportation_SubScore()));
//            serie_EP.getData().add(new XYChart.Data<>(mixID, mix.getEP_Transportation_SubScore()));
//            serie_POCP.getData().add(new XYChart.Data<>(mixID, mix.getPOCP_Transportation_SubScore()));
////            serie_TotalWater.getData().add(new XYChart.Data<>(mixID, mix.getTW_Transportation_SubScore()));
//            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixID, mix.getTPEC_Transportation_SubScore()));
//        }
//
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalPrimaryEnergyConsumption);
//    }
//
//    public void barChart_Transportation_SubScore_Mix(String designID, String layerID, String mixID, Mix mix){
//        // clear old data
//        sbc.getData().clear();
//        sbc.setVisible(false);
//        sbc.layout();
//        bc.getData().clear();
//        bc.setVisible(true);
//        bc.layout();
//        // set up axis label
//        xAxis.setLabel("Mix");
//        yAxis.setLabel("Value");
//
//        StringBuilder sb = new StringBuilder().append(designID).append(layerID).append(mixID);
//        final String mixid = sb.toString();
//        serie_GWP.getData().add(new XYChart.Data<>(mixid, mix.getGWP_Transportation_SubScore()));
//        serie_ODP.getData().add(new XYChart.Data<>(mixid, mix.getODP_Transportation_SubScore()));
//        serie_AP.getData().add(new XYChart.Data<>(mixid, mix.getAP_Transportation_SubScore()));
//        serie_EP.getData().add(new XYChart.Data<>(mixid, mix.getEP_Transportation_SubScore()));
//        serie_POCP.getData().add(new XYChart.Data<>(mixid, mix.getPOCP_Transportation_SubScore()));
////        serie_TotalWater.getData().add(new XYChart.Data<>(mixid, mix.getTW_Transportation_SubScore()));
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(mixid, mix.getTPEC_Transportation_SubScore()));
//
//        serie_GWP.setName("GWP");
//        serie_ODP.setName("ODP");
//        serie_AP.setName("AP");
//        serie_EP.setName("EP");
//        serie_POCP.setName("POCP");
////        serie_TotalWater.setName("TotalWater");
//        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
//        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalPrimaryEnergyConsumption);
//    }



    public void cleanChart(){
        // clear old data
//        sbc.getData().clear();
//        sbc.layout();
//        bc.getData().clear();
//        bc.layout();
    }
}
