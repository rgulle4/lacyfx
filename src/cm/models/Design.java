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
        for (int i = 0; i < numberOfLayers; i++) {
            this.addLayer();
        }
    }

    /* -- Methods ------------------------------------------------------- */

    /**
     * Set number of layers in the design, adding or removing at
     * the the bottom as appropriate.
     * @param numberOfLayers The number of layers.
     * @return this, for chaining.
     */
    public Design setNumberOfLayers(int numberOfLayers) {
        int numberOfLayersToAdd = numberOfLayers - getNumberOfLayers();
        if (getNumberOfLayers() == 0 || numberOfLayersToAdd > 0) {
            for (int i = 0; i < numberOfLayersToAdd; i++) {
                this.addLayer();
            }
        } else if (numberOfLayersToAdd < 0) {
            for (int i = numberOfLayersToAdd; i < 0; i++) {
                this.removeLayer();
            }
        } else {
            System.out.println("sadf");
        }
        return this;
    }



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

    /**
     * Removes the bottom layer if it exists, otherwise noop.
     * @return this.
     */
    public Design removeLayer() {
        if (getNumberOfLayers() > 0)
            layers.remove(layers.size() - 1);
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
        System.out.println("-----");
        d = new Design();
        d.setNumberOfLayers(3);
        System.out.println(d.getNumberOfLayers());
        d.setNumberOfLayers(2);
        System.out.println(d.getNumberOfLayers());
        d.setNumberOfLayers(6);
        System.out.println(d.getNumberOfLayers());
    }

    /* == "Old" Stuff =================================================== */


    private String Design_ID;
    private String Design_Type;
    private String Pavement_Type;
    private double EnvPerfAnalysis_TotalScore_Design;
    private double EnvPerfAnalysis_EPDScore_Design;
    private double EnvPerfAnalysis_TransportationScore_Design;

    public String getDesign_ID() {
        return Design_ID;
    }

    public Design setDesign_ID(String design_ID) {
        Design_ID = design_ID;
        return this;
    }

    public String getDesign_Type() {
        return Design_Type;
    }

    public Design setDesign_Type(String design_Type) {
        Design_Type = design_Type;
        return this;
    }

    public String getPavement_Type() {
        return Pavement_Type;
    }

    public Design setPavement_Type(String pavement_Type) {
        Pavement_Type = pavement_Type;
        return this;
    }

    public int getNumberOfLayers() {
        return layers.size();
//        return NumberOfLayers;
    }

    public double getEnvPerfAnalysis_TotalScore_Design() {
        return EnvPerfAnalysis_TotalScore_Design;
    }

    public Design setEnvPerfAnalysis_TotalScore_Design(double envPerfAnalysis_TotalScore_Design) {
        EnvPerfAnalysis_TotalScore_Design = envPerfAnalysis_TotalScore_Design;
        return this;
    }

    public double getEnvPerfAnalysis_EPDScore_Design() {
        return EnvPerfAnalysis_EPDScore_Design;
    }

    public Design setEnvPerfAnalysis_EPDScore_Design(double envPerfAnalysis_EPDScore_Design) {
        EnvPerfAnalysis_EPDScore_Design = envPerfAnalysis_EPDScore_Design;
        return this;
    }

    public double getEnvPerfAnalysis_TransportationScore_Design() {
        return EnvPerfAnalysis_TransportationScore_Design;
    }

    public Design setEnvPerfAnalysis_TransportationScore_Design(double envPerfAnalysis_TransportationScore_Design) {
        EnvPerfAnalysis_TransportationScore_Design = envPerfAnalysis_TransportationScore_Design;
        return this;
    }
}
