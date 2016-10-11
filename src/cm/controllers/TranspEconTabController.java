package cm.controllers;

import cm.App;
import cm.models.EnvAnalysisCalc;
import cm.models.EnvPerformanceCalc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by royg59 on 9/21/16.
 */
public class TranspEconTabController {
    ObservableList<String> VehicleList = FXCollections.observableArrayList("Light-Duty Trucks","Medium-Duty Trucks","Heavy-Duty Trucks");
    ObservableList<String> FuelList = FXCollections.observableArrayList("Gasoline","Diesel");

    private App main;

    @FXML
    private ChoiceBox vehicletype;
    @FXML
    private ChoiceBox fueltype;
    @FXML
    private TextField distance;
    @FXML
    private Label enp_score;

    @FXML
    private void initialize(){
        vehicletype.setValue("Light-Duty Trucks");
        vehicletype.setItems(VehicleList);

        fueltype.setValue("Gasoline");
        fueltype.setItems(FuelList);

    }

    public void computeButton(){
        //update distance
        double dst = Double.parseDouble(distance.getText())*1.609344;       //convert miles to kilometers bc the unit of sun-content is kg/mile
        EnvAnalysisCalc.setDistance(dst);

        //Ligtht-Duty truck selected
        if (vehicletype.getSelectionModel().isSelected(0)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysisCalc.setSub_GWP(0.82639609);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.002720667);
                EnvAnalysisCalc.setSub_EP(0.0);
                EnvAnalysisCalc.setSub_POCP(0.00105986);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(0.82639609);
                EnvAnalysisCalc.setFCF(22.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysisCalc.setSub_GWP(0.484666667);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.000725667);
                EnvAnalysisCalc.setSub_EP(4.59E-05);
                EnvAnalysisCalc.setSub_POCP(0.0);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(0.484666667);
                EnvAnalysisCalc.setFCF(18.6);
            }
        }

        // Mid truck
        if (vehicletype.getSelectionModel().isSelected(1)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysisCalc.setSub_GWP(0.955169333);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.002720667);
                EnvAnalysisCalc.setSub_EP(0.0);
                EnvAnalysisCalc.setSub_POCP(0.097543444);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(0.953083333);
                EnvAnalysisCalc.setFCF(40.1);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysisCalc.setSub_GWP(0.1275);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(10.3551);
                EnvAnalysisCalc.setSub_EP(0.008555);
                EnvAnalysisCalc.setSub_POCP(0.187780911);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(0.1275);
                EnvAnalysisCalc.setFCF(18.6);
            }
        }

        // Heavy truck
        if (vehicletype.getSelectionModel().isSelected(2)) {
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)) {
                EnvAnalysisCalc.setSub_GWP(0.8325);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.003282732);
                EnvAnalysisCalc.setSub_EP(0.000207703);
                EnvAnalysisCalc.setSub_POCP(0.117447769);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(0.8325);
                EnvAnalysisCalc.setFCF(42.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)) {
                EnvAnalysisCalc.setSub_GWP(1.074);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.007513333);
                EnvAnalysisCalc.setSub_EP(0.000475379);
                EnvAnalysisCalc.setSub_POCP(0.117447769);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(1.074);
                EnvAnalysisCalc.setFCF(39.0);
            }


        }
        //Calculate
        EnvPerformanceCalc envPerformanceCalc = new EnvPerformanceCalc();
        envPerformanceCalc.cal();
        enp_score.setText(Double.toString(EnvAnalysisCalc.getEnp_Score()));

        //step by step test
//        System.out.println("Sub_GWP: "+EnvAnalysisCalc.getSub_GWP()+"\n"
//                            +"Sub_ODP: "+EnvAnalysisCalc.getSub_ODP()+"\n"
//                            +"Sub_AP: "+EnvAnalysisCalc.getSub_AP()+"\n"
//                            +"Sub_EP: "+EnvAnalysisCalc.getSub_EP()+"\n"
//                            +"Sub_POCP: "+EnvAnalysisCalc.getSub_POCP()+"\n"
//                            +"Sub_TW: "+EnvAnalysisCalc.getSub_TW()+"\n"
//                            +"Sub_: "+EnvAnalysisCalc.getSub_DNER());
        //EPD_score step by step
//        System.out.println("EPD_GWP: "+EnvAnalysisCalc.getGWP_EDP_Ctb()+"\n"
//                            +"EPD_OPD: "+EnvAnalysisCalc.getODP_EDP_Ctb()+"\n"+"EPD_AP: "+EnvAnalysisCalc.getAP_EDP_Ctb()+"\n"+"EPD_EP: "+EnvAnalysisCalc.getPOCP_EDP_Ctb()
//                            +"\n"+"EPD_TW: "+EnvAnalysisCalc.getTotalWater_EDP_Ctb()+"\n"+"EPD_TPEC: "+EnvAnalysisCalc.getTotalPrimaryEnergyConsumption_EDP_Ctb());

                System.out.println("TSP_GWP: "+EnvAnalysisCalc.getGWP_Transportation_Ctb()+"\n"
                            +"TSP_OPD: "+EnvAnalysisCalc.getODP_Transportation_Ctb()+"\n"+"TSP_AP: "+EnvAnalysisCalc.getAP_Transportation_Ctb()+"\n"+"TSP_EP: "+EnvAnalysisCalc.getPOCP_Transportation_Ctb()
                            +"\n"+"TSP_TW: "+EnvAnalysisCalc.getTotalWater_Transportation_Ctb()+"\n");
    }

    public void showReport() throws IOException{
        App.showEnvironmentalScoreReport();
        EnvironmentalReportController environmentalReportController = new EnvironmentalReportController();
        environmentalReportController.initialize();
    }

}
