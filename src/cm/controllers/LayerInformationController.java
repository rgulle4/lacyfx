package cm.controllers;

import cm.App;
import static cm.models.Model.*;
import cm.models.Design;
import cm.models.Layer;
import cm.models.Model;
import cm.models.ZipCodeUtil;
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

    //Save the zip code of location
    @FXML public TextField projectLocationTextField;

    // The TabPanes
    @FXML public TabPane designsTabPane;

    // vars we'll use for current design, current layer
//    String currentDesignKey = "Design 1";
//    int currentLayerIndex = 0;

//    Design currentDesign = null;
//    Layer currentLayer = null;

    @FXML
    Tab newTabTab;

    @FXML
    private void debugButtonListener(ActionEvent actionEvent) {
        printDebugMsg(GSON_PP.toJson(DESIGNS));
    }

    private Tab getNewestTab() {
        if (designsTabsList == null) return null;
        int numTabs = designsTabsList.size();
        return designsTabPane.getTabs().get(numTabs - 1);
    }

    private List<Tab> designsTabsList;

    private void addDesign() {
        if (designsTabsList == null) return;
        int numTabs = designsTabsList.size();
        int newTabPosition = numTabs - 1;
        int newDesignNumber = newTabPosition + 1;
        String newDesignId = "Design " + newDesignNumber;

        Design newDesign = Model.addNewDesign();
        Layer newFirstLayer = null;
        if (newDesign.getLayers().isEmpty())
            newFirstLayer = newDesign.addLayer();
        else
            newFirstLayer = newDesign.getLayer(0);

        Tab newDesignTab  = new Tab(newDesignId);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
              App.class.getResource("views/designView.fxml"));
        Node node = null;
        try { node = loader.load(); }
        catch (IOException e) { e.printStackTrace(); }
        DesignController newDesignTabController
              = loader.<DesignController>getController();
        newDesignTabController.setCurrentDesign(newDesign);
        newDesignTab.setContent(node);

        newDesignTabController.setUpFirstLayerTab(newFirstLayer);

        designsTabPane.getTabs().add(newTabPosition, newDesignTab);
    }

    private final Button newTabButton = new Button("+");

//    private void setCurrentDesign(String designKey) {
//        if (DESIGNS.containsKey(designKey)) {
//            currentDesign = DESIGNS.get(designKey);
//            printDebugMsg("setCurrentDesign() success");
//        } else {
//            printDebugMsg("Couldn't set current design with key = " + designKey);
//        }
//    }

//    private void addLayePropertyrsTabsListener() {
//        layersTabPane.getSelectionModel().selectedIndexProperty()
//              .addListener((observable1, oldValue1, newValue1) -> {
//                  currentLayerIndex = getSelectedTabIndex(layersTabPane);
//                  printCurrentIndexes();
//                  setCurrentLayer(currentLayerIndex);
//              });
//    }

//    private void setCurrentLayer(int layerIndex) {
//        boolean designHasOurCurrentKey = DESIGNS.containsKey(currentDesignKey);
//        boolean layerIndexIsValid = false;
//        if (currentDesign != null) {
//            layerIndexIsValid = currentDesign.hasLayerIndex(layerIndex);
//        }
//        if (designHasOurCurrentKey && layerIndexIsValid) {
//            currentLayer = currentDesign.getLayer(layerIndex);
//            printDebugMsg("setCurrentLayer() success");
//        } else {
//            printDebugMsg(
//                  "Couldn't set current layer with index = " + layerIndex);
//        }
//    }

//    private String getSelectedTabText(TabPane tabPane) {
//        return tabPane.getSelectionModel().getSelectedItem().getText();
//    }
//    private int getSelectedTabIndex(TabPane tabPane) {
//        return tabPane.getSelectionModel().getSelectedIndex();
//    }

    @FXML
    private void initialize() {
        projectLocationTextField.setText("70820");

        // set up first design tab -------------------------
        Design firstDesign = Model.addNewDesign();
        Layer firstLayer = null;
        if (firstDesign.getLayers().isEmpty())
            firstLayer = firstDesign.addLayer();
        else
            firstLayer = firstDesign.getLayer(0);

        designsTabsList = designsTabPane.getTabs();
        Tab firstDesignTab = designsTabsList.get(0);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
              App.class.getResource("views/designView.fxml"));
        Node node = null;
        try { node = loader.load(); }
        catch (IOException e) { e.printStackTrace(); }
        DesignController firstDesignTabController
              = loader.<DesignController>getController();
        firstDesignTab.setContent(node);
        firstDesignTabController.setCurrentDesign(firstDesign);

        // set up first layer tab -------------------------
        firstDesignTabController.setUpFirstLayerTab(firstLayer);

        // set up new tab functionality -------------
        newTabButton.getStyleClass().add("tab-button");
        newTabButton.setOnAction(e -> { this.addDesign(); });
        newTabTab.setGraphic(newTabButton);

        printCurrentIndexes();

        /* add listeners so we know the current tabs */
//        addLayersTabsListener();

        /* figure out current design and current layer at initialize */
//        currentDesignKey = getSelectedTabText(designsTabPane);
//        setCurrentDesign(currentDesignKey);
//        currentLayerIndex = getSelectedTabIndex(layersTabPane);
//        setCurrentLayer(currentLayerIndex);

        setUpDebugCheatSheet();
    }



    @FXML
    private void loadMaterialBtnAction() throws IOException {
        saveProjectLocation();
//        saveLayerType();
//        saveThickness();
//        printDebugMsg(currentLayer);
//        App.showLoadMaterial(currentLayer);
        App.showLoadMaterial(null);
    }
    private void saveProjectLocation() {
        DESTINATION_ZIP_CODE_MUTABLE = projectLocationTextField.getText();
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

    @FXML private Tooltip debugCheatSheet;
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
    public void printDebugStuff(ActionEvent actionEvent) {
//        System.out.println(
//              currentLayer.getMaterial(1).getCS()
//                    + ","  + currentLayer.getMaterial(1).getCompany_Name()
//                    + " ," + currentLayer.getMaterial(1).getLocation()
//                    + " ," + currentLayer.getMaterial(1).getZipCode()
//                    + " ," + currentLayer.getMaterial(1).getMixNum());
    }

    private void setUpDebugCheatSheet() {
        if (DEBUG_MODE == false) { return; }
        debugCheatSheet.activatedProperty().addListener(
              (observable, oldValue, newValue) -> {
                  debugCheatSheet.setText(GSON_PP.toJson(DESIGNS));
              });
    }


}
