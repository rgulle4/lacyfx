package cm.controllers;

import cm.models.EnvAnalysisCalc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller for the weights tab.
 */
public class WeightsTabController {

    @FXML public TextField envPerWeightTextField;
    @FXML public TextField encPerWeightTextField;
    @FXML public TextField gwpWeightTextField;
    @FXML public TextField odpWeightTextField;
    @FXML public TextField apWeightTextField;
    @FXML public TextField epWeightTextField;
    @FXML public TextField pocpWeightTextField;
    @FXML public TextField concreteWasteWeightTextField;
    @FXML public TextField totWaterConsumptionWeightTextField;
    @FXML public TextField PrimaryEnergyUseWeightTextField;
    @FXML public TextField MaterialResourceUseWeightTextField;

    ObservableList<String> preDefinedWeight = FXCollections.observableArrayList("Default","Equal Weights","User_Defined");
    @FXML public ComboBox predefinedweightComboBox;

    // initialization
    @FXML
    private void initialize(){
        predefinedweightComboBox.setItems(preDefinedWeight);
        predefinedweightComboBox.setValue("Select Weights");
        envPerWeightTextField.setText("50.0");
        encPerWeightTextField.setText("50.0");
    }
    @FXML
    private void predefineWeight(){
        // default predefined weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(0)){
            gwpWeightTextField.setText("15.0");
            odpWeightTextField.setText("10.0");
            apWeightTextField.setText("10.0");
            epWeightTextField.setText("10.0");
            pocpWeightTextField.setText("10.0");
            concreteWasteWeightTextField.setText("10.0");
            totWaterConsumptionWeightTextField.setText("10.0");
            PrimaryEnergyUseWeightTextField.setText("15.0");
            MaterialResourceUseWeightTextField.setText("10.0");
        }
        // equal predefined weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(1)){
            gwpWeightTextField.setText("12.0");
            odpWeightTextField.setText("11.0");
            apWeightTextField.setText("11.0");
            epWeightTextField.setText("11.0");
            pocpWeightTextField.setText("11.0");
            concreteWasteWeightTextField.setText("11.0");
            totWaterConsumptionWeightTextField.setText("11.0");
            PrimaryEnergyUseWeightTextField.setText("11.0");
            MaterialResourceUseWeightTextField.setText("11.0");
        }
        // user_defined weights
        if (predefinedweightComboBox.getSelectionModel().isSelected(2)){
            gwpWeightTextField.setText("0.0");
            odpWeightTextField.setText("0.0");
            apWeightTextField.setText("0.0");
            epWeightTextField.setText("0.0");
            pocpWeightTextField.setText("0.0");
            concreteWasteWeightTextField.setText("0.0");
            totWaterConsumptionWeightTextField.setText("0.0");
            PrimaryEnergyUseWeightTextField.setText("0.0");
            MaterialResourceUseWeightTextField.setText("0.0");
        }
    }

    @FXML
    public void nextButton(){
        double w_enp = Double.parseDouble(envPerWeightTextField.getText());
        double w_ecp = Double.parseDouble(encPerWeightTextField.getText());
        double w_gwp = Double.parseDouble(gwpWeightTextField.getText());
        double w_odp = Double.parseDouble(odpWeightTextField.getText());
        double w_ap = Double.parseDouble(apWeightTextField.getText());
        double w_ep = Double.parseDouble(epWeightTextField.getText());
        double w_pocp = Double.parseDouble(pocpWeightTextField.getText());
        double w_chw = Double.parseDouble(concreteWasteWeightTextField.getText());
        double w_twc = Double.parseDouble(totWaterConsumptionWeightTextField.getText());
        double w_rpeu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText());
        double w_rmru = Double.parseDouble(MaterialResourceUseWeightTextField.getText());
        // TO DO
        // A warning to information everyTextfield should be valued and there is a default value for everyTextfield
        //weights W = new weights(w_enp,w_ecp,w_gwp,w_odp,w_ap,w_ep,w_pocp,w_chw,w_cnhw,w_twc,w_ap,w_dner,w_rmru,w_dnmr);

        /*
        Set and store Weights
         */
        EnvAnalysisCalc.setwEnp(w_enp);
        EnvAnalysisCalc.setwEcp(w_ecp);
        EnvAnalysisCalc.setwGwp(w_gwp);
        EnvAnalysisCalc.setwOdp(w_odp);
        EnvAnalysisCalc.setwAp(w_ap);
        EnvAnalysisCalc.setwEp(w_ep);
        EnvAnalysisCalc.setwPocp(w_pocp);
        EnvAnalysisCalc.setwChw(w_chw);
        EnvAnalysisCalc.setwTwc(w_twc);
        EnvAnalysisCalc.setwRpeu(w_rpeu);
        EnvAnalysisCalc.setwRmru(w_rmru);

        System.out.println("Set up weights in the static method EnvAnalysis_Calc");
    }

}
