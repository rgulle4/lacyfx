package cm.controllers;

import cm.models.EnvAnalysis_cal;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/6.
 */
public class EnvironmentalReportController {
//    final static String design1 = EnvAnalysis_cal.getProductId;

    private static String design1 = "Design1";
    private static String design2 = "Design2";
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

    public  void initialize(){
        xAxis.setLabel("Alternative");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(design1,design2)));
        yAxis.setLabel("Score");
        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getGWP_EDP_Ctb()));
        serie_GWP.getData().add(new XYChart.Data<>(design2,EnvAnalysis_cal.getGWP_Transportation_Ctb()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getODP_EDP_Ctb()));
        serie_ODP.getData().add(new XYChart.Data<>(design2,EnvAnalysis_cal.getODP_Transportation_Ctb()));
        serie_AP.setName("AP");
        serie_AP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getAP_EDP_Ctb()));
        serie_AP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getAP_Transportation_Ctb()));
        serie_EP.setName("EP");
        serie_EP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getEP_EDP_Ctb()));
        serie_EP.getData().add(new XYChart.Data<>(design2,EnvAnalysis_cal.getEP_Transportation_Ctb()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getPOCP_EDP_Ctb()));
        serie_POCP.getData().add(new XYChart.Data<>(design2,EnvAnalysis_cal.getPOCP_Transportation_Ctb()));
        serie_TotalWater.setName("TotalWater");
        serie_TotalWater.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getTotalWater_EDP_Ctb()));
        serie_TotalWater.getData().add(new XYChart.Data<>(design2,EnvAnalysis_cal.getTotalWater_Transportation_Ctb()));
        serie_NonRenewableEnergy.setName("NonRenewableEnergy");
        serie_NonRenewableEnergy.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getNonRenewableEnergy_EDP_Ctb()));
        serie_NonRenewableEnergy.getData().add(new XYChart.Data<>(design2,EnvAnalysis_cal.getNonRenewableEnergy_Transportation_Ctb()));
        sbc.getData().addAll(serie_GWP,serie_ODP,serie_AP,serie_EP,serie_POCP,serie_TotalWater,serie_NonRenewableEnergy);
    }


}
