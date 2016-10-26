package cm.controllers;

import cm.models.Design;
import cm.models.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import java.util.Arrays;

import static cm.models.Model.*;

//import static cm.App.designMap;


/**
 * Created by Administrator on 2016/10/6.
 */
public class EnvironmentalReportController {
//    final static String design1 = EnvAnalysisCalc.getProductId;

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
    private ComboBox alternative_ComboBox;
    @FXML
    private ComboBox impactCategory;

    @FXML
    private RadioButton sumGraph_RadioButton;
    @FXML
    private Button show_Button;
    @FXML
    private Button clean_Button;

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
                    "TSP"
            );
    // raw Value type
    ObservableList<String> rawValueType = FXCollections.
            observableArrayList(
                    "Raw value",
                    "Normalized value",
                    "Weighted value"
            );

    // Number of designs
    ObservableList<String> designNum = FXCollections.
            observableArrayList(
                    "Design 1", "Design 2", "Design 3", "Design 4",
                    "Design 5", "Design 6", "Design 7", "Design 8",
                    "Overall");
    // Number of layers in this design
    ObservableList<String> layerNum = FXCollections
            .observableArrayList(
                    "Layer 1", "Layer 2", "Layer 3", "Layer 4",
                    "Layer 5", "Layer 6", "Layer 7", "Layer 8",
                    "Overall");
    // Number of layers in this design
    ObservableList<String> alternativeNum = FXCollections
            .observableArrayList(
                    "Alternative 1", "Alternative 2", "Alternative 3",
                    "Alternative 4", "Alternative 5", "Alternative 6",
                    "Alternative 7", "Alternative 8","Overall");
    // Impact Category
    ObservableList<String> impacyCategoryName =FXCollections
            .observableArrayList(
                    "Overall","GWP","ODP","AP","EP",
                    "POCP","TotalWater","PrimaryEnergyConsumption"
            );

    @FXML
    public void initialize() {

        //set up default elements
        performanceType_ComboBox.setItems(performanceType);
        performanceType_ComboBox.setValue(performanceType.get(0));
        environmentalImpact_ComboBox.setItems(impactType);
        environmentalImpact_ComboBox.setValue(impactType.get(0));
        rawValue_ComboBox.setItems(rawValueType);
        rawValue_ComboBox.setValue(rawValueType.get(0));

        design_ComboBox.setItems(designNum);
        design_ComboBox.setValue(designNum.get(0));
        layer_ComboBox.setItems(layerNum);
        layer_ComboBox.setValue(layerNum.get(0));
        alternative_ComboBox.setItems(alternativeNum);
        alternative_ComboBox.setValue(alternativeNum.get(0));
        impactCategory.setItems(impacyCategoryName);
        impactCategory.setValue(impacyCategoryName.get(0));


//            serie_envPerf_EPDScore.setName("envPerf_EPDScore");
//            serie_envPerf_TransportationScore.setName("envPerf_TransportationScore");
//            //Stacked Chart for EPD_score and Transportation_score of Environmental analysis
//
//            serie_envPerf_EPDScore.getData().add(new XYChart.Data<>(design.getDesignId(), design.getEnvPerfAnalysis_EPDScore_Design()));
//            serie_envPerf_TransportationScore.getData().add(new XYChart.Data<>(design.getDesignId(), design.getEnvPerfAnalysis_TransportationScore_Design()));
//            sbc.getData().addAll(serie_envPerf_EPDScore,serie_envPerf_TransportationScore);

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
        // setValue according to performanceType_ComboBox,
        // environmentalImpact_ComboBox and rawValue_ComboBox
        if (performanceType_ComboBox.getSelectionModel().isSelected(0)){
            // Environmental Performance is selected
            if (environmentalImpact_ComboBox.getSelectionModel().isSelected(0)){
                // EPD is selected
                if (rawValue_ComboBox.getSelectionModel().isSelected(0)) {
                    //ctb is selected
                    barChart_EPD_Ctr_Alternative(designID,layerTemp);
                }
                if(rawValue_ComboBox.getSelectionModel().isSelected(1)){
                    //Norm is selected
                    barChart_EPD_Norm_Alternative(designID,layerTemp);
                }
                if(rawValue_ComboBox.getSelectionModel().isSelected(2)){
                    //SubScore is selected
                    barChart_EPD_SubScore_Alternative(designID,layerTemp);
                }
            }
        }
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
                        "alternative 1",
                        "alternative 2",
                        "alternative 3")));

        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getGWP_EDP_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getODP_EDP_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getAP_EDP_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getEP_EDP_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getPOCP_EDP_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTW_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTPEC_EDP_Ctb()));
        sbc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_EPD_Ctr_Alternative(String designID, Layer layerTemp){
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
        serie_GWP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getGWP_EDP_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getODP_EDP_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getAP_EDP_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getEP_EDP_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getPOCP_EDP_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTW_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTW_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTPEC_EDP_Ctb()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTPEC_EDP_Ctb()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_EPD_Norm_Alternative(String designID, Layer layerTemp){
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
        serie_GWP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getGWP_EDP_NORM()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getGWP_EDP_NORM()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getGWP_EDP_NORM()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getODP_EDP_NORM()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getODP_EDP_NORM()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getODP_EDP_NORM()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getAP_EDP_NORM()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getAP_EDP_NORM()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getAP_EDP_NORM()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getEP_EDP_NORM()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getEP_EDP_NORM()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getEP_EDP_NORM()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getPOCP_EDP_NORM()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getPOCP_EDP_NORM()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getPOCP_EDP_NORM()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTW_EDP_NORM()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTW_EDP_NORM()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTW_EDP_NORM()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTPEC_EDP_NORM()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTPEC_EDP_NORM()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTPEC_EDP_NORM()));
        bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
    }

    public void barChart_EPD_SubScore_Alternative(String designID, Layer layerTemp){
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
        serie_GWP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getGWP_EDP_Subsore()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getGWP_EDP_Subsore()));
        serie_GWP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getGWP_EDP_Subsore()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getODP_EDP_Subsore()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getODP_EDP_Subsore()));
        serie_ODP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getODP_EDP_Subsore()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getAP_EDP_Subsore()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getAP_EDP_Subsore()));
        serie_AP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getAP_EDP_Subsore()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getEP_EDP_Subsore()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getEP_EDP_Subsore()));
        serie_EP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getEP_EDP_Subsore()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getPOCP_EDP_Subsore()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getPOCP_EDP_Subsore()));
        serie_POCP.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getPOCP_EDP_Subsore()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTW_EDP_Subsore()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTW_EDP_Subsore()));
        serie_TotalWater.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTW_EDP_Subsore()));
        serie_TotalPrimaryEnergyConsumption.setName("TotalPrimaryEnergyConsumption");
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 1", layerTemp.getTPEC_EDP_Subsore()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 2", layerTemp.getTPEC_EDP_Subsore()));
        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>("alternative 3", layerTemp.getTPEC_EDP_Subsore()));
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
