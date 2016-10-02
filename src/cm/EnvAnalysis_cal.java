package cm;


/**
 * Created by Administrator on 2016/10/1.
 */
public class EnvAnalysis_cal {
    double length = 1609.34;        //1 mile = 1609.34 meter
    double width = 12*0.3048;       //1 ft = 0.3048 meter
    double thickness;

    double TotV;


    public EnvAnalysis_cal(double Thickness){
        this.thickness = Thickness;
        this.TotV = length*thickness*width;
    }
    public double getTotalV(){
        return this.TotV;
    }
}
