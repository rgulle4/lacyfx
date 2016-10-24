package cm.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The main model.
 */
public class Model {

    // Instantiating a model isn't allowed, because it's a singleton.
    private Model() { /* */ }

    // the collection of DESIGNS that we're evaluating
    public static final Map<String, Design> DESIGNS = new LinkedHashMap<>();

    // the "WEIGHTS" that we'll use for scoring
    public static final Weights WEIGHTS = new Weights();

    // the destination zip code (used for distance calculations).
    public static String DESTINATION_ZIP_CODE_MUTABLE;

    // the "NORMALIZATIONS" that we'll use for scoring;
    public static final Normalization NORMALIZATIONS = new Normalization();

    // -- experiments ---------------------------- //

    public static void foo() {
        System.out.println("Model.foo() called!");
    }

    public static void main(String[] args) {
        WEIGHTS.setwOdp(200);
        WEIGHTS.setwRenewableEnergyConsumption(500);
        WEIGHTS.setwGwp(20).setwOdp(3);
    }
}
