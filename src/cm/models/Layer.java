package cm.models;

/**
 * A layer has a thickness (inches) and a material.
 */
public class Layer {

    /* -- Fields -------------------------------------------------------- */

    // A layer has one material
    private Material material;

    // A layerId is probably "layer1", "layer2", etc.
    private String layerId;

    // Dimensions... the user specifies thickness and thicknessUnit.
    private double length = 1609.34;        //1 mile = 1609.34 meter
    private double width = 12*0.3048;       //1 ft = 0.3048 meter;
    private double thickness;
    private String thicknessUnit;
    private double volume;
    private String volumeUnit;

    // Layer type determines which material database to use.
    // Maybe we should enumerate these?
    //   - Asphalt Concrete
    //   - Portland Cement Concrete
    //   - Aggregate
    // Right now, we just have `db.rt`, which is for Portland
    // Cement Concrete.
    private String layerType;

    /* == "Old" Stuff =================================================== */

    // Calculated values
    private double EnvPerfAnalysis_TotalScore_Layer;
    private double EnvPerfAnalysis_EPDScore_Layer;
    private double EnvPerfAnalysis_TransportationScore_Layer;

    // User inputs for weights of the different env impacts (EPD).
    // Each of these is calculated by the impact's formula, times the
    // "weight" specified by the user.
    private double GWP_EDP_Ctb;
    private double ODP_EDP_Ctb;
    private double AP_EDP_Ctb;
    private double EP_EDP_Ctb;
    private double POCP_EDP_Ctb;
    private double TW_EDP_Ctb;      // Total Water Consumption
    private double TPEC_EDP_Ctb;    // Total Primary Energy Consumption

    private double GWP_Transportation_Ctb;
    private double ODP_Transportation_Ctb;
    private double AP_Transportation_Ctb;
    private double EP_Transportation_Ctb;
    private double POCP_Transportation_Ctb;
    private double TW_Transportation_Ctb;      // Total Water Consumption
    private double TPEC_Transportation_Ctb;    // Total Primary Energy Consumption

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

    public String getThicknessUnit() {
        return thicknessUnit;
    }

    public void setThicknessUnit(String thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
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
}
