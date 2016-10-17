package cm.models;

/**
 * A layer has a thickness (inches) and a material.
 */
public class Layer {

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
}
