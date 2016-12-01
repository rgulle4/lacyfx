package cm.controllers;

import cm.models.EPDDatabase;
import cm.models.Layer;
import cm.models.Mix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

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
    public TableColumn Region_Column;
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
    @FXML
    public Label Label_MixSize;

    ObservableList<String> AirEntrainedList = FXCollections.observableArrayList("Yes","No","NA");
    ObservableList<String> RegionList = FXCollections.observableArrayList(
            "West","South","Northeast","Midwest","Nationwide");
    @FXML
    public void initialize(){
        System.out.println("------------------------------------");
        System.out.println("layer in LoadMixController...");
        System.out.println(currentLayer);
        System.out.println("------------------------------------");

        textField_CS.setText("3000.0");
        textField_Distance.setText("15.0");

        ComboBox_AirEntrained.setItems(AirEntrainedList);
        ComboBox_AirEntrained.setValue(AirEntrainedList.get(0));
        ComboBox_Region.setItems(RegionList);
        ComboBox_Region.setValue(RegionList.get(4));

        //Alternative materials
        CS_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CS"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Location"));
        Region_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Region"));
        Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Cement"));
        WaterCement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("WaterCement"));
        Fly_Ash_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Fly_Ash"));
        Slag_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Slag"));
        Air_Entrained_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Air_Entrained"));
        Aggregate1_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Aggregate1"));
        Aggregate2_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Aggregate2"));
    }

    @FXML
    public void searchbutton_newVersion() throws SQLException, ParseException {
        Double distance = 0.0;

        Double CS = 0.0;
        String region = "";
        String cement = "";
        String water_cement = "";
        String fly_ash = "";
        String slag = "";
        String air_Entrained = "";
        String aggregate1 = "";
        String aggregate2 = "";
        if(!textField_Distance.getText().isEmpty()){
            distance = Double.parseDouble(textField_Distance.getText());
        }
        if(!textField_CS.getText().isEmpty()){
            CS = Double.parseDouble(textField_CS.getText());
        }
        if(!textField_Cement.getText().isEmpty()){
            cement = textField_Cement.getText();
        }
        if(!textField_WaterCement.getText().isEmpty()){
            water_cement = textField_WaterCement.getText();
        }
        if(!textField_FlyAsh.getText().isEmpty()){
            fly_ash = textField_FlyAsh.getText();
        }
        if(!textField_Slag.getText().isEmpty()){
            slag = textField_Slag.getText();
        }
        if(!ComboBox_Region.getSelectionModel().isEmpty()&&!ComboBox_Region.getSelectionModel().isSelected(4)){
            region = ComboBox_Region.getSelectionModel().getSelectedItem().toString();
        }
        if(!ComboBox_AirEntrained.getSelectionModel().isEmpty()){
            if (ComboBox_AirEntrained.getSelectionModel().isSelected(0)) air_Entrained ="Y";
            else if (ComboBox_AirEntrained.getSelectionModel().isSelected(1)) air_Entrained ="N";
            else air_Entrained = "";
        }
        if(!ComboBox_Aggregate1.getSelectionModel().isEmpty()){
            aggregate1 = ComboBox_Aggregate1.getSelectionModel().getSelectedItem().toString();
        }
        if(!ComboBox_Aggregate2.getSelectionModel().isEmpty()){
            aggregate2 = ComboBox_Aggregate2.getSelectionModel().getSelectedItem().toString();
        }
        List<Mix> result = new EPDDatabase().getResultsFilteredBy_newVersion
                (CS,region,air_Entrained,cement,water_cement,fly_ash,slag,aggregate1,aggregate2);
        StringBuilder sb_MixSize = new StringBuilder(Integer.toString(result.size())).append(" Mixes");
        Label_MixSize.setText(sb_MixSize.toString());

        ObservableList<Mix> data = FXCollections.observableArrayList();

        // get material properties from the column names.
        for (int i = 0; i < result.size(); i++){
            Mix m = result.get(i);
            data.add(m);
        }
        MaterialTable.setItems(data);
    }
}
