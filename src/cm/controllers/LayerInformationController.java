package cm.controllers;

import cm.App;
import cm.EnvAnalysisCal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by royg59 on 9/21/16.
 */
public class LayerInformationController {

    ObservableList<String> MaterialType = FXCollections.observableArrayList("Concrete","Flexible");       //Material type of a layer
    ObservableList<String> UWUnit = FXCollections.observableArrayList("feet^3","meter^3");
    ObservableList<String> TUnit = FXCollections.observableArrayList("inch","meter");


    @FXML
    public ChoiceBox CB_UW;
    @FXML
    public ChoiceBox CB_T;

    @FXML
    public TextField TF_UW;
    @FXML
    public TextField TF_T;

    private App main;

    @FXML
    private void initialize(){

        CB_UW.setValue("meter^3");
        CB_UW.setItems(UWUnit);

        CB_T.setValue("meter");
        CB_T.setItems(TUnit);

        TF_UW.setText("");
        TF_T.setText("10.0");
    }

    @FXML
    private void LoadMatBtn() throws IOException {

        if (CB_T.getValue() == "meter"){
            EnvAnalysisCal.setThickness(Double.parseDouble(TF_T.getText()));
            EnvAnalysisCal.setTotV(EnvAnalysisCal.getThickness());
            System.out.println(EnvAnalysisCal.getTotV());
        }
        if (CB_T.getValue() == "inch"){
            EnvAnalysisCal.setThickness(Double.parseDouble(TF_T.getText())*0.0254);     // 1 inch = 0.0254 inch
            EnvAnalysisCal.setTotV(EnvAnalysisCal.getThickness());
            System.out.println(EnvAnalysisCal.getTotV());
        }
        main.showLoadMaterial();
    }
}
