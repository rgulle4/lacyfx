package cm.controllers;

import cm.App;
import static cm.models.Model.*;
import cm.models.Design;
import cm.models.Layer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    // The TabPanes
    @FXML
    TabPane designsTabPane;
    @FXML
    TabPane layersTabPane;

    // vars we'll use for current design, current layer
    String currentDesignKey = "Design 1";
    int currentLayerIndex = 0;

    Design currentDesign = null;
    Layer currentLayer = null;

    private void printCurrentIndexes() {
        System.out.println("-----------");
        System.out.println("Design Key = " + currentDesignKey);
        System.out.println("Layer index = " + currentLayerIndex);
    }

    private void addDesignsTabsListener() {
        designsTabPane.getSelectionModel().selectedItemProperty()
              .addListener((observable, oldValue, newValue) -> {
                  currentDesignKey = getSelectedTabText(designsTabPane);
                  printCurrentIndexes();
                  setCurrentDesign(currentDesignKey);
        });
    }

    private void setCurrentDesign(String designKey) {
        if (DESIGNS.containsKey(designKey)) {
            currentDesign = DESIGNS.get(designKey);
            System.out.println("setCurrentDesign() success");
        } else {
            System.out.println("Couldn't set current design with key = " + designKey);
        }
    }

    private void addLayersTabsListener() {
        layersTabPane.getSelectionModel().selectedIndexProperty()
              .addListener((observable1, oldValue1, newValue1) -> {
                  currentLayerIndex = getSelectedTabIndex(layersTabPane);
                  printCurrentIndexes();
                  setCurrentLayer(currentLayerIndex);
              });
    }

    private void setCurrentLayer(int layerIndex) {
        boolean designHasOurCurrentKey = DESIGNS.containsKey(currentDesignKey);
        boolean layerIndexIsValid = false;
        if (currentDesign != null) {
            layerIndexIsValid = currentDesign.hasLayerIndex(layerIndex);
        }
        if (designHasOurCurrentKey && layerIndexIsValid) {
            currentLayer = currentDesign.getLayer(layerIndex);
            System.out.println(
                  "setCurrentLayer() success");
        } else {
            System.out.println(
                  "Couldn't set current layer with index = " + layerIndex);
        }
    }


    private String getSelectedTabText(TabPane tabPane) {
        return tabPane.getSelectionModel().getSelectedItem().getText();
    }
    private int getSelectedTabIndex(TabPane tabPane) {
        return tabPane.getSelectionModel().getSelectedIndex();
    }


    @FXML
    private void initialize() {
        printCurrentIndexes();

        // add listeners so we know the current tabs
        addDesignsTabsListener();
        addLayersTabsListener();

        // Set up gui elements
        layerTypeComboBox.setItems(layerTypes);
        thicknessUnitChoiceBox.setValue("inch");
        thicknessUnitChoiceBox.setItems(thicknessUnits);
        thicknessTextField.setText("10.0");

        // figure out current design and current layer at initialize
        currentDesignKey = getSelectedTabText(designsTabPane);
        setCurrentDesign(currentDesignKey);
        currentLayerIndex = getSelectedTabIndex(layersTabPane);
        setCurrentLayer(currentLayerIndex);

        System.out.println();
    }

    // used to parse design/layer numbers
    private StringBuilder sb;

    @FXML
    private void loadMaterialBtnAction() throws IOException {
        saveLayerType();
        saveThickness();
        System.out.println(currentLayer);
        App.showLoadMaterial();
    }

    private void saveLayerType() {
        currentLayer.setLayerType(toString(layerTypeComboBox));
    }


    private void saveThickness() {
        Double thicknessValue = toDouble(thicknessTextField);
        String thicknessUnit = thicknessUnitChoiceBox.getValue().toString();
        if (thicknessUnit == "inch")
            thicknessValue *= 0.0254;
        currentLayer.setThickness(thicknessValue);
    }

    private Double toDouble(TextInputControl o) {
        return Double.parseDouble(o.getText());
    }

    private String toString(ComboBox cb) {
        return cb.getValue().toString();
    }
}
