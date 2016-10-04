package cm;

import static cm.EnvAnalysis_cal.*;

/**
 * Created by Administrator on 2016/10/3.
 */
public class Cal_Enp {
    double W_ENP = EnvAnalysis_cal.getwEnp();

    double W_GWP = EnvAnalysis_cal.getwGwp();
    double W_ODP = EnvAnalysis_cal.getwOdp();
    double W_AP = EnvAnalysis_cal.getwAp();
    double W_EP = EnvAnalysis_cal.getwEp();
    double W_POCP = EnvAnalysis_cal.getwPocp();
    double W_TWC = EnvAnalysis_cal.getwTwc();
    double W_DNER = EnvAnalysis_cal.getwDner();

    double GWP_EDP = EnvAnalysis_cal.getGwp();
    double ODP_EDP = EnvAnalysis_cal.getOdp();
    double AP_EDP = EnvAnalysis_cal.getAp();
    double EP_EDP = EnvAnalysis_cal.getSub_EP();
    double POCP_EDP = EnvAnalysis_cal.getPocp();
    double TWC_EDP = EnvAnalysis_cal.getTwc();
    double DNER_EDP = EnvAnalysis_cal.getDner();

    double GWP_TSP = EnvAnalysis_cal.getGwp();
    double ODP_TSP = EnvAnalysis_cal.getOdp();
    double AP_TSP = EnvAnalysis_cal.getAp();
    double EP_TSP = EnvAnalysis_cal.getSub_EP();
    double POCP_TSP = EnvAnalysis_cal.getPocp();
    double TWC_TSP = EnvAnalysis_cal.getTwc();
    double DNER_TSP = EnvAnalysis_cal.getDner();



    double Es= W_ENP*((W_GWP*(GWP_EDP+GWP_TSP)/Norm_GWP
                        +W_ODP*(ODP_EDP+ODP_TSP)/Norm_ODP
                        +W_AP*(AP_EDP+AP_TSP)/Norm_AP
                        +W_EP*(EP_EDP+EP_TSP)/Norm_EP
                        +W_POCP*(POCP_EDP+POCP_TSP)/Norm_POCP
                        +W_TWC*(TWC_EDP+TWC_TSP)/Norm_TW
                        +W_DNER*(DNER_EDP+DNER_TSP)/Norm_DNER)
                        );

    public void cal(){
        EnvAnalysis_cal.setEnp_Score(Es);
    }
}
