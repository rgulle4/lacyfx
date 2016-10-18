package cm.models;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Administrator on 2016/10/1.
 */
public class TransportationParameters {


    //variables for transportation part

    private double distance;
    private double FCF;      //Fuel Consumption Factor
    private double sub_GWP;
    private double sub_ODP;
    private double sub_AP;
    private double sub_EP;
    private double sub_POCP;
    private double sub_TW;
    private double sub_TPEC;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFCF() {
        return FCF;
    }

    public void setFCF(double FCF) {
        this.FCF = FCF;
    }

    public double getSub_GWP() {
        return sub_GWP;
    }

    public void setSub_GWP(double sub_GWP) {
        this.sub_GWP = sub_GWP;
    }

    public double getSub_ODP() {
        return sub_ODP;
    }

    public void setSub_ODP(double sub_ODP) {
        this.sub_ODP = sub_ODP;
    }

    public double getSub_AP() {
        return sub_AP;
    }

    public void setSub_AP(double sub_AP) {
        this.sub_AP = sub_AP;
    }

    public double getSub_EP() {
        return sub_EP;
    }

    public void setSub_EP(double sub_EP) {
        this.sub_EP = sub_EP;
    }

    public double getSub_POCP() {
        return sub_POCP;
    }

    public void setSub_POCP(double sub_POCP) {
        this.sub_POCP = sub_POCP;
    }

    public double getSub_TW() {
        return sub_TW;
    }

    public void setSub_TW(double sub_TW) {
        this.sub_TW = sub_TW;
    }

    public double getSub_TPEC() {
        return sub_TPEC;
    }

    public void setSub_TPEC(double sub_TPEC) {
        this.sub_TPEC = sub_TPEC;
    }
}
