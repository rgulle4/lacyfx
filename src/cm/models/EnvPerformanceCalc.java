package cm.models;

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

    //distribute each factor's contribution from EPD
    double GWP_EDP_Ctb=ConvF*W_ENP*W_GWP*GWP_EDP/ EnvAnalysisCalc.Norm_GWP;
    double ODP_EDP_Ctb=ConvF*W_ENP*W_ODP*ODP_EDP/ EnvAnalysisCalc.Norm_ODP;
    double AP_EDP_Ctb=ConvF*W_ENP*W_AP*AP_EDP/ EnvAnalysisCalc.Norm_AP;
    double EP_EDP_Ctb=ConvF*W_ENP*W_EP*EP_EDP/ EnvAnalysisCalc.Norm_EP;
    double POCP_EDP_Ctb=ConvF*W_ENP*W_POCP*POCP_EDP/ EnvAnalysisCalc.Norm_POCP;
    double TotalWater_EDP_Ctb=ConvF*W_ENP*W_TWC*TWC_EDP/ EnvAnalysisCalc.Norm_TW;
    double NonRenewableEnergy_EDP_Ctb=ConvF*W_ENP*W_DNER*DNER_EDP/ EnvAnalysisCalc.Norm_DNER;

    //distribute each factor's contribution from Transportation
    double GWP_Transportation_Ctb=W_ENP*W_GWP*GWP_TSP/ EnvAnalysisCalc.Norm_GWP;
    double ODP_Transportation_Ctb=W_ENP*W_ODP*ODP_TSP/ EnvAnalysisCalc.Norm_ODP;
    double AP_Transportation_Ctb=W_ENP*W_AP*AP_TSP/ EnvAnalysisCalc.Norm_AP;
    double EP_Transportation_Ctb=W_ENP*W_EP*EP_TSP/ EnvAnalysisCalc.Norm_EP;
    double POCP_Transportation_Ctb=W_ENP*W_POCP*POCP_TSP/ EnvAnalysisCalc.Norm_POCP;
    double TotalWater_Transportation_Ctb=W_ENP*W_TWC*TWC_TSP/ EnvAnalysisCalc.Norm_TW;
    double NonRenewableEnergy_Transportation_Ctb=W_ENP*W_DNER*DNER_TSP/ EnvAnalysisCalc.Norm_DNER;

    double Es= GWP_EDP_Ctb+ODP_EDP_Ctb+AP_EDP_Ctb+EP_EDP_Ctb+POCP_EDP_Ctb+TotalWater_EDP_Ctb+NonRenewableEnergy_EDP_Ctb
            +GWP_Transportation_Ctb+ODP_Transportation_Ctb+AP_Transportation_Ctb+EP_Transportation_Ctb+POCP_Transportation_Ctb
            +TotalWater_Transportation_Ctb+NonRenewableEnergy_Transportation_Ctb;

    public void cal(){
        // save contributed values
        EnvAnalysisCalc.setGWP_EDP_Ctb(GWP_EDP_Ctb);
        EnvAnalysisCalc.setODP_EDP_Ctb(ODP_EDP_Ctb);
        EnvAnalysisCalc.setAP_EDP_Ctb(AP_EDP_Ctb);
        EnvAnalysisCalc.setEP_EDP_Ctb(EP_EDP_Ctb);
        EnvAnalysisCalc.setPOCP_EDP_Ctb(POCP_EDP_Ctb);
        EnvAnalysisCalc.setTotalWater_EDP_Ctb(TotalWater_EDP_Ctb);
        EnvAnalysisCalc.setNonRenewableEnergy_EDP_Ctb(NonRenewableEnergy_EDP_Ctb);

        EnvAnalysisCalc.setGWP_Transportation_Ctb(GWP_Transportation_Ctb);
        EnvAnalysisCalc.setODP_Transportation_Ctb(ODP_Transportation_Ctb);
        EnvAnalysisCalc.setAP_Transportation_Ctb(AP_Transportation_Ctb);
        EnvAnalysisCalc.setEP_Transportation_Ctb(EP_Transportation_Ctb);
        EnvAnalysisCalc.setPOCP_Transportation_Ctb(POCP_Transportation_Ctb);
        EnvAnalysisCalc.setTotalWater_Transportation_Ctb(TotalWater_Transportation_Ctb);
        EnvAnalysisCalc.setNonRenewableEnergy_Transportation_Ctb(NonRenewableEnergy_Transportation_Ctb);

        //save calculated Environmental Performance Score
        EnvAnalysisCalc.setEnp_Score(Es);
    }
}
