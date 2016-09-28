package cm.models;

/**
 * A material is a component of a layer (along with a defined thickness). A
 * material will also probably correspond to a row in the EPD database.
 */
public class Material {

    /* -- Fields ----------------------------------------------- */

    // Fields taken directly from the EPD database and/or the AASHTOWARE
    // output. There's lots of these!

    private double poissonsRatio; // [unitless?] (e.g. 0.5)
    private double coeffOfLateralEarthPressure; // [k0] Coefficient of lateral earth pressure (e.g. 0.5)
    // TODO: add more


    // Calculated fields... (if any).

    private double placeholder;
    //etc...



    /* -- Constructor(s) --------------------------------------- */

    // TODO: find a decent creation pattern (Effective Java and/or GoF).

    /**
     * Constructs an empty material.
     */
    private Material() {
        // noop
    }

    /* -- Method(s) -------------------------------------------- */

    /* -- Calculation(s) --------------------------------------- */

}
