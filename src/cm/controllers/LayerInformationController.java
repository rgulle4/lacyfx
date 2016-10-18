package cm.controllers;

import cm.App;
import cm.models.Design;
import cm.models.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Set;

import static cm.App.designMap;
import static cm.App.layerMap;

/**
 * Created by royg59 on 9/21/16.
 */
public class LayerInformationController {

    ObservableList<String> LayerType = FXCollections.observableArrayList("Asphalt Concrete","Portland Cement Concrete","Aggregate");       //Material type of a layer
    ObservableList<String> ThicknessUnit = FXCollections.observableArrayList("inch","meter");


    @FXML
    //Design 1
    public ComboBox comboLayerType;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit;
    @FXML
    public TextField TextField_Thickness;


    private App main;

    @FXML
    private void initialize(){

        //Design 1
        comboLayerType.setItems(LayerType);
        ChoiceBox_ThicknessUnit.setValue("inch");
        ChoiceBox_ThicknessUnit.setItems(ThicknessUnit);
        TextField_Thickness.setText("6.0");

    }
    private Design design = new Design();
    private Layer layer = new Layer();
    @FXML
    private void LoadMatBtn() throws IOException {


        Set<String> design_keys = designMap.keySet();
        for (String key_design: design_keys){
            int layerNum = designMap.get(key_design).getNumberOfLayers();
            for (int i = 0;i<=layerNum;i++){

                if (ChoiceBox_ThicknessUnit.getValue() == "meter"){
                    layer.setThickness(Double.parseDouble(TextField_Thickness.getText()));
                    double Volume = layer.getThickness()*layer.getLengthness()*layer.getWidth();
                    layer.setVolume(Volume);

                }
                if (ChoiceBox_ThicknessUnit.getValue() == "inch"){

                    layer.setThickness(Double.parseDouble(TextField_Thickness.getText())*0.0254);
                    double Volume = layer.getThickness()*layer.getLengthness()*layer.getWidth();
                    layer.setVolume(Volume);

                }
                StringBuilder sb = new StringBuilder(designMap.get(key_design).getDesign_ID());
                sb.append("L"+Integer.toString(i));
                String layerID = sb.toString();
                layer.setLayer_ID(layerID);

                layer.setLayerType(this.comboLayerType.getValue().toString());
                //save data in the layerMap
                layerMap.put(layer.getLayer_ID(),layer);
                System.out.println("Volume:  "+layerMap.get(layer.getLayer_ID()).getVolume()+"   LayerType:  "+layerMap.get(layer.getLayer_ID()).getLayerType());
            }

        }




        main.showLoadMaterial();
    }
}
