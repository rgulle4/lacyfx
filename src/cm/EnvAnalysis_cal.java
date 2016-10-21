package cm;


/**
 * Created by Administrator on 2016/10/1.
 */
public class EnvAnalysis_cal {
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
    static double Rpeu;
    static double Dner;
    static double Rmru;
    static double Dnmr;

    //weights
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
    static double wDner;
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
    static double sub_DNER;

    // Normalization value
    static double Norm_GWP =24000;      //KgCO2eq
    static double Norm_ODP =0.16;       //KgCFC-11eq
    static double Norm_AP =91;          //KgSO2eq
    static double Norm_EP =22;          //KgN eq
    static double Norm_POCP =1400;      //Kg O3 eq
    static double Norm_TW =17000;       //MJ surplus
    static double Norm_DNER =1934.34543;//Meter^3

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
        EnvAnalysis_cal.width = width;
    }

    public static double getwEnp() {
        return wEnp;
    }

    public static void setwEnp(double wEnp) {
        EnvAnalysis_cal.wEnp = wEnp;
    }

    public static double getwEcp() {
        return wEcp;
    }

    public static void setwEcp(double wEcp) {
        EnvAnalysis_cal.wEcp = wEcp;
    }

    public static double getwGwp() {
        return wGwp;
    }

    public static void setwGwp(double wGwp) {
        EnvAnalysis_cal.wGwp = wGwp;
    }

    public static double getwOdp() {
        return wOdp;
    }

    public static void setwOdp(double wOdp) {
        EnvAnalysis_cal.wOdp = wOdp;
    }

    public static double getwAp() {
        return wAp;
    }

    public static void setwAp(double wAp) {
        EnvAnalysis_cal.wAp = wAp;
    }

    public static double getwEp() {
        return wEp;
    }

    public static void setwEp(double wEp) {
        EnvAnalysis_cal.wEp = wEp;
    }

    public static double getwPocp() {
        return wPocp;
    }

    public static void setwPocp(double wPocp) {
        EnvAnalysis_cal.wPocp = wPocp;
    }

    public static double getwChw() {
        return wChw;
    }

    public static void setwChw(double wChw) {
        EnvAnalysis_cal.wChw = wChw;
    }

    public static double getwCnhw() {
        return wCnhw;
    }

    public static void setwCnhw(double wCnhw) {
        EnvAnalysis_cal.wCnhw = wCnhw;
    }

    public static double getwTwc() {
        return wTwc;
    }

    public static void setwTwc(double wTwc) {
        EnvAnalysis_cal.wTwc = wTwc;
    }

    public static double getwRpeu() {
        return wRpeu;
    }

    public static void setwRpeu(double wRpeu) {
        EnvAnalysis_cal.wRpeu = wRpeu;
    }

    public static double getwDner() {
        return wDner;
    }

    public static void setwDner(double wDner) {
        EnvAnalysis_cal.wDner = wDner;
    }

    public static double getwRmru() {
        return wRmru;
    }

    public static void setwRmru(double wRmru) {
        EnvAnalysis_cal.wRmru = wRmru;
    }

    public static double getwDnmr() {
        return wDnmr;
    }

    public static void setwDnmr(double wDnmr) {
        EnvAnalysis_cal.wDnmr = wDnmr;
    }

    public static double getDistance() {
        return distance;
    }

    public static void setDistance(double distance) {
        EnvAnalysis_cal.distance = distance;
    }

    public static double getFCF() {
        return FCF;
    }

    public static void setFCF(double FCF) {
        EnvAnalysis_cal.FCF = FCF;
    }

    public static double getSub_GWP() {
        return sub_GWP;
    }

    public static void setSub_GWP(double sub_GWP) {
        EnvAnalysis_cal.sub_GWP = sub_GWP;
    }

    public static double getSub_ODP() {
        return sub_ODP;
    }

    public static void setSub_ODP(double sub_ODP) {
        EnvAnalysis_cal.sub_ODP = sub_ODP;
    }

    public static double getSub_AP() {
        return sub_AP;
    }

    public static void setSub_AP(double sub_AP) {
        EnvAnalysis_cal.sub_AP = sub_AP;
    }

    public static double getSub_EP() {
        return sub_EP;
    }

    public static void setSub_EP(double sub_EP) {
        EnvAnalysis_cal.sub_EP = sub_EP;
    }

    public static double getSub_POCP() {
        return sub_POCP;
    }

    public static void setSub_POCP(double sub_POCP) {
        EnvAnalysis_cal.sub_POCP = sub_POCP;
    }

    public static double getSub_TW() {
        return sub_TW;
    }

    public static void setSub_TW(double sub_TW) {
        EnvAnalysis_cal.sub_TW = sub_TW;
    }

    public static double getSub_DNER() {
        return sub_DNER;
    }

    public static void setSub_DNER(double sub_DNER) {
        EnvAnalysis_cal.sub_DNER = sub_DNER;
    }

    public static double getEnp_Score() {
        return Enp_Score;
    }

    public static void setEnp_Score(double enp_Score) {
        Enp_Score = enp_Score;
    }
}
