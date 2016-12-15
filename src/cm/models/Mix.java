package cm.models;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A material is a component of a layer_temp (along with a defined thickness). A
 * material will also probably correspond to a row in the EPD database.
 */
public final class Mix {

    //EPD
    private String Product_ID;
    private String CS;
    private String Company_Name;
    private String Location;
    private String MixNum;
    private String ZipCode;
    private String Unit;
    private Double GWP;
    private Double ODP;
    private Double AP;
    private Double EP;
    private Double POCP;
    private Double ConcreteHazardousWaste;
    private Double ConcreteNonHazardousWaste;
    private Double TotalWaterConsumption;
    private Double ConcreteWashingWaterConsumption;
    private Double ConcreteBatchingWaterConsumption;
    private Double TotalPrimaryEnergyConsumption;
    private Double RenewablePrimaryEnergyUse;
    private Double NonRenewableEnergyUse;
    private Double RenewableMaterialResourcesUse;
    private Double NonRenewableMaterialResource;
    private Double Price;
    private Double Distance;
    private Double unitConversion_Factor;
    private Boolean isTotalWaterConsumptionSpecial = false;
    private Boolean isPrimaryEnergyConsumptionSpecial = false;
    public Map<String,Double> CalcResult = new HashMap<>();
    public Map<String,String> EPDUnits = new HashMap<>();
    //Mix description
    public String Cement;
    public String Mixing_Water;
    public String Air_Percent;
    public String Water_Reducer;
    public String High_Range_Water_Reducer;
    public String Fly_Ash;
    public String Slag;
    public String Air_Entrained;
    public String Crushed_Coarse_Aggregate;
    public String Crushed_Fine_Aggregate;
    public String Natural_Coarse_Aggregate;
    public String Natrual_Fine_Aggregate;
    public String LightWeight_Aggregate;
    public String Accelerator;
    public String Region;
    public String IsLightWeight;
    //unit
    public String GWP_Units;
    public String ODP_Units;
    public String AP_Units;
    public String EP_Units;
    public String POCP_Units;
    public String TotalPrimaryEnergyConsumption_Units;
    public String TotalWaterConsumption_Units;
    public String RenewableMaterialResourcesUse_Units;
    public String NonRenewableMaterialResource_Units;
    //Scores
    private Double EnvPerfAnalysis_TotalScore_Material;
    private Double EnvPerfAnalysis_EPDScore_Material;
    private Double EnvPerfAnalysis_TransportationScore_Material;
    // Subscores of EPD score for the different env impacts (before
    // NORMALIZATIONS). Each of these is calculated using the same basic
    // formula:
    //   GWP_EPD_Ctb = gwp (from material with epd.value per epd.unit)
    //                 * "Conversion Factor (volume with matching unit)"
    private Double GWP_EPD_Ctb;
    private Double ODP_EPD_Ctb;
    private Double AP_EPD_Ctb;
    private Double EP_EPD_Ctb;
    private Double POCP_EPD_Ctb;
    private Double TW_EPD_Ctb;      // Total Water Consumption
    private Double TPEC_EPD_Ctb;    // Total Primary Energy Consumption

    // EPD raw value for the different env impacts (after NORMALIZATIONS)
    private Double GWP_EPD_NORM;
    private Double ODP_EPD_NORM;
    private Double AP_EPD_NORM;
    private Double EP_EPD_NORM;
    private Double POCP_EPD_NORM;
    private Double TW_EPD_NORM;      // Total Water Consumption
    private Double TPEC_EPD_NORM;    // Total Primary Energy Consumption

    // EPD's SubScore for the different env impacts (after WEIGHTS)
    private Double GWP_EPD_Subsore;
    private Double ODP_EPD_Subsore;
    private Double AP_EPD_Subsore;
    private Double EP_EPD_Subsore;
    private Double POCP_EPD_Subsore;
    private Double TW_EPD_Subsore;      // Total Water Consumption
    private Double TPEC_EPD_Subsore;    // Total Primary Energy Consumption
    // Subscores of Transportation score for the different env
    // impacts (before NORMALIZATIONS). Each of these is calculated using:
    //   2 * distance * substance
    private Double GWP_Transportation_Ctb;
    private Double ODP_Transportation_Ctb;
    private Double AP_Transportation_Ctb;
    private Double EP_Transportation_Ctb;
    private Double POCP_Transportation_Ctb;
    private Double TW_Transportation_Ctb;      // Total Water Consumption
    private Double TPEC_Transportation_Ctb;    // Total Primary Energy Consumption

    // Transportation part's raw value for the different env impacts (after NORMALIZATIONS)
    private Double GWP_Transportation_NORM;
    private Double ODP_Transportation_NORM;
    private Double AP_Transportation_NORM;
    private Double EP_Transportation_NORM;
    private Double POCP_Transportation_NORM;
    private Double TW_Transportation_NORM;      // Total Water Consumption
    private Double TPEC_Transportation_NORM;    // Total Primary Energy Consumption

    // Transportation part's SubScore for the different env impacts (after WEIGHTS)
    private Double GWP_Transportation_SubScore;
    private Double ODP_Transportation_SubScore;
    private Double AP_Transportation_SubScore;
    private Double EP_Transportation_SubScore;
    private Double POCP_Transportation_SubScore;
    private Double TW_Transportation_SubScore;      // Total Water Consumption
    private Double TPEC_Transportation_SubScore;    // Total Primary Energy Consumption


    public void setUnitConversion_Factor(){
        if (this.Unit.equalsIgnoreCase("m3"))
            unitConversion_Factor = 1.0;
        else
            unitConversion_Factor = 1.30795;     // 1 m^3 = 1.30795 yd^3
    }
    public Double getUnitConversion_Factor(){
        return unitConversion_Factor;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
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

    public Double getGWP() {
        return GWP;
    }

    public void setGWP(Double GWP) {
        this.GWP = GWP;
    }

    public Double getODP() {
        return ODP;
    }

    public void setODP(Double ODP) {
        this.ODP = ODP;
    }

    public Double getAP() {
        return AP;
    }

    public void setAP(Double AP) {
        this.AP = AP;
    }

    public Double getEP() {
        return EP;
    }

    public void setEP(Double EP) {
        this.EP = EP;
    }

    public Double getPOCP() {
        return POCP;
    }

    public void setPOCP(Double POCP) {
        this.POCP = POCP;
    }

    public Double getConcreteHazardousWaste() {
        return ConcreteHazardousWaste;
    }

    public void setConcreteHazardousWaste(Double concreteHazardousWaste) {
        ConcreteHazardousWaste = concreteHazardousWaste;
    }

    public Double getConcreteNonHazardousWaste() {
        return ConcreteNonHazardousWaste;
    }

    public void setConcreteNonHazardousWaste(Double concreteNonHazardousWaste) {
        ConcreteNonHazardousWaste = concreteNonHazardousWaste;
    }

    public Double getTotalWaterConsumption() {
        return TotalWaterConsumption;
    }

    public void setTotalWaterConsumption(Double totalWaterConsumption) {
        TotalWaterConsumption = totalWaterConsumption;
    }

    public Double getConcreteWashingWaterConsumption() {
        return ConcreteWashingWaterConsumption;
    }

    public void setConcreteWashingWaterConsumption(Double concreteWashingWaterConsumption) {
        ConcreteWashingWaterConsumption = concreteWashingWaterConsumption;
    }

    public Double getConcreteBatchingWaterConsumption() {
        return ConcreteBatchingWaterConsumption;
    }

    public void setConcreteBatchingWaterConsumption(Double concreteBatchingWaterConsumption) {
        ConcreteBatchingWaterConsumption = concreteBatchingWaterConsumption;
    }

    public Double getTotalPrimaryEnergyConsumption() {
        return TotalPrimaryEnergyConsumption;
    }

    public void setTotalPrimaryEnergyConsumption(String totalPrimaryEnergyConsumption)throws ParseException {
        if(totalPrimaryEnergyConsumption.isEmpty()){TotalPrimaryEnergyConsumption = 0.0;}
        else{
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number number = format.parse(totalPrimaryEnergyConsumption);
            TotalPrimaryEnergyConsumption = number.doubleValue();
        }
    }

    public Double getRenewablePrimaryEnergyUse() {
        return RenewablePrimaryEnergyUse;
    }

    public void setRenewablePrimaryEnergyUse(Double renewablePrimaryEnergyUse) {
        RenewablePrimaryEnergyUse = renewablePrimaryEnergyUse;
    }

    public Double getNonRenewableEnergyUse() {
        return NonRenewableEnergyUse;
    }

    public void setNonRenewableEnergyUse(String nonRenewableEnergyUse) throws ParseException {
        if (nonRenewableEnergyUse == null ||
                nonRenewableEnergyUse.equals("N/A") ){
            NonRenewableEnergyUse = 0.0;}
        else{
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number number = format.parse(nonRenewableEnergyUse);
            NonRenewableEnergyUse = number.doubleValue();
        }
    }

    public Double getRenewableMaterialResourcesUse() {
        return RenewableMaterialResourcesUse;
    }

    public void setRenewableMaterialResourcesUse(Double renewableMaterialResourcesUse) {
        RenewableMaterialResourcesUse = renewableMaterialResourcesUse;
    }

    public Double getNonRenewableMaterialResource() {
        return NonRenewableMaterialResource;
    }

    public void setNonRenewableMaterialResource(String nonRenewableMaterialResource) throws ParseException {
        if(nonRenewableMaterialResource == null ||
            nonRenewableMaterialResource.equals("N/A")){NonRenewableMaterialResource = 0.0;}
        else{
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number number = format.parse(nonRenewableMaterialResource);
            NonRenewableMaterialResource = number.doubleValue();
        }
    }

    public String getGWP_Units() {
        return GWP_Units;
    }

    public void setGWP_Units(String GWP_Units) {
        this.GWP_Units = GWP_Units;
    }

    public String getODP_Units() {
        return ODP_Units;
    }

    public void setODP_Units(String ODP_Units) {
        this.ODP_Units = ODP_Units;
    }

    public String getAP_Units() {
        return AP_Units;
    }

    public void setAP_Units(String AP_Units) {
        this.AP_Units = AP_Units;
    }

    public String getEP_Units() {
        return EP_Units;
    }

    public void setEP_Units(String EP_Units) {
        this.EP_Units = EP_Units;
    }

    public String getPOCP_Units() {
        return POCP_Units;
    }

    public void setPOCP_Units(String POCP_Units) {
        this.POCP_Units = POCP_Units;
    }

    public String getTotalPrimaryEnergyConsumption_Units() {
        return TotalPrimaryEnergyConsumption_Units;
    }

    public void setTotalPrimaryEnergyConsumption_Units(String totalPrimaryEnergyConsumption_Units) {
        TotalPrimaryEnergyConsumption_Units = totalPrimaryEnergyConsumption_Units;
    }

    public String getTotalWaterConsumption_Units() {
        return TotalWaterConsumption_Units;
    }

    public void setTotalWaterConsumption_Units(String totalWaterConsumption_Units) {
        TotalWaterConsumption_Units = totalWaterConsumption_Units;
    }

    public String getRenewableMaterialResourcesUse_Units() {
        return RenewableMaterialResourcesUse_Units;
    }

    public void setRenewableMaterialResourcesUse_Units(String renewableMaterialResourcesUse_Units) {
        RenewableMaterialResourcesUse_Units = renewableMaterialResourcesUse_Units;
    }

    public String getNonRenewableMaterialResource_Units() {
        return NonRenewableMaterialResource_Units;
    }

    public void setNonRenewableMaterialResource_Units(String nonRenewableMaterialResource_Units) {
        NonRenewableMaterialResource_Units = nonRenewableMaterialResource_Units;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }

    public Boolean getTotalWaterConsumptionSpecial() {
        return isTotalWaterConsumptionSpecial;
    }

    public void setTotalWaterConsumptionSpecial(Boolean totalWaterConsumptionSpecial) {
        isTotalWaterConsumptionSpecial = totalWaterConsumptionSpecial;
    }

    public Boolean getPrimaryEnergyConsumptionSpecial() {
        return isPrimaryEnergyConsumptionSpecial;
    }

    public void setPrimaryEnergyConsumptionSpecial(Boolean primaryEnergyConsumptionSpecial) {
        isPrimaryEnergyConsumptionSpecial = primaryEnergyConsumptionSpecial;
    }

    public Double getEnvPerfAnalysis_TotalScore_Material() {
        return EnvPerfAnalysis_TotalScore_Material;
    }

    public void setEnvPerfAnalysis_TotalScore_Material(Double envPerfAnalysis_TotalScore_Material) {
        EnvPerfAnalysis_TotalScore_Material = envPerfAnalysis_TotalScore_Material;
    }

    public Double getEnvPerfAnalysis_EPDScore_Material() {
        return EnvPerfAnalysis_EPDScore_Material;
    }

    public void setEnvPerfAnalysis_EPDScore_Material(Double envPerfAnalysis_EPDScore_Material) {
        EnvPerfAnalysis_EPDScore_Material = envPerfAnalysis_EPDScore_Material;
    }

    public Double getEnvPerfAnalysis_TransportationScore_Material() {
        return EnvPerfAnalysis_TransportationScore_Material;
    }

    public void setEnvPerfAnalysis_TransportationScore_Material(Double envPerfAnalysis_TransportationScore_Material) {
        EnvPerfAnalysis_TransportationScore_Material = envPerfAnalysis_TransportationScore_Material;
    }

    public Double getGWP_EPD_Ctb() {
        return GWP_EPD_Ctb;
    }

    public void setGWP_EPD_Ctb(Double GWP_EPD_Ctb) {
        this.GWP_EPD_Ctb = GWP_EPD_Ctb;
    }

    public Double getODP_EPD_Ctb() {
        return ODP_EPD_Ctb;
    }

    public void setODP_EPD_Ctb(Double ODP_EPD_Ctb) {
        this.ODP_EPD_Ctb = ODP_EPD_Ctb;
    }

    public Double getAP_EPD_Ctb() {
        return AP_EPD_Ctb;
    }

    public void setAP_EPD_Ctb(Double AP_EPD_Ctb) {
        this.AP_EPD_Ctb = AP_EPD_Ctb;
    }

    public Double getEP_EPD_Ctb() {
        return EP_EPD_Ctb;
    }

    public void setEP_EPD_Ctb(Double EP_EPD_Ctb) {
        this.EP_EPD_Ctb = EP_EPD_Ctb;
    }

    public Double getPOCP_EPD_Ctb() {
        return POCP_EPD_Ctb;
    }

    public void setPOCP_EPD_Ctb(Double POCP_EPD_Ctb) {
        this.POCP_EPD_Ctb = POCP_EPD_Ctb;
    }

    public Double getTW_EPD_Ctb() {
        return TW_EPD_Ctb;
    }

    public void setTW_EPD_Ctb(Double TW_EPD_Ctb) {
        this.TW_EPD_Ctb = TW_EPD_Ctb;
    }

    public Double getTPEC_EPD_Ctb() {
        return TPEC_EPD_Ctb;
    }

    public void setTPEC_EPD_Ctb(Double TPEC_EPD_Ctb) {
        this.TPEC_EPD_Ctb = TPEC_EPD_Ctb;
    }

    public Double getGWP_EPD_NORM() {
        return GWP_EPD_NORM;
    }

    public void setGWP_EPD_NORM(Double GWP_EPD_NORM) {
        this.GWP_EPD_NORM = GWP_EPD_NORM;
    }

    public Double getODP_EPD_NORM() {
        return ODP_EPD_NORM;
    }

    public void setODP_EPD_NORM(Double ODP_EPD_NORM) {
        this.ODP_EPD_NORM = ODP_EPD_NORM;
    }

    public Double getAP_EPD_NORM() {
        return AP_EPD_NORM;
    }

    public void setAP_EPD_NORM(Double AP_EPD_NORM) {
        this.AP_EPD_NORM = AP_EPD_NORM;
    }

    public Double getEP_EPD_NORM() {
        return EP_EPD_NORM;
    }

    public void setEP_EPD_NORM(Double EP_EPD_NORM) {
        this.EP_EPD_NORM = EP_EPD_NORM;
    }

    public Double getPOCP_EPD_NORM() {
        return POCP_EPD_NORM;
    }

    public void setPOCP_EPD_NORM(Double POCP_EPD_NORM) {
        this.POCP_EPD_NORM = POCP_EPD_NORM;
    }

    public Double getTW_EPD_NORM() {
        return TW_EPD_NORM;
    }

    public void setTW_EPD_NORM(Double TW_EPD_NORM) {
        this.TW_EPD_NORM = TW_EPD_NORM;
    }

    public Double getTPEC_EPD_NORM() {
        return TPEC_EPD_NORM;
    }

    public void setTPEC_EPD_NORM(Double TPEC_EPD_NORM) {
        this.TPEC_EPD_NORM = TPEC_EPD_NORM;
    }

    public Double getGWP_EPD_SubScore() {
        return GWP_EPD_Subsore;
    }

    public void setGWP_EPD_Subsore(Double GWP_EPD_Subsore) {
        this.GWP_EPD_Subsore = GWP_EPD_Subsore;
    }

    public Double getODP_EPD_SubScore() {
        return ODP_EPD_Subsore;
    }

    public void setODP_EPD_Subsore(Double ODP_EPD_Subsore) {
        this.ODP_EPD_Subsore = ODP_EPD_Subsore;
    }

    public Double getAP_EPD_SubScore() {
        return AP_EPD_Subsore;
    }

    public void setAP_EPD_Subsore(Double AP_EPD_Subsore) {
        this.AP_EPD_Subsore = AP_EPD_Subsore;
    }

    public Double getEP_EPD_SubScore() {
        return EP_EPD_Subsore;
    }

    public void setEP_EPD_Subsore(Double EP_EPD_Subsore) {
        this.EP_EPD_Subsore = EP_EPD_Subsore;
    }

    public Double getPOCP_EPD_SubScore() {
        return POCP_EPD_Subsore;
    }

    public void setPOCP_EPD_Subsore(Double POCP_EPD_Subsore) {
        this.POCP_EPD_Subsore = POCP_EPD_Subsore;
    }

    public Double getTW_EPD_SubScore() {
        return TW_EPD_Subsore;
    }

    public void setTW_EPD_Subsore(Double TW_EPD_Subsore) {
        this.TW_EPD_Subsore = TW_EPD_Subsore;
    }

    public Double getTPEC_EPD_SubScore() {
        return TPEC_EPD_Subsore;
    }

    public void setTPEC_EPD_Subsore(Double TPEC_EDP_Subsore) {
        this.TPEC_EPD_Subsore = TPEC_EDP_Subsore;
    }

    public Double getGWP_Transportation_Ctb() {
        return GWP_Transportation_Ctb;
    }

    public void setGWP_Transportation_Ctb(Double GWP_Transportation_Ctb) {
        this.GWP_Transportation_Ctb = GWP_Transportation_Ctb;
    }

    public Double getODP_Transportation_Ctb() {
        return ODP_Transportation_Ctb;
    }

    public void setODP_Transportation_Ctb(Double ODP_Transportation_Ctb) {
        this.ODP_Transportation_Ctb = ODP_Transportation_Ctb;
    }

    public Double getAP_Transportation_Ctb() {
        return AP_Transportation_Ctb;
    }

    public void setAP_Transportation_Ctb(Double AP_Transportation_Ctb) {
        this.AP_Transportation_Ctb = AP_Transportation_Ctb;
    }

    public Double getEP_Transportation_Ctb() {
        return EP_Transportation_Ctb;
    }

    public void setEP_Transportation_Ctb(Double EP_Transportation_Ctb) {
        this.EP_Transportation_Ctb = EP_Transportation_Ctb;
    }

    public Double getPOCP_Transportation_Ctb() {
        return POCP_Transportation_Ctb;
    }

    public void setPOCP_Transportation_Ctb(Double POCP_Transportation_Ctb) {
        this.POCP_Transportation_Ctb = POCP_Transportation_Ctb;
    }

    public Double getTW_Transportation_Ctb() {
        return TW_Transportation_Ctb;
    }

    public void setTW_Transportation_Ctb(Double TW_Transportation_Ctb) {
        this.TW_Transportation_Ctb = TW_Transportation_Ctb;
    }

    public Double getTPEC_Transportation_Ctb() {
        return TPEC_Transportation_Ctb;
    }

    public void setTPEC_Transportation_Ctb(Double TPEC_Transportation_Ctb) {
        this.TPEC_Transportation_Ctb = TPEC_Transportation_Ctb;
    }

    public Double getGWP_Transportation_NORM() {
        return GWP_Transportation_NORM;
    }

    public void setGWP_Transportation_NORM(Double GWP_Transportation_NORM) {
        this.GWP_Transportation_NORM = GWP_Transportation_NORM;
    }

    public Double getODP_Transportation_NORM() {
        return ODP_Transportation_NORM;
    }

    public void setODP_Transportation_NORM(Double ODP_Transportation_NORM) {
        this.ODP_Transportation_NORM = ODP_Transportation_NORM;
    }

    public Double getAP_Transportation_NORM() {
        return AP_Transportation_NORM;
    }

    public void setAP_Transportation_NORM(Double AP_Transportation_NORM) {
        this.AP_Transportation_NORM = AP_Transportation_NORM;
    }

    public Double getEP_Transportation_NORM() {
        return EP_Transportation_NORM;
    }

    public void setEP_Transportation_NORM(Double EP_Transportation_NORM) {
        this.EP_Transportation_NORM = EP_Transportation_NORM;
    }

    public Double getPOCP_Transportation_NORM() {
        return POCP_Transportation_NORM;
    }

    public void setPOCP_Transportation_NORM(Double POCP_Transportation_NORM) {
        this.POCP_Transportation_NORM = POCP_Transportation_NORM;
    }

    public Double getTW_Transportation_NORM() {
        return TW_Transportation_NORM;
    }

    public void setTW_Transportation_NORM(Double TW_Transportation_NORM) {
        this.TW_Transportation_NORM = TW_Transportation_NORM;
    }

    public Double getTPEC_Transportation_NORM() {
        return TPEC_Transportation_NORM;
    }

    public void setTPEC_Transportation_NORM(Double TPEC_Transportation_NORM) {
        this.TPEC_Transportation_NORM = TPEC_Transportation_NORM;
    }

    public Double getGWP_Transportation_SubScore() {
        return GWP_Transportation_SubScore;
    }

    public void setGWP_Transportation_SubScore(Double GWP_Transportation_SubScore) {
        this.GWP_Transportation_SubScore = GWP_Transportation_SubScore;
    }

    public Double getODP_Transportation_SubScore() {
        return ODP_Transportation_SubScore;
    }

    public void setODP_Transportation_SubScore(Double ODP_Transportation_SubScore) {
        this.ODP_Transportation_SubScore = ODP_Transportation_SubScore;
    }

    public Double getAP_Transportation_SubScore() {
        return AP_Transportation_SubScore;
    }

    public void setAP_Transportation_SubScore(Double AP_Transportation_SubScore) {
        this.AP_Transportation_SubScore = AP_Transportation_SubScore;
    }

    public Double getEP_Transportation_SubScore() {
        return EP_Transportation_SubScore;
    }

    public void setEP_Transportation_SubScore(Double EP_Transportation_SubScore) {
        this.EP_Transportation_SubScore = EP_Transportation_SubScore;
    }

    public Double getPOCP_Transportation_SubScore() {
        return POCP_Transportation_SubScore;
    }

    public void setPOCP_Transportation_SubScore(Double POCP_Transportation_SubScore) {
        this.POCP_Transportation_SubScore = POCP_Transportation_SubScore;
    }

    public Double getTW_Transportation_SubScore() {
        return TW_Transportation_SubScore;
    }

    public void setTW_Transportation_SubScore(Double TW_Transportation_SubScore) {
        this.TW_Transportation_SubScore = TW_Transportation_SubScore;
    }

    public Double getTPEC_Transportation_SubScore() {
        return TPEC_Transportation_SubScore;
    }

    public void setTPEC_Transportation_SubScore(Double TPEC_Transportation_SubScore) {
        this.TPEC_Transportation_SubScore = TPEC_Transportation_SubScore;
    }

    public Double getSum_EPD_SubScore() {
        return this.GWP_EPD_Subsore +this.ODP_EPD_Subsore +this.AP_EPD_Subsore
                +this.EP_EPD_Subsore +this.POCP_EPD_Subsore +this.TPEC_EPD_Subsore;}


    public Double getSum_Transportation_SubScore() {
        return this.GWP_Transportation_SubScore+this.ODP_Transportation_SubScore
                +this.AP_Transportation_SubScore+this.EP_Transportation_SubScore
                +this.POCP_Transportation_SubScore+this.TPEC_Transportation_SubScore;
    }

    public String getCement() {
        return Cement;
    }

    public void setCement(String cement) {
        Cement = cement;
    }

    public String getAir_Percent() {
        return Air_Percent;
    }

    public void setAir_Percent(String air_Percent) {
        Air_Percent = air_Percent;
    }

    public String getWater_Reducer() {
        return Water_Reducer;
    }

    public void setWater_Reducer(String water_Reducer) {
        Water_Reducer = water_Reducer;
    }

    public String getFly_Ash() {
        return Fly_Ash;
    }

    public void setFly_Ash(String fly_Ash) {
        Fly_Ash = fly_Ash;
    }

    public String getSlag() {
        return Slag;
    }

    public void setSlag(String slag) {
        Slag = slag;
    }

    public String getAir_Entrained() {
        return Air_Entrained;
    }

    public void setAir_Entrained(String air_Entrained) {
        Air_Entrained = air_Entrained;
    }

    public String getCrushed_Coarse_Aggregate() {
        return Crushed_Coarse_Aggregate;
    }

    public void setCrushed_Coarse_Aggregate(String crushed_Coarse_Aggregate) {
        Crushed_Coarse_Aggregate = crushed_Coarse_Aggregate;
    }

    public String getCrushed_Fine_Aggregate() {
        return Crushed_Fine_Aggregate;
    }

    public String getHigh_Range_Water_Reducer() {
        return High_Range_Water_Reducer;
    }

    public void setHigh_Range_Water_Reducer(String high_Range_Water_Reducer) {
        High_Range_Water_Reducer = high_Range_Water_Reducer;
    }

    public String getNatural_Coarse_Aggregate() {
        return Natural_Coarse_Aggregate;
    }

    public void setNatural_Coarse_Aggregate(String natural_Coarse_Aggregate) {
        Natural_Coarse_Aggregate = natural_Coarse_Aggregate;
    }

    public String getNatrual_Fine_Aggregate() {
        return Natrual_Fine_Aggregate;
    }

    public void setNatrual_Fine_Aggregate(String natrual_Fine_Aggregate) {
        Natrual_Fine_Aggregate = natrual_Fine_Aggregate;
    }

    public String getLightWeight_Aggregate() {
        return LightWeight_Aggregate;
    }

    public void setLightWeight_Aggregate(String lightWeight_Aggregate) {
        LightWeight_Aggregate = lightWeight_Aggregate;
    }

    public String getAccelerator() {
        return Accelerator;
    }

    public void setAccelerator(String accelerator) {
        Accelerator = accelerator;
    }

    public void setCrushed_Fine_Aggregate(String crushed_Fine_Aggregate) {
        Crushed_Fine_Aggregate = crushed_Fine_Aggregate;
    }

    public String getRegion() {
        return Region;
    }

    public String getMixing_Water() {
        return Mixing_Water;
    }

    public void setMixing_Water(String mixing_Water) {
        Mixing_Water = mixing_Water;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getIsLightWeight() {
        return IsLightWeight;
    }

    public void setIsLightWeight(String isLightWeight) {
        IsLightWeight = isLightWeight;
    }
}
