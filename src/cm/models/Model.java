package cm.models;

import java.util.HashMap;

/**
 * The main model.
 */
public class Model {

    // the collection of designs that we're evaluating
    public static HashMap<String, Design> designs = new HashMap<>();

    public static void foo() {
        System.out.println("Model.foo() called!");
    }
}
