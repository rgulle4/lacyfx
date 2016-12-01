package cm.controllers;

import cm.models.Layer;
import cm.models.Mix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by Administrator on 2016/11/30.
 */
public class LoadMixController_newVersion {

    private Layer currentLayer;

    public void setCurrentLayer(Layer currentLayer) {
        this.currentLayer = currentLayer;
    }

    @FXML
    public TableView<Mix> MaterialTable;
    @FXML
    public TableColumn CS_Column;
    @FXML
    public TableColumn Location_Column;
    @FXML
    public TableColumn Cement_Column;
    @FXML
    public TableColumn WaterCement_Column;
    @FXML
    public TableColumn Fly_Ash_Column;
    @FXML
    public TableColumn Slag_Column;
    @FXML
    public TableColumn Air_Entrained_Column;
    @FXML
    public TableColumn Aggregate1_Column;
    @FXML
    public TableColumn Aggregate2_Column;
    @FXML
    public TextField textField_CS;
    @FXML
    public TextField textField_Distance;
    @FXML
    public TextField textField_Cement;
    @FXML
    public TextField textField_WaterCement;
    @FXML
    public TextField textField_FlyAsh;
    @FXML
    public TextField textField_Slag;
    @FXML
    public ComboBox ComboBox_Region;
    @FXML
    public ComboBox ComboBox_AirEntrained;
    @FXML
    public ComboBox ComboBox_Aggregate1;
    @FXML
    public ComboBox ComboBox_Aggregate2;

    ObservableList<String> AirEntrainedList = FXCollections.observableArrayList("Yes","No");
    ObservableList<String> RegionList = FXCollections.observableArrayList(
            "West","South","East","Middle","National Wide");
    @FXML
    public void initialize(){
        System.out.println("------------------------------------");
        System.out.println("layer in LoadMixController...");
        System.out.println(currentLayer);
        System.out.println("------------------------------------");

        textField_CS.setText("3000.0");
        textField_Cement.setText("5.0");
        textField_WaterCement.setText("0.60");
        textField_Distance.setText("15.0");
        textField_FlyAsh.setText("25");
        textField_Slag.setText("35");

        ComboBox_AirEntrained.setItems(AirEntrainedList);
        ComboBox_AirEntrained.setValue(AirEntrainedList.get(0));
        ComboBox_Region.setItems(RegionList);
        ComboBox_Region.setValue(RegionList.get(4));


    }


}
