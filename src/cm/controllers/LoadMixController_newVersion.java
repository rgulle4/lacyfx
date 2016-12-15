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
    public TextField textField_FlyAsh_Min;
    @FXML
    public TextField textField_FlyAsh_Max;
    @FXML
    public TextField textField_SlagCement_Min;
    @FXML
    public TextField textField_SlagCement_Max;
    @FXML
    public TextField textField_MixingWater_Min;
    @FXML
    public TextField textField_MixingWater_Max;
    @FXML
    public TextField textField_CCAggregate_Min;
    @FXML
    public TextField textField_CCAggregate_Max;
    @FXML
    public TextField textField_CFAggregate_Min;
    @FXML
    public TextField textField_CFAggregate_Max;
    @FXML
    public TextField textField_NCAggregate_Min;
    @FXML
    public TextField textField_NCAggregate_Max;
    @FXML
    public TextField textField_NFAggregate_Min;
    @FXML
    public TextField textField_NFAggregate_Max;
    @FXML
    public TextField textField_LWAggregate_Min;
    @FXML
    public TextField textField_LWAggregate_Max;
    @FXML
    public TextField textField_Air_Min;
    @FXML
    public TextField textField_Air_Max;
    @FXML
    public TextField textField_EntrainingAdmixture_Min;
    @FXML
    public TextField textField_EntrainingAdmixture_Max;
    @FXML
    public TextField textField_WaterReducer_Min;
    @FXML
    public TextField textField_WaterReducer_Max;
    @FXML
    public TextField textField_HighWaterReducer_Min;
    @FXML
    public TextField textField_HighWaterReducer_Max;
    @FXML
    public TextField textField_Accelerator_Min;
    @FXML
    public TextField textField_Accelerator_Max;

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
//        Light_Weight_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("IsLightWeight"));
        Region_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Region"));
        Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Cement"));
        Fly_Ash_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Fly_Ash"));
        Slag_Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Slag"));
        Mixing_Water_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Mixing_Water"));
//        Crushed_Coarse_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Crushed_Coarse_Aggregate"));
//        Crushed_Fine_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Crushed_Fine_Aggregate"));
//        Natural_Coarse_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Natural_Coarse_Aggregate"));
//        Natural_Fine_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Natural_Fine_Aggregate"));
//        Light_Weight_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("LightWeight_Aggregate"));
        Air_Percent_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Air_Percent"));
//        Air_Entraining_Admixture_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Air_Entrained"));
//        Water_Reducer_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Water_Reducer"));
//        High_Range_Water_Reducer_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("High_Range_Water_Reducer"));
//        Accelerator_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Accelerator"));
    }

    @FXML
    public void searchbutton_newVersion() throws SQLException, ParseException {

        Double CS = 0.0;
        String region = "";
        Double cement_Min = 0.0;
        Double cement_Max = 0.0;
        Double flyAsh_Min= 0.0;
        Double flyAsh_Max= 0.0;
        Double slag_Cement_Min= 0.0; Double slag_Cement_Max= 0.0;
        Double mixing_Water_Min= 0.0; Double mixing_Water_Max= 0.0;
        Double CC_Aggregate_Min= 0.0; Double CC_Aggregate_Max= 0.0;
        Double CF_Aggregate_Min= 0.0; Double CF_Aggregate_Max= 0.0;
        Double NC_Aggregate_Min= 0.0; Double NC_Aggregate_Max= 0.0;
        Double NF_Aggregate_Min= 0.0; Double NF_Aggregate_Max= 0.0;
        Double LW_Aggregate_Min= 0.0; Double LW_Aggregate_Max= 0.0;
        Double air_percent_Min= 0.0; Double air_percent_Max= 0.0;
        Double airEntrainingAdmix_Min= 0.0; Double airEntrainingAdmix_Max= 0.0;
        Double waterReducer_Min= 0.0; Double waterReducer_Max= 0.0;
        Double highRange_WaterReducer_Min= 0.0; Double highRange_WaterReducer_Max= 0.0;
        Double accelerator_Min= 0.0; Double accelerator_Max= 0.0;

        if(!textField_CS.getText().isEmpty()){
            CS = Double.parseDouble(textField_CS.getText());
        }
        if(!ComboBox_Region.getSelectionModel().isEmpty()){
            region = ComboBox_Region.getSelectionModel().getSelectedItem().toString();
        }
        if(!textField_Cement_Min.getText().isEmpty()){
            // -5.0 lbs error is allowed
            cement_Min = Double.parseDouble(textField_Cement_Min.getText())-5.0;
        }
        //Fly Ash
        if(!textField_FlyAsh_Min.getText().isEmpty()){
            // -5.0 lbs error is allowed
            flyAsh_Min = Double.parseDouble(textField_FlyAsh_Min.getText())-5.0;
        }
        //Slag Cement
        if(!textField_SlagCement_Min.getText().isEmpty()){
            // -0.5 lbs error is allowed
            slag_Cement_Min = Double.parseDouble(textField_SlagCement_Min.getText())-0.5;
        }
        //Air_Percent
        if(!textField_Air_Min.getText().isEmpty()){
            // -5.0 lbs error is allowed
            air_percent_Min = Double.parseDouble(textField_Air_Min.getText());
        }

        List<Mix> result = new EPDDatabase().getResultsFilteredBy_epds_NRMCA
                (       CS,region,cement_Min,cement_Max,
                        flyAsh_Min,flyAsh_Max,slag_Cement_Min,slag_Cement_Max,
                        mixing_Water_Min,mixing_Water_Max,CC_Aggregate_Min,
                        CC_Aggregate_Max,CF_Aggregate_Min,CF_Aggregate_Max,
                        NC_Aggregate_Min,NC_Aggregate_Max,NF_Aggregate_Min,
                        NF_Aggregate_Max,LW_Aggregate_Min,LW_Aggregate_Max,
                        air_percent_Min,air_percent_Max,
                        airEntrainingAdmix_Min,airEntrainingAdmix_Max,
                        waterReducer_Min,waterReducer_Max,
                        highRange_WaterReducer_Min,highRange_WaterReducer_Max,
                        accelerator_Min,accelerator_Max
                );
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
