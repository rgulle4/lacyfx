package models;

import java.util.ArrayList;

/**
 * A layer has a thickness (inches) and a material.
 */
public class Layer {

    /* -- Fields ----------------------------------------------- */

    private double thickness; // inches, for now
    private Material material;

    /* -- Constructor(s) --------------------------------------- */

    /**
     * Constructs a layer with a thickness (inches) and a material.
     * @param thickness The layer thickness, in inches.
     * @param material The material.
     */
    public Layer(double thickness, Material material) {
        this.thickness = thickness;
        this.material = material;
    }

    /**
     * Constructs an empty layer.
     */
    private Layer() {
        // noop
    }

    /* -- Method(s) -------------------------------------------- */

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    /* -- Calculation(s) --------------------------------------- */

    // TODO: add these. Are these even necessary for Layers?


}
