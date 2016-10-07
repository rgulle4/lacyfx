package cm.models;

import cm.controllers.EnvironmentalReportController;

/**
 * Created by Administrator on 2016/10/3.
 */
public class Cal_Enp {
    double W_ENP = EnvAnalysis_cal.getwEnp();
    double ConvF = EnvAnalysis_cal.getConFc();

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
    double EP_EDP = EnvAnalysis_cal.getEp();
    double POCP_EDP = EnvAnalysis_cal.getPocp();
    double TWC_EDP = EnvAnalysis_cal.getTwc();
    double DNER_EDP = EnvAnalysis_cal.getDner();

    double GWP_TSP = EnvAnalysis_cal.getSub_GWP()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;
    double ODP_TSP = EnvAnalysis_cal.getSub_ODP()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;
    double AP_TSP = EnvAnalysis_cal.getSub_AP()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;
    double EP_TSP = EnvAnalysis_cal.getSub_EP()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;
    double POCP_TSP = EnvAnalysis_cal.getSub_POCP()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;
    double TWC_TSP = EnvAnalysis_cal.getSub_TW()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;
    double DNER_TSP = EnvAnalysis_cal.getSub_DNER()*EnvAnalysis_cal.getFCF()*EnvAnalysis_cal.getDistance()*2;

    //distribute each factor's contribution from EPD
    double GWP_EDP_Ctb=ConvF*W_ENP*W_GWP*GWP_EDP/ EnvAnalysis_cal.Norm_GWP;
    double ODP_EDP_Ctb=ConvF*W_ENP*W_ODP*ODP_EDP/ EnvAnalysis_cal.Norm_ODP;
    double AP_EDP_Ctb=ConvF*W_ENP*W_AP*AP_EDP/ EnvAnalysis_cal.Norm_AP;
    double EP_EDP_Ctb=ConvF*W_ENP*W_EP*EP_EDP/ EnvAnalysis_cal.Norm_EP;
    double POCP_EDP_Ctb=ConvF*W_ENP*W_POCP*POCP_EDP/ EnvAnalysis_cal.Norm_POCP;
    double TotalWater_EDP_Ctb=ConvF*W_ENP*W_TWC*TWC_EDP/ EnvAnalysis_cal.Norm_TW;
    double NonRenewableEnergy_EDP_Ctb=ConvF*W_ENP*W_DNER*DNER_EDP/ EnvAnalysis_cal.Norm_DNER;

    //distribute each factor's contribution from Transportation
    double GWP_Transportation_Ctb=W_ENP*W_GWP*GWP_TSP/ EnvAnalysis_cal.Norm_GWP;
    double ODP_Transportation_Ctb=W_ENP*W_ODP*ODP_TSP/ EnvAnalysis_cal.Norm_ODP;
    double AP_Transportation_Ctb=W_ENP*W_AP*AP_TSP/ EnvAnalysis_cal.Norm_AP;
    double EP_Transportation_Ctb=W_ENP*W_EP*EP_TSP/ EnvAnalysis_cal.Norm_EP;
    double POCP_Transportation_Ctb=W_ENP*W_POCP*POCP_TSP/ EnvAnalysis_cal.Norm_POCP;
    double TotalWater_Transportation_Ctb=W_ENP*W_TWC*TWC_TSP/ EnvAnalysis_cal.Norm_TW;
    double NonRenewableEnergy_Transportation_Ctb=W_ENP*W_DNER*DNER_TSP/ EnvAnalysis_cal.Norm_DNER;

    double Es= GWP_EDP_Ctb+ODP_EDP_Ctb+AP_EDP_Ctb+EP_EDP_Ctb+POCP_EDP_Ctb+TotalWater_EDP_Ctb+NonRenewableEnergy_EDP_Ctb
            +GWP_Transportation_Ctb+ODP_Transportation_Ctb+AP_Transportation_Ctb+EP_Transportation_Ctb+POCP_Transportation_Ctb
            +TotalWater_Transportation_Ctb+NonRenewableEnergy_Transportation_Ctb;

    public void cal(){
        // save contributed values
        EnvAnalysis_cal.setGWP_EDP_Ctb(GWP_EDP_Ctb);
        EnvAnalysis_cal.setODP_EDP_Ctb(ODP_EDP_Ctb);
        EnvAnalysis_cal.setAP_EDP_Ctb(AP_EDP_Ctb);
        EnvAnalysis_cal.setEP_EDP_Ctb(EP_EDP_Ctb);
        EnvAnalysis_cal.setPOCP_EDP_Ctb(POCP_EDP_Ctb);
        EnvAnalysis_cal.setTotalWater_EDP_Ctb(TotalWater_EDP_Ctb);
        EnvAnalysis_cal.setNonRenewableEnergy_EDP_Ctb(NonRenewableEnergy_EDP_Ctb);

        EnvAnalysis_cal.setGWP_Transportation_Ctb(GWP_Transportation_Ctb);
        EnvAnalysis_cal.setODP_Transportation_Ctb(ODP_Transportation_Ctb);
        EnvAnalysis_cal.setAP_Transportation_Ctb(AP_Transportation_Ctb);
        EnvAnalysis_cal.setEP_Transportation_Ctb(EP_Transportation_Ctb);
        EnvAnalysis_cal.setPOCP_Transportation_Ctb(POCP_Transportation_Ctb);
        EnvAnalysis_cal.setTotalWater_Transportation_Ctb(TotalWater_Transportation_Ctb);
        EnvAnalysis_cal.setNonRenewableEnergy_Transportation_Ctb(NonRenewableEnergy_Transportation_Ctb);

        //save calculated Environmental Performance Score
        EnvAnalysis_cal.setEnp_Score(Es);
    }
}
