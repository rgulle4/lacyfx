package cm.controllers;

import cm.models.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.util.Arrays;
import java.util.Set;

//import static cm.App.designMap;
import static cm.models.Model.*;
import static cm.App.layerMap;
import static cm.App.materialMap;

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
    private XYChart.Series<String,Number> serie_NonRenewableEnergy = new XYChart.Series<>();

    //summary chart
    @FXML
    private XYChart.Series<String,Number> serie_envPerf_EPDScore = new XYChart.Series<>();
    @FXML
    private  XYChart.Series<String,Number> serie_envPerf_TransportationScore = new XYChart.Series<>();

    public  void initialize(){

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
//        serie_NonRenewableEnergy.setName("NonRenewableEnergy");
//        serie_NonRenewableEnergy.getData().add(new XYChart.Data<>(design1, EnvAnalysisCalc.getTotalPrimaryEnergyConsumption_EDP_Ctb()));
//        serie_NonRenewableEnergy.getData().add(new XYChart.Data<>(design2, EnvAnalysisCalc.getTotalPrimaryEnergyConsumption_Transportation_Ctb()));
//        sbc.getData().addAll(serie_GWP,serie_ODP,serie_AP,serie_EP,serie_POCP,serie_TotalWater,serie_NonRenewableEnergy);


        Design design;
        Layer layer;
        Material material;

        Set<String> design_keys = DESIGNS.keySet();
        Set<String> layer_keys = layerMap.keySet();
        Set<String> material_keys = materialMap.keySet();

        EnvPerformanceCalc envPerformanceCalc = new EnvPerformanceCalc();
        envPerformanceCalc.EnvAnalysisCalc();

        for (String key_design:design_keys){
            design = DESIGNS.get(key_design);
            xAxis.setLabel("Alternative");
            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(design.getDesign_ID(),design.getDesign_ID())));
            yAxis.setLabel("Score");

            serie_envPerf_EPDScore.setName("envPerf_EPDScore");
            serie_envPerf_TransportationScore.setName("envPerf_TransportationScore");
            //Stacked Chart for EPD_score and Transportation_score of Environmental analysis

            serie_envPerf_EPDScore.getData().add(new XYChart.Data<>(design.getDesign_ID(), design.getEnvPerfAnalysis_EPDScore_Design()));
            serie_envPerf_TransportationScore.getData().add(new XYChart.Data<>(design.getDesign_ID(), design.getEnvPerfAnalysis_TransportationScore_Design()));
            sbc.getData().addAll(serie_envPerf_EPDScore,serie_envPerf_TransportationScore);
            for(String key_layer:layer_keys){

                for (String key_material:material_keys){

                }
            }
        }




    }


}
