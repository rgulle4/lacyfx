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
    @FXML public TextField SumWeightsTextField;

    ObservableList<String> preDefinedWeight = FXCollections.observableArrayList("Default","Equal Weights","User_Defined");
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
            gwpWeightTextField.setText("25.0");
            odpWeightTextField.setText("10.0");
            apWeightTextField.setText("10.0");
            epWeightTextField.setText("10.0");
            pocpWeightTextField.setText("10.0");
            concreteWasteWeightTextField.setText("0.0");
            totWaterConsumptionWeightTextField.setText("10.0");
            PrimaryEnergyUseWeightTextField.setText("25.0");
            MaterialResourceUseWeightTextField.setText("0.0");
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
        SumWeight();
    }

    @FXML
    private void SumWeight(){
        double w_gwp = Double.parseDouble(gwpWeightTextField.getText());
        double w_odp = Double.parseDouble(odpWeightTextField.getText());
        double w_ap = Double.parseDouble(apWeightTextField.getText());
        double w_ep = Double.parseDouble(epWeightTextField.getText());
        double w_pocp = Double.parseDouble(pocpWeightTextField.getText());
        double w_cw = Double.parseDouble(concreteWasteWeightTextField.getText());
        double w_twc = Double.parseDouble(totWaterConsumptionWeightTextField.getText());
        double w_peu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText());
        double w_mru = Double.parseDouble(MaterialResourceUseWeightTextField.getText());

        double sum = w_gwp+ w_odp + w_ap+ w_ep+ w_pocp+ w_cw+ w_twc+ w_peu+ w_mru;
        SumWeightsTextField.setText(Double.toString(sum));
    }

    @FXML
    public void nextButton(){

        double w_enp = Double.parseDouble(envPerWeightTextField.getText())/100.0;
        double w_ecp = Double.parseDouble(encPerWeightTextField.getText())/100.0;
        double w_gwp = Double.parseDouble(gwpWeightTextField.getText())/100.0;
        double w_odp = Double.parseDouble(odpWeightTextField.getText())/100.0;
        double w_ap = Double.parseDouble(apWeightTextField.getText())/100.0;
        double w_ep = Double.parseDouble(epWeightTextField.getText())/100.0;
        double w_pocp = Double.parseDouble(pocpWeightTextField.getText())/100.0;
        double w_cw = Double.parseDouble(concreteWasteWeightTextField.getText())/100.0;
        double w_twc = Double.parseDouble(totWaterConsumptionWeightTextField.getText())/100.0;
        double w_peu = Double.parseDouble(PrimaryEnergyUseWeightTextField.getText())/100.0;
        double w_mru = Double.parseDouble(MaterialResourceUseWeightTextField.getText())/100.0;
        // TO DO
        // A warning to information everyTextfield should be valued and there is a default value for everyTextfield
        //weights W = new weights(w_enp,w_ecp,w_gwp,w_odp,w_ap,w_ep,w_pocp,w_cw,w_cnhw,w_twc,w_ap,w_dner,w_mru,w_dnmr);

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
        EnvAnalysisCalc.setwChw(w_cw);
        EnvAnalysisCalc.setwTwc(w_twc);
        EnvAnalysisCalc.setwRpeu(w_peu);
        EnvAnalysisCalc.setwRmru(w_mru);

        System.out.println("Set up weights in the static method EnvAnalysis_Calc");
    }

}
