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
    private final StringProperty Unit;
    private final DoubleProperty GWP;
    private final DoubleProperty ODP;
    private final DoubleProperty AP;
    private final DoubleProperty EP;
    private final DoubleProperty POCP;
    private final DoubleProperty ConcreteHazardousWaste;
    private final DoubleProperty ConcreteNonHazardousWaste;
    private final DoubleProperty TotalWaterConsumption;
    private final StringProperty TotalPrimaryEnergyConsumption;
    private final DoubleProperty RenewablePrimaryEnergyUse;
    private final DoubleProperty NonRenewableEnergyUse;
    private final DoubleProperty RenewableMaterialResourcesUse;
    private final DoubleProperty NonRenewableMaterialResource;


    /**
     * Default constructor.
     */

    public AlternativeMat() {
        this(null, null, null, null, null, null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, null, 0.0, 0.0, 0.0, 0.0);
    }

    public String getCS() {
        return CS.get();
    }

    public StringProperty CSProperty() {
        return CS;
    }

    public void setCS(String CS) {
        this.CS.set(CS);
    }

    public String getCM_name() {
        return CM_name.get();
    }

    public StringProperty CM_nameProperty() {
        return CM_name;
    }

    public void setCM_name(String CM_name) {
        this.CM_name.set(CM_name);
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

    public String getUnit() {
        return Unit.get();
    }

    public StringProperty unitProperty() {
        return Unit;
    }

    public void setUnit(String unit) {
        this.Unit.set(unit);
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

    public double getConcreteHazardousWaste() {
        return ConcreteHazardousWaste.get();
    }

    public DoubleProperty concreteHazardousWasteProperty() {
        return ConcreteHazardousWaste;
    }

    public void setConcreteHazardousWaste(double concreteHazardousWaste) {
        this.ConcreteHazardousWaste.set(concreteHazardousWaste);
    }

    public double getConcreteNonHazardousWaste() {
        return ConcreteNonHazardousWaste.get();
    }

    public DoubleProperty concreteNonHazardousWasteProperty() {
        return ConcreteNonHazardousWaste;
    }

    public void setConcreteNonHazardousWaste(double concreteNonHazardousWaste) {
        this.ConcreteNonHazardousWaste.set(concreteNonHazardousWaste);
    }

    public double getTotalWaterConsumption() {
        return TotalWaterConsumption.get();
    }

    public DoubleProperty totalWaterConsumptionProperty() {
        return TotalWaterConsumption;
    }

    public void setTotalWaterConsumption(double totalWaterConsumption) {
        this.TotalWaterConsumption.set(totalWaterConsumption);
    }

    public String getTotalPrimaryEnergyConsumption() {
        return TotalPrimaryEnergyConsumption.get();
    }

    public StringProperty totalPrimaryEnergyConsumptionProperty() {
        return TotalPrimaryEnergyConsumption;
    }

    public void setTotalPrimaryEnergyConsumption(String totalPrimaryEnergyConsumption) {
        this.TotalPrimaryEnergyConsumption.set(totalPrimaryEnergyConsumption);
    }

    public double getRenewablePrimaryEnergyUse() {
        return RenewablePrimaryEnergyUse.get();
    }

    public DoubleProperty renewablePrimaryEnergyUseProperty() {
        return RenewablePrimaryEnergyUse;
    }

    public void setRenewablePrimaryEnergyUse(double renewablePrimaryEnergyUse) {
        this.RenewablePrimaryEnergyUse.set(renewablePrimaryEnergyUse);
    }

    public double getNonRenewableEnergyUse() {
        return NonRenewableEnergyUse.get();
    }

    public DoubleProperty nonRenewableEnergyUseProperty() {
        return NonRenewableEnergyUse;
    }

    public void setNonRenewableEnergyUse(double nonRenewableEnergyUse) {
        this.NonRenewableEnergyUse.set(nonRenewableEnergyUse);
    }

    public double getRenewableMaterialResourcesUse() {
        return RenewableMaterialResourcesUse.get();
    }

    public DoubleProperty renewableMaterialResourcesUseProperty() {
        return RenewableMaterialResourcesUse;
    }

    public void setRenewableMaterialResourcesUse(double renewableMaterialResourcesUse) {
        this.RenewableMaterialResourcesUse.set(renewableMaterialResourcesUse);
    }

    public double getNonRenewableMaterialResource() {
        return NonRenewableMaterialResource.get();
    }

    public DoubleProperty nonRenewableMaterialResourceProperty() {
        return NonRenewableMaterialResource;
    }

    public void setNonRenewableMaterialResource(double nonRenewableMaterialResource) {
        this.NonRenewableMaterialResource.set(nonRenewableMaterialResource);
    }

    /**
     * Constructor with some initial data.
     *
     * @param CS
     * @param CM_name and other factors
     */
    public AlternativeMat(String CS, String CM_name, String Location, String MixNum,
                          String ZipCode, String Unit, Double GWP, Double ODP, Double AP,
                          Double EP, Double POCP, Double CHW, Double CNHW, Double TWC, String TPEC,
                          Double RPEU, Double DNER, Double RMRU, Double DNMR) {
        this.CS = new SimpleStringProperty(CS);
        this.CM_name = new SimpleStringProperty(CM_name);
        this.Location = new SimpleStringProperty(Location);
        this.MixNum = new SimpleStringProperty(MixNum);
        this.ZipCode = new SimpleStringProperty(ZipCode);
        this.Unit = new SimpleStringProperty(Unit);
        this.GWP = new SimpleDoubleProperty(GWP);
        this.ODP = new SimpleDoubleProperty(ODP);
        this.AP = new SimpleDoubleProperty(AP);
        this.EP = new SimpleDoubleProperty(EP);
        this.POCP = new SimpleDoubleProperty(POCP);
        this.ConcreteHazardousWaste = new SimpleDoubleProperty(CHW);
        this.ConcreteNonHazardousWaste = new SimpleDoubleProperty(CNHW);
        this.TotalWaterConsumption = new SimpleDoubleProperty(TWC);
        this.RenewablePrimaryEnergyUse = new SimpleDoubleProperty(RPEU);
        this.NonRenewableEnergyUse = new SimpleDoubleProperty(DNER);
        this.RenewableMaterialResourcesUse = new SimpleDoubleProperty(RMRU);
        this.NonRenewableMaterialResource = new SimpleDoubleProperty(DNMR);
        this.TotalPrimaryEnergyConsumption = new SimpleStringProperty(TPEC);
    }
}

