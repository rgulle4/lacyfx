package cm.controllers;

import cm.App;
import cm.EnvAnalysis_cal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import cm.EnvAnalysis_cal;

/**
 * Created by royg59 on 9/21/16.
 */
public class LayerInformationController {

    ObservableList<String> EMUnit = FXCollections.observableArrayList("psi");
    ObservableList<String> CSUnit = FXCollections.observableArrayList("psi");
    ObservableList<String> UWUnit = FXCollections.observableArrayList("feet^3","meter^3");
    ObservableList<String> TUnit = FXCollections.observableArrayList("inch","meter");

    @FXML
    public ChoiceBox CB_EM;
    @FXML
    public ChoiceBox CB_CS;
    @FXML
    public ChoiceBox CB_UW;
    @FXML
    public ChoiceBox CB_T;

    @FXML
    public TextField TF_EM;
    @FXML
    public TextField TF_CS;
    @FXML
    public TextField TF_UW;
    @FXML
    public TextField TF_T;

    private App main;

    @FXML
    private void initialize(){
        CB_EM.setValue("psi");
        CB_EM.setItems(EMUnit);

        CB_CS.setValue("psi");
        CB_CS.setItems(CSUnit);

        CB_UW.setValue("meter^3");
        CB_UW.setItems(UWUnit);

        CB_T.setValue("meter");
        CB_T.setItems(TUnit);

        TF_EM.setText("");
        TF_CS.setText("");
        TF_UW.setText("");
        TF_T.setText("");
    }

    @FXML
    private void LoadMatBtn() throws IOException {
        EnvAnalysis_cal EC = new EnvAnalysis_cal(Double.parseDouble(TF_T.getText()));
        System.out.println(EC.getTotalV());
        main.showLoadMaterial();
    }
}
