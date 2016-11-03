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
    // Subscores of EPD score for the different env impacts (before
    // NORMALIZATIONS). Each of these is calculated using the same basic
    // formula:
    //   GWP_EDP_Ctb = gwp (from material with epd.value per epd.unit)
    //                 * "Conversion Factor (volume with matching unit)"
    private double GWP_EDP_Ctb;
    private double ODP_EDP_Ctb;
    private double AP_EDP_Ctb;
    private double EP_EDP_Ctb;
    private double POCP_EDP_Ctb;
    private double TW_EDP_Ctb;      // Total Water Consumption
    private double TPEC_EDP_Ctb;    // Total Primary Energy Consumption

    // EPD raw value for the different env impacts (after NORMALIZATIONS)
    private double GWP_EDP_NORM;
    private double ODP_EDP_NORM;
    private double AP_EDP_NORM;
    private double EP_EDP_NORM;
    private double POCP_EDP_NORM;
    private double TW_EDP_NORM;      // Total Water Consumption
    private double TPEC_EDP_NORM;    // Total Primary Energy Consumption

    // EPD's SubScore for the different env impacts (after WEIGHTS)
    private double GWP_EDP_Subsore;
    private double ODP_EDP_Subsore;
    private double AP_EDP_Subsore;
    private double EP_EDP_Subsore;
    private double POCP_EDP_Subsore;
    private double TW_EDP_Subsore;      // Total Water Consumption
    private double TPEC_EDP_Subsore;    // Total Primary Energy Consumption

    // Subscores of Transportation score for the different env
    // impacts (before NORMALIZATIONS). Each of these is calculated using:
    //   2 * distance * substance
    private double GWP_Transportation_Ctb;
    private double ODP_Transportation_Ctb;
    private double AP_Transportation_Ctb;
    private double EP_Transportation_Ctb;
    private double POCP_Transportation_Ctb;
    private double TW_Transportation_Ctb;      // Total Water Consumption
    private double TPEC_Transportation_Ctb;    // Total Primary Energy Consumption

    // Transportation part's raw value for the different env impacts (after NORMALIZATIONS)
    private double GWP_Transportation_NORM;
    private double ODP_Transportation_NORM;
    private double AP_Transportation_NORM;
    private double EP_Transportation_NORM;
    private double POCP_Transportation_NORM;
    private double TW_Transportation_NORM;      // Total Water Consumption
    private double TPEC_Transportation_NORM;    // Total Primary Energy Consumption

    // Transportation part's SubScore for the different env impacts (after WEIGHTS)
    private double GWP_Transportation_Subsore;
    private double ODP_Transportation_Subsore;
    private double AP_Transportation_Subsore;
    private double EP_Transportation_Subsore;
    private double POCP_Transportation_Subsore;
    private double TW_Transportation_Subsore;      // Total Water Consumption
    private double TPEC_Transportation_Subsore;    // Total Primary Energy Consumption


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

    public double getGWP_EDP_Ctb() {
        return GWP_EDP_Ctb;
    }

    public void setGWP_EDP_Ctb(double GWP_EDP_Ctb) {
        this.GWP_EDP_Ctb = GWP_EDP_Ctb;
    }

    public double getODP_EDP_Ctb() {
        return ODP_EDP_Ctb;
    }

    public void setODP_EDP_Ctb(double ODP_EDP_Ctb) {
        this.ODP_EDP_Ctb = ODP_EDP_Ctb;
    }

    public double getAP_EDP_Ctb() {
        return AP_EDP_Ctb;
    }

    public void setAP_EDP_Ctb(double AP_EDP_Ctb) {
        this.AP_EDP_Ctb = AP_EDP_Ctb;
    }

    public double getEP_EDP_Ctb() {
        return EP_EDP_Ctb;
    }

    public void setEP_EDP_Ctb(double EP_EDP_Ctb) {
        this.EP_EDP_Ctb = EP_EDP_Ctb;
    }

    public double getPOCP_EDP_Ctb() {
        return POCP_EDP_Ctb;
    }

    public void setPOCP_EDP_Ctb(double POCP_EDP_Ctb) {
        this.POCP_EDP_Ctb = POCP_EDP_Ctb;
    }

    public double getTW_EDP_Ctb() {
        return TW_EDP_Ctb;
    }

    public void setTW_EDP_Ctb(double TW_EDP_Ctb) {
        this.TW_EDP_Ctb = TW_EDP_Ctb;
    }

    public double getTPEC_EDP_Ctb() {
        return TPEC_EDP_Ctb;
    }

    public void setTPEC_EDP_Ctb(double TPEC_EDP_Ctb) {
        this.TPEC_EDP_Ctb = TPEC_EDP_Ctb;
    }

    public double getGWP_EDP_NORM() {
        return GWP_EDP_NORM;
    }

    public void setGWP_EDP_NORM(double GWP_EDP_NORM) {
        this.GWP_EDP_NORM = GWP_EDP_NORM;
    }

    public double getODP_EDP_NORM() {
        return ODP_EDP_NORM;
    }

    public void setODP_EDP_NORM(double ODP_EDP_NORM) {
        this.ODP_EDP_NORM = ODP_EDP_NORM;
    }

    public double getAP_EDP_NORM() {
        return AP_EDP_NORM;
    }

    public void setAP_EDP_NORM(double AP_EDP_NORM) {
        this.AP_EDP_NORM = AP_EDP_NORM;
    }

    public double getEP_EDP_NORM() {
        return EP_EDP_NORM;
    }

    public void setEP_EDP_NORM(double EP_EDP_NORM) {
        this.EP_EDP_NORM = EP_EDP_NORM;
    }

    public double getPOCP_EDP_NORM() {
        return POCP_EDP_NORM;
    }

    public void setPOCP_EDP_NORM(double POCP_EDP_NORM) {
        this.POCP_EDP_NORM = POCP_EDP_NORM;
    }

    public double getTW_EDP_NORM() {
        return TW_EDP_NORM;
    }

    public void setTW_EDP_NORM(double TW_EDP_NORM) {
        this.TW_EDP_NORM = TW_EDP_NORM;
    }

    public double getTPEC_EDP_NORM() {
        return TPEC_EDP_NORM;
    }

    public void setTPEC_EDP_NORM(double TPEC_EDP_NORM) {
        this.TPEC_EDP_NORM = TPEC_EDP_NORM;
    }

    public double getGWP_EDP_Subsore() {
        return GWP_EDP_Subsore;
    }

    public void setGWP_EDP_Subsore(double GWP_EDP_Subsore) {
        this.GWP_EDP_Subsore = GWP_EDP_Subsore;
    }

    public double getODP_EDP_Subsore() {
        return ODP_EDP_Subsore;
    }

    public void setODP_EDP_Subsore(double ODP_EDP_Subsore) {
        this.ODP_EDP_Subsore = ODP_EDP_Subsore;
    }

    public double getAP_EDP_Subsore() {
        return AP_EDP_Subsore;
    }

    public void setAP_EDP_Subsore(double AP_EDP_Subsore) {
        this.AP_EDP_Subsore = AP_EDP_Subsore;
    }

    public double getEP_EDP_Subsore() {
        return EP_EDP_Subsore;
    }

    public void setEP_EDP_Subsore(double EP_EDP_Subsore) {
        this.EP_EDP_Subsore = EP_EDP_Subsore;
    }

    public double getPOCP_EDP_Subsore() {
        return POCP_EDP_Subsore;
    }

    public void setPOCP_EDP_Subsore(double POCP_EDP_Subsore) {
        this.POCP_EDP_Subsore = POCP_EDP_Subsore;
    }

    public double getTW_EDP_Subsore() {
        return TW_EDP_Subsore;
    }

    public void setTW_EDP_Subsore(double TW_EDP_Subsore) {
        this.TW_EDP_Subsore = TW_EDP_Subsore;
    }

    public double getTPEC_EDP_Subsore() {
        return TPEC_EDP_Subsore;
    }

    public void setTPEC_EDP_Subsore(double TPEC_EDP_Subsore) {
        this.TPEC_EDP_Subsore = TPEC_EDP_Subsore;
    }

    public double getGWP_Transportation_Ctb() {
        return GWP_Transportation_Ctb;
    }

    public void setGWP_Transportation_Ctb(double GWP_Transportation_Ctb) {
        this.GWP_Transportation_Ctb = GWP_Transportation_Ctb;
    }

    public double getODP_Transportation_Ctb() {
        return ODP_Transportation_Ctb;
    }

    public void setODP_Transportation_Ctb(double ODP_Transportation_Ctb) {
        this.ODP_Transportation_Ctb = ODP_Transportation_Ctb;
    }

    public double getAP_Transportation_Ctb() {
        return AP_Transportation_Ctb;
    }

    public void setAP_Transportation_Ctb(double AP_Transportation_Ctb) {
        this.AP_Transportation_Ctb = AP_Transportation_Ctb;
    }

    public double getEP_Transportation_Ctb() {
        return EP_Transportation_Ctb;
    }

    public void setEP_Transportation_Ctb(double EP_Transportation_Ctb) {
        this.EP_Transportation_Ctb = EP_Transportation_Ctb;
    }

    public double getPOCP_Transportation_Ctb() {
        return POCP_Transportation_Ctb;
    }

    public void setPOCP_Transportation_Ctb(double POCP_Transportation_Ctb) {
        this.POCP_Transportation_Ctb = POCP_Transportation_Ctb;
    }

    public double getTW_Transportation_Ctb() {
        return TW_Transportation_Ctb;
    }

    public void setTW_Transportation_Ctb(double TW_Transportation_Ctb) {
        this.TW_Transportation_Ctb = TW_Transportation_Ctb;
    }

    public double getTPEC_Transportation_Ctb() {
        return TPEC_Transportation_Ctb;
    }

    public void setTPEC_Transportation_Ctb(double TPEC_Transportation_Ctb) {
        this.TPEC_Transportation_Ctb = TPEC_Transportation_Ctb;
    }

    public double getGWP_Transportation_NORM() {
        return GWP_Transportation_NORM;
    }

    public void setGWP_Transportation_NORM(double GWP_Transportation_NORM) {
        this.GWP_Transportation_NORM = GWP_Transportation_NORM;
    }

    public double getODP_Transportation_NORM() {
        return ODP_Transportation_NORM;
    }

    public void setODP_Transportation_NORM(double ODP_Transportation_NORM) {
        this.ODP_Transportation_NORM = ODP_Transportation_NORM;
    }

    public double getAP_Transportation_NORM() {
        return AP_Transportation_NORM;
    }

    public void setAP_Transportation_NORM(double AP_Transportation_NORM) {
        this.AP_Transportation_NORM = AP_Transportation_NORM;
    }

    public double getEP_Transportation_NORM() {
        return EP_Transportation_NORM;
    }

    public void setEP_Transportation_NORM(double EP_Transportation_NORM) {
        this.EP_Transportation_NORM = EP_Transportation_NORM;
    }

    public double getPOCP_Transportation_NORM() {
        return POCP_Transportation_NORM;
    }

    public void setPOCP_Transportation_NORM(double POCP_Transportation_NORM) {
        this.POCP_Transportation_NORM = POCP_Transportation_NORM;
    }

    public double getTW_Transportation_NORM() {
        return TW_Transportation_NORM;
    }

    public void setTW_Transportation_NORM(double TW_Transportation_NORM) {
        this.TW_Transportation_NORM = TW_Transportation_NORM;
    }

    public double getTPEC_Transportation_NORM() {
        return TPEC_Transportation_NORM;
    }

    public void setTPEC_Transportation_NORM(double TPEC_Transportation_NORM) {
        this.TPEC_Transportation_NORM = TPEC_Transportation_NORM;
    }

    public double getGWP_Transportation_Subsore() {
        return GWP_Transportation_Subsore;
    }

    public void setGWP_Transportation_Subsore(double GWP_Transportation_Subsore) {
        this.GWP_Transportation_Subsore = GWP_Transportation_Subsore;
    }

    public double getODP_Transportation_Subsore() {
        return ODP_Transportation_Subsore;
    }

    public void setODP_Transportation_Subsore(double ODP_Transportation_Subsore) {
        this.ODP_Transportation_Subsore = ODP_Transportation_Subsore;
    }

    public double getAP_Transportation_Subsore() {
        return AP_Transportation_Subsore;
    }

    public void setAP_Transportation_Subsore(double AP_Transportation_Subsore) {
        this.AP_Transportation_Subsore = AP_Transportation_Subsore;
    }

    public double getEP_Transportation_Subsore() {
        return EP_Transportation_Subsore;
    }

    public void setEP_Transportation_Subsore(double EP_Transportation_Subsore) {
        this.EP_Transportation_Subsore = EP_Transportation_Subsore;
    }

    public double getPOCP_Transportation_Subsore() {
        return POCP_Transportation_Subsore;
    }

    public void setPOCP_Transportation_Subsore(double POCP_Transportation_Subsore) {
        this.POCP_Transportation_Subsore = POCP_Transportation_Subsore;
    }

    public double getTW_Transportation_Subsore() {
        return TW_Transportation_Subsore;
    }

    public void setTW_Transportation_Subsore(double TW_Transportation_Subsore) {
        this.TW_Transportation_Subsore = TW_Transportation_Subsore;
    }

    public double getTPEC_Transportation_Subsore() {
        return TPEC_Transportation_Subsore;
    }

    public void setTPEC_Transportation_Subsore(double TPEC_Transportation_Subsore) {
        this.TPEC_Transportation_Subsore = TPEC_Transportation_Subsore;
    }
}
