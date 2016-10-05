package cm.controllers;

import cm.App;
import cm.SearchWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.File;


/**
 * Created by royg59 on 9/21/16.
 */
public class ImportTabController {
    ObservableList<String> DesignType = FXCollections.observableArrayList("Concrete","Flexible","Composite");       //Design type of pavement

    @FXML
    private ComboBox Dtype;
    @FXML
    private TextField TotLayernum;

    public void initialize(){

        Dtype.setItems(DesignType);

    }
}
