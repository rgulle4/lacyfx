package cm;

import static cm.EnvAnalysisCalc.*;

/**
 * Created by Administrator on 2016/10/3.
 */
public class EnvPerformanceCalc {
    double W_ENP = EnvAnalysisCalc.getwEnp();
    double ConvF = EnvAnalysisCalc.getConFc();

    double W_GWP = EnvAnalysisCalc.getwGwp();
    double W_ODP = EnvAnalysisCalc.getwOdp();
    double W_AP = EnvAnalysisCalc.getwAp();
    double W_EP = EnvAnalysisCalc.getwEp();
    double W_POCP = EnvAnalysisCalc.getwPocp();
    double W_TWC = EnvAnalysisCalc.getwTwc();
    double W_DNER = EnvAnalysisCalc.getwDner();

    double GWP_EDP = EnvAnalysisCalc.getGwp();
    double ODP_EDP = EnvAnalysisCalc.getOdp();
    double AP_EDP = EnvAnalysisCalc.getAp();
    double EP_EDP = EnvAnalysisCalc.getEp();
    double POCP_EDP = EnvAnalysisCalc.getPocp();
    double TWC_EDP = EnvAnalysisCalc.getTwc();
    double DNER_EDP = EnvAnalysisCalc.getDner();

    double GWP_TSP = EnvAnalysisCalc.getSub_GWP()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;
    double ODP_TSP = EnvAnalysisCalc.getSub_ODP()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;
    double AP_TSP = EnvAnalysisCalc.getSub_AP()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;
    double EP_TSP = EnvAnalysisCalc.getSub_EP()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;
    double POCP_TSP = EnvAnalysisCalc.getSub_POCP()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;
    double TWC_TSP = EnvAnalysisCalc.getSub_TW()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;
    double DNER_TSP = EnvAnalysisCalc.getSub_DNER()* EnvAnalysisCalc.getFCF()* EnvAnalysisCalc.getDistance()*2;



    double Es= ConvF*W_ENP*((W_GWP*(GWP_EDP+GWP_TSP)/Norm_GWP
                        +W_ODP*(ODP_EDP+ODP_TSP)/Norm_ODP
                        +W_AP*(AP_EDP+AP_TSP)/Norm_AP
                        +W_EP*(EP_EDP+EP_TSP)/Norm_EP
                        +W_POCP*(POCP_EDP+POCP_TSP)/Norm_POCP
                        +W_TWC*(TWC_EDP+TWC_TSP)/Norm_TW
                        +W_DNER*(DNER_EDP+DNER_TSP)/Norm_DNER)
                        );

    public void cal(){
        EnvAnalysisCalc.setEnp_Score(Es);
    }
}
