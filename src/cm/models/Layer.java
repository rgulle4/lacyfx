package cm.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A layer_temp has a thickness (inches) and a material.
 */
public final class Layer {

    private int hasCode = this.hashCode();

    /* -- Fields -------------------------------------------------------- */

    // A layer_temp has one material
    private List<Mix> mixes = new ArrayList<>();

    // A layerId is probably "layer1", "layer2", etc.
    private String layerId;

    // Dimensions... the user specifies thickness and thicknessUnit.
    private Double length = 1609.34;        // 1 mile
    private Double width = 12/3.28084;       // 12 ft
    private Double thickness;

    // Layer type determines which material database to use.
    // Maybe we should enumerate these?
    //   - Asphalt Concrete
    //   - Portland Cement Concrete
    //   - Aggregate
    // Right now, we just have `db.rt`, which is for Portland
    // Cement Concrete.
    private String layerType;

    public Layer setNumberofMaterials(int numberofMaterials){
        int numberofMaterialsToAdd = numberofMaterials - getNumberofMaterials();
        if(getNumberofMaterials() == 0 || numberofMaterialsToAdd > 0){
            for (int i = 0; i < numberofMaterialsToAdd; i++){
                this.addMaterial();
            }
        }else if (numberofMaterialsToAdd < 0){
            for (int i = numberofMaterialsToAdd; i < 0; i++){
                this.removeMaterial();
            }
        }else{
            System.out.println("sadf");
        }
        return this;
    }

    public List<Mix> getMixes(){
        return mixes;
    }

    public Layer addMaterial(Mix mix) {
        this.mixes.add(mix);
        return this;
    }
    public Layer addMaterial(){
        mixes.add(new Mix());
        return this;
    }

    public Mix getMaterial(int materialNumber) {
        return mixes.get(materialNumber);
    }

    public int getNumberofMaterials(){
        return mixes.size();
    }

    public Layer removeMaterial(){
        if (getNumberofMaterials() > 0)
            mixes.remove(mixes.size() - 1);
        return this;
    }
    public Layer setMaterial(int materialIndex, Mix mix){
        mixes.set(materialIndex, mix);
        return this;
    }

    /* == "Old" Stuff =================================================== */

    // Calculated values
    private Double EnvPerfAnalysis_TotalScore_Layer;
    private Double EnvPerfAnalysis_EPDScore_Layer;
    private Double EnvPerfAnalysis_TransportationScore_Layer;

    // Subscores of EPD score for the different env impacts (before
    // NORMALIZATIONS). Each of these is calculated using the same basic
    // formula:
    //   GWP_EDP_Ctb = gwp (from material with epd.value per epd.unit)
    //                 * "Conversion Factor (volume with matching unit)"
        private Double GWP_EDP_Ctb;
        private Double ODP_EDP_Ctb;
        private Double AP_EDP_Ctb;
        private Double EP_EDP_Ctb;
        private Double POCP_EDP_Ctb;
        private Double TW_EDP_Ctb;      // Total Water Consumption
        private Double TPEC_EDP_Ctb;    // Total Primary Energy Consumption

        // EPD raw value for the different env impacts (after NORMALIZATIONS)
        private Double GWP_EDP_NORM;
        private Double ODP_EDP_NORM;
        private Double AP_EDP_NORM;
        private Double EP_EDP_NORM;
        private Double POCP_EDP_NORM;
        private Double TW_EDP_NORM;      // Total Water Consumption
        private Double TPEC_EDP_NORM;    // Total Primary Energy Consumption

        // EPD's SubScore for the different env impacts (after WEIGHTS)
        private Double GWP_EDP_Subsore;
        private Double ODP_EDP_Subsore;
        private Double AP_EDP_Subsore;
        private Double EP_EDP_Subsore;
        private Double POCP_EDP_Subsore;
        private Double TW_EDP_Subsore;      // Total Water Consumption
        private Double TPEC_EDP_Subsore;    // Total Primary Energy Consumption

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
        private Double GWP_Transportation_Subsore;
        private Double ODP_Transportation_Subsore;
        private Double AP_Transportation_Subsore;
        private Double EP_Transportation_Subsore;
        private Double POCP_Transportation_Subsore;
        private Double TW_Transportation_Subsore;      // Total Water Consumption
        private Double TPEC_Transportation_Subsore;    // Total Primary Energy Consumption

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double getVolume() {
        return getThickness() * getLength() * getWidth();
    }

    public double getEnvPerfAnalysis_TotalScore_Layer() {
        return EnvPerfAnalysis_TotalScore_Layer;
    }

    public void setEnvPerfAnalysis_TotalScore_Layer(double envPerfAnalysis_TotalScore_Layer) {
        EnvPerfAnalysis_TotalScore_Layer = envPerfAnalysis_TotalScore_Layer;
    }

    public double getEnvPerfAnalysis_EPDScore_Layer() {
        return EnvPerfAnalysis_EPDScore_Layer;
    }

    public void setEnvPerfAnalysis_EPDScore_Layer(double envPerfAnalysis_EPDScore_Layer) {
        EnvPerfAnalysis_EPDScore_Layer = envPerfAnalysis_EPDScore_Layer;
    }

    public double getEnvPerfAnalysis_TransportationScore_Layer() {
        return EnvPerfAnalysis_TransportationScore_Layer;
    }

    public void setEnvPerfAnalysis_TransportationScore_Layer(double envPerfAnalysis_TransportationScore_Layer) {
        EnvPerfAnalysis_TransportationScore_Layer = envPerfAnalysis_TransportationScore_Layer;
    }

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String layerType) {
        this.layerType = layerType;
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
