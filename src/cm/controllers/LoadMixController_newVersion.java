package cm.controllers;

import cm.models.EPDDatabase;
import cm.models.Layer;
import cm.models.Mix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    public TableColumn Light_Weight_Column;
    @FXML
    public TableColumn Cement_Column;
    @FXML
    public TableColumn Fly_Ash_Column;
    @FXML
    public TableColumn Slag_Cement_Column;
    @FXML
    public TableColumn Mixing_Water_Column;
    @FXML
    public TableColumn Crushed_Coarse_Aggregate_Column;
    @FXML
    public TableColumn Crushed_Fine_Aggregate_Column;
    @FXML
    public TableColumn Natural_Coarse_Aggregate_Column;
    @FXML
    public TableColumn Natural_Fine_Aggregate_Column;
    @FXML
    public TableColumn Light_Weight_Aggregate_Column;
    @FXML
    public TableColumn Air_Percent_Column;
    @FXML
    public TableColumn Air_Entraining_Admixture_Column;
    @FXML
    public TableColumn Water_Reducer_Column;
    @FXML
    public TableColumn High_Range_Water_Reducer_Column;
    @FXML
    public TableColumn Accelerator_Column;
    @FXML
    public TableColumn Region_Column;
    @FXML
    public TextField textField_CS;
    @FXML
    public TextField textField_Cement_Min;
    @FXML
    public TextField textField_Cement_Max;
    @FXML
    public ComboBox ComboBox_Region;
    @FXML
    public Label Label_MixSize;
    @FXML
    public Button closeButton;

    ObservableList<String> RegionList = FXCollections.observableArrayList(
            "North Central","Pacific Northwest","Pacific Southwest",
            "Rocky Mountains","South Central","South Eastern",
            "Eastern","Great Lakes Midwest","National");
    @FXML
    public void initialize(){
        System.out.println("------------------------------------");
        System.out.println("layer in LoadMixController...");
        System.out.println(currentLayer);
        System.out.println("------------------------------------");

        textField_CS.setText("3000.0");
        ComboBox_Region.setItems(RegionList);
        ComboBox_Region.setValue(RegionList.get(4));        //Default selection is "South Central"
        //Alternative materials
        CS_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CS"));
        Light_Weight_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("IsLightWeight"));
        Region_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Region"));
        Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Cement"));
        Fly_Ash_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Fly_Ash"));
        Slag_Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Slag"));
        Mixing_Water_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Mixing_Water"));
        Crushed_Coarse_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Crushed_Coarse_Aggregate"));
        Crushed_Fine_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Crushed_Fine_Aggregate"));
        Natural_Coarse_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Natural_Coarse_Aggregate"));
        Natural_Fine_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Natural_Fine_Aggregate"));
        Light_Weight_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("LightWeight_Aggregate"));
        Air_Percent_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Air_Percent"));
        Air_Entraining_Admixture_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Air_Entrained"));
        Water_Reducer_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Water_Reducer"));
        High_Range_Water_Reducer_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("High_Range_Water_Reducer"));
        Accelerator_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Accelerator"));
    }

    @FXML
    public void searchbutton_newVersion() throws SQLException, ParseException {

        Double CS = 0.0;
        String region = "";
        Double cement_Min = 0.0;
        Double cement_Max = 0.0;

        if(!textField_CS.getText().isEmpty()){
            CS = Double.parseDouble(textField_CS.getText());
        }
        if(!textField_Cement_Min.getText().isEmpty()){
            // -5.0 Kg error is allowed
            cement_Min = Double.parseDouble(textField_Cement_Min.getText())-5.0;
        }
        if(!textField_Cement_Max.getText().isEmpty()){
            // +5.0 Kg error is allowed
            cement_Max = Double.parseDouble(textField_Cement_Max.getText())+5.0;
        }
        if(!ComboBox_Region.getSelectionModel().isEmpty()){
            region = ComboBox_Region.getSelectionModel().getSelectedItem().toString();
        }

        List<Mix> result = new EPDDatabase().getResultsFilteredBy_epds_NRMCA
                (CS,region,cement_Min,cement_Max);
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

    public void saveButton(){
        currentLayer.setMixes(MaterialTable.getItems());
    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
