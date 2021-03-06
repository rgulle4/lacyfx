package cm.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A design is a pavement design, consisting of a number of layers. Each layer
 * has a thickness, and a material.
 */
public final class Design {

    /* -- Fields -------------------------------------------------------- */

    private String designId;
    private String designType;
    private String pavementType;

    // A design has a bunch of layers, added from top to bottom.
    private List<Layer> layers = new ArrayList<>();

    private Double EnvPerfAnalysis_TotalScore_Design;
    private Double EnvPerfAnalysis_EPDScore_Design;
    private Double EnvPerfAnalysis_TransportationScore_Design;

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
     * Return a layer by number, layer 0 is the top.
     * @param layerNumber
     * @return
     */
    public Layer getLayer(int layerNumber) {
        return layers.get(layerNumber);
    }

    public boolean hasLayerIndex(int layerIndex) {
        if (layerIndex < 0 || layerIndex >= layers.size())
            return false;
        return true;
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
     * @param layer
     * @return the newly-added layer.
     */
    public Layer addLayer(Layer layer) {
        layers.add(layer);
        return layer;
    }

    /**
     * Add an empty layer to the bottom, or to the top if the design is empty.
     * @return The newly-added empty layer.
     */
    public Layer addLayer() {
        Layer newLayer  = new Layer();
        layers.add(newLayer);
        return newLayer;
    }

    /**
     * Removes the bottom layer if it exists, otherwise noop.
     * @return The newly-removed layer.
     */
    public Layer removeLayer() {
        if (getNumberOfLayers() > 0)
            return layers.remove(layers.size() - 1);
        return null;
    }

    public Design setLayer(int layerNumber, Layer layer) {
        layers.set(layerNumber - 1, layer);
        return this;
    }

    public String getDesignId() {
        return designId;
    }

    public Design setDesignId(String designId) {
        this.designId = designId;
        return this;
    }

    public String getDesignType() {
        return designType;
    }

    public Design setDesignType(String designType) {
        this.designType = designType;
        return this;
    }

    public String getPavementType() {
        return pavementType;
    }

    public Design setPavementType(String pavementType) {
        this.pavementType = pavementType;
        return this;
    }

    public int getNumberOfLayers() {
        return layers.size();
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

    /* -- debugging -------------------------------------------- */

    private static final Gson GSON_PP = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String toString() { return GSON_PP.toJson(this); }

}
