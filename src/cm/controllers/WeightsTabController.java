package cm.controllers;

import cm.EnvAnalysis_cal;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the Weights tab.
 */
public class WeightsTabController {

    @FXML public TextField T_EnP;
    @FXML public TextField T_EcP;
    @FXML public TextField T_GWP;
    @FXML public TextField T_ODP;
    @FXML public TextField T_AP;
    @FXML public TextField T_EP;
    @FXML public TextField T_POCP;
    @FXML public TextField T_CHW;
    @FXML public TextField T_CNHW;
    @FXML public TextField T_TWC;
    @FXML public TextField T_RPEU;
    @FXML public TextField T_DNER;
    @FXML public TextField T_RMRU;
    @FXML public TextField T_DNMR;

    // initialization
    @FXML
    private void initialize(){
        T_EnP.setText("50.0");
        T_EcP.setText("50.0");
        T_GWP.setText("15.0");
        T_ODP.setText("10.0");
        T_AP.setText("10.0");
        T_EP.setText("10.0");
        T_POCP.setText("10.0");
        T_CHW.setText("10.0");
        T_CNHW.setText("10.0");
        T_TWC.setText("10.0");
        T_RPEU.setText("10.0");
        T_DNER.setText("10.0");
        T_RMRU.setText("10.0");
        T_DNMR.setText("10.0");

    }

    @FXML
    public void nextButton(){
        double w_enp = Double.parseDouble(T_EnP.getText());
        double w_ecp = Double.parseDouble(T_EcP.getText());
        double w_gwp = Double.parseDouble(T_GWP.getText());
        double w_odp = Double.parseDouble(T_ODP.getText());
        double w_ap = Double.parseDouble(T_AP.getText());
        double w_ep = Double.parseDouble(T_EP.getText());
        double w_pocp = Double.parseDouble(T_POCP.getText());
        double w_chw = Double.parseDouble(T_CHW.getText());
        double w_cnhw = Double.parseDouble(T_CNHW.getText());
        double w_twc = Double.parseDouble(T_TWC.getText());
        double w_rpeu = Double.parseDouble(T_RPEU.getText());
        double w_dner = Double.parseDouble(T_DNER.getText());
        double w_rmru = Double.parseDouble(T_RMRU.getText());
        double w_dnmr = Double.parseDouble(T_DNMR.getText());
        // TO DO
        // A warning to information everyTextfield should be valued and there is a default value for everyTextfield
        //Weights W = new Weights(w_enp,w_ecp,w_gwp,w_odp,w_ap,w_ep,w_pocp,w_chw,w_cnhw,w_twc,w_ap,w_dner,w_rmru,w_dnmr);

        /*
        Set and store Weights
         */
        EnvAnalysis_cal.setwEnp(w_enp);
        EnvAnalysis_cal.setwEcp(w_ecp);
        EnvAnalysis_cal.setwGwp(w_gwp);
        EnvAnalysis_cal.setwOdp(w_odp);
        EnvAnalysis_cal.setwAp(w_ap);
        EnvAnalysis_cal.setwEp(w_ep);
        EnvAnalysis_cal.setwPocp(w_pocp);
        EnvAnalysis_cal.setwChw(w_chw);
        EnvAnalysis_cal.setwCnhw(w_cnhw);
        EnvAnalysis_cal.setwTwc(w_twc);
        EnvAnalysis_cal.setwRpeu(w_rpeu);
        EnvAnalysis_cal.setwDner(w_dner);
        EnvAnalysis_cal.setwRmru(w_rmru);
        EnvAnalysis_cal.setwDnmr(w_dnmr);
    }

}
