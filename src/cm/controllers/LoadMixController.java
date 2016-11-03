package cm.controllers;

import cm.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static cm.models.Model.DESTINATION_ZIP_CODE_MUTABLE;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMixController {

    ObservableList<String> DistanceList = FXCollections.observableArrayList(
            "<10 miles","<25 miles","<50 miles","<100 miles");
    @FXML
    private ChoiceBox distanceChoice;
    // To DO: select alternative materials from database for calculating and comparing
    @FXML
    private TextField textField_CS;
    @FXML
    private TextField textField_companyName;
    @FXML
    private TextField textField_ZipCode;
    @FXML
    private TableView<Mix> MaterialTable;
    @FXML
    private TableView<Mix> SelectedTable;
    @FXML
    private TableColumn<Mix, String> CS_Column;
    @FXML
    private TableColumn<Mix, String> CM_Name_Column;
    @FXML
    private TableColumn<Mix, String> Location_Column;
    @FXML
    private TableColumn<Mix, String> MixNum_Column;
    @FXML
    private TableColumn<Mix,String> ZipCode_Column;
    @FXML
    private TableColumn<Mix, String> CS_Column_selected;
    @FXML
    private TableColumn<Mix, String> CM_Name_Column_selected;
    @FXML
    private TableColumn<Mix, String> Location_Column_selected;
    @FXML
    private TableColumn<Mix, String> MixNum_Column_selected;
    @FXML
    private TableColumn<Mix,String> ZipCode_Column_selected;

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
        System.out.println("layer in LoadMixController...");
        System.out.println(currentLayer);
        System.out.println("------------------------------------");

        //set item for distance choiceBox

        textField_CS.setText("");
//        textField_companyName.setText("");
        textField_ZipCode.setText("");
        distanceChoice.setValue("<10 miles");
        distanceChoice.setItems(DistanceList);


        //Alternative materials
        CS_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CS"));
        CM_Name_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("CM_name"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("Location"));
        MixNum_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("MixNum"));
        ZipCode_Column.setCellValueFactory(new PropertyValueFactory<Mix, String>("ZipCode"));

        //Selected materials
        CS_Column_selected.setCellValueFactory(new PropertyValueFactory<Mix, String>("CS"));
        CM_Name_Column_selected.setCellValueFactory(new PropertyValueFactory<Mix, String>("CM_name"));
        Location_Column_selected.setCellValueFactory(new PropertyValueFactory<Mix, String>("Location"));
        MixNum_Column_selected.setCellValueFactory(new PropertyValueFactory<Mix, String>("MixNum"));
        ZipCode_Column_selected.setCellValueFactory(new PropertyValueFactory<Mix, String>("ZipCode"));

    }

    private ObservableList<Mix> data;

    @FXML

    public void searchbutton() throws SQLException, ParseException {
        String CS = textField_CS.getText();
        String cmName = textField_companyName.getText();
        // get a zip set within a certain radius to the location of project
        ZipCodeUtil zcu = new ZipCodeUtil();
        String destinationZipcode = DESTINATION_ZIP_CODE_MUTABLE;
        Double radius = getRadius();
        // TEST: Get 41 zip codes of original place from database;
        List<String> origins = new EPDDatabase().getZipcode();
        Map<String, Double> filteredZipcodeMap
                = zcu.zipsWithinRadius(radius,origins,destinationZipcode);
        // get qualified material after searching
        List<Mix> result = new EPDDatabase()
                .getResultsFilteredBy(filteredZipcodeMap,CS,cmName);

        data = FXCollections.observableArrayList();

        // get material properties from the column names.
        for (int i = 0; i < result.size(); i++){
            Mix m = new Mix();
            m = result.get(i);
            data.add(m);
        }

        // get a zipCode list
        List<String> zipCode_result = new EPDDatabase().getAllZipcode();
        System.out.println("show results of all distinct zip code in the database");
        for (int j = 0; j< zipCode_result.size();j++){
            String zip_code = zipCode_result.get(j);
            // show result of the zipcode list
            System.out.println(zip_code);
        }

        System.out.println("Filtered down to " + data.size() + " records");
        MaterialTable.setItems(data);
    }

    ObservableList<Mix> selectedMixesList = FXCollections.observableArrayList();

    @FXML
    public void selectButton(){

        Mix SelectedMat = MaterialTable.getSelectionModel().getSelectedItem();
        selectedMixesList.add(SelectedMat);
        SelectedTable.setItems(selectedMixesList);
        currentLayer.addMaterial(new Mix());       //add a new mix with null value
    }

    public void removeButton(){
        if (selectedMixesList != null){

            int selectedIndex = SelectedTable.getSelectionModel().getSelectedIndex();
            selectedMixesList.remove(selectedIndex);
            SelectedTable.setItems(selectedMixesList);
            currentLayer.removeMaterial();      //remove a new mix with null value

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
        // remove all the selectedMix first
        System.out.println("All mix in the SelectedMixesList are removed!!");
        // add all the selectedMix
        for (int i =0; i < currentLayer.getNumberofMaterials();i ++){
            Mix newSelectedMix = selectedMixesList.get(i);
            currentLayer.setMaterial(i,newSelectedMix);
        }
        System.out.println(currentLayer.getNumberofMaterials()+ "  mix added!!");
        printMaterialInfo();
        System.out.println("END nextButton() method");

    }

    private void removeAllMix(Layer currentLayer){
        for (int i =0; i < currentLayer.getNumberofMaterials();i ++){
            currentLayer.removeMaterial();
        }
    }

    private void printMaterialInfo() {
        System.out.println(
                        "CurrentLayer has "
                        +currentLayer.getNumberofMaterials()
                        + " SelectedMix");
        for (int i = 0; i < currentLayer.getNumberofMaterials(); i++){
            System.out.println(
                    // print information of the first material
                    currentLayer.getMaterial(i).getCS()
                            + "," + currentLayer.getMaterial(i).getCompany_Name()
                            + " ," + currentLayer.getMaterial(i).getLocation()
                            + " ," + currentLayer.getMaterial(i).getZipCode()
                            + " ," + currentLayer.getMaterial(i).getMixNum());
        }

    }

    private double getRadius(){
        Double radius;
        if (distanceChoice.getSelectionModel().isSelected(0)){
            radius = 10.0*1609.344; //1 miles = 1609.344 meters
        }
        else if (distanceChoice.getSelectionModel().isSelected(1)){
            radius = 25.0*1609.344; //1 miles = 1609.344 meters
        }
        else if (distanceChoice.getSelectionModel().isSelected(2)){
            radius = 50.0*1609.344; //1 miles = 1609.344 meters
        }
        else if (distanceChoice.getSelectionModel().isSelected(3)){
            radius = 100.0*1609.344; //1 miles = 1609.344 meters
        }
        else radius = 0.0;
        return radius;
    }


}
