package cm.models;

import java.util.HashMap;

/**
 * The main model.
 */
public class Model {

    // the collection of designs that we're evaluating
    public static HashMap<String, Design> designs = new HashMap<>();

    // the "weights" that we'll use for scoring
    public static Weights weights = new Weights();

    // -- experiments ---------------------------- //

    public static void foo() {
        System.out.println("Model.foo() called!");
    }

    public static void main(String[] args) {
        weights.setwOdp(200);
        weights.setwRenewableEnergyConsumption(500);
        weights.setwGwp(20).setwOdp(3);
    }
}
