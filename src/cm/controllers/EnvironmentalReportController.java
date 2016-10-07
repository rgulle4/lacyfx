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
    private XYChart.Series<String,Number> serie_POCP = new XYChart.Series<>();

    public  void initialize(){
        xAxis.setLabel("Alternative");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(design1)));
        yAxis.setLabel("Score");
        serie_GWP.setName("GWP");
        serie_GWP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getGwp()));
        serie_ODP.setName("ODP");
        serie_ODP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getOdp()));
        serie_POCP.setName("POCP");
        serie_POCP.getData().add(new XYChart.Data<>(design1,EnvAnalysis_cal.getPocp()));
        sbc.getData().addAll(serie_GWP,serie_ODP,serie_POCP);
    }


}
