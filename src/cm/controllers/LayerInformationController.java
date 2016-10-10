package cm.controllers;

import cm.App;
import cm.models.EnvAnalysisCalc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by royg59 on 9/21/16.
 */
public class LayerInformationController {

    ObservableList<String> LayerType = FXCollections.observableArrayList("Asphalt Concrete","Portland Cement Concrete","Aggregate");       //Material type of a layer
    ObservableList<String> ThicknessUnit = FXCollections.observableArrayList("inch","meter");


    @FXML
    public ComboBox comboLayerType;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit;
    @FXML
    public TextField TextField_Thickness;

    private App main;

    @FXML
    private void initialize(){

        comboLayerType.setItems(LayerType);
        ChoiceBox_ThicknessUnit.setValue("inch");
        ChoiceBox_ThicknessUnit.setItems(ThicknessUnit);

        TextField_Thickness.setText("10.0");
    }

    @FXML
    private void LoadMatBtn() throws IOException {

        if (ChoiceBox_ThicknessUnit.getValue() == "meter"){
            EnvAnalysisCalc.setThickness(Double.parseDouble(TextField_Thickness.getText()));
            EnvAnalysisCalc.setTotV(EnvAnalysisCalc.getThickness());
            System.out.println(EnvAnalysisCalc.getTotV());
        }
        if (ChoiceBox_ThicknessUnit.getValue() == "inch"){
            EnvAnalysisCalc.setThickness(Double.parseDouble(TextField_Thickness.getText())*0.0254);     // 1 inch = 0.0254 inch
            EnvAnalysisCalc.setTotV(EnvAnalysisCalc.getThickness());
            System.out.println(EnvAnalysisCalc.getTotV());
        }
        main.showLoadMaterial();
    }
}
