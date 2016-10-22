package cm.controllers;

import cm.App;
import cm.models.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Created by royg59 on 9/21/16.
 */
public class LayerInformationController {

    // The allowed material types
    ObservableList<String> layerTypes = FXCollections.observableArrayList(
          "Asphalt Concrete",
          "Portland Cement Concrete",
          "Aggregate");

    // The allowed thickness units
    ObservableList<String> thicknessUnits = FXCollections.observableArrayList(
          "inch",
          "meter");


    // Some layer specs
    @FXML
    public ComboBox layerTypeComboBox;
    @FXML
    public TextField thicknessTextField;
    @FXML
    public ChoiceBox thicknessUnitChoiceBox;

    // The tabs
    @FXML
    public Tab DesginTab1;
    @FXML
    public Tab LayerTab1;


    // used to parse design/layer numbers
    private StringBuilder sb;

    @FXML
    private void initialize() {

        //Design 1
        layerTypeComboBox.setItems(layerTypes);
        thicknessUnitChoiceBox.setValue("inch");
        thicknessUnitChoiceBox.setItems(thicknessUnits);
        thicknessTextField.setText("10.0");

    }

    private Layer layer = new Layer();
    @FXML
    private void LoadMatBtn() throws IOException {

        if (thicknessUnitChoiceBox.getValue() == "meter") {
            layer.setThickness(toDouble(thicknessTextField));
        }

        if (thicknessUnitChoiceBox.getValue() == "inch") {
            layer.setThickness(toDouble(thicknessTextField)
                               * 0.0254);
        }

        App.showLoadMaterial();
    }

    private Double toDouble(TextInputControl o) {
        return Double.parseDouble(o.getText());
    }
}
