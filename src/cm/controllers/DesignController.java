package cm.controllers;

import static cm.models.Model.*;
import cm.models.Design;
import cm.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DesignController {

    /* -- Constructor(s) and init ----------------------------- */

    private Design design;

    private static boolean firstInstanceHasBeenMade = false;
    public DesignController() {
        super();
        if (!firstInstanceHasBeenMade)
            firstInstanceHasBeenMade = true;
//        if (firstInstanceHasBeenMade)
//            this.setCurrentDesign(Model.addNewDesign());
    }

    public DesignController(Design design) {
        this();
        this.design = design;
    }

    public Design getCurrentDesign() {
        return design;
    }

    public void setCurrentDesign(Design design) {
        this.design = design;
        if (design.getNumberOfLayers() == 0)
            design.addLayer();
    }

    public void setCurrentDesign(String designKey) {
        if (DESIGNS.containsKey(designKey)) {
            design = DESIGNS.get(designKey);
            printDebugMsg("setCurrentDesign() success");
        } else {
            printDebugMsg("Couldn't set current design with key = " + designKey);
        }
    }

    @FXML
    private void debugDesign() {
        printDebugMsg("++++++++++ debugDesign() +++++++++++++++");
        printDebugMsg("Number of designs: " + DESIGNS.size());
        printDebugMsg("Current design: ");
        printDebugMsg(design);
        printDebugMsg("---------- debugDesign() ---");
    }

    @FXML
    private void initialize() {
        setDesignOptionsToDefaults();
//        saveDesignOptions();
    }

    private void setDesignOptionsToDefaults() {
        designTypeComboBox.setItems(designTypes);
        selectFirst(designTypeComboBox);
        populatePavementTypeComboBox();
        selectFirst(pavementTypeComboBox);
    }

    @FXML
    private void saveDesignOptions() {
        design.setDesignType(toString(designTypeComboBox));
        design.setPavementType(toString(pavementTypeComboBox));
    }

    /* -- Gui control ------------------------------------------ */

    @FXML private ComboBox designTypeComboBox;
    @FXML private ComboBox pavementTypeComboBox;

    // Design types
    ObservableList<String> designTypes = FXCollections.observableArrayList(
          "New pavement",
          "Overlay");

    // Pavement types (when design is new pavement)
    ObservableList<String> newPavementTypes
          = FXCollections.observableArrayList(
          "Flexible pavement",
          "Joint Reinforced concrete pavement");

    // Pavement types (when design is overlay)
    ObservableList<String> overlayPavementTypes
          = FXCollections.observableArrayList(
          "AC over AC",
          "AC over JRCP");

    @FXML
    private void populatePavementTypeComboBox() {
        if (selectedDesignTypeIsNewPavement())
            pavementTypeComboBox.setItems(newPavementTypes);
        else if (selectedDesignTypeIsOverlay())
            pavementTypeComboBox.setItems(overlayPavementTypes);
        else
            return;
        selectFirst(pavementTypeComboBox);
    }

    private boolean selectedDesignTypeIsNewPavement() {
        return (designTypeComboBox.getSelectionModel().isSelected(0));
    }

    private boolean selectedDesignTypeIsOverlay() {
        return (designTypeComboBox.getSelectionModel().isSelected(1));
    }


    /* -- aliases for dealing with javafx components ----------- */

    private void selectFirst(ComboBox cbb) {
        cbb.getSelectionModel().selectFirst();
    }

    private Double toDouble(TextInputControl o) {
        return Double.parseDouble(o.getText());
    }

    private String toString(ComboBox cbb) {
        return cbb.getValue().toString();
    }

    private String toString(ChoiceBox chb) {
        return chb.getValue().toString();
    }


    /* -- helper methods for debugging ------------------------- */

    private void println() { System.out.println(); }
    private void println(Object o) { System.out.println(o); }

    private final boolean DEBUG_MODE  = true;

    private void printDebugMsg() { if (DEBUG_MODE) println(); }
    private void printDebugMsg(Object o) { if (DEBUG_MODE) println(o); }

    private void printCurrentIndexes() {
//        if (!DEBUG_MODE) return;
//        printDebugMsg("-----------");
//        printDebugMsg("Design Key = " + currentDesignKey);
//        printDebugMsg("Layer index = " + currentLayerIndex);
    }

    @FXML
    private void printDebugStuff(ActionEvent actionEvent) {
//        if (!DEBUG_MODE) return;
//        printDebugMsg(currentLayer.getMaterial().getCS() + ","
//              + currentLayer.getMaterial().getCompany_Name() + " ,"
//              + currentLayer.getMaterial().getLocation() + " ,"
//              + currentLayer.getMaterial().getZipCode() + " ,"
//              + currentLayer.getMaterial().getMixNum());
    }
}
