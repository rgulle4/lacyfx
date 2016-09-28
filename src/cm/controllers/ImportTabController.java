package cm.controllers;

import cm.App;
import cm.SearchWord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private Label label1;

    @FXML
    private void addfile(){

        File file =main.showfile();
        if (file != null){
            file1.setText(file.toString());     //show path in the Textfield

        }

    }

    @FXML
    private void checkbutton(){
        File file = new File(file1.getText());
        if (file != null){
            Object[] obj = SearchWord.searchword(file);

            if (obj[4].toString() =="0")
                label1.setText("Pass");
            else {
                label1.setText("Failed");
            }
        }
    }
}
