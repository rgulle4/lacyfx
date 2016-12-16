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
    public TableColumn Cement_Column;
    @FXML
    public TableColumn Fly_Ash_Column;
    @FXML
    public TableColumn Slag_Cement_Column;
    @FXML
    public TableColumn Mixing_Water_Column;
    @FXML
    public TableColumn Coarse_Aggregate_Column;
    @FXML
    public TableColumn Fine_Aggregate_Column;
    @FXML
    public TableColumn Air_Percent_Column;
    @FXML
    public TableColumn Region_Column;
    @FXML
    public TextField textField_CS;
    @FXML
    public TextField textField_Cement_Min;
    @FXML
    public TextField textField_FlyAsh_Min;
    @FXML
    public TextField textField_SlagCement_Min;
    @FXML
    public TextField textField_MixingWater_Min;
    @FXML
    public TextField textField_CoarseAggregate_Min;
    @FXML
    public TextField textField_FineAggregate_Min;
    @FXML
    public TextField textField_Air_Min;

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
        Region_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Region"));
        Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Cement"));
        Fly_Ash_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Fly_Ash"));
        Slag_Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Slag"));
        Mixing_Water_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Mixing_Water"));
        Coarse_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CoarseAggregate"));
        Fine_Aggregate_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("FineAggregate"));
        Air_Percent_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Air_Percent"));
    }

    @FXML
    public void searchbutton_newVersion() throws SQLException, ParseException {

        Double CS = 0.0;
        String region = "";
        Double cement_Min = 0.0;
        Double flyAsh_Min= 0.0;
        Double slag_Cement_Min= 0.0;
        Double mixing_Water_Min= 0.0;
        Double air_percent_Min= 0.0;
        Double coarse_Aggregate = 0.0;
        Double fine_Aggregate = 0.0;
        //Compressive Strength
        if(!textField_CS.getText().isEmpty()){
            CS = Double.parseDouble(textField_CS.getText());
        }
        //Region
        if(!ComboBox_Region.getSelectionModel().isEmpty()){
            region = ComboBox_Region.getSelectionModel().getSelectedItem().toString();
        }
        //Portland Cement
        if(!textField_Cement_Min.getText().isEmpty()){
            cement_Min = Double.parseDouble(textField_Cement_Min.getText());
        }
        //Fly Ash
        if(!textField_FlyAsh_Min.getText().isEmpty()){
            flyAsh_Min = Double.parseDouble(textField_FlyAsh_Min.getText());
        }
        //Slag Cement
        if(!textField_SlagCement_Min.getText().isEmpty()){
            slag_Cement_Min = Double.parseDouble(textField_SlagCement_Min.getText());
        }
        //Mixing Water
        if(!textField_MixingWater_Min.getText().isEmpty()){
            mixing_Water_Min = Double.parseDouble(textField_MixingWater_Min.getText());
        }
        //Coarse Aggregate
        if(!textField_CoarseAggregate_Min.getText().isEmpty())
            coarse_Aggregate = Double.parseDouble(textField_CoarseAggregate_Min.getText());
        //Fine Aggregate
        if(!textField_FineAggregate_Min.getText().isEmpty())
            fine_Aggregate = Double.parseDouble(textField_FineAggregate_Min.getText());
        //Air_Percent
        if(!textField_Air_Min.getText().isEmpty()){
            air_percent_Min = Double.parseDouble(textField_Air_Min.getText());
        }

        List<Mix> result = new EPDDatabase().getResultsFilteredBy_epds_NRMCA
                (       CS,region,cement_Min,
                        flyAsh_Min,slag_Cement_Min,
                        mixing_Water_Min,coarse_Aggregate,fine_Aggregate,
                        air_percent_Min
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
