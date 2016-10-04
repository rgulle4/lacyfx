package cm.models;

/**
 * Created by Administrator on 2016/10/2.
 */
public class Normalization {

    double Norm_GWP;
    double Norm_ODP;
    double Norm_AP;
    double Norm_EP;
    double Norm_POCP;
    double Norm_fuel;
    double Norm_water;

    //initialization
    public Normalization(){
        this.Norm_GWP=1;
        this.Norm_ODP=1;
        this.Norm_AP =1;
        this.Norm_EP =1;
        this.Norm_POCP =1;
        this.Norm_fuel =1;
        this.Norm_water =1;

    }


    public double getNorm_GWP() {
        return Norm_GWP;
    }

    public double getNorm_ODP() {
        return Norm_ODP;
    }

    public double getNorm_AP() {
        return Norm_AP;
    }

    public double getNorm_EP() {
        return Norm_EP;
    }

    public double getNorm_POCP() {
        return Norm_POCP;
    }

    public double getNorm_fuel() {
        return Norm_fuel;
    }

    public double getNorm_water() {
        return Norm_water;
    }
}
