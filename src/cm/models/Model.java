package cm.models;

import java.util.HashMap;

/**
 * The main model.
 */
public class Model {

    // Instantiating a model isn't allowed, because it's a singleton.
    private Model() { /* */ }

    // the collection of DESIGNS that we're evaluating
    public static final HashMap<String, Design> DESIGNS = new HashMap<>();

    // the "WEIGHTS" that we'll use for scoring
    public static final Weights WEIGHTS = new Weights();

    // the destination zip code (used for distance calculations).
    public static String DESTINATION_ZIP_CODE_MUTABLE;

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
