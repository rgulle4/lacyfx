package cm.controllers;

import cm.models.Design;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Map;

import static cm.models.Model.*;



/**
 * Created by royg59 on 9/21/16.
 */
public class ImportTabController {

    private final int MAX_NUM_OF_DESIGNS = 3;

    // Number of designs
    ObservableList<String> numberOfDesigns = FXCollections.
          observableArrayList("1","2","3");

    // Design Type
    ObservableList<String> designTypes = FXCollections
          .observableArrayList(
                "New pavement",
                "Overlay");

    // Pavement type (when design is new pavement)
    ObservableList<String> newPavementTypes = FXCollections
          .observableArrayList(
                "Flexible pavement",
                "Joint Reinforced concrete pavement");

    // Pavement type (when design is overlay)
    ObservableList<String> overlayPavementTypes = FXCollections.
          observableArrayList(
                "AC over AC",
                "AC over JRCP");

    // Number of layers in this design
    ObservableList<String> layerNum = FXCollections
          .observableArrayList(
                "1","2","3","4","5","6","7","8","others");

    @FXML
    private ComboBox designType1ComboBox;
    @FXML
    private ComboBox pavementType1ComboBox;
    @FXML
    private ComboBox numberOfLayers1ComboBox;
    @FXML
    private ComboBox designType2ComboBox;
    @FXML
    private ComboBox pavementType2ComboBox;
    @FXML
    private ComboBox numberOfLayers2ComboBox;
    @FXML
    private ComboBox designType3ComboBox;
    @FXML
    private ComboBox pavementType3ComboBox;
    @FXML
    private ComboBox numberOfLayers3ComboBox;

    @FXML
    private ComboBox numberOfDesignsComboBox;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox vBox2;
    @FXML
    private VBox vBox3;


    private ComboBox[] designTypeComboBoxes = new ComboBox[MAX_NUM_OF_DESIGNS];
    private VBox[] vBoxes = new VBox[MAX_NUM_OF_DESIGNS];
    private ComboBox[] pavementTypeComboBoxes = new ComboBox[MAX_NUM_OF_DESIGNS];
    private ComboBox[] layerNumComboBoxes = new ComboBox[MAX_NUM_OF_DESIGNS];

    public void initialize() {
        numberOfDesignsComboBox.setItems(numberOfDesigns);
        vBoxes[0] = vBox1;
        vBoxes[1] = vBox2;
        vBoxes[2] = vBox3;
        designTypeComboBoxes[0] = designType1ComboBox;
        designTypeComboBoxes[1] = designType2ComboBox;
        designTypeComboBoxes[2] = designType3ComboBox;
        layerNumComboBoxes[0] = numberOfLayers1ComboBox;
        layerNumComboBoxes[1] = numberOfLayers2ComboBox;
        layerNumComboBoxes[2] = numberOfLayers3ComboBox;
        pavementTypeComboBoxes[0] = pavementType1ComboBox;
        pavementTypeComboBoxes[1] = pavementType2ComboBox;
        pavementTypeComboBoxes[2] = pavementType3ComboBox;

        // hide all the design choices
        for (int i=0;i<3;i++){
            vBoxes[i].setVisible(false);
            designTypeComboBoxes[i].setItems(designTypes);
            layerNumComboBoxes[i].setItems(layerNum);
        }

        // show the designs based on number of designs combo box
        if (numberOfDesignsComboBox.getSelectionModel().getSelectedItem() != null)
            showTheRightNumberOfDesigns();
    }

    public void showTheRightNumberOfDesigns() {
        // show new ones
        for (int i = 0; i < toInt(numberOfDesignsComboBox); i++){
            vBoxes[i].setVisible(true);
        }
        // hide "deleted" ones
        for (int i = toInt(numberOfDesignsComboBox); i < MAX_NUM_OF_DESIGNS; i++) {
            vBoxes[i].setVisible(false);
        }
    }



    public void updatePavementTypeComboBox() {
        for (int i = 0; i < MAX_NUM_OF_DESIGNS; i++) {
            if (designTypeComboBoxes[i].getSelectionModel().isSelected(0))
                pavementTypeComboBoxes[i].setItems(newPavementTypes);
            if (designTypeComboBoxes[i].getSelectionModel().isSelected(1))
                pavementTypeComboBoxes[i].setItems(overlayPavementTypes);
        }
    }


    public void saveButtonAction() {

        // loop through, and put all the basic design specs into DESIGNS
        for (int i = 0; i < toInt(numberOfDesignsComboBox); i++) {
            Design aNewDesign = new Design();

            // Design id string, "indexed" at 1
            StringBuilder ID = new StringBuilder("Design");
            String designID = ID.append(Integer.toString(i+1)).toString();
            aNewDesign.setDesign_ID(designID);

            // Set design type
            aNewDesign.setDesign_Type(toString(designTypeComboBoxes[i]));

            // Set pavement type
            aNewDesign.setPavement_Type(toString(pavementTypeComboBoxes[i]));

            // Set number of layers
            aNewDesign.setNumberOfLayers(toInt(layerNumComboBoxes[i]));

            // put into our DESIGNS collection
            DESIGNS.put(aNewDesign.getDesign_ID(),aNewDesign);
        }



        /* -- some debug stuff ------------------------------------- */

        System.out.println("All the basic design information was saved!!");

        for (Map.Entry<String, Design> elt : DESIGNS.entrySet()) {
            System.out.println("Design with key [" + elt.getKey()
                        + "] has "
                        + elt.getValue().getNumberOfLayers()
                        + " number of layers");
        }
    }

    /* -- helper methods -------------------------------------------- */

    private int toInt(ComboBox cb) {
        return Integer.parseInt(cb.getValue().toString());
    }

    private String toString(ComboBox cb) {
        return cb.getValue().toString();
    }
}
