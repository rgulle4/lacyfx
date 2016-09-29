package cm.controllers;

import cm.App;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by royg59 on 9/21/16.
 */
public class LayerInformationController {

    private App main;

    @FXML
    private void LoadMatBtn() throws IOException {
        main.showLoadMaterial();
    }
}
