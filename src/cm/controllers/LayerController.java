package cm.controllers;

import cm.App;
import cm.models.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import static cm.models.Model.GSON_PP;

public final class LayerController {

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
        setUpAutoSaves();
        setUpDebugCheatSheet();
        saveLayerOptions();
    }

    private void setUpAutoSaves() {
        // listens for changes
        layerTypeComboBox.getSelectionModel().selectedIndexProperty().addListener(
              ((observable, oldValue, newValue) -> {
                  saveLayerType();
              }));

        // listens for when user hits ENTER within the text field
        thicknessTextField.setOnAction(
              (event) -> {
                  saveThickness();
              });
        
        // listens for when entering/exiting focus
        thicknessTextField.focusedProperty().addListener((
              (observable, oldValue, newValue) -> {
                  saveThickness();
              }));

        // listens for changes
            thicknessUnitChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
              (((observable, oldValue, newValue) -> {
                  saveThickness();
              })));
    }

    private void setDefaultOptions() {
        layerTypeComboBox.setItems(layerTypes);
        thicknessUnitChoiceBox.setItems(thicknessUnits);

        select(layerTypeComboBox, 1);
        selectFirst(thicknessUnitChoiceBox);
        thicknessTextField.setText("10.0");
    }
    
    public void saveLayerOptions() {
        if (layer == null) { return ; }
        saveLayerType();
        saveThickness();
    }

    private void saveLayerType() {
        if (layer == null) { return ; }
        layer.setLayerType(toString(layerTypeComboBox));
    }

    private void saveThickness() {
        if (layer == null) { return ; }
        Double thicknessValue = toDouble(thicknessTextField);
        String thicknessUnit = toString(thicknessUnitChoiceBox);
        if (thicknessUnit == "inch")
            thicknessValue /= 39.3701;
        layer.setThickness(thicknessValue);
    }

    @FXML Button loadMaterialBtn;
    @FXML
    private void loadMaterialBtnAction() throws IOException {
        saveLayerType();
        saveThickness();
        App.showLoadMaterial(layer);
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
