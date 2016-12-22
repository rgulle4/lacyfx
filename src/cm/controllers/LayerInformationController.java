package cm.controllers;

import cm.App;
import cm.models.Design;
import cm.models.Layer;
import cm.models.Model;
import cm.models.ZipCodeUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

import static cm.models.Model.*;

public final class LayerInformationController {

    private final ZipCodeUtil ZCU = new ZipCodeUtil();

    //Save the zip code of location
    @FXML public TextField projectLocationTextField;

    // The TabPanes
    @FXML public TabPane designsTabPane;
    private List<Tab> designsTabsList;

    @FXML
    Tab newTabTab;
    private final Button newTabButton = new Button("+");

    private Tab getNewestTab() {
        if (designsTabsList == null) return null;
        int numTabs = designsTabsList.size();
        return designsTabPane.getTabs().get(numTabs - 1);
    }

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

    @FXML
    private void initialize() {
        projectLocationTextField.setText("95192");

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
    
        // set up new autosave triggers -------------
        setupAutosave();
        saveProjectLocation();
    }
    
    private void saveProjectLocation() {
        printDebugMsg("------------");
        printDebugMsg("saveProjectLocation()");
        printDebugMsg(DESTINATION_ZIP_CODE_MUTABLE);
        printDebugMsg("------------");
        DESTINATION_ZIP_CODE_MUTABLE = projectLocationTextField.getText();
    }
    
    private void setupAutosave() {
        autosaveOnFocus(designsTabPane);
        autosaveOnFocus(projectLocationTextField);
    }
    
    private void autosaveOnFocus(Control control) {
        control.focusedProperty().addListener((
              (observable, oldValue, newValue) -> {
                  saveProjectLocation();
              }));
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

    @FXML
    public void printDebugStuff() {
        printDebugMsg(GSON_PP.toJson(DESIGNS));
        printDebugMsg(DESTINATION_ZIP_CODE_MUTABLE);
    }
}
