package cm.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Administrator on 2016/9/29.
 */
public class AlternativeMat {
    private final StringProperty CS;
    private final StringProperty CM_name;
    private final StringProperty Location;
    private final StringProperty MixNum;
    private final StringProperty ZipCode;

    private final DoubleProperty GWP;
    private final DoubleProperty ODP;
    private final DoubleProperty AP;


    private final DoubleProperty EP;
    private final DoubleProperty POCP;

    /**
     * Default constructor.
     */

    public AlternativeMat(){
        this(null, null, null, null, null, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
    /**
     * Constructor with some initial data.
     *
     * @param CS
     * @param CM_name
     * and other factors
     */
    public AlternativeMat(String CS, String CM_name, String Location, String MixNum, String ZipCode, Double GWP, Double ODP, Double AP, Double EP, Double POCP){
        this.CS = new SimpleStringProperty(CS);
        this.CM_name = new SimpleStringProperty(CM_name);
        this.Location = new SimpleStringProperty(Location);
        this.MixNum = new SimpleStringProperty(MixNum);
        this.ZipCode = new SimpleStringProperty(ZipCode);
        this.GWP = new SimpleDoubleProperty(GWP);
        this.ODP = new SimpleDoubleProperty(ODP);
        this.AP = new SimpleDoubleProperty(AP);
        this.EP = new SimpleDoubleProperty(EP);
        this.POCP = new SimpleDoubleProperty(POCP);
    }

    public String getCS() {
        return CS.get();
    }

    public void setCS(String CS) {
        this.CS.set(CS);
    }

    public StringProperty CSProperty() {
        return CS;
    }

    public String getCM_name() {
        return CM_name.get();
    }

    public void setCM_name(String CM_name) {
        this.CM_name.set(CM_name);
    }

    public StringProperty CM_nameProperty() {
        return CM_name;
    }

    public String getLocation() {
        return Location.get();
    }

    public StringProperty locationProperty() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location.set(location);
    }

    public String getMixNum() {
        return MixNum.get();
    }

    public StringProperty mixNumProperty() {
        return MixNum;
    }

    public void setMixNum(String mixNum) {
        this.MixNum.set(mixNum);
    }


    public String getZipCode() {
        return ZipCode.get();
    }

    public StringProperty zipCodeProperty() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        this.ZipCode.set(zipCode);
    }

    public double getGWP() {
        return GWP.get();
    }

    public DoubleProperty GWPProperty() {
        return GWP;
    }

    public void setGWP(double GWP) {
        this.GWP.set(GWP);
    }

    public double getODP() {
        return ODP.get();
    }

    public DoubleProperty ODPProperty() {
        return ODP;
    }

    public void setODP(double ODP) {
        this.ODP.set(ODP);
    }

    public double getAP() {
        return AP.get();
    }

    public DoubleProperty APProperty() {
        return AP;
    }

    public void setAP(double AP) {
        this.AP.set(AP);
    }

    public double getEP() {
        return EP.get();
    }

    public DoubleProperty EPProperty() {
        return EP;
    }

    public void setEP(double EP) {
        this.EP.set(EP);
    }

    public double getPOCP() {
        return POCP.get();
    }

    public DoubleProperty POCPProperty() {
        return POCP;
    }

    public void setPOCP(double POCP) {
        this.POCP.set(POCP);
    }

}
