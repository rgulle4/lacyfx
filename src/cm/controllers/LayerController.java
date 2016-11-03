package cm.controllers;

import cm.models.Layer;
import static cm.models.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LayerController {

    private Layer layer;

    public LayerController() { super(); }

    public LayerController(Layer layer) {
        this();
        setCurrentLayer(layer);
    }

    public Layer getCurrentLayer() {
        return layer;
    }

    public void setCurrentLayer(Layer layer) {
        this.layer = layer;
    }

    /* -- Gui control ------------------------------------------ */

    @FXML private ComboBox<String> layerTypeComboBox;
    @FXML private TextField thicknessTextField;
    @FXML private ChoiceBox<String> thicknessUnitChoiceBox;

    // The allowed material types
    private final ObservableList<String> layerTypes
          = FXCollections.observableArrayList(
          "Asphalt Concrete",
          "Portland Cement Concrete",
          "Aggregate");

    // The allowed thickness units
    private final ObservableList<String> thicknessUnits
          = FXCollections.observableArrayList(
          "inch",
          "meter");

    @FXML
    private void initialize() {
        setDefaultOptions();
        setUpDebugCheatSheet();
    }

    private void setDefaultOptions() {
        layerTypeComboBox.setItems(layerTypes);
        thicknessUnitChoiceBox.setItems(thicknessUnits);

        select(layerTypeComboBox, 1);
        selectFirst(thicknessUnitChoiceBox);
        thicknessTextField.setText("10.0");
    }

    private void saveLayerOptions() {
        saveLayerType();
        saveThickness();
    }

    private void saveLayerType() {
        layer.setLayerType(toString(layerTypeComboBox));
    }

    private void saveThickness() {
        Double thicknessValue = toDouble(thicknessTextField);
        String thicknessUnit = toString(thicknessUnitChoiceBox);
        if (thicknessUnit == "inch")
            thicknessValue /= 39.3701;
        layer.setThickness(thicknessValue);
    }

    /* -- aliases for dealing with javafx components ------ */

    private void select(ComboBox cbb, int index) {
        cbb.getSelectionModel().select(index);
    }

    private void selectFirst(ComboBox cbb) {
        cbb.getSelectionModel().selectFirst();
    }

    private void selectFirst(ChoiceBox<String> chb) {
        chb.getSelectionModel().selectFirst();
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

    @FXML private Tooltip debugCheatSheet;
    private final boolean DEBUG_MODE  = true;
    private void println() { System.out.println(); }
    private void println(Object o) { System.out.println(o); }
    private void printDebugMsg() { if (DEBUG_MODE) println(); }
    private void printDebugMsg(Object o) { if (DEBUG_MODE) println(o); }
    private void setUpDebugCheatSheet() {
        if (DEBUG_MODE == false) { return; }
        debugCheatSheet.activatedProperty().addListener(
              (observable, oldValue, newValue) -> {
                  debugCheatSheet.setText(GSON_PP.toJson(layer));
              });
    }
}
