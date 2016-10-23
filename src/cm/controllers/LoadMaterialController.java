package cm.controllers;

import cm.App;
import cm.models.*;
import com.sun.org.apache.bcel.internal.generic.Select;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMaterialController {

    ObservableList<String> DistanceList = FXCollections.observableArrayList("<25 miles","<50 miles","<100 miles",">100 miles");
    @FXML
    private ChoiceBox distanceChoice;
    // To DO: select alternative materials from database for calculating and comparing
    @FXML
    private TextField TF_CS;
    @FXML
    private TableView<Material> MaterialTable;
    @FXML
    private TableView<Material> SelectedTable;
    @FXML
    private TableColumn<Material, String> CS_Column;
    @FXML
    private TableColumn<Material, String> CM_Name_Column;
    @FXML
    private TableColumn<Material, String> Location_Column;
    @FXML
    private TableColumn<Material, String> MixNum_Column;
    @FXML
    private TableColumn<Material,String> ZipCode_Column;
    @FXML
    private TableColumn<Material, String> CS_Column_selected;
    @FXML
    private TableColumn<Material, String> CM_Name_Column_selected;
    @FXML
    private TableColumn<Material, String> Location_Column_selected;
    @FXML
    private TableColumn<Material, String> MixNum_Column_selected;
    @FXML
    private TableColumn<Material,String> ZipCode_Column_selected;

    // Reference to the main application.
    private App mainApp;

    // Constructor is called before the initialize method
//    public LoadMaterialController() {
//
//    }

    private Layer currentLayer;

    public void setCurrentLayer(Layer currentLayer) {
        this.currentLayer = currentLayer;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public void initialize() throws SQLException {
        System.out.println("------------------------------------");
        System.out.println("layer in LoadMaterialController...");
        System.out.println(currentLayer);
        System.out.println("------------------------------------");

        //set item for distance choiceBox

        distanceChoice.setValue("<25 miles");
        distanceChoice.setItems(DistanceList);

        //Alternative materials
        CS_Column.setCellValueFactory(new PropertyValueFactory<Material, String>("CS"));
        CM_Name_Column.setCellValueFactory(new PropertyValueFactory<Material, String>("CM_name"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<Material, String>("Location"));
        MixNum_Column.setCellValueFactory(new PropertyValueFactory<Material, String>("MixNum"));
        ZipCode_Column.setCellValueFactory(new PropertyValueFactory<Material, String>("ZipCode"));

        //Selected materials
        CS_Column_selected.setCellValueFactory(new PropertyValueFactory<Material, String>("CS"));
        CM_Name_Column_selected.setCellValueFactory(new PropertyValueFactory<Material, String>("CM_name"));
        Location_Column_selected.setCellValueFactory(new PropertyValueFactory<Material, String>("Location"));
        MixNum_Column_selected.setCellValueFactory(new PropertyValueFactory<Material, String>("MixNum"));
        ZipCode_Column_selected.setCellValueFactory(new PropertyValueFactory<Material, String>("ZipCode"));

    }

    private ObservableList<Material> data;

    @FXML
    public void searchbutton() throws SQLException, ParseException {
        System.out.println("------------------------------------");
        System.out.println("layer in LoadMaterialController...");
        System.out.println(currentLayer);
        System.out.println("------------------------------------");

        StringBuilder CS_field = new StringBuilder("CS >= ");
        String CS_Textfiled = CS_field.append("'").append(TF_CS.getText()).append("'").toString();

        List<Material> result = new EPDDatabase().getResultsFilteredBy(CS_Textfiled);

//        //test the consistency of data
//        for (int i = 0; i < result.size(); i++){
//            System.out.println(result.get(i).getCS()+
//                    ":"+result.get(i).getODP()+" "+result.get(i).getAP());
//        }
        data = FXCollections.observableArrayList();

        // get material properties from the column names.
        for (int i = 0; i < result.size(); i++){
            Material m = new Material();
            m = result.get(i);
            data.add(m);
        }

        System.out.println("Filtered down to " + data.size() + " records");
        MaterialTable.setItems(data);
    }

    ObservableList<Material> SelectedMaterialsList = FXCollections.observableArrayList();

    //update conversion factor
    public void updatecvf(){
        for (int i = 0; i < SelectedMaterialsList.size(); i++){
            Material selectedMaterial = SelectedMaterialsList.get(i);
            if (selectedMaterial.getUnit().equals("m3")){
                EnvAnalysisCalc.setConFc(EnvAnalysisCalc.getTotV());
            }
            if (selectedMaterial.getUnit().equals("y3")){
                EnvAnalysisCalc.setConFc(EnvAnalysisCalc.getTotV()*1.30795); //1 m^3 = 1 yd^3
            }
        }

    }
    @FXML
    public void selectButton(){

        Material SelectedMat = MaterialTable.getSelectionModel().getSelectedItem();
        SelectedMaterialsList.add(SelectedMat);

        SelectedTable.setItems(SelectedMaterialsList);
    }

    public void removeButton(){
        if (SelectedMaterialsList != null){

            Material SelectedMat = SelectedTable.getSelectionModel().getSelectedItem();
            SelectedMaterialsList.remove(SelectedMat);
            SelectedTable.setItems(SelectedMaterialsList);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No alternative material selected");
            alert.setContentText("Please selected an alternative material first");

            alert.showAndWait();
        }
    }

    public void nextButton() throws ParseException {
        System.out.println("Begin nextButton() method");
        updatecvf();        //update conversion factor
        int i =0;
        Material firstSelectedMaterial = SelectedMaterialsList.get(i);
        currentLayer.setMaterial(firstSelectedMaterial);
        printMaterialInfo();
        System.out.println("END nextButton() method");

    }

    private void printMaterialInfo() {
        System.out.println(
              currentLayer.getMaterial().getCS()
                    + "," + currentLayer.getMaterial().getCompany_Name()
                    + " ," + currentLayer.getMaterial().getLocation()
                    + " ," + currentLayer.getMaterial().getZipCode()
                    + " ," + currentLayer.getMaterial().getMixNum());
    }



}
