package cm.models;

import javafx.beans.property.DoubleProperty;

/**
 * Created by Administrator on 2016/10/2.
 */
public class weights {
    private double W_EnP;
    private double W_EcP;
    private double W_GWP;
    private double W_ODP;
    private double W_AP;
    private double W_EP;
    private double W_POCP;
    private double W_CHW;
    private double W_CNHW;
    private double W_TWC;
    private double W_RPEO;
    private double W_DNER;
    private double W_RMRU;
    private double W_DNMR;

    // initialize
    public weights(double W_enp, double W_ecp, double W_gwp, double W_odp,
                    double W_ap, double W_ep, double W_pocp, double W_chw,
                    double W_cnhw, double W_twc, double W_rpeo, double W_dner,
                    double W_rmru, double W_dnmr)
    {
        this.W_EnP = W_enp;
        this.W_EcP = W_ecp;
        this.W_GWP = W_gwp;
        this.W_ODP = W_odp;
        this.W_AP = W_ap;
        this.W_EP = W_ep;
        this.W_POCP = W_pocp;
        this.W_CHW = W_chw;
        this.W_CNHW = W_cnhw;
        this.W_TWC = W_twc;
        this.W_RPEO = W_rpeo;
        this. W_DNER = W_dner;
        this. W_RMRU = W_rmru;
        this. W_DNMR = W_dnmr;

    }
    public double getW_EnP() {
        return W_EnP;
    }

    public void setW_EnP(double w_EnP) {
        W_EnP = w_EnP;
    }

    public double getW_EcP() {
        return W_EcP;
    }

    public void setW_EcP(double w_EcP) {
        W_EcP = w_EcP;
    }

    public double getW_GWP() {
        return W_GWP;
    }

    public void setW_GWP(double w_GWP) {
        W_GWP = w_GWP;
    }

    public double getW_ODP() {
        return W_ODP;
    }

    public void setW_ODP(double w_ODP) {
        W_ODP = w_ODP;
    }

    public double getW_AP() {
        return W_AP;
    }

    public void setW_AP(double w_AP) {
        W_AP = w_AP;
    }

    public double getW_EP() {
        return W_EP;
    }

    public void setW_EP(double w_EP) {
        W_EP = w_EP;
    }

    public double getW_POCP() {
        return W_POCP;
    }

    public void setW_POCP(double w_POCP) {
        W_POCP = w_POCP;
    }

    public double getW_CHW() {
        return W_CHW;
    }

    public void setW_CHW(double w_CHW) {
        W_CHW = w_CHW;
    }

    public double getW_CNHW() {
        return W_CNHW;
    }

    public void setW_CNHW(double w_CNHW) {
        W_CNHW = w_CNHW;
    }

    public double getW_TWC() {
        return W_TWC;
    }

    public void setW_TWC(double w_TWC) {
        W_TWC = w_TWC;
    }

    public double getW_RPEO() {
        return W_RPEO;
    }

    public void setW_RPEO(double w_RPEO) {
        W_RPEO = w_RPEO;
    }

    public double getW_DNER() {
        return W_DNER;
    }

    public void setW_DNER(double w_DNER) {
        W_DNER = w_DNER;
    }

    public double getW_RMRU() {
        return W_RMRU;
    }

    public void setW_RMRU(double w_RMRU) {
        W_RMRU = w_RMRU;
    }

    public double getW_DNMR() {
        return W_DNMR;
    }

    public void setW_DNMR(double w_DNMR) {
        W_DNMR = w_DNMR;
    }

}
