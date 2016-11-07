package cm.models;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import java.util.List;

/**
 * Created by Qiandong on 2016/11/5.
 */
public class ShowChartData {
    private String performanceType;
    private String envImpactType;
    private String valueType;
    private String impactCategory;      //GWP, ODP, AP, EP, POCP, Energy Consumption...
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
    //    @FXML
    private XYChart.Series<String, Number> serie_TotalWater = new XYChart.Series<>();
    @FXML
    private XYChart.Series<String, Number> serie_TotalPrimaryEnergyConsumption = new XYChart.Series<>();

    // initialize
    public ShowChartData(String perfType,String envImpactType, String vType, String impacCategory ){
        this.performanceType = perfType;
        this.envImpactType = envImpactType;
        this.valueType = vType;
        this.impactCategory = impacCategory;
    }
    @FXML
    public void initialize(){
        // Initialize bar chart and stack chart
        sbc.getData().clear();
        sbc.layout();
        sbc.setVisible(false);
        bc.getData().clear();
        bc.layout();
        bc.setVisible(false);
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
        for (Mix aMix:mixs){
            String mix_ID = aMix.getMaterial_ID();
            StringBuilder sb = new StringBuilder(incompletedAlternative_ID).append(mix_ID);
            String alternative_ID = sb.toString();
            serie_GWP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            serie_ODP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            serie_AP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            serie_EP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            serie_POCP.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            serie_TotalPrimaryEnergyConsumption.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
            serie_TotalWater.getData().add(new XYChart.Data<>(alternative_ID, getDataValue(aMix)));
        }
        if(valueType != "Weighted impact per functional unit"){
            bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP, serie_TotalWater, serie_TotalPrimaryEnergyConsumption);
        }
        else {
            bc.getData().addAll(serie_GWP, serie_ODP, serie_AP, serie_EP, serie_POCP,serie_TotalPrimaryEnergyConsumption);
        }
    }

    public double getDataValue(Mix mix){
        double dataTemp = 0.0;
        if (performanceType == "Environmental Performance"){
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
        }else if (performanceType == "Economical Performance"){

        }
        else if (performanceType == "Overall"){

        } else {
            System.out.println("Can not identify a performanceType");
            return 0.0;
        }
        // return data value
        return dataTemp;
    }
}
