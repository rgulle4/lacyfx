package cm.controllers;

import cm.models.EnvAnalysisCalc;
import javafx.fxml.FXML;
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
    @FXML public TextField concreteHazardousWasteWeightTextField;
    @FXML public TextField concreteNonhazardousWastteWeightTextField;
    @FXML public TextField totWaterConsumptionWeightTextField;
    @FXML public TextField renewablePrimaryEnergyUseWeightTextField;
    @FXML public TextField depletionOfNonrenewableEnergyResourceWeightTextField;
    @FXML public TextField renewableMaterialResourceUseWeightTextField;
    @FXML public TextField depletionOfNonrenewableMaterialResourcesWeightTextField;

    // initialization
    @FXML
    private void initialize(){
        envPerWeightTextField.setText("50.0");
        encPerWeightTextField.setText("50.0");
        gwpWeightTextField.setText("15.0");
        odpWeightTextField.setText("10.0");
        apWeightTextField.setText("10.0");
        epWeightTextField.setText("10.0");
        pocpWeightTextField.setText("10.0");
        concreteHazardousWasteWeightTextField.setText("10.0");
        concreteNonhazardousWastteWeightTextField.setText("10.0");
        totWaterConsumptionWeightTextField.setText("10.0");
        renewablePrimaryEnergyUseWeightTextField.setText("10.0");
        depletionOfNonrenewableEnergyResourceWeightTextField.setText("10.0");
        renewableMaterialResourceUseWeightTextField.setText("10.0");
        depletionOfNonrenewableMaterialResourcesWeightTextField.setText("10.0");

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
        double w_chw = Double.parseDouble(concreteHazardousWasteWeightTextField.getText());
        double w_cnhw = Double.parseDouble(concreteNonhazardousWastteWeightTextField.getText());
        double w_twc = Double.parseDouble(totWaterConsumptionWeightTextField.getText());
        double w_rpeu = Double.parseDouble(renewablePrimaryEnergyUseWeightTextField.getText());
        double w_dner = Double.parseDouble(depletionOfNonrenewableEnergyResourceWeightTextField.getText());
        double w_rmru = Double.parseDouble(renewableMaterialResourceUseWeightTextField.getText());
        double w_dnmr = Double.parseDouble(depletionOfNonrenewableMaterialResourcesWeightTextField.getText());
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
        EnvAnalysisCalc.setwCnhw(w_cnhw);
        EnvAnalysisCalc.setwTwc(w_twc);
        EnvAnalysisCalc.setwRpeu(w_rpeu);
        EnvAnalysisCalc.setwDner(w_dner);
        EnvAnalysisCalc.setwRmru(w_rmru);
        EnvAnalysisCalc.setwDnmr(w_dnmr);

        System.out.println("Set up weights in the static method EnvAnalysis_Calc");
    }

}
