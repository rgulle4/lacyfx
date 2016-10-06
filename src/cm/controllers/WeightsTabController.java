package cm.controllers;

import cm.EnvAnalysisCal;
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
        EnvAnalysisCal.setwEnp(w_enp);
        EnvAnalysisCal.setwEcp(w_ecp);
        EnvAnalysisCal.setwGwp(w_gwp);
        EnvAnalysisCal.setwOdp(w_odp);
        EnvAnalysisCal.setwAp(w_ap);
        EnvAnalysisCal.setwEp(w_ep);
        EnvAnalysisCal.setwPocp(w_pocp);
        EnvAnalysisCal.setwChw(w_chw);
        EnvAnalysisCal.setwCnhw(w_cnhw);
        EnvAnalysisCal.setwTwc(w_twc);
        EnvAnalysisCal.setwRpeu(w_rpeu);
        EnvAnalysisCal.setwDner(w_dner);
        EnvAnalysisCal.setwRmru(w_rmru);
        EnvAnalysisCal.setwDnmr(w_dnmr);
    }

}
