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
import javafx.scene.layout.Region;
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
    public TableColumn Location_Column;
    @FXML
    public TableColumn Cement_Column;
    @FXML
    public TableColumn CementReplacementMin_Column;
    @FXML
    public TableColumn CementReplacementMax_Column;
    @FXML
    public TableColumn Region_Column;
    @FXML
    public TextField textField_CS;
    @FXML
    public TextField textField_Cement_Min;
    @FXML
    public TextField textField_Cement_Max;
    @FXML
    public TextField textField_CementetiousMaterial_Min;
    @FXML
    public TextField textField_CementetiousMaterial_Max;
    @FXML
    public ComboBox ComboBox_Region;
    @FXML
    public Label Label_MixSize;
    @FXML
    public Button closeButton;

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
        ComboBox_Region.setItems(RegionList);
        ComboBox_Region.setValue(RegionList.get(4));        //Default selection is "Nationwide"

        //Alternative materials
        CS_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CS"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Location"));
        Region_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Region"));
        Cement_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Cement"));
        CementReplacementMin_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CementReplacement_Min"));
        CementReplacementMax_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CementReplacement_Max"));
    }

    @FXML
    public void searchbutton_newVersion() throws SQLException, ParseException {

        Double CS = 0.0;
        String region = "";
        Double cement_Min = 0.0;
        Double cement_Max = 0.0;
        Double cementReplacement_Min = 0.0;
        Double cementReplacement_Max = 0.0;

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
        if(!textField_CementetiousMaterial_Min.getText().isEmpty()){
            cementReplacement_Min = Double.parseDouble(textField_CementetiousMaterial_Min.getText());
        }
        if(!textField_CementetiousMaterial_Max.getText().isEmpty()){
            cementReplacement_Max = Double.parseDouble(textField_CementetiousMaterial_Max.getText());
        }
        if(!ComboBox_Region.getSelectionModel().isEmpty()&&!ComboBox_Region.getSelectionModel().isSelected(4)){
            region = ComboBox_Region.getSelectionModel().getSelectedItem().toString();
        }

        List<Mix> result = new EPDDatabase().getResultsFilteredBy_newVersion
                (CS,region,cement_Min,cement_Max,cementReplacement_Min,cementReplacement_Max);
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
