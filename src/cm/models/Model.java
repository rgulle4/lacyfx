package cm.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

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

    // the "TRANSPORTATION_PARAMETERS" that we will use for scoring
    public static final TransportationParameters TRANSPORTATION_PARAMETERS = new TransportationParameters();

    /**
     * Append a new design to DESIGNS, with an appropriate key.
     * @return The new design object, for convenience.
     */
    public static final Design addNewDesign() {
        int newDesignNumber = 1 + DESIGNS.size();
        String newDesignKey = "Design " + newDesignNumber;
        Design newDesign = (new Design()).setDesignId(newDesignKey);
        DESIGNS.put(newDesignKey, newDesign);
        return newDesign;
    }

    /* -- debugging -------------------------------------------- */
    public static final Gson GSON_PP
          = new GsonBuilder().setPrettyPrinting().create();

    private static final boolean DEBUG_MODE = true;
    private static void printDebugMsg() { if (DEBUG_MODE) println(); }
    private static void printDebugMsg(Object o) { if (DEBUG_MODE) println(o); }

    private static void println() { System.out.println(); }
    private static void println(Object o) { System.out.println(o);}

    public static final void printDebugDesigns() {
        if (!DEBUG_MODE) return;
        printDebugMsg("=======================");
        printDebugMsg("Number of designs: " + DESIGNS.size() + "... ");
        printDebugMsg(GSON_PP.toJson(DESIGNS));
        printDebugMsg("- - - - - - - - - - - -");
    }
}
