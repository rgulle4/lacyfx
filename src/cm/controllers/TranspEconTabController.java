package cm.controllers;

import cm.Cal_Enp;
import cm.EnvAnalysis_cal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * Created by royg59 on 9/21/16.
 */
public class TranspEconTabController {
    ObservableList<String> VehicleList = FXCollections.observableArrayList("Light-Duty Trucks","Medium-Duty Trucks","Heavy-Duty Trucks");
    ObservableList<String> FuelList = FXCollections.observableArrayList("Gasoline","Diesel");
    ObservableList<String> DistanceList = FXCollections.observableArrayList("<25 miles","<50 miles","<100 miles",">100 miles");
    @FXML
    private ChoiceBox vehicletype;
    @FXML
    private ChoiceBox fueltype;
    @FXML
    private ChoiceBox distanceChoice;
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

        distanceChoice.setValue("<25 miles");
        distanceChoice.setItems(DistanceList);

    }

    public void nextButton(){
        //update distance
        double dst = Double.parseDouble(distance.getText());
        EnvAnalysis_cal.setDistance(dst);

        //Ligtht-Duty truck selected
        if (vehicletype.getSelectionModel().isSelected(0)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysis_cal.setSub_GWP(184.2863);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.606709);
                EnvAnalysis_cal.setSub_EP(0.0);
                EnvAnalysis_cal.setSub_POCP(0.236349);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(184.2863);
                EnvAnalysis_cal.setFCF(22.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysis_cal.setSub_GWP(90.30939);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.135216);
                EnvAnalysis_cal.setSub_EP(0.008555);
                EnvAnalysis_cal.setSub_POCP(26.4664);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(90.30939);
                EnvAnalysis_cal.setFCF(18.6);
            }
        }

        // Mid truck
        if (vehicletype.getSelectionModel().isSelected(1)){
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)){
                EnvAnalysis_cal.setSub_GWP(184.2863);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.606709);
                EnvAnalysis_cal.setSub_EP(0.0);
                EnvAnalysis_cal.setSub_POCP(0.236349);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(184.2863);
                EnvAnalysis_cal.setFCF(40.1);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)){
                EnvAnalysis_cal.setSub_GWP(90.30939);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.135216);
                EnvAnalysis_cal.setSub_EP(0.008555);
                EnvAnalysis_cal.setSub_POCP(26.4664);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(90.30939);
                EnvAnalysis_cal.setFCF(18.6);
            }
        }

        // Heavy truck
        if (vehicletype.getSelectionModel().isSelected(2)) {
            //gasoline selected
            if (fueltype.getSelectionModel().isSelected(0)) {
                EnvAnalysis_cal.setSub_GWP(184.2863);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.606709);
                EnvAnalysis_cal.setSub_EP(0.0);
                EnvAnalysis_cal.setSub_POCP(0.236349);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(184.2863);
                EnvAnalysis_cal.setFCF(42.3);
            }
            //diesel selected
            if (fueltype.getSelectionModel().isSelected(1)) {
                EnvAnalysis_cal.setSub_GWP(90.30939);
                EnvAnalysis_cal.setSub_ODP(0.0);
                EnvAnalysis_cal.setSub_AP(0.135216);
                EnvAnalysis_cal.setSub_EP(0.008555);
                EnvAnalysis_cal.setSub_POCP(26.4664);
                EnvAnalysis_cal.setSub_TW(0.0);
                EnvAnalysis_cal.setSub_DNER(90.30939);
                EnvAnalysis_cal.setFCF(40.0);
            }


        }
        //Calculate
        Cal_Enp cal_enp = new Cal_Enp();
        cal_enp.cal();
        //enp_score.setText(EnvAnalysis_cal.getEnp_Score());
        System.out.println(EnvAnalysis_cal.getEnp_Score());
    }

}
