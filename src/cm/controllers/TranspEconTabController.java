package cm.controllers;

import cm.App;
import cm.models.Cal_Enp;
import cm.models.EnvAnalysis_cal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import cm.controllers.EnvironmentalReportController;

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
        double dst = Double.parseDouble(distance.getText());
        EnvAnalysis_cal.setDistance(dst);

        //Ligtht-Duty truck selected
        if (vehicletype.getSelectionModel().isSelected(0)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysis_cal.setSub_GWP(0.82639609);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.002720667);
                EnvAnalysis_cal.setSub_EP(0.0);
                EnvAnalysis_cal.setSub_POCP(0.00105986);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(0.82639609);
                EnvAnalysis_cal.setFCF(22.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysis_cal.setSub_GWP(0.484666667);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.000725667);
                EnvAnalysis_cal.setSub_EP(4.59E-05);
                EnvAnalysis_cal.setSub_POCP(0.142038161);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(0.484666667);
                EnvAnalysis_cal.setFCF(18.6);
            }
        }

        // Mid truck
        if (vehicletype.getSelectionModel().isSelected(1)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysis_cal.setSub_GWP(0.955169333);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.002720667);
                EnvAnalysis_cal.setSub_EP(0.0);
                EnvAnalysis_cal.setSub_POCP(0.097543444);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(0.953083333);
                EnvAnalysis_cal.setFCF(40.1);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysis_cal.setSub_GWP(0.1275);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(10.3551);
                EnvAnalysis_cal.setSub_EP(0.008555);
                EnvAnalysis_cal.setSub_POCP(0.187780911);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(0.1275);
                EnvAnalysis_cal.setFCF(18.6);
            }
        }

        // Heavy truck
        if (vehicletype.getSelectionModel().isSelected(2)) {
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)) {
                EnvAnalysis_cal.setSub_GWP(0.8325);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.003282732);
                EnvAnalysis_cal.setSub_EP(0.000207703);
                EnvAnalysis_cal.setSub_POCP(0.117447769);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(0.8325);
                EnvAnalysis_cal.setFCF(42.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)) {
                EnvAnalysis_cal.setSub_GWP(1.074);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.007513333);
                EnvAnalysis_cal.setSub_EP(0.000475379);
                EnvAnalysis_cal.setSub_POCP(0.117447769);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(1.074);
                EnvAnalysis_cal.setFCF(39.0);
            }


        }
        //Calculate
        Cal_Enp cal_enp = new Cal_Enp();
        cal_enp.cal();
        enp_score.setText(Double.toString(EnvAnalysis_cal.getEnp_Score()));
    }

    public void showReport() throws IOException{
        App.showEnvironmentalScoreReport();
        EnvironmentalReportController environmentalReportController = new EnvironmentalReportController();
        environmentalReportController.initialize();
    }

}
