package cm.controllers;

import cm.models.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class LayerController {

    private Layer currentLayer;

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
    public void initialize() {
        setDefaultOptions();
    }

    private void setDefaultOptions() {
        layerTypeComboBox.setItems(layerTypes);
        thicknessUnitChoiceBox.setItems(thicknessUnits);

        select(layerTypeComboBox, 1);
        selectFirst(thicknessUnitChoiceBox);
        thicknessTextField.setText("10.0");
    }

    private void saveLayerType() {
        currentLayer.setLayerType(toString(layerTypeComboBox));
    }

    private void saveThickness() {
        Double thicknessValue = toDouble(thicknessTextField);
        String thicknessUnit = toString(thicknessUnitChoiceBox);
        if (thicknessUnit == "inch")
            thicknessValue /= 39.3701;
        currentLayer.setThickness(thicknessValue);
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
}
