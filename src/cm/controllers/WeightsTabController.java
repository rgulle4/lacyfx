package cm.controllers;

import cm.models.Weights;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    ObservableList<String> preDefinedWeight = FXCollections.observableArrayList("Default","test1","BEES Stakeholder Pane","EPA Science Advisory Board-based","User_Defined");
    @FXML public ComboBox predefinedweightComboBox;

    // initialization
    @FXML
    private void initialize(){
        predefinedweightComboBox.setItems(preDefinedWeight);
        predefinedweightComboBox.setValue("Select Weights");
        envPerWeightTextField.setText("50.0");
        double encPerfWeigh = 100.0 - Double.parseDouble(envPerWeightTextField.getText());
        encPerWeightTextField.setText(Double.toString(encPerfWeigh));
    }
    @FXML
    private void encPerfWeight(){
        double encPerfWeigh = 100.0 - Double.parseDouble(envPerWeightTextField.getText());
        encPerWeightTextField.setText(Double.toString(encPerfWeigh));
    }
    @FXML
    private void predefineWeight(){
        // default predefined Weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(0)){
            gwpWeightTextField.setText("15.0");
            odpWeightTextField.setText("10.0");
            apWeightTextField.setText("10.0");
            epWeightTextField.setText("10.0");
            pocpWeightTextField.setText("10.0");
            PrimaryEnergyUseWeightTextField.setText("15.0");
        }
        // equal predefined Weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(1)){
            gwpWeightTextField.setText("30.0");
            odpWeightTextField.setText("10.0");
            apWeightTextField.setText("10.0");
            epWeightTextField.setText("10.0");
            pocpWeightTextField.setText("10.0");
            PrimaryEnergyUseWeightTextField.setText("30.0");
        }
        // user_defined Weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(2)){
            gwpWeightTextField.setText("29.0");
            odpWeightTextField.setText("2.0");
            apWeightTextField.setText("3.0");
            epWeightTextField.setText("6.0");
            pocpWeightTextField.setText("4.0");
            PrimaryEnergyUseWeightTextField.setText("10.0");
        }
        if (predefinedweightComboBox.getSelectionModel().isSelected(3)){
            gwpWeightTextField.setText("16.0");
            odpWeightTextField.setText("5.0");
            apWeightTextField.setText("5.0");
            epWeightTextField.setText("5.0");
            pocpWeightTextField.setText("6.0");
            PrimaryEnergyUseWeightTextField.setText("5.0");
        }
        if (predefinedweightComboBox.getSelectionModel().isSelected(4)){
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
        double w_gwp = Double.parseDouble(gwpWeightTextField.getText());
        double w_odp = Double.parseDouble(odpWeightTextField.getText());
        double w_ap = Double.parseDouble(apWeightTextField.getText());
        double w_ep = Double.parseDouble(epWeightTextField.getText());
        double w_pocp = Double.parseDouble(pocpWeightTextField.getText());
        double w_peu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText());

        double sum = w_gwp+ w_odp + w_ap+ w_ep+ w_pocp+ w_peu;
        SumWeightsTextField.setText(Double.toString(sum));
    }

    @FXML
    public void SaveButton(){

        double w_enp = Double.parseDouble(envPerWeightTextField.getText())/100.0;
        double w_ecp = Double.parseDouble(encPerWeightTextField.getText())/100.0;
        double w_gwp = Double.parseDouble(gwpWeightTextField.getText())/100.0;
        double w_odp = Double.parseDouble(odpWeightTextField.getText())/100.0;
        double w_ap = Double.parseDouble(apWeightTextField.getText())/100.0;
        double w_ep = Double.parseDouble(epWeightTextField.getText())/100.0;
        double w_pocp = Double.parseDouble(pocpWeightTextField.getText())/100.0;
        double w_peu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText())/100.0;
        // TO DO
        // A warning to information everyTextfield should be valued and there is a default value for everyTextfield
        //Weights W = new Weights(w_enp,w_ecp,w_gwp,w_odp,w_ap,w_ep,w_pocp,w_cw,w_cnhw,w_twc,w_ap,w_dner,w_mru,w_dnmr);

        /*
        Set and store Weights
         */
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
