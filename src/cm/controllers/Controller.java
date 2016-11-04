package cm.controllers;

import cm.models.Model;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import cm.controllers.WeightsTabController;

import static cm.models.Model.*;

/**
 * The main controller.
 */
public final class Controller {

    @FXML
    private TabPane mainTabPane;

    public void NextButton() {
        mainTabPane.getSelectionModel().select(mainTabPane.getSelectionModel().getSelectedIndex()+1);
    }
}
