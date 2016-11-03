package cm.controllers;

import static cm.models.Model.*;
import cm.models.Design;
import cm.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class DesignController {

    /* -- Constructor(s) and init ----------------------------- */

    private Design design;

    public DesignController() {
        super();
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

    @FXML private TabPane layersTabPane;
    @FXML private Tab newTabTab;

    private List<Tab> layerTabsList;

    private void addLayer() {
        if (layerTabsList == null) return;
        int numTabs = layerTabsList.size();
        int newTabPosition = numTabs - 1;
        int newLayerNumber = newTabPosition + 1;
        String newLayerId = "Layer " + newLayerNumber;
    }

    // Design types
    private final ObservableList<String> designTypes
          = FXCollections.observableArrayList(
          "New pavement",
          "Overlay");

    // Pavement types (when design is new pavement)
    private final ObservableList<String> newPavementTypes
          = FXCollections.observableArrayList(
          "Flexible pavement",
          "Joint Reinforced concrete pavement");

    // Pavement types (when design is overlay)
    private final ObservableList<String> overlayPavementTypes
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

    private final boolean DEBUG_MODE  = true;
    private void println() { System.out.println(); }
    private void println(Object o) { System.out.println(o); }
    private void printDebugMsg() { if (DEBUG_MODE) println(); }
    private void printDebugMsg(Object o) { if (DEBUG_MODE) println(o); }
}
