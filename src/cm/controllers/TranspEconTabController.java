package cm.controllers;

import cm.EnvPerformanceCalc;
import cm.EnvAnalysisCalc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by royg59 on 9/21/16.
 */
public class TranspEconTabController {
    ObservableList<String> VehicleList = FXCollections.observableArrayList("Light-Duty Trucks","Medium-Duty Trucks","Heavy-Duty Trucks");
    ObservableList<String> FuelList = FXCollections.observableArrayList("Gasoline","Diesel");

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

    public void nextButton(){
        //update distance
        double dst = Double.parseDouble(distance.getText());
        EnvAnalysisCalc.setDistance(dst);

        //Ligtht-Duty truck selected
        if (vehicletype.getSelectionModel().isSelected(0)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysisCalc.setSub_GWP(184.2863);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.606709);
                EnvAnalysisCalc.setSub_EP(0.0);
                EnvAnalysisCalc.setSub_POCP(0.236349);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(184.2863);
                EnvAnalysisCalc.setFCF(22.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysisCalc.setSub_GWP(90.30939);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.135216);
                EnvAnalysisCalc.setSub_EP(0.008555);
                EnvAnalysisCalc.setSub_POCP(26.4664);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(90.30939);
                EnvAnalysisCalc.setFCF(18.6);
            }
        }

        // Mid truck
        if (vehicletype.getSelectionModel().isSelected(1)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysisCalc.setSub_GWP(184.2863);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.606709);
                EnvAnalysisCalc.setSub_EP(0.0);
                EnvAnalysisCalc.setSub_POCP(0.236349);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(184.2863);
                EnvAnalysisCalc.setFCF(40.1);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysisCalc.setSub_GWP(90.30939);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.135216);
                EnvAnalysisCalc.setSub_EP(0.008555);
                EnvAnalysisCalc.setSub_POCP(26.4664);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(90.30939);
                EnvAnalysisCalc.setFCF(18.6);
            }
        }

        // Heavy truck
        if (vehicletype.getSelectionModel().isSelected(2)) {
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)) {
                EnvAnalysisCalc.setSub_GWP(184.2863);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.606709);
                EnvAnalysisCalc.setSub_EP(0.0);
                EnvAnalysisCalc.setSub_POCP(0.236349);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(184.2863);
                EnvAnalysisCalc.setFCF(42.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)) {
                EnvAnalysisCalc.setSub_GWP(90.30939);
                EnvAnalysisCalc.setSub_ODP(0.0);
                EnvAnalysisCalc.setSub_AP(0.135216);
                EnvAnalysisCalc.setSub_EP(0.008555);
                EnvAnalysisCalc.setSub_POCP(26.4664);
                EnvAnalysisCalc.setSub_TW(0.0);
                EnvAnalysisCalc.setSub_DNER(90.30939);
                EnvAnalysisCalc.setFCF(40.0);
            }


        }
        //Calculate
        EnvPerformanceCalc cal_enp = new EnvPerformanceCalc();
        cal_enp.cal();
        enp_score.setText(Double.toString(EnvAnalysisCalc.getEnp_Score()));
    }

}
