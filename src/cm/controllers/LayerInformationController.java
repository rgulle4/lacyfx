package cm.controllers;

import cm.App;
import cm.models.EnvAnalysisCalc;
import cm.models.EnvPerfAnalysis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import cm.App;

import static cm.App.envPerfAnalysisMap;

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

        TextField_Thickness.setText("6.0");
    }
    private EnvPerfAnalysis envPerfAnalysis= new EnvPerfAnalysis();
    @FXML
    private void LoadMatBtn() throws IOException {

        if (ChoiceBox_ThicknessUnit.getValue() == "meter"){
//            envPerfAnalysis.setThickness(Double.parseDouble(TextField_Thickness.getText()));
//            envPerfAnalysis.setTotV(envPerfAnalysis.getThickness());
//            envPerfAnalysis.setConFc(envPerfAnalysis.getTotV());
//            envPerfAnalysisMap.put("D1L1",envPerfAnalysis);
//            System.out.println(envPerfAnalysisMap.get("D1L1").getTotV());

            EnvAnalysisCalc.setThickness(Double.parseDouble(TextField_Thickness.getText()));
            EnvAnalysisCalc.setTotV(EnvAnalysisCalc.getThickness());
            EnvAnalysisCalc.setConFc(EnvAnalysisCalc.getTotV());
            System.out.println(EnvAnalysisCalc.getTotV());
        }
        if (ChoiceBox_ThicknessUnit.getValue() == "inch"){
            EnvAnalysisCalc.setThickness(Double.parseDouble(TextField_Thickness.getText())*0.0254);     // 1 inch = 0.0254 inch
            EnvAnalysisCalc.setTotV(EnvAnalysisCalc.getThickness());
            EnvAnalysisCalc.setConFc(EnvAnalysisCalc.getTotV());
            System.out.println(EnvAnalysisCalc.getTotV());
//            envPerfAnalysis.setThickness(Double.parseDouble(TextField_Thickness.getText())*0.0254);
//            envPerfAnalysis.setTotV(envPerfAnalysis.getThickness());
//            envPerfAnalysis.setConFc(envPerfAnalysis.getTotV());
//            envPerfAnalysisMap.put("D1L1",envPerfAnalysis);
//            System.out.println(envPerfAnalysisMap.get("D1L1").getTotV());
        }
        main.showLoadMaterial();
    }
}
