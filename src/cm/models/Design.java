package cm.models;

import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.List;

/**
 * A design is a pavement design, consisting of a number of layers. Each layer
 * has a thickness, and a material.
 */
public class Design {

    /* -- Fields -------------------------------------------------------- */

    // A design has a bunch of layers, added from top to bottom.
    private List<Layer> layers = new ArrayList<>();

    /* -- Constructor(s) ------------------------------------------------ */

    /**
     * Construct a new, empty design
     */
    public Design() {
        /* noop */
    }

    /**
     * Construct a new design with a number of empty layers.
     * @param numberOfLayers The number of layers, typically between 2 and 8.
     */
    public Design(int numberOfLayers) {
        this();
        this.setNumberOfLayers(numberOfLayers);
    }

    /* -- Methods ------------------------------------------------------- */

    /**
     * Return a layer by number, layer 1 is the top.
     * @param layerNumber
     * @return
     */
    public Layer getLayer(int layerNumber) {
        return layers.get(layerNumber - 1);
    }

    /**
     * Get the list of layers.
     * @return the list of layers.
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * Add a layer to the bottom, or to the top if the design is empty.
     * @param layer The layer to add.
     */
    public Design addLayer(Layer layer) {
        layers.add(layer);
        return this;
    }

    /**
     * Add an empty layer to the bottom, or to the top if the design is empty.
     */
    public Design addLayer() {
        layers.add(new Layer());
        return this;
    }

    public Design setLayer(int layerNumber, Layer layer) {
        layers.set(layerNumber - 1, layer);
        return this;
    }

    /* -- Tester -------------------------------------------------------- */

    public static void main(String[] args) {
        Design d = new Design();
        System.out.println(d.getNumberOfLayers());
        d.addLayer();
        System.out.println(d.getNumberOfLayers());
        d.addLayer(new Layer());
        System.out.println(d.getNumberOfLayers());
        d = new Design(8);
        System.out.println(d.getNumberOfLayers());
    }

    /* == "Old" Stuff =================================================== */


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
        return layers.size();
//        return NumberOfLayers;
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
