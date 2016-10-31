package cm.controllers;

import cm.App;
import static cm.models.Model.*;
import cm.models.Design;
import cm.models.Layer;
import cm.models.Model;
import cm.models.ZipCodeUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.List;

public class LayerInformationController {

    /* -- new stuff --------------------------------------- */

    private final ZipCodeUtil ZCU = new ZipCodeUtil();

    @FXML private ComboBox designTypeComboBox;
    @FXML private ComboBox pavementTypeComboBox;

    // Design types
    ObservableList<String> designTypes = FXCollections.observableArrayList(
          "New pavement",
          "Overlay");

    // Pavement types (when design is new pavement)
    ObservableList<String> newPavementTypes = FXCollections.observableArrayList(
          "Flexible pavement",
          "Joint Reinforced concrete pavement");

    // Pavement types (when design is overlay)
    ObservableList<String> overlayPavementTypes = FXCollections.observableArrayList(
          "AC over AC",
          "AC over JRCP");

    @FXML
    public void populatePavementTypeComboBox() {
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

    /* -- "OLD" stuff ------------------------------------- */


    // The allowed material types
    ObservableList<String> layerTypes = FXCollections.observableArrayList(
          "Asphalt Concrete",
          "Portland Cement Concrete",
          "Aggregate");

    // The allowed thickness units
    ObservableList<String> thicknessUnits = FXCollections.observableArrayList(
          "inch",
          "meter");

    //Save the zip code of location
    @FXML public TextField projectLocationTextField;

    // Some layer specs
    @FXML public ComboBox layerTypeComboBox;
    @FXML public TextField thicknessTextField;
    @FXML public ChoiceBox thicknessUnitChoiceBox;

    // The TabPanes
    @FXML public TabPane designsTabPane;
//    @FXML TabPane layersTabPane;

    // vars we'll use for current design, current layer
    String currentDesignKey = "Design 1";
    int currentLayerIndex = 0;

    Design currentDesign = null;
    Layer currentLayer = null;

    @FXML
    Tab newTabTab;

    @FXML
    private void debugButtonListener(ActionEvent actionEvent) {
        Model.printDebugDesigns();
    }

    private List<Tab> designTabsList;

    private void addDesign() {
        if (designTabsList == null) return;
        int numTabs = designTabsList.size();
        int newTabPosition = numTabs - 1;
        int newDesignNumber = newTabPosition + 1;
        String newDesignId = "Design " + newDesignNumber;

        Design newDesign = Model.addNewDesign();
        Layer newLayer = newDesign.addLayer();

        Tab newTab  = new Tab(newDesignId);
        designsTabPane.getTabs().add(newTabPosition, newTab);
    }

    private void addDesignsTabsListener() {
        SelectionModel selectionModel
              = designsTabPane.getSelectionModel();
        ReadOnlyObjectProperty<Tab> property
              = selectionModel.selectedItemProperty();
        property.addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(newTabTab)) {
                selectionModel.select(oldValue);
                addDesign();
            }
        });
    }

    private void setCurrentDesign(String designKey) {
        if (DESIGNS.containsKey(designKey)) {
            currentDesign = DESIGNS.get(designKey);
            printDebugMsg("setCurrentDesign() success");
        } else {
            printDebugMsg("Couldn't set current design with key = " + designKey);
        }
    }

//    private void addLayersTabsListener() {
//        layersTabPane.getSelectionModel().selectedIndexProperty()
//              .addListener((observable1, oldValue1, newValue1) -> {
//                  currentLayerIndex = getSelectedTabIndex(layersTabPane);
//                  printCurrentIndexes();
//                  setCurrentLayer(currentLayerIndex);
//              });
//    }

    private void setCurrentLayer(int layerIndex) {
        boolean designHasOurCurrentKey = DESIGNS.containsKey(currentDesignKey);
        boolean layerIndexIsValid = false;
        if (currentDesign != null) {
            layerIndexIsValid = currentDesign.hasLayerIndex(layerIndex);
        }
        if (designHasOurCurrentKey && layerIndexIsValid) {
            currentLayer = currentDesign.getLayer(layerIndex);
            printDebugMsg("setCurrentLayer() success");
        } else {
            printDebugMsg(
                  "Couldn't set current layer with index = " + layerIndex);
        }
    }

//    private String getSelectedTabText(TabPane tabPane) {
//        return tabPane.getSelectionModel().getSelectedItem().getText();
//    }
//    private int getSelectedTabIndex(TabPane tabPane) {
//        return tabPane.getSelectionModel().getSelectedIndex();
//    }

    @FXML
    private void initialize() {
        designTabsList = designsTabPane.getTabs();
        projectLocationTextField.setText("70820");

        setDesignOptionsToDefaults();
        setLayerOptionsToDefaults();

        Design firstDesign = Model.addNewDesign();
        Layer firstLayer = firstDesign.addLayer();


        printCurrentIndexes();

        /* add listeners so we know the current tabs */
        addDesignsTabsListener();
//        addLayersTabsListener();

        /* figure out current design and current layer at initialize */
//        currentDesignKey = getSelectedTabText(designsTabPane);
        setCurrentDesign(currentDesignKey);
//        currentLayerIndex = getSelectedTabIndex(layersTabPane);
        setCurrentLayer(currentLayerIndex);
    }

    private void setDesignOptionsToDefaults() {
        designTypeComboBox.setItems(designTypes);
        selectFirst(designTypeComboBox);
        populatePavementTypeComboBox();
        selectFirst(pavementTypeComboBox);
    }

    private void setLayerOptionsToDefaults() {
        layerTypeComboBox.setItems(layerTypes);
        selectFirst(layerTypeComboBox);
        thicknessUnitChoiceBox.setValue("inch");
        thicknessUnitChoiceBox.setItems(thicknessUnits);
        thicknessTextField.setText("10.0");
    }

    @FXML
    private void loadMaterialBtnAction() throws IOException {
        saveProjectLocation();
        saveLayerType();
        saveThickness();
        printDebugMsg(currentLayer);
        App.showLoadMaterial(currentLayer);
    }
    private void saveProjectLocation() {
        DESTINATION_ZIP_CODE_MUTABLE = projectLocationTextField.getText();
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

    /* -- helper methods for debugging -------------------- */

    private void println() { System.out.println(); }
    private void println(Object o) { System.out.println(o); }

    private final boolean DEBUG_MODE  = true;

    private void printDebugMsg() { if (DEBUG_MODE) println(); }
    private void printDebugMsg(Object o) { if (DEBUG_MODE) println(o); }

    private void printCurrentIndexes() {
        if (!DEBUG_MODE) return;
        printDebugMsg("-----------");
        printDebugMsg("Design Key = " + currentDesignKey);
        printDebugMsg("Layer index = " + currentLayerIndex);
    }

    @FXML
    public void printDebugStuff(ActionEvent actionEvent) {
        System.out.println(
              currentLayer.getMaterial(1).getCS()
                    + ","  + currentLayer.getMaterial(1).getCompany_Name()
                    + " ," + currentLayer.getMaterial(1).getLocation()
                    + " ," + currentLayer.getMaterial(1).getZipCode()
                    + " ," + currentLayer.getMaterial(1).getMixNum());
    }


}
