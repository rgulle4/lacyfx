package cm.models;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * A material is a component of a layer_temp (along with a defined thickness). A
 * material will also probably correspond to a row in the EPD database.
 */
public class Material {

    //EPD
    private String Material_ID;
    private String CS;
    private String Company_Name;
    private String Location;
    private String MixNum;
    private String ZipCode;
    private String Unit;
    private double GWP;
    private double ODP;
    private double AP;
    private double EP;
    private double POCP;
    private double ConcreteHazardousWaste;
    private double ConcreteNonHazardousWaste;
    private double TotalWaterConsumption;
    private double TotalPrimaryEnergyConsumption;
    private double RenewablePrimaryEnergyUse;
    private double NonRenewableEnergyUse;
    private double RenewableMaterialResourcesUse;
    private double NonRenewableMaterialResource;
    private double Price;
    private double Distance;
    private double unitConversion_Factor;

    //Scores
    private double EnvPerfAnalysis_TotalScore_Material;
    private double EnvPerfAnalysis_EPDScore_Material;
    private double EnvPerfAnalysis_TransportationScore_Material;

    public void setUnitConversion_Factor(){
        if (this.Unit.equalsIgnoreCase("m3"))
            unitConversion_Factor = 1.0;
        else
            unitConversion_Factor = 1.30795;     // 1 m^3 = 1.30795 yd^3
    }
    public double getUnitConversion_Factor(){
        return unitConversion_Factor;
    }

    public String getMaterial_ID() {
        return Material_ID;
    }

    public void setMaterial_ID(String material_ID) {
        Material_ID = material_ID;
    }

    public String  getCS() {
        return CS;
    }

    public void setCS(String CS) {
        this.CS = CS;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMixNum() {
        return MixNum;
    }

    public void setMixNum(String mixNum) {
        MixNum = mixNum;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public double getGWP() {
        return GWP;
    }

    public void setGWP(double GWP) {
        this.GWP = GWP;
    }

    public double getODP() {
        return ODP;
    }

    public void setODP(double ODP) {
        this.ODP = ODP;
    }

    public double getAP() {
        return AP;
    }

    public void setAP(double AP) {
        this.AP = AP;
    }

    public double getEP() {
        return EP;
    }

    public void setEP(double EP) {
        this.EP = EP;
    }

    public double getPOCP() {
        return POCP;
    }

    public void setPOCP(double POCP) {
        this.POCP = POCP;
    }

    public double getConcreteHazardousWaste() {
        return ConcreteHazardousWaste;
    }

    public void setConcreteHazardousWaste(double concreteHazardousWaste) {
        ConcreteHazardousWaste = concreteHazardousWaste;
    }

    public double getConcreteNonHazardousWaste() {
        return ConcreteNonHazardousWaste;
    }

    public void setConcreteNonHazardousWaste(double concreteNonHazardousWaste) {
        ConcreteNonHazardousWaste = concreteNonHazardousWaste;
    }

    public double getTotalWaterConsumption() {
        return TotalWaterConsumption;
    }

    public void setTotalWaterConsumption(double totalWaterConsumption) {
        TotalWaterConsumption = totalWaterConsumption;
    }

    public double getTotalPrimaryEnergyConsumption() {
        return TotalPrimaryEnergyConsumption;
    }

    public void setTotalPrimaryEnergyConsumption(String totalPrimaryEnergyConsumption)throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = format.parse(totalPrimaryEnergyConsumption);
        TotalPrimaryEnergyConsumption = number.doubleValue();
    }

    public double getRenewablePrimaryEnergyUse() {
        return RenewablePrimaryEnergyUse;
    }

    public void setRenewablePrimaryEnergyUse(double renewablePrimaryEnergyUse) {
        RenewablePrimaryEnergyUse = renewablePrimaryEnergyUse;
    }

    public double getNonRenewableEnergyUse() {
        return NonRenewableEnergyUse;
    }

    public void setNonRenewableEnergyUse(double nonRenewableEnergyUse) {
        NonRenewableEnergyUse = nonRenewableEnergyUse;
    }

    public double getRenewableMaterialResourcesUse() {
        return RenewableMaterialResourcesUse;
    }

    public void setRenewableMaterialResourcesUse(double renewableMaterialResourcesUse) {
        RenewableMaterialResourcesUse = renewableMaterialResourcesUse;
    }

    public double getNonRenewableMaterialResource() {
        return NonRenewableMaterialResource;
    }

    public void setNonRenewableMaterialResource(double nonRenewableMaterialResource) {
        NonRenewableMaterialResource = nonRenewableMaterialResource;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public double getEnvPerfAnalysis_TotalScore_Material() {
        return EnvPerfAnalysis_TotalScore_Material;
    }

    public void setEnvPerfAnalysis_TotalScore_Material(double envPerfAnalysis_TotalScore_Material) {
        EnvPerfAnalysis_TotalScore_Material = envPerfAnalysis_TotalScore_Material;
    }

    public double getEnvPerfAnalysis_EPDScore_Material() {
        return EnvPerfAnalysis_EPDScore_Material;
    }

    public void setEnvPerfAnalysis_EPDScore_Material(double envPerfAnalysis_EPDScore_Material) {
        EnvPerfAnalysis_EPDScore_Material = envPerfAnalysis_EPDScore_Material;
    }

    public double getEnvPerfAnalysis_TransportationScore_Material() {
        return EnvPerfAnalysis_TransportationScore_Material;
    }

    public void setEnvPerfAnalysis_TransportationScore_Material(double envPerfAnalysis_TransportationScore_Material) {
        EnvPerfAnalysis_TransportationScore_Material = envPerfAnalysis_TransportationScore_Material;
    }
}
