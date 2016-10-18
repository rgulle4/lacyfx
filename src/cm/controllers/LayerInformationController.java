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
    public ComboBox comboLayerType_D1L1;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D1L1;
    @FXML
    public TextField TextField_Thickness_D1L1;
    @FXML
    public ComboBox comboLayerType_D1L2;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D1L2;
    @FXML
    public TextField TextField_Thickness_D1L2;
    @FXML
    public ComboBox comboLayerType_D1L3;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D1L3;
    @FXML
    public TextField TextField_Thickness_D1L3;
    //Design 2
    public ComboBox comboLayerType_D2L1;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D2L1;
    @FXML
    public TextField TextField_Thickness_D2L1;
    @FXML
    public ComboBox comboLayerType_D2L2;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D2L2;
    @FXML
    public TextField TextField_Thickness_D2L2;
    @FXML
    public ComboBox comboLayerType_D2L3;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D2L3;
    @FXML
    public TextField TextField_Thickness_D2L3;
    //Design 3
    public ComboBox comboLayerType_D3L1;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D3L1;
    @FXML
    public TextField TextField_Thickness_D3L1;
    @FXML
    public ComboBox comboLayerType_D3L2;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D3L2;
    @FXML
    public TextField TextField_Thickness_D3L2;
    @FXML
    public ComboBox comboLayerType_D3L3;
    @FXML
    public ChoiceBox ChoiceBox_ThicknessUnit_D3L3;
    @FXML
    public TextField TextField_Thickness_D3L3;

    private App main;

    @FXML
    private void initialize(){

        //Design 1
        comboLayerType_D1L1.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D1L1.setValue("inch");
        ChoiceBox_ThicknessUnit_D1L1.setItems(ThicknessUnit);
        TextField_Thickness_D1L1.setText("6.0");
        comboLayerType_D1L2.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D1L2.setValue("inch");
        ChoiceBox_ThicknessUnit_D1L2.setItems(ThicknessUnit);
        TextField_Thickness_D1L2.setText("6.0");
        comboLayerType_D1L3.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D1L3.setValue("inch");
        ChoiceBox_ThicknessUnit_D1L3.setItems(ThicknessUnit);
        TextField_Thickness_D1L3.setText("6.0");
        //Design 2
        comboLayerType_D2L1.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D2L1.setValue("inch");
        ChoiceBox_ThicknessUnit_D2L1.setItems(ThicknessUnit);
        TextField_Thickness_D2L1.setText("6.0");
        comboLayerType_D2L2.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D2L2.setValue("inch");
        ChoiceBox_ThicknessUnit_D2L2.setItems(ThicknessUnit);
        TextField_Thickness_D2L2.setText("6.0");
        comboLayerType_D2L3.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D2L3.setValue("inch");
        ChoiceBox_ThicknessUnit_D2L3.setItems(ThicknessUnit);
        TextField_Thickness_D2L3.setText("6.0");
        //Design 3
        comboLayerType_D3L1.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D3L1.setValue("inch");
        ChoiceBox_ThicknessUnit_D3L1.setItems(ThicknessUnit);
        TextField_Thickness_D3L1.setText("6.0");
        comboLayerType_D3L2.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D3L2.setValue("inch");
        ChoiceBox_ThicknessUnit_D3L2.setItems(ThicknessUnit);
        TextField_Thickness_D3L2.setText("6.0");
        comboLayerType_D3L3.setItems(LayerType);
        ChoiceBox_ThicknessUnit_D3L3.setValue("inch");
        ChoiceBox_ThicknessUnit_D3L3.setItems(ThicknessUnit);
        TextField_Thickness_D3L3.setText("6.0");
    }
    private Design design = new Design();
    private Layer layer = new Layer();
    @FXML
    private void LoadMatBtn() throws IOException {


        Set<String> design_keys = designMap.keySet();
        for (String key_design: design_keys){
            int layerNum = designMap.get(key_design).getNumberOfLayers();
            for (int i = 0;i<=layerNum;i++){

                if (ChoiceBox_ThicknessUnit_D1L1.getValue() == "meter"){
                    layer.setThickness(Double.parseDouble(TextField_Thickness_D1L1.getText()));
                    double Volume = layer.getThickness()*layer.getLengthness()*layer.getWidth();
                    layer.setVolume(Volume);

                }
                if (ChoiceBox_ThicknessUnit_D1L1.getValue() == "inch"){

                    layer.setThickness(Double.parseDouble(TextField_Thickness_D1L1.getText())*0.0254);
                    double Volume = layer.getThickness()*layer.getLengthness()*layer.getWidth();
                    layer.setVolume(Volume);

                }
                layer.setLayer_ID("Design1Layer1");
                layer.setLayerType(comboLayerType_D1L1.getValue().toString());
                //save data in the layerMap
                layerMap.put(layer.getLayer_ID(),layer);
                System.out.println("Volume:  "+layerMap.get(layer.getLayer_ID()).getVolume()+"   LayerType:  "+layerMap.get(layer.getLayer_ID()).getLayerType());
            }

        }




        main.showLoadMaterial();
    }
}
