package cm.controllers;

import cm.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;


/**
 * Created by royg59 on 9/21/16.
 */
public class ImportTabController {
    private App main;
    @FXML
    private TextField file1;

    @FXML
    private void addfile(){

        File file =main.showfile();
        if (file != null)
        file1.setText(file.toString());     //show path in the Textfield
    }
}
