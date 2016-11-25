package cm.controllers;

import cm.models.Weights;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static cm.models.Model.*;

/**
 * Controller for the Weights tab.
 */
public final class WeightsTabController {

    @FXML public TextField envPerWeightTextField;
    @FXML public TextField encPerWeightTextField;
    @FXML public TextField gwpWeightTextField;
    @FXML public TextField odpWeightTextField;
    @FXML public TextField apWeightTextField;
    @FXML public TextField epWeightTextField;
    @FXML public TextField pocpWeightTextField;
    @FXML public TextField PrimaryEnergyUseWeightTextField;
    @FXML public TextField SumWeightsTextField;

    ObservableList<String> preDefinedWeight = FXCollections.observableArrayList(
            "Default","BEES Stakeholder Panel",
            "EPA Science Advisory Board-based","User_Defined");
    @FXML public ComboBox predefinedweightComboBox;

    // initialization
    @FXML
    private void initialize(){
        predefinedweightComboBox.setItems(preDefinedWeight);
        predefinedweightComboBox.setValue("Select Weights");
        envPerWeightTextField.setText("50.0");
        double encPerfWeigh = 100.0 - Double.parseDouble(envPerWeightTextField.getText());
        encPerWeightTextField.setText(Double.toString(encPerfWeigh));
        predefinedweightComboBox.getSelectionModel().selectFirst();
        predefineWeight();
    }
    @FXML
    private void encPerfWeight(){
        if(anyTextFieldISEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Textfield is empty");
            alert.setContentText("No Textfield is allowed to be empty\nPlease fill up all the textfileds.");
            alert.showAndWait();
        }
        else{
            double encPerfWeigh = 100.0 - Double.parseDouble(envPerWeightTextField.getText());
            encPerWeightTextField.setText(Double.toString(encPerfWeigh));
        }

    }
    @FXML
    private void predefineWeight(){
        // default predefined Weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(0)){
            gwpWeightTextField.setText("20.0");
            odpWeightTextField.setText("15.0");
            apWeightTextField.setText("15.0");
            epWeightTextField.setText("15.0");
            pocpWeightTextField.setText("15.0");
            PrimaryEnergyUseWeightTextField.setText("20.0");
        }
        // user_defined Weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(1)){
            gwpWeightTextField.setText("37.0");
            odpWeightTextField.setText("10.0");
            apWeightTextField.setText("10.0");
            epWeightTextField.setText("13.0");
            pocpWeightTextField.setText("12.0");
            PrimaryEnergyUseWeightTextField.setText("18.0");
        }
        if (predefinedweightComboBox.getSelectionModel().isSelected(2)){
            gwpWeightTextField.setText("25.0");
            odpWeightTextField.setText("15.0");
            apWeightTextField.setText("15.0");
            epWeightTextField.setText("15.0");
            pocpWeightTextField.setText("15.0");
            PrimaryEnergyUseWeightTextField.setText("15.0");
        }
        if (predefinedweightComboBox.getSelectionModel().isSelected(3)){
            gwpWeightTextField.setText("0.0");
            odpWeightTextField.setText("0.0");
            apWeightTextField.setText("0.0");
            epWeightTextField.setText("0.0");
            pocpWeightTextField.setText("0.0");
            PrimaryEnergyUseWeightTextField.setText("0.0");
        }
        SumWeight();
    }

    @FXML
    private void SumWeight(){
        if(anyTextFieldISEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Textfield is empty");
            alert.setContentText("No Textfield is allowed to be empty\nPlease fill up all the textfileds.");
            alert.showAndWait();
        }
        else{
            double w_gwp = Double.parseDouble(gwpWeightTextField.getText());
            double w_odp = Double.parseDouble(odpWeightTextField.getText());
            double w_ap = Double.parseDouble(apWeightTextField.getText());
            double w_ep = Double.parseDouble(epWeightTextField.getText());
            double w_pocp = Double.parseDouble(pocpWeightTextField.getText());
            double w_peu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText());

            double sum = w_gwp+ w_odp + w_ap+ w_ep+ w_pocp+ w_peu;
            SumWeightsTextField.setText(Double.toString(sum));
        }
    }

    @FXML
    public void SaveButton(){
        double w_enp = 0.0;
        double w_ecp = 0.0;
        double w_gwp = 0.0;
        double w_odp = 0.0;
        double w_ap = 0.0;
        double w_ep = 0.0;
        double w_pocp = 0.0;
        double w_peu = 0.0;
        if(anyTextFieldISEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Textfield is empty");
            alert.setContentText("No Textfield is allowed to be empty\nPlease fill up all the textfileds.");
            alert.showAndWait();
        }else{
            if(Double.parseDouble(envPerWeightTextField.getText())
                    +Double.parseDouble(encPerWeightTextField.getText())<100.0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("The Sum of Performance Weight is not 100%!");
                alert.setContentText("Please modify it before saving them.");
                alert.showAndWait();
            }else{
                w_enp = Double.parseDouble(envPerWeightTextField.getText())/100.0;
                w_ecp = Double.parseDouble(encPerWeightTextField.getText())/100.0;}
            // TO DO
            // A warning to information everyTextfield should be valued and there is a default value for everyTextfield
            //Weights W = new Weights(w_enp,w_ecp,w_gwp,w_odp,w_ap,w_ep,w_pocp,w_cw,w_cnhw,w_twc,w_ap,w_dner,w_mru,w_dnmr);
            if(Double.parseDouble(SumWeightsTextField.getText())<100.0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("The Sum of Weight is not 100%!");
                alert.setContentText("Please modify it or use a predefined weights\nbefore saving them.");
                alert.showAndWait();
            }
            else {
                w_gwp = Double.parseDouble(gwpWeightTextField.getText()) / 100.0;
                w_odp = Double.parseDouble(odpWeightTextField.getText()) / 100.0;
                w_ap = Double.parseDouble(apWeightTextField.getText()) / 100.0;
                w_ep = Double.parseDouble(epWeightTextField.getText()) / 100.0;
                w_pocp = Double.parseDouble(pocpWeightTextField.getText()) / 100.0;
                w_peu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText()) / 100.0;
            }/*
        Set and store Weights
         */
            if(SumWeightsTextField.getText().equals("100.0")
                    && Double.parseDouble(envPerWeightTextField.getText())
                    +Double.parseDouble(encPerWeightTextField.getText())==100.0) {
                WEIGHTS.setwEnvPerformance(w_enp);
                WEIGHTS.setwEconPerformance(w_ecp);
                WEIGHTS.setwGwp(w_gwp);
                WEIGHTS.setwOdp(w_odp);
                WEIGHTS.setwAp(w_ap);
                WEIGHTS.setwEp(w_ep);
                WEIGHTS.setwPocp(w_pocp);
                WEIGHTS.setwTotalPrimaryEnergyConsumption(w_peu);
                System.out.println("Set up Weights in the static method EnvAnalysis_Calc");
            }

        }


        }
    private Boolean anyTextFieldISEmpty(){
        if(     envPerWeightTextField.getText().isEmpty()
                ||encPerWeightTextField.getText().isEmpty()
                ||gwpWeightTextField.getText().isEmpty()
                ||odpWeightTextField.getText().isEmpty()
                ||apWeightTextField.getText().isEmpty()
                ||epWeightTextField.getText().isEmpty()
                ||pocpWeightTextField.getText().isEmpty()
                ||PrimaryEnergyUseWeightTextField.getText().isEmpty()
                ) return true;
        else return false;
    }
}

