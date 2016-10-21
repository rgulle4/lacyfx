package cm.controllers;

import cm.App;
import cm.models.Design;
import cm.models.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    //All the items in a layerInformation tab
    public ComboBox comboLayerType;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit;
    @FXML
    public TextField TextField_Thickness;
    @FXML
    public Tab DesginTab1;
    @FXML
    public Tab LayerTab1;

    private App main;

    private StringBuilder sb;

    @FXML
    private void initialize(){

        //Design 1
        comboLayerType.setItems(LayerType);
        ChoiceBox_ThicknessUnit.setValue("inch");
        ChoiceBox_ThicknessUnit.setItems(ThicknessUnit);
        TextField_Thickness.setText("10.0");

    }

    private Layer layer = new Layer();
    @FXML
    private void LoadMatBtn() throws IOException {


        if (ChoiceBox_ThicknessUnit.getValue() == "meter") {
            layer.setThickness(Double.parseDouble(TextField_Thickness.getText()));
            double Volume = layer.getThickness() * layer.getLength() * layer.getWidth();
            layer.setVolume(Volume);

        }
        if (ChoiceBox_ThicknessUnit.getValue() == "inch") {

            layer.setThickness(Double.parseDouble(TextField_Thickness.getText()) * 0.0254);
            double Volume = layer.getThickness() * layer.getLength() * layer.getWidth();
            layer.setVolume(Volume);

        }
//                sb.append("L"+Integer.toString(i));
//                String layerID = sb.toString();
//                layer.setLayerId(layerID);
//
//                layer.setLayerType(this.comboLayerType.getValue().toString());
//                //save data in the layerMap
//                layerMap.put(layer.getLayerId(),layer);
//                System.out.println("Volume:  "+layerMap.get(layer.getLayerId()).getVolume()+"   LayerType:  "+layerMap.get(layer.getLayerId()).getLayerType());


        main.showLoadMaterial();
    }
}
