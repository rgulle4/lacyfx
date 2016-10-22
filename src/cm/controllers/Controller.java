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
public class Controller {

    /* -- Fields -------------------------------------------------------- */

    // we need to know about the model... probably just need
    // one, but just in case...
//    Model model = new cm.models.Model();

    @FXML
    private TabPane mainTabPane;

    public void NextButton() {
        mainTabPane.getSelectionModel().select(mainTabPane.getSelectionModel().getSelectedIndex()+1);
    }

    public static void main(String[] args) {
        foo();
        Model.foo();
        // both of those work
    }
}
