package cm;

import static cm.EnvAnalysisCal.*;

/**
 * Created by Administrator on 2016/10/3.
 */
public class CalEnp {
    double W_ENP = EnvAnalysisCal.getwEnp();
    double ConvF = EnvAnalysisCal.getConFc();

    double W_GWP = EnvAnalysisCal.getwGwp();
    double W_ODP = EnvAnalysisCal.getwOdp();
    double W_AP = EnvAnalysisCal.getwAp();
    double W_EP = EnvAnalysisCal.getwEp();
    double W_POCP = EnvAnalysisCal.getwPocp();
    double W_TWC = EnvAnalysisCal.getwTwc();
    double W_DNER = EnvAnalysisCal.getwDner();

    double GWP_EDP = EnvAnalysisCal.getGwp();
    double ODP_EDP = EnvAnalysisCal.getOdp();
    double AP_EDP = EnvAnalysisCal.getAp();
    double EP_EDP = EnvAnalysisCal.getEp();
    double POCP_EDP = EnvAnalysisCal.getPocp();
    double TWC_EDP = EnvAnalysisCal.getTwc();
    double DNER_EDP = EnvAnalysisCal.getDner();

    double GWP_TSP = EnvAnalysisCal.getSub_GWP()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;
    double ODP_TSP = EnvAnalysisCal.getSub_ODP()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;
    double AP_TSP = EnvAnalysisCal.getSub_AP()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;
    double EP_TSP = EnvAnalysisCal.getSub_EP()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;
    double POCP_TSP = EnvAnalysisCal.getSub_POCP()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;
    double TWC_TSP = EnvAnalysisCal.getSub_TW()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;
    double DNER_TSP = EnvAnalysisCal.getSub_DNER()* EnvAnalysisCal.getFCF()* EnvAnalysisCal.getDistance()*2;



    double Es= ConvF*W_ENP*((W_GWP*(GWP_EDP+GWP_TSP)/Norm_GWP
                        +W_ODP*(ODP_EDP+ODP_TSP)/Norm_ODP
                        +W_AP*(AP_EDP+AP_TSP)/Norm_AP
                        +W_EP*(EP_EDP+EP_TSP)/Norm_EP
                        +W_POCP*(POCP_EDP+POCP_TSP)/Norm_POCP
                        +W_TWC*(TWC_EDP+TWC_TSP)/Norm_TW
                        +W_DNER*(DNER_EDP+DNER_TSP)/Norm_DNER)
                        );

    public void cal(){
        EnvAnalysisCal.setEnp_Score(Es);
    }
}
