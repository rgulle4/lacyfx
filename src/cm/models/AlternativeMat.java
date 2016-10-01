package cm.models;

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

    /**
     * Default constructor.
     */

    public AlternativeMat(){
        this(null,null,null,null);
    }
    /**
     * Constructor with some initial data.
     *
     * @param CS
     * @param CM_name
     */
    public AlternativeMat(String CS,String CM_name,String Location,String MixNum){
        this.CS = new SimpleStringProperty(CS);
        this.CM_name = new SimpleStringProperty(CM_name);
        this.Location = new SimpleStringProperty(Location);
        this.MixNum = new SimpleStringProperty(MixNum);
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
        return CS.get();
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
}
