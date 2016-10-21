package cm.models;

/**
 * A design is a pavement design, consisting of a number of layers. Each layer
 * has a thickness, and a material.
 */
public class Design {

    private String Design_ID;
    private String Design_Type;
    private String Pavement_Type;
    private int NumberOfLayers;
    private double EnvPerfAnalysis_TotalScore_Design;
    private double EnvPerfAnalysis_EPDScore_Design;
    private double EnvPerfAnalysis_TransportationScore_Design;

    public String getDesign_ID() {
        return Design_ID;
    }

    public void setDesign_ID(String design_ID) {
        Design_ID = design_ID;
    }

    public String getDesign_Type() {
        return Design_Type;
    }

    public void setDesign_Type(String design_Type) {
        Design_Type = design_Type;
    }

    public String getPavement_Type() {
        return Pavement_Type;
    }

    public void setPavement_Type(String pavement_Type) {
        Pavement_Type = pavement_Type;
    }

    public int getNumberOfLayers() {
        return NumberOfLayers;
    }

    public void setNumberOfLayers(int numberOfLayers) {
        NumberOfLayers = numberOfLayers;
    }

    public double getEnvPerfAnalysis_TotalScore_Design() {
        return EnvPerfAnalysis_TotalScore_Design;
    }

    public void setEnvPerfAnalysis_TotalScore_Design(double envPerfAnalysis_TotalScore_Design) {
        EnvPerfAnalysis_TotalScore_Design = envPerfAnalysis_TotalScore_Design;
    }

    public double getEnvPerfAnalysis_EPDScore_Design() {
        return EnvPerfAnalysis_EPDScore_Design;
    }

    public void setEnvPerfAnalysis_EPDScore_Design(double envPerfAnalysis_EPDScore_Design) {
        EnvPerfAnalysis_EPDScore_Design = envPerfAnalysis_EPDScore_Design;
    }

    public double getEnvPerfAnalysis_TransportationScore_Design() {
        return EnvPerfAnalysis_TransportationScore_Design;
    }

    public void setEnvPerfAnalysis_TransportationScore_Design(double envPerfAnalysis_TransportationScore_Design) {
        EnvPerfAnalysis_TransportationScore_Design = envPerfAnalysis_TransportationScore_Design;
    }
}
