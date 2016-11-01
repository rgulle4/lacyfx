package cm.controllers;

import cm.App;
import static cm.models.Model.*;
import cm.models.Design;
import cm.models.Layer;
import cm.models.Model;
import cm.models.ZipCodeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class LayerInformationController {

    /* -- new stuff --------------------------------------- */

    private final ZipCodeUtil ZCU = new ZipCodeUtil();

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

    private static final Gson GSON_PP = new GsonBuilder().setPrettyPrinting().create();

    @FXML
    private void debugButtonListener(ActionEvent actionEvent) {
        Model.printDebugDesigns();
        printDebugMsg("Current design key: " + currentDesignKey);
        printDebugMsg("Current design object: ");
        printDebugMsg(GSON_PP.toJson(currentDesign));
    }

    private Tab getNewestTab() {
        if (designTabsList == null) return null;
        int numTabs = designTabsList.size();
        return designsTabPane.getTabs().get(numTabs - 1);
    }

    private List<Tab> designTabsList;

    private void addDesign() {
        if (designTabsList == null) return;
        int numTabs = designTabsList.size();
        int newTabPosition = numTabs - 1;
        int newDesignNumber = newTabPosition + 1;
        String newDesignId = "Design " + newDesignNumber;

        Design newDesign = Model.addNewDesign();
        Tab newTab  = new Tab(newDesignId);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(
              App.class.getResource("views/designView.fxml"));
        Node node = null;
        try { node = fxmlLoader.load(); }
        catch (IOException e) { e.printStackTrace(); }
        DesignController newDesignTabController
              = fxmlLoader.<DesignController>getController();
        newDesignTabController.setCurrentDesign(newDesign);
        newTab.setContent(node);

        designsTabPane.getTabs().add(newTabPosition, newTab);
    }

    private final Button newTabButton = new Button("+");
    private void addDesignsTabsListener() {
        newTabButton.setOnAction(e -> {this.addDesign(); });
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
        projectLocationTextField.setText("70820");

        designTabsList = designsTabPane.getTabs();
        Design firstDesign = Model.addNewDesign();
        Layer firstLayer = firstDesign.addLayer();

        Tab firstTab = designTabsList.get(0);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(
              App.class.getResource("views/designView.fxml"));
        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesignController firstTabController
              = fxmlLoader.<DesignController>getController();

        firstTab.setContent(node);
        firstTabController.setCurrentDesign(firstDesign);
        printDebugMsg(firstTabController.getCurrentDesign());


        // set up newTabTab label thingy
        newTabButton.getStyleClass().add("tab-button");
        newTabTab.setGraphic(newTabButton);

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
