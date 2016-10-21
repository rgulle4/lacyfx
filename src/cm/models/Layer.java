package cm.models;

/**
 * A layer has a thickness (inches) and a material.
 */
public class Layer {

    private Material material;

    private String Layer_ID;
    private double Lengthness= 1609.34;        //1 mile = 1609.34 meter
    private double Width = 12*0.3048;       //1 ft = 0.3048 meter;
    private double Thickness;
    private String ThicknessUnit;
    private double Volume;
    private String VolumneUnit;
    private double EnvPerfAnalysis_TotalScore_Layer;
    private double EnvPerfAnalysis_EPDScore_Layer;
    private double EnvPerfAnalysis_TransportationScore_Layer;
    private String LayerType;

    //distributed contribution in details
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

    public double getLengthness() {
        return Lengthness;
    }

    public double getWidth() {
        return Width;
    }

    public String getLayer_ID() {
        return Layer_ID;
    }

    public void setLayer_ID(String layer_ID) {
        Layer_ID = layer_ID;
    }

    public double getThickness() {
        return Thickness;
    }

    public void setThickness(double thickness) {
        Thickness = thickness;
    }

    public String getThicknessUnit() {
        return ThicknessUnit;
    }

    public void setThicknessUnit(String thicknessUnit) {
        ThicknessUnit = thicknessUnit;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public String getVolumneUnit() {
        return VolumneUnit;
    }

    public void setVolumneUnit(String volumneUnit) {
        VolumneUnit = volumneUnit;
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
        return LayerType;
    }

    public void setLayerType(String layerType) {
        LayerType = layerType;
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
