package cm.controllers;

import cm.App;
import cm.models.Design;
import cm.models.Layer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

import static cm.models.Model.DESIGNS;
import static cm.models.Model.GSON_PP;

public class DesignViewController {
    private LayerInformationController main;
    @FXML
    LayerViewController layerViewController;
    @FXML
    private Button Button_Test;

    /* -- Constructor(s) and init ----------------------------- */

    public Design design;

    public DesignViewController() {
        super();
    }

    public DesignViewController(Design design) {
        this();
        setCurrentDesign(design);
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
    public void initialize() {
        setDesignOptionsToDefaults();
        
        // set up new tab functionality
        newTabButton.getStyleClass().add("tab-button");
        newTabButton.setOnAction(e -> { this.addLayer(); } );
        newTabTab.setGraphic(newTabButton);

        // set up tooltip debug info
        setUpDebugCheatSheet();
        saveDesignOptions();
        
        // set up autosave triggers
        setupAutosave();
        
        // wait and save
        Thread saveWhenReady = new Thread() {
            public void run() {
                while (design == null)
                    try { Thread.sleep(250); }
                    catch (InterruptedException e) { }
                saveDesignOptions();
            }
        };
        
        saveWhenReady.start();

        //pass this controller to LayerViewController
        layerViewController.init(this);
        pavementTypeComboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(layerViewController.textField.getText());
                layerViewController.layerTypeComboBox.getSelectionModel().clearSelection();
            }
        });
    }


    private void setupAutosave() {
        autosaveOnFocus(designTypeComboBox);
        autosaveOnFocus(pavementTypeComboBox);
        autosaveOnFocus(layersTabPane);
    }
    
    private void autosaveOnFocus(Node n) {
        if (n instanceof ComboBox) {
            ComboBox cb = (ComboBox) n;
            cb.getSelectionModel().selectedIndexProperty().addListener((
                  (observable, oldValue, newValue) -> {
                      saveDesignOptions();
            }));
        }
            
        n.focusedProperty().addListener((
              (observable, oldValue, newValue) -> {
                  saveDesignOptions();
              }));
    }

    public void setUpFirstLayerTab() {
        setUpFirstLayerTab(addInitialLayer());
    }

    public void setUpFirstLayerTab(Layer firstLayer) {
        layersTabsList = layersTabPane.getTabs();
        Tab firstTab = layersTabsList.get(0);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("views/layerView.fxml"));
        Node node = null;
        try { node = loader.load(); }
        catch (IOException e) { e.printStackTrace(); }
        LayerViewController firstTabController
              = loader.<LayerViewController>getController();

        firstTab.setContent(node);
        firstTabController.setCurrentLayer(firstLayer);
        printDebugMsg("firstTabController.getCurrentLayer() = ");
        printDebugMsg(firstTabController.getCurrentLayer());
    }

    private Layer addInitialLayer() {
        if (design == null) {
            printDebugMsg("ERROR: Couldn't add initial layer! (Bc design == null)");
            return null;
        }
        List<Layer> layers = design.getLayers();
        if (layers == null) {
            printDebugMsg("ERROR: Couldn't add initial layer! (Bc layers == null)");
            return null;
        } if (layers.isEmpty()) {
            design.addLayer();
            printDebugMsg("Added initial layer to design.");
            return layers.get(0);
        } else if (layers.size() == 1) {
            printDebugMsg("WARNING: design already has an initial layer");
            return layers.get(0);
        } else if (layers.size() > 1) {
            printDebugMsg("ERROR: Couldn't add initial layer! (Bc layers.size() > 1)");
            return null;
        } else {
            printDebugMsg("ERROR: addInitialLayer() fell all the way through!");
            return null;
        }
    }

    private void setDesignOptionsToDefaults() {
        designTypeComboBox.setItems(designTypes);
        selectFirst(designTypeComboBox);
        populatePavementTypeComboBox();
        selectFirst(pavementTypeComboBox);
    }

    public void saveDesignOptions() {
        if (design == null) { return ; }
        design.setDesignType(toString(designTypeComboBox));
        design.setPavementType(toString(pavementTypeComboBox));
    }

    /* -- Gui control ------------------------------------------ */

    @FXML public ComboBox designTypeComboBox;
    @FXML public ComboBox pavementTypeComboBox;

    @FXML private TabPane layersTabPane;
    @FXML private Tab newTabTab;
    private final Button newTabButton = new Button("+");

    private List<Tab> layersTabsList;

    private void addLayer() {
        if (layersTabsList == null) return;
        int numTabs = layersTabsList.size();
        int newTabPosition = numTabs - 1;
        int newLayerNumber = newTabPosition + 1;
        String newLayerId = "Layer " + newLayerNumber;

        Layer newLayer = design.addLayer();
        Tab newLayerTab = new Tab(newLayerId);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
              App.class.getResource("views/layerView.fxml"));
        Node node = null;
        try { node = loader.load(); }
        catch (IOException e) { e.printStackTrace(); }
        LayerViewController newLayerTabController
              = loader.<LayerViewController>getController();
        newLayerTabController.setCurrentLayer(newLayer);
        newLayerTab.setContent(node);

        layersTabPane.getTabs().add(newTabPosition, newLayerTab);
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
          "Rigid pavement");

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

    @FXML
    public void button_click(){
        layerViewController.init(this);
        layerViewController.textField.setText("Change!!");
    }

    @FXML
    public void PavementTypeComboBoxClicked(ActionEvent event){
        //change pavement type
        //set layerType
//        layerViewController.layerTypeComboBox.setEditable(false);
//        if (pavementTypeComboBox.getSelectionModel()
//                .getSelectedItem().toString().contains("Rigid")) {
//            layerViewController.layerTypeComboBox.getSelectionModel().select(1);//Portland concrete
//        }else {
//            layerViewController.layerTypeComboBox.getSelectionModel()
//                    .select(0);//AC
//        }
//        System.out.println("ComboBox Action");

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
                  debugCheatSheet.setText(GSON_PP.toJson(design));
              });
    }

    public void init(LayerInformationController layerInformationController) {
        main = layerInformationController;
    }
}
