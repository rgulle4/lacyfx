package cm.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

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
    private StackedBarChart<String,Number> sbc = new StackedBarChart<>(xAxis,yAxis);
    @FXML
    private XYChart.Series<String,Number> serie_GWP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String,Number> serie_ODP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String,Number> serie_AP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String,Number> serie_EP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String,Number> serie_POCP = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String,Number> serie_TotalWater = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String,Number> serie_TotalPrimaryEnergyConsumption = new XYChart.Series<>();

    //summary chart
    @FXML
    private XYChart.Series<String,Number> serie_envPerf_EPDScore = new XYChart.Series<>();
    @FXML
    private  XYChart.Series<String,Number> serie_envPerf_TransportationScore = new XYChart.Series<>();

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
    private RadioButton sumGraph_RadioButton;

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
                    "Design 1","Design 2","Design 3","Design 4",
                    "Design 5","Design 6","Design 7","Design 8",
                    "All designs");
    // Number of layers in this design
    ObservableList<String> layerNum = FXCollections
            .observableArrayList(
                    "Layer 1","Layer 2","Layer 3","Layer 4",
                    "Layer 5","Layer 6","Layer 7","Layer 8",
                    "All layers");
    // Number of layers in this design
    ObservableList<String> alternativeNum = FXCollections
            .observableArrayList(
                    "Alternative 1","Alternative 2","Alternative 3",
                    "Alternative 4","Alternative 5","Alternative 6",
                    "Alternative 7","Alternative 8","All alternatives");

    @FXML
    public  void initialize() {

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

        //Environmental analysis in details
//        serie_GWP.setName("GWP");
//        serie_GWP.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getGWP_EDP_Ctb()));
//        serie_GWP.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getGWP_Transportation_Ctb()));
//        serie_ODP.setName("ODP");
//        serie_ODP.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getODP_EDP_Ctb()));
//        serie_ODP.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getODP_Transportation_Ctb()));
//        serie_AP.setName("AP");
//        serie_AP.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getAP_EDP_Ctb()));
//        serie_AP.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getAP_Transportation_Ctb()));
//        serie_EP.setName("EP");
//        serie_EP.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getEP_EDP_Ctb()));
//        serie_EP.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getEP_Transportation_Ctb()));
//        serie_POCP.setName("POCP");
//        serie_POCP.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getPOCP_EDP_Ctb()));
//        serie_POCP.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getPOCP_Transportation_Ctb()));
//        serie_TotalWater.setName("TotalWater");
//        serie_TotalWater.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getTotalWater_EDP_Ctb()));
//        serie_TotalWater.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getTotalWater_Transportation_Ctb()));
//        serie_TotalPrimaryEnergyConsumption.setName("NonRenewableEnergy");
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getTotalPrimaryEnergyConsumption_EDP_Ctb()));
//        serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getTotalPrimaryEnergyConsumption_Transportation_Ctb()));
//        sbc.getData().addAll(serie_GWP,serie_ODP,serie_AP,serie_EP,serie_POCP,serie_TotalWater,serie_TotalPrimaryEnergyConsumption);


//        Design design;
//        Layer layer;
//        Material material;
//
//        EnvPerformanceCalc envPerformanceCalc = new EnvPerformanceCalc();
//        envPerformanceCalc.EnvAnalysisCalc();
//
//            xAxis.setLabel("Alternative");
//            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(design.getDesignId(),design.getDesignId())));
//            yAxis.setLabel("Score");
//
//            serie_envPerf_EPDScore.setName("envPerf_EPDScore");
//            serie_envPerf_TransportationScore.setName("envPerf_TransportationScore");
//            //Stacked Chart for EPD_score and Transportation_score of Environmental analysis
//
//            serie_envPerf_EPDScore.getData().add(new XYChart.Data<>(design.getDesignId(), design.getEnvPerfAnalysis_EPDScore_Design()));
//            serie_envPerf_TransportationScore.getData().add(new XYChart.Data<>(design.getDesignId(), design.getEnvPerfAnalysis_TransportationScore_Design()));
//            sbc.getData().addAll(serie_envPerf_EPDScore,serie_envPerf_TransportationScore);


    }
}
