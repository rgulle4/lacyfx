package cm.controllers;

import cm.App;
import javafx.fxml.FXML;

/**
 * Created by royg59 on 9/21/16.
 */
public class ImportTabController {
    private App main;

    @FXML
    private void addfile(){

        main.showfile();
    }
}
