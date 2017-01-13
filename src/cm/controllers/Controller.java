package cm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

/**
 * The main controller.
 */
public class Controller {

    @FXML
    private TabPane mainTabPane;
    @FXML
    LayerInformationController layerInformationTabController;

    @FXML
    public void initialize(){
        layerInformationTabController.init(this);
    }
    public void NextButton() {
        mainTabPane.getSelectionModel().select(mainTabPane.getSelectionModel().getSelectedIndex()+1);
    }
}
