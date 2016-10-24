package cm.models;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import static cm.models.Model.*;

/**
 * Created by Administrator on 2016/10/1.
 * This is meant for a single layer_temp. (Deprecated).
 */
public class EnvAnalysisCalc {
    static double length = 1609.34;        //1 mile = 1609.34 meter
    static double width = 12*0.3048;       //1 ft = 0.3048 meter
    static double thickness;

    static double TotV;        //Total value
    static double ConFac;      //Conversion factor

    //factor value
    static double Enp;
    static double Ecp;
    static double Gwp;
    static double Odp;
    static double Ap;
    static double Ep;
    static double Pocp;
    static double Chw;
    static double Cnhw;
    static double Twc;
    static double Tpeu;
    static double Rpeu;
    static double Dner;
    static double Rmru;
    static double Dnmr;

    //Weights
    static double wEnp;
    static double wEcp;
    static double wGwp;
    static double wOdp;
    static double wAp;
    static double wEp;
    static double wPocp;
    static double wChw;
    static double wCnhw;
    static double wTwc;
    static double wRpeu;
    static double wTpec;
    static double wRmru;
    static double wDnmr;

    //variables for transportation part

    static double distance;
    static double FCF;      //Fuel Consumption Factor
    static double sub_GWP;
    static double sub_ODP;
    static double sub_AP;
    static double sub_EP;
    static double sub_POCP;
    static double sub_TW;
    static double sub_TPEC;

    // Normalization value
    static double Norm_GWP =24000;      //KgCO2eq
    static double Norm_ODP =0.16;       //KgCFC-11eq
    static double Norm_AP =91;          //KgSO2eq
    static double Norm_EP =22;          //KgN eq
    static double Norm_POCP =1400;      //Kg O3 eq
    static double Norm_TW =529.9587;       //MJ surplus
    static double Norm_TPEC =17000;//Meter^3

    //Environmental Contribution from EPD
    static double GWP_EDP_Ctb;
    static double ODP_EDP_Ctb;
    static double AP_EDP_Ctb;
    static double EP_EDP_Ctb;
    static double POCP_EDP_Ctb;
    static double TotalWater_EDP_Ctb;
    static double TotalPrimaryEnergyConsumption_EDP_Ctb;
    //Environmental Contribution from transportation
    static double GWP_Transportation_Ctb;
    static double ODP_Transportation_Ctb;
    static double AP_Transportation_Ctb;
    static double EP_Transportation_Ctb;
    static double POCP_Transportation_Ctb;
    static double TotalWater_Transportation_Ctb;
    static double TotalPrimaryEnergyConsumption_Transportation_Ctb;

    // EPD Score of Environmental Performance
    static double envPerf_EPDScore;
    // Transportation Score of Environmental Performance
    static double envPerf_TransportationScore;
    //EnP Score
    static double Enp_Score;



    public static double getThickness() {
        return thickness;
    }

    public static void setThickness(double tc) {
        thickness = tc;
    }

    public static void setTotV(double T) {
        TotV = length*T*width;
    }

    public static double getTotV(){
        return TotV;
    }

    public static void setConFc(double cvf){
        ConFac = cvf;
    }

    public static double getConFc(){
        return ConFac;
    }
    public static double EnpScore(){
        return 2*TotV;
    }

    public static double getEnp() {
        return Enp;
    }

    public static void setEnp(double enp) {
        Enp = enp;
    }

    public static double getEcp() {
        return Ecp;
    }

    public static void setEcp(double ecp) {
        Ecp = ecp;
    }

    public static double getGwp() {
        return Gwp;
    }

    public static void setGwp(double gwp) {
        Gwp = gwp;
    }

    public static double getOdp() {
        return Odp;
    }

    public static void setOdp(double odp) {
        Odp = odp;
    }

    public static double getAp() {
        return Ap;
    }

    public static void setAp(double ap) {
        Ap = ap;
    }

    public static double getEp() {
        return Ep;
    }

    public static void setEp(double ep) {
        Ep = ep;
    }

    public static double getPocp() {
        return Pocp;
    }

    public static void setPocp(double pocp) {
        Pocp = pocp;
    }

    public static double getChw() {
        return Chw;
    }

    public static void setChw(double chw) {
        Chw = chw;
    }

    public static double getCnhw() {
        return Cnhw;
    }

    public static void setCnhw(double cnhw) {
        Cnhw = cnhw;
    }

    public static double getTwc() {
        return Twc;
    }

    public static void setTwc(double twc) {
        Twc = twc;
    }

    public static double getTpeu() {
        return Tpeu;
    }

    public static void setTpeu(String tpeu) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = format.parse(tpeu);
        Tpeu = number.doubleValue();
    }

    public static double getRpeu() {
        return Rpeu;
    }

    public static void setRpeu(double rpeu) {
        Rpeu = rpeu;
    }

    public static double getDner() {
        return Dner;
    }

    public static void setDner(double dner) {
        Dner = dner;
    }

    public static double getRmru() {
        return Rmru;
    }

    public static void setRmru(double rmru) {
        Rmru = rmru;
    }

    public static double getDnmr() {
        return Dnmr;
    }

    public static void setDnmr(double dnmr) {
        Dnmr = dnmr;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        EnvAnalysisCalc.width = width;
    }

    public static double getwEnp() {
        return wEnp;
    }

    public static void setwEnp(double wEnp) {
        EnvAnalysisCalc.wEnp = wEnp;
    }

    public static double getwEcp() {
        return wEcp;
    }

    public static void setwEcp(double wEcp) {
        EnvAnalysisCalc.wEcp = wEcp;
    }

    public static double getwGwp() {
        return wGwp;
    }

    public static void setwGwp(double wGwp) {
        EnvAnalysisCalc.wGwp = wGwp;
    }

    public static double getwOdp() {
        return wOdp;
    }

    public static void setwOdp(double wOdp) {
        EnvAnalysisCalc.wOdp = wOdp;
    }

    public static double getwAp() {
        return wAp;
    }

    public static void setwAp(double wAp) {
        EnvAnalysisCalc.wAp = wAp;
    }

    public static double getwEp() {
        return wEp;
    }

    public static void setwEp(double wEp) {
        EnvAnalysisCalc.wEp = wEp;
    }

    public static double getwPocp() {
        return wPocp;
    }

    public static void setwPocp(double wPocp) {
        EnvAnalysisCalc.wPocp = wPocp;
    }

    public static double getwChw() {
        return wChw;
    }

    public static void setwChw(double wChw) {
        EnvAnalysisCalc.wChw = wChw;
    }

    public static double getwCnhw() {
        return wCnhw;
    }

    public static void setwCnhw(double wCnhw) {
        EnvAnalysisCalc.wCnhw = wCnhw;
    }

    public static double getwTwc() {
        return wTwc;
    }

    public static void setwTwc(double wTwc) {
        EnvAnalysisCalc.wTwc = wTwc;
    }

    public static double getwRpeu() {
        return wRpeu;
    }

    public static void setwRpeu(double wRpeu) {
        EnvAnalysisCalc.wRpeu = wRpeu;
    }

    public static double getwTpec() {
        return wTpec;
    }

    public static void setwTpec(double wTpec) {
        EnvAnalysisCalc.wTpec = wTpec;
    }

    public static double getwRmru() {
        return wRmru;
    }

    public static void setwRmru(double wRmru) {
        EnvAnalysisCalc.wRmru = wRmru;
    }

    public static double getwDnmr() {
        return wDnmr;
    }

    public static void setwDnmr(double wDnmr) {
        EnvAnalysisCalc.wDnmr = wDnmr;
    }

    public static double getDistance() {
        return distance;
    }

    public static void setDistance(double distance) {
        EnvAnalysisCalc.distance = distance;
    }

    public static double getFCF() {
        return FCF;
    }

    public static void setFCF(double FCF) {
        EnvAnalysisCalc.FCF = FCF;
    }

    public static double getSub_GWP() {
        return sub_GWP;
    }

    public static void setSub_GWP(double sub_GWP) {
        EnvAnalysisCalc.sub_GWP = sub_GWP;
    }

    public static double getSub_ODP() {
        return sub_ODP;
    }

    public static void setSub_ODP(double sub_ODP) {
        EnvAnalysisCalc.sub_ODP = sub_ODP;
    }

    public static double getSub_AP() {
        return sub_AP;
    }

    public static void setSub_AP(double sub_AP) {
        EnvAnalysisCalc.sub_AP = sub_AP;
    }

    public static double getSub_EP() {
        return sub_EP;
    }

    public static void setSub_EP(double sub_EP) {
        EnvAnalysisCalc.sub_EP = sub_EP;
    }

    public static double getSub_POCP() {
        return sub_POCP;
    }

    public static void setSub_POCP(double sub_POCP) {
        EnvAnalysisCalc.sub_POCP = sub_POCP;
    }

    public static double getSub_TW() {
        return sub_TW;
    }

    public static void setSub_TW(double sub_TW) {
        EnvAnalysisCalc.sub_TW = sub_TW;
    }

    public static double getSub_TPEC() {
        return sub_TPEC;
    }

    public static void setSub_TPEC(double sub_TPEC) {
        EnvAnalysisCalc.sub_TPEC = sub_TPEC;
    }

    public static double getEnp_Score() {
        return Enp_Score;
    }

    public static void setEnp_Score(double enp_Score) {
        Enp_Score = enp_Score;
    }

    public static double getGWP_EDP_Ctb() {
        return GWP_EDP_Ctb;
    }

    public static void setGWP_EDP_Ctb(double GWP_EDP_Ctb) {
        EnvAnalysisCalc.GWP_EDP_Ctb = GWP_EDP_Ctb;
    }

    public static double getODP_EDP_Ctb() {
        return ODP_EDP_Ctb;
    }

    public static void setODP_EDP_Ctb(double ODP_EDP_Ctb) {
        EnvAnalysisCalc.ODP_EDP_Ctb = ODP_EDP_Ctb;
    }

    public static double getAP_EDP_Ctb() {
        return AP_EDP_Ctb;
    }

    public static void setAP_EDP_Ctb(double AP_EDP_Ctb) {
        EnvAnalysisCalc.AP_EDP_Ctb = AP_EDP_Ctb;
    }

    public static double getEP_EDP_Ctb() {
        return EP_EDP_Ctb;
    }

    public static void setEP_EDP_Ctb(double EP_EDP_Ctb) {
        EnvAnalysisCalc.EP_EDP_Ctb = EP_EDP_Ctb;
    }

    public static double getPOCP_EDP_Ctb() {
        return POCP_EDP_Ctb;
    }

    public static void setPOCP_EDP_Ctb(double POCP_EDP_Ctb) {
        EnvAnalysisCalc.POCP_EDP_Ctb = POCP_EDP_Ctb;
    }

    public static double getTotalWater_EDP_Ctb() {
        return TotalWater_EDP_Ctb;
    }

    public static void setTotalWater_EDP_Ctb(double totalWater_EDP_Ctb) {
        TotalWater_EDP_Ctb = totalWater_EDP_Ctb;
    }

    public static double getTotalPrimaryEnergyConsumption_EDP_Ctb() {
        return TotalPrimaryEnergyConsumption_EDP_Ctb;
    }

    public static void setTotalPrimaryEnergyConsumption_EDP_Ctb(double totalPrimaryEnergyConsumption_EDP_Ctb) {
        TotalPrimaryEnergyConsumption_EDP_Ctb = totalPrimaryEnergyConsumption_EDP_Ctb;
    }

    public static double getGWP_Transportation_Ctb() {
        return GWP_Transportation_Ctb;
    }

    public static void setGWP_Transportation_Ctb(double GWP_Transportation_Ctb) {
        EnvAnalysisCalc.GWP_Transportation_Ctb = GWP_Transportation_Ctb;
    }

    public static double getODP_Transportation_Ctb() {
        return ODP_Transportation_Ctb;
    }

    public static void setODP_Transportation_Ctb(double ODP_Transportation_Ctb) {
        EnvAnalysisCalc.ODP_Transportation_Ctb = ODP_Transportation_Ctb;
    }

    public static double getAP_Transportation_Ctb() {
        return AP_Transportation_Ctb;
    }

    public static void setAP_Transportation_Ctb(double AP_Transportation_Ctb) {
        EnvAnalysisCalc.AP_Transportation_Ctb = AP_Transportation_Ctb;
    }

    public static double getEP_Transportation_Ctb() {
        return EP_Transportation_Ctb;
    }

    public static void setEP_Transportation_Ctb(double EP_Transportation_Ctb) {
        EnvAnalysisCalc.EP_Transportation_Ctb = EP_Transportation_Ctb;
    }

    public static double getPOCP_Transportation_Ctb() {
        return POCP_Transportation_Ctb;
    }

    public static void setPOCP_Transportation_Ctb(double POCP_Transportation_Ctb) {
        EnvAnalysisCalc.POCP_Transportation_Ctb = POCP_Transportation_Ctb;
    }

    public static double getTotalWater_Transportation_Ctb() {
        return TotalWater_Transportation_Ctb;
    }

    public static void setTotalWater_Transportation_Ctb(double totalWater_Transportation_Ctb) {
        TotalWater_Transportation_Ctb = totalWater_Transportation_Ctb;
    }

    public static double getTotalPrimaryEnergyConsumption_Transportation_Ctb() {
        return TotalPrimaryEnergyConsumption_Transportation_Ctb;
    }

    public static void setTotalPrimaryEnergyConsumption_Transportation_Ctb(double totalPrimaryEnergyConsumption_Transportation_Ctb) {
        TotalPrimaryEnergyConsumption_Transportation_Ctb = totalPrimaryEnergyConsumption_Transportation_Ctb;
    }

    public static double getEnvPerf_EPDScore() {
        return envPerf_EPDScore;
    }

//    public static void setEnvPerf_EPDScore(double GWP_EDP_Ctb, double ODP_EDP_Ctb, double AP_EDP_Ctb, double EP_EDP_Ctb, double POCP_EDP_Ctb, double totalWater_EDP_Ctb, double nonRenewableEnergy_EDP_Ctb) {
//        EnvAnalysisCalc.envPerf_EPDScore = GWP_EDP_Ctb+ ODP_EDP_Ctb+ AP_EDP_Ctb+ EP_EDP_Ctb+ POCP_EDP_Ctb+ totalWater_EDP_Ctb+ nonRenewableEnergy_EDP_Ctb;
//    }

    public void setEnvPerf_EPDScore() {
        EnvAnalysisCalc.envPerf_EPDScore = this.GWP_EDP_Ctb+ this.ODP_EDP_Ctb+ this.AP_EDP_Ctb+ this.EP_EDP_Ctb+ this.POCP_EDP_Ctb+ this.TotalWater_EDP_Ctb+ this.TotalPrimaryEnergyConsumption_EDP_Ctb;
  }

    public static double getEnvPerf_TransportationScore() {
        return envPerf_TransportationScore;
    }

    public void setEnvPerf_TransportationScore() {
        EnvAnalysisCalc.envPerf_TransportationScore = this.GWP_Transportation_Ctb+ this.ODP_Transportation_Ctb+ this.AP_Transportation_Ctb+ this.EP_Transportation_Ctb+ this.POCP_Transportation_Ctb+ this.TotalWater_Transportation_Ctb+ this.TotalPrimaryEnergyConsumption_Transportation_Ctb;
    }
}
