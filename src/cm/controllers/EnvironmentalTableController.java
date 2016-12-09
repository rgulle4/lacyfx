package cm.controllers;

import cm.models.Design;
import cm.models.Layer;
import cm.models.Mix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static cm.models.Model.DESIGNS;

/**
 * Created by Administrator on 2016/11/10.
 */
public class EnvironmentalTableController {
    @FXML
    private TableView<propertyMix> energyConsumptionTable;
    @FXML
    private ComboBox performanceType_ComboBox;
    @FXML
    private ComboBox environmentalImpact_ComboBox;
    @FXML
    private ComboBox rawValue_ComboBox;
    @FXML
    private ComboBox design_ComboBox;
    @FXML
    private ComboBox layer_ComboBox;
    @FXML
    private ComboBox impactCategory_ComboBox;
    @FXML
    private TableView<propertyMix> resourceConsumptionTable;
    @FXML
    private Label energyConsumptionLabel;
    @FXML
    private Label resourceConsumptionLabel;
    @FXML
    private Label resourceConsumptionNoteLabel;
            
    // Type of performance
    ObservableList<String> performanceType = FXCollections.
            observableArrayList(
                    "Environmental Performance",
                    "Economical Performance",
                    "Overall Performance");
    // EnvironmentalImpact type
    ObservableList<String> impactType = FXCollections.
            observableArrayList(
                    "EPD",
                    "TSP",
                    "Overall"
            );
    // raw Value type
    ObservableList<String> rawValueType = FXCollections.
            observableArrayList(
                    "Raw impact per functional unit",
                    "Normalized impact per functional unit",
                    "Weighted impact per functional unit"
            );
    // Number of designs
    ObservableList<String> designNum = FXCollections.
            observableArrayList();
    // Number of layers in this design
    ObservableList<String> layerNum = FXCollections
            .observableArrayList();
    // Number of layers in this design
    ObservableList<String> mixNum = FXCollections
            .observableArrayList();
    @FXML
    public void initialize() {
        //set up default elements
        setupPerformanceType_ComboBox();
        setupEnvironmentalImpact_ComboBox();
        setupRawValue_ComboBox();
        setupImpactCategory_ComboBox();
        //set up design number
        setupDesignNumber_ComboBox();
        energyConsumptionTable.setVisible(false);
        energyConsumptionLabel.setVisible(false);
        resourceConsumptionTable.setVisible(false);
        resourceConsumptionLabel.setVisible(false);
        resourceConsumptionNoteLabel.setVisible(false);
    }
    private String perfType;
    private String envImpactType;
    private String valueType;
    private String impactCategory;      //GWP, ODP, AP, EP, POCP, Energy Consumption...
    public void showOutputTable(){
        String designID = design_ComboBox.getSelectionModel().getSelectedItem().toString();
        String layerID = layer_ComboBox.getSelectionModel().getSelectedItem().toString();
        Design designTemp = DESIGNS.get(designID);
        int layerIndex = layer_ComboBox.getSelectionModel().getSelectedIndex();
        Layer layerTemp = designTemp.getLayer(layerIndex);
        //Get the whole selected Mix lists
        Mix mixTemp = layerTemp.getAveraged_Mix();

        perfType = performanceType_ComboBox.getSelectionModel().getSelectedItem().toString();
        envImpactType = environmentalImpact_ComboBox.getSelectionModel().getSelectedItem().toString();
        valueType = rawValue_ComboBox.getSelectionModel().getSelectedItem().toString();
        impactCategory = impactCategory_ComboBox.getSelectionModel().getSelectedItem().toString();
        showData(mixTemp,designID,layerID);
    }
    public void showData(Mix aMix, String design_ID, String layer_ID){
        if(impactCategory != "All Resource Consumption Impact"){
            showEnergyConsumptionData(aMix,design_ID,layer_ID);
        }else {
            showResourceConsumptionData(aMix,design_ID,layer_ID);
        }
    }
    public void showEnergyConsumptionData(Mix amix, String design_ID, String layer_ID){
        energyConsumptionTable.getColumns().clear();
        energyConsumptionTable.layout();
        energyConsumptionTable.setVisible(true);
        energyConsumptionLabel.setVisible(true);
        resourceConsumptionTable.getColumns().clear();
        resourceConsumptionTable.layout();
        resourceConsumptionTable.setVisible(false);
        resourceConsumptionLabel.setVisible(false);
        resourceConsumptionNoteLabel.setVisible(false);
        //Obtain data from mixes
        ObservableList<propertyMix> data = FXCollections.observableArrayList();

        propertyMix pmix = new propertyMix();
        pmix.setDesign_ID(design_ID);
        pmix.setLayer_ID(layer_ID);
        pmix.setProduct_ID(amix.getProduct_ID()+amix.getZipCode());
        List<Double> result = getEnergyConsumptionResults(amix);
        List<String> result_Units = getEPDUnits(amix);
        int count =0;
        if(impactCategory == "GWP") {
            pmix.setGWP(result.get(count));
            if (valueType == "Raw impact per functional unit") {
                String gwp_units = result_Units.get(0);
                if (gwp_units.equals("kg CO2 eq/m3")) pmix.setGWP_Units("Kg CO2 eq");
                else System.out.println(gwp_units +" was not found");
            }count++;
        }
        if(impactCategory == "ODP") {
            pmix.setODP(result.get(count));
            if (valueType == "Raw impact per functional unit") {
                String odp_units = result_Units.get(0);
                if (odp_units.equals("kg CFC-11 eq/m3")) pmix.setODP_Units("Kg CFC -11 eq");
                else System.out.println(odp_units+" was not found");
            }count++;
        }
        if(impactCategory == "AP") {
            pmix.setAP(result.get(count));
            if (valueType == "Raw impact per functional unit") {
                String ap_units = result_Units.get(0);
                if (ap_units.equals("kg SO2 eq/m3")) pmix.setAP_Units("Kg SO2 eq");
                else System.out.println(ap_units+" was not found");
            }count++;
        }
        if(impactCategory == "EP") {
            pmix.setEP(result.get(count));
            if (valueType == "Raw impact per functional unit") {
                String ep_units = result_Units.get(0);
                if (ep_units.equals("kg N eq/m3")) pmix.setEP_Units("Kg N eq");
                else System.out.println(ep_units+" was not found");
            }count++;
        }
        if(impactCategory == "POCP") {
            pmix.setPOCP(result.get(count));
            if (valueType == "Raw impact per functional unit") {
                String pocp_units = result_Units.get(0);
                if (pocp_units.equals("kg O3 eq/m3")) pmix.setPOCP_Units("Kg O3 eq");
                else System.out.println(pocp_units+" was not found");
            }count++;
        }
        if(impactCategory == "PrimaryEnergyConsumption") {
            pmix.setTotalPrimaryEnergyConsumption(result.get(count));
            if (valueType == "Raw impact per functional unit") {
                String tpec_units = result_Units.get(0);
                if (tpec_units.equals("MJ/m3")) pmix.setTotalPrimaryEnergyConsumption_Units("MJ");
                else System.out.println(tpec_units+" was not found");
            }count=0;
        }
        if(impactCategory == "Impact Analysis Comparison of All Alternatives"){}
        if(impactCategory == "All Energy Consumption Impact"){
            pmix.setGWP(result.get(0));
            pmix.setODP(result.get(1));
            pmix.setAP(result.get(2));
            pmix.setEP(result.get(3));
            pmix.setPOCP(result.get(4));
            pmix.setTotalPrimaryEnergyConsumption(result.get(5));
            if (valueType == "Raw impact per functional unit"){
                String gwp_units = result_Units.get(0);
                if (gwp_units.equals("kg CO2 eq/m3")) pmix.setGWP_Units("Kg CO2 eq");
                else System.out.println(gwp_units +" was not found");
                String odp_units = result_Units.get(1);
                if (odp_units.equals("kg CFC-11 eq/m3")) pmix.setODP_Units("Kg CFC -11 eq");
                else System.out.println(odp_units+" was not found");
                String ap_units = result_Units.get(2);
                if (ap_units.equals("kg SO2 eq/m3")) pmix.setAP_Units("Kg SO2 eq");
                else System.out.println(ap_units+" was not found");
                String ep_units = result_Units.get(3);
                if (ep_units.equals("kg N eq/m3")) pmix.setEP_Units("Kg N eq");
                else System.out.println(ep_units+" was not found");
                String pocp_units = result_Units.get(4);
                if (pocp_units.equals("kg O3 eq/m3")) pmix.setPOCP_Units("Kg O3 eq");
                else System.out.println(pocp_units+" was not found");
                String tpec_units = result_Units.get(5);
                if (tpec_units.equals("MJ/m3")) pmix.setTotalPrimaryEnergyConsumption_Units("MJ");
                else System.out.println(tpec_units+" was not found");
            }
        }
        data.add(pmix);
        TableColumn<propertyMix,String> designColumn = new TableColumn("Design_ID");
        designColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> layerColumn = new TableColumn("Layer_ID");
        layerColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> gwpColumn = new TableColumn("GWP");
        gwpColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> gwpUnitsColumn = new TableColumn("GWP_Unit");
        gwpUnitsColumn.setPrefWidth(100.0);
        TableColumn<propertyMix,Double> odpColumn = new TableColumn("ODP");
        odpColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> odpUnitsColumn = new TableColumn("ODP_Unit");
        odpUnitsColumn.setPrefWidth(100.0);
        TableColumn<propertyMix,Double> apColumn = new TableColumn("AP");
        apColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> apUnitsColumn = new TableColumn("AP_Unit");
        apUnitsColumn.setPrefWidth(100.0);
        TableColumn<propertyMix,Double> epColumn = new TableColumn("EP");
        epColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> epUnitsColumn = new TableColumn("EP_Unit");
        epUnitsColumn.setPrefWidth(100.0);
        TableColumn<propertyMix,Double> pocpColumn = new TableColumn("POCP");
        pocpColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> pocpUnitsColumn = new TableColumn("POCP_Unit");
        pocpUnitsColumn.setPrefWidth(100.0);
        TableColumn<propertyMix,Double> tpecColumn = new TableColumn("EnergyConsumption");
        tpecColumn.setPrefWidth(125.0);
        TableColumn<propertyMix,String> tpecUnitsColumn = new TableColumn("EnergyConsumption_Unit");
        tpecUnitsColumn.setPrefWidth(100.0);

        if(impactCategory == "All Energy Consumption Impact"){
            energyConsumptionTable.getColumns().add(designColumn);
            designColumn.setCellValueFactory(new PropertyValueFactory<>("Design_ID"));
            energyConsumptionTable.getColumns().add(layerColumn);
            layerColumn.setCellValueFactory(new PropertyValueFactory<>("Layer_ID"));
            energyConsumptionTable.getColumns().add(gwpColumn);
            gwpColumn.setCellValueFactory(new PropertyValueFactory<>("GWP"));
            if (valueType == "Raw impact per functional unit") energyConsumptionTable.getColumns().add(gwpUnitsColumn);
            gwpUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("GWP_Units"));
            energyConsumptionTable.getColumns().add(odpColumn);
            odpColumn.setCellValueFactory(new PropertyValueFactory<>("ODP"));
            if (valueType == "Raw impact per functional unit") energyConsumptionTable.getColumns().add(odpUnitsColumn);
            odpUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("ODP_Units"));
            energyConsumptionTable.getColumns().add(apColumn);
            apColumn.setCellValueFactory(new PropertyValueFactory<>("AP"));
            if (valueType == "Raw impact per functional unit") energyConsumptionTable.getColumns().add(apUnitsColumn);
            apUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("AP_Units"));
            energyConsumptionTable.getColumns().add(epColumn);
            epColumn.setCellValueFactory(new PropertyValueFactory<>("EP"));
            if (valueType == "Raw impact per functional unit") energyConsumptionTable.getColumns().add(epUnitsColumn);
            epUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("EP_Units"));
            energyConsumptionTable.getColumns().add(pocpColumn);
            pocpColumn.setCellValueFactory(new PropertyValueFactory<>("POCP"));
            if (valueType == "Raw impact per functional unit") energyConsumptionTable.getColumns().add(pocpUnitsColumn);
            pocpUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("POCP_Units"));
            energyConsumptionTable.getColumns().add(tpecColumn);
            tpecColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrimaryEnergyConsumption"));
            if (valueType == "Raw impact per functional unit") energyConsumptionTable.getColumns().add(tpecUnitsColumn);
            tpecUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrimaryEnergyConsumption_Units"));
//            energyConsumptionTable.getColumns().add(totEneryConsumptionEmissionColumn);
//            pocpColumn.setCellValueFactory(new PropertyValueFactory<>("TotalEnergyConsumptionEmission"));
        }else{
            energyConsumptionTable.getColumns().add(designColumn);
            designColumn.setCellValueFactory(new PropertyValueFactory<>("Design_ID"));
            energyConsumptionTable.getColumns().add(layerColumn);
            layerColumn.setCellValueFactory(new PropertyValueFactory<>("Layer_ID"));
            if(impactCategory == "GWP"){
                energyConsumptionTable.getColumns().add(gwpColumn);
                gwpColumn.setCellValueFactory(new PropertyValueFactory<>("GWP"));
                energyConsumptionTable.getColumns().add(gwpUnitsColumn);
                gwpUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("GWP_Units"));
            }
            if(impactCategory == "ODP"){
                energyConsumptionTable.getColumns().add(odpColumn);
                odpColumn.setCellValueFactory(new PropertyValueFactory<>("ODP"));
                energyConsumptionTable.getColumns().add(odpUnitsColumn);
                odpUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("ODP_Units"));
            }
            if(impactCategory == "AP"){
                energyConsumptionTable.getColumns().add(apColumn);
                apColumn.setCellValueFactory(new PropertyValueFactory<>("AP"));
                energyConsumptionTable.getColumns().add(apUnitsColumn);
                apUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("AP_Units"));
            }
            if(impactCategory == "EP"){
                energyConsumptionTable.getColumns().add(epColumn);
                epColumn.setCellValueFactory(new PropertyValueFactory<>("EP"));
                energyConsumptionTable.getColumns().add(epUnitsColumn);
                epUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("EP_Units"));
            }
            if(impactCategory == "POCP"){
                energyConsumptionTable.getColumns().add(pocpColumn);
                pocpColumn.setCellValueFactory(new PropertyValueFactory<>("POCP"));
                energyConsumptionTable.getColumns().add(pocpUnitsColumn);
                pocpUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("POCP_Units"));
            }
            if(impactCategory == "PrimaryEnergyConsumption"){
                energyConsumptionTable.getColumns().add(tpecColumn);
                tpecColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrimaryEnergyConsumption"));
                energyConsumptionTable.getColumns().add(tpecUnitsColumn);
                tpecUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrimaryEnergyConsumption_Units"));
            }
        }
        //Insert value to table
        System.out.println("propertyMix size is: "+data.size());
        energyConsumptionTable.setItems(data);
    }

    public void showResourceConsumptionData(Mix amix, String design_ID, String layer_ID){
        resourceConsumptionTable.getColumns().clear();
        resourceConsumptionTable.layout();
        resourceConsumptionTable.setVisible(true);
        resourceConsumptionLabel.setVisible(true);
        energyConsumptionTable.getColumns().clear();
        energyConsumptionTable.layout();
        energyConsumptionTable.setVisible(false);
        energyConsumptionLabel.setVisible(false);
        resourceConsumptionNoteLabel.setVisible(false);
        ObservableList<propertyMix> data = FXCollections.observableArrayList();
        propertyMix pmix = new propertyMix();
        pmix.setDesign_ID(design_ID);
        pmix.setLayer_ID(layer_ID);
        pmix.setProduct_ID(amix.getProduct_ID()+amix.getZipCode());
        List<String> result_Units = getEPDUnits(amix);
        //set up Raw impact per functional unit for resource data
        pmix.setTotalWaterConsumption(amix.getTotalWaterConsumption());
        // show special flag
        if(amix.getRenewableMaterialResourcesUse() == 0.0
            || amix.getNonRenewableMaterialResource() == 0.0){
            resourceConsumptionNoteLabel.setVisible(true);
        }
        pmix.setRenewableMaterialResourcesUse(amix.getRenewableMaterialResourcesUse());
        pmix.setNonRenewableMaterialResource(amix.getNonRenewableMaterialResource());
        if (valueType == "Raw impact per functional unit"){
            if (valueType == "Raw impact per functional unit"){
                String twc_units = result_Units.get(0);
                String renewable_mix_source_units = result_Units.get(1);
                String non_renewable_mix_source_units = result_Units.get(2);
                if(twc_units.equals("m3/m3")) pmix.setTotalWaterConsumption_Units("m3");
                else System.out.println(twc_units+ " was not found");
                if(renewable_mix_source_units.equals("Kg/m3")) pmix.setRenewableMaterialResourcesUse_Units("kg");
                else System.out.println(renewable_mix_source_units+ " was not found");
                if (non_renewable_mix_source_units.equals("Kg/m3")) pmix.setNonRenewableMaterialResource_Unit("kg");
                else System.out.println(non_renewable_mix_source_units+ " was not found");
            }
        }
        data.add(pmix);

        TableColumn<propertyMix,String> designColumn = new TableColumn("Design_ID");
        designColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> layerColumn = new TableColumn("Layer_ID");
        layerColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> totWaterColumn = new TableColumn("TotalWater");
        totWaterColumn.setPrefWidth(100.0);
        TableColumn<propertyMix,String> totWaterUnitsColumn = new TableColumn("TotalWater_Unit");
        totWaterUnitsColumn.setPrefWidth(50.0);
        TableColumn<propertyMix,Double> renewableMaterialResourceColumn = new TableColumn("RenewableMaterialResource");
        renewableMaterialResourceColumn.setPrefWidth(150.0);
        TableColumn<propertyMix,String> renewableMaterialResourceUnitsColumn = new TableColumn("RenewableMaterialResource_Unit");
        renewableMaterialResourceUnitsColumn.setPrefWidth(50.0);
        TableColumn<propertyMix,Double> nonrenewableMaterialResourceColumn = new TableColumn("NonrenewableMaterialResource");
        nonrenewableMaterialResourceColumn.setPrefWidth(150.0);
        TableColumn<propertyMix,String> nonrenewableMaterialResourceUnitsColumn = new TableColumn("NonrenewableMaterialResource_Unit");
        nonrenewableMaterialResourceUnitsColumn.setPrefWidth(50.0);


        designColumn.setCellValueFactory(new PropertyValueFactory<>("Design_ID"));
        layerColumn.setCellValueFactory(new PropertyValueFactory<>("Layer_ID"));
        totWaterColumn.setCellValueFactory(new PropertyValueFactory<>("TotalWaterConsumption"));
        totWaterUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("TotalWaterConsumption_Units"));
        renewableMaterialResourceColumn.setCellValueFactory(new PropertyValueFactory<>("RenewableMaterialResourcesUse"));
        renewableMaterialResourceUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("RenewableMaterialResourcesUse_Units"));
        nonrenewableMaterialResourceColumn.setCellValueFactory(new PropertyValueFactory<>("NonRenewableMaterialResource"));
        nonrenewableMaterialResourceUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("NonRenewableMaterialResource_Unit"));
        resourceConsumptionTable.getColumns().addAll(
                designColumn,layerColumn,totWaterColumn,totWaterUnitsColumn,
                renewableMaterialResourceColumn,renewableMaterialResourceUnitsColumn,
                nonrenewableMaterialResourceColumn,nonrenewableMaterialResourceUnitsColumn);
        resourceConsumptionTable.setItems(data);
    }

    public List<Double> getEnergyConsumptionResults(Mix mix){
        List<Double> results = new ArrayList<>();
        //obtain key for CalcResult
        List<String> impactList = new ArrayList<>();
        if (impactCategory == "GWP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("GWP");
        if (impactCategory == "ODP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("ODP");
        if (impactCategory == "AP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("AP");
        if (impactCategory == "EP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("EP");
        if (impactCategory == "POCP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("POCP");
        if (impactCategory == "PrimaryEnergyConsumption"||impactCategory == "All Energy Consumption Impact")
            impactList.add("TPEC");
        for (String s1:impactList){
            String s2=getKey2(envImpactType);
            String s3=getKey3(valueType);
            String completed_Key = new StringBuilder().
                    append(s1).append("_").
                    append(s2).append("_").
                    append(s3).toString();      //format should be like GWP_EPD_Ctb
            Double result = mix.CalcResult.get(completed_Key);
            //Double decimal formatting
            NumberFormat numberFormat = new DecimalFormat("#0.00E00");
            Double formattedResult = Double.valueOf(numberFormat.format(result));
            results.add(formattedResult);
        }
        return results;
    }
    public List<String> getEPDUnits(Mix mix){
        List<String> unit_Results = new ArrayList<>();
        List<String> impactList = new ArrayList<>();
        if (impactCategory == "GWP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("GWP");
        if (impactCategory == "ODP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("ODP");
        if (impactCategory == "AP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("AP");
        if (impactCategory == "EP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("EP");
        if (impactCategory == "POCP"||impactCategory == "All Energy Consumption Impact")
            impactList.add("POCP");
        if (impactCategory == "PrimaryEnergyConsumption"||impactCategory == "All Energy Consumption Impact")
            impactList.add("TPEC");
        if (impactCategory == "All Resource Consumption Impact") {
            impactList.add("TWC");
            impactList.add("RMRU");
            impactList.add("NRMR");
        }
        for (String s1:impactList){
            String completed_Key = new StringBuilder().
                    append(s1).append("_Units").toString();
            String result = mix.EPDUnits.get(completed_Key);
            unit_Results.add(result);
        }
        return unit_Results;
    }

    private String getKey2(String envImpactType){
        String s2=null;
        if (envImpactType.equals("EPD")){s2 = "EPD";}
        else if (envImpactType.equals("TSP")){s2 ="TSP";}
        else if (envImpactType.equals("Overall")){
            System.out.println("Environmental Impact Type is selected as OVERALL");
        }
        else System.out.println("Can not identify an envImpact type");
        return s2;
    }
    private String getKey3(String valueType){
        String s3=null;
        if(valueType.equals("Raw impact per functional unit")){s3 ="Ctb";}
        else if(valueType.equals("Normalized impact per functional unit")){s3 ="NORM";}
        else if(valueType.equals("Weighted impact per functional unit")){s3 ="SubScore";}
        else System.out.println("Can not identify an value type");
        return s3;
    }

    private void setupPerformanceType_ComboBox(){
        performanceType_ComboBox.setItems(performanceType);
        performanceType_ComboBox.setValue(performanceType.get(0));
    }

    private void setupEnvironmentalImpact_ComboBox(){
        environmentalImpact_ComboBox.setItems(impactType);
        environmentalImpact_ComboBox.setValue(impactType.get(0));
    }

    private void setupRawValue_ComboBox(){
        rawValue_ComboBox.setItems(rawValueType);
        rawValue_ComboBox.setValue(rawValueType.get(0));
    }

    public void setupImpactCategory_ComboBox(){
        valueType = rawValue_ComboBox.getSelectionModel().getSelectedItem().toString();
        // Impact Category
        ObservableList<String> impactCategoryName =FXCollections
                .observableArrayList(
                        "All Energy Consumption Impact",
                        "GWP","ODP","AP","EP", "POCP",
                        "PrimaryEnergyConsumption"
                );
        if(valueType == "Raw impact per functional unit"){
            impactCategoryName.add(0,"All Resource Consumption Impact");
            impactCategory_ComboBox.setItems(impactCategoryName);
        }else{
            impactCategory_ComboBox.setItems(impactCategoryName);
        }
    }

    private void setupDesignNumber_ComboBox(){
        for (String dKey:DESIGNS.keySet()){
            designNum.add(dKey);
        }
        if (DESIGNS.size()>0){
            designNum.addAll("All designs");
        }
        design_ComboBox.setItems(designNum);
        design_ComboBox.setValue(designNum.get(0));
    }

    public void setupLayerNumber_ComboBox(){
        // clean items in the layerNumber_ComboBox
        layerNum.clear();
        if (!design_ComboBox.getSelectionModel().isEmpty()){
            String selectedDeisgnKey = design_ComboBox.getValue().toString();
            int layerNumber = DESIGNS.get(selectedDeisgnKey).getNumberOfLayers();
            if(layerNumber>0){
                for (int i = 1; i<= layerNumber;i++){
                    StringBuilder sb = new StringBuilder("Layer ");
                    String layerName= sb.append(i).toString();
                    layerNum.add(layerName);
                }
                layerNum.addAll("All layers");
                layer_ComboBox.setItems(layerNum);
            }
            else {
                System.out.println("No layer was added for this design!!");
            }
        }
        else{
            System.out.println("Select a certain design first!!");
        }

    }

    public class propertyMix{
        private String Design_ID;
        private String Layer_ID;
        private Double GWP;
        private Double ODP;
        private Double AP;
        private Double EP;
        private Double POCP;
        private Double ConcreteHazardousWaste;
        private Double ConcreteNonHazardousWaste;
        private Double TotalWaterConsumption;
        private Double BatchingWaterConsumption;
        private Double WashingWaterConsumption;
        private Double TotalPrimaryEnergyConsumption;
        private Double RenewablePrimaryEnergyUse;
        private Double NonRenewableEnergyUse;
        private Double RenewableMaterialResourcesUse;
        private Double NonRenewableMaterialResource;
        private String Product_ID;
        private Double TotalEnergyConsumptionEmission;
        private String GWP_Units;
        private String ODP_Units;
        private String AP_Units;
        private String EP_Units;
        private String POCP_Units;
        private String TotalPrimaryEnergyConsumption_Units;
        private String TotalWaterConsumption_Units;
        private String RenewableMaterialResourcesUse_Units;
        private String NonRenewableMaterialResource_Unit;

        public Double getGWP() {
            return GWP;
        }

        public void setGWP(Double GWP) {
            this.GWP = GWP;
        }

        public Double getODP() {
            return ODP;
        }

        public void setODP(Double ODP) {
            this.ODP = ODP;
        }

        public Double getAP() {
            return AP;
        }

        public void setAP(Double AP) {
            this.AP = AP;
        }

        public Double getEP() {
            return EP;
        }

        public void setEP(Double EP) {
            this.EP = EP;
        }

        public Double getPOCP() {
            return POCP;
        }

        public void setPOCP(Double POCP) {
            this.POCP = POCP;
        }

        public Double getTotalEnergyConsumptionEmission() {
            return TotalEnergyConsumptionEmission;
        }

        public void setTotalEnergyConsumptionEmission() {
            TotalEnergyConsumptionEmission = getGWP() + getODP() + getAP() + getEP() + getPOCP();
        }

        public Double getConcreteHazardousWaste() {
            return ConcreteHazardousWaste;
        }

        public void setConcreteHazardousWaste(Double concreteHazardousWaste) {
            ConcreteHazardousWaste = concreteHazardousWaste;
        }

        public Double getConcreteNonHazardousWaste() {
            return ConcreteNonHazardousWaste;
        }

        public void setConcreteNonHazardousWaste(Double concreteNonHazardousWaste) {
            ConcreteNonHazardousWaste = concreteNonHazardousWaste;
        }

        public Double getTotalWaterConsumption() {
            return TotalWaterConsumption;
        }

        public void setTotalWaterConsumption(Double totalWaterConsumption) {
            TotalWaterConsumption = totalWaterConsumption;
        }

        public Double getBatchingWaterConsumption() {
            return BatchingWaterConsumption;
        }

        public void setBatchingWaterConsumption(Double batchingWaterConsumption) {
            BatchingWaterConsumption = batchingWaterConsumption;
        }

        public Double getWashingWaterConsumption() {
            return WashingWaterConsumption;
        }

        public void setWashingWaterConsumption(Double washingWaterConsumption) {
            WashingWaterConsumption = washingWaterConsumption;
        }

        public Double getTotalPrimaryEnergyConsumption() {
            return TotalPrimaryEnergyConsumption;
        }

        public void setTotalPrimaryEnergyConsumption(Double totalPrimaryEnergyConsumption) {
            TotalPrimaryEnergyConsumption = totalPrimaryEnergyConsumption;
        }

        public Double getRenewablePrimaryEnergyUse() {
            return RenewablePrimaryEnergyUse;
        }

        public void setRenewablePrimaryEnergyUse(Double renewablePrimaryEnergyUse) {
            RenewablePrimaryEnergyUse = renewablePrimaryEnergyUse;
        }

        public Double getNonRenewableEnergyUse() {
            return NonRenewableEnergyUse;
        }

        public void setNonRenewableEnergyUse(Double nonRenewableEnergyUse) {
            NonRenewableEnergyUse = nonRenewableEnergyUse;
        }

        public Double getRenewableMaterialResourcesUse() {
            return RenewableMaterialResourcesUse;
        }

        public void setRenewableMaterialResourcesUse(Double renewableMaterialResourcesUse) {
            RenewableMaterialResourcesUse = renewableMaterialResourcesUse;
        }

        public Double getNonRenewableMaterialResource() {
            return NonRenewableMaterialResource;
        }

        public void setNonRenewableMaterialResource(Double nonRenewableMaterialResource) {
            NonRenewableMaterialResource = nonRenewableMaterialResource;
        }

        public String getProduct_ID() {
            return Product_ID;
        }

        public void setProduct_ID(String product_ID) {
            this.Product_ID = product_ID;
        }

        public String getDesign_ID() {
            return Design_ID;
        }

        public void setDesign_ID(String design_ID) {
            Design_ID = design_ID;
        }

        public String getLayer_ID() {
            return Layer_ID;
        }

        public void setLayer_ID(String layer_ID) {
            Layer_ID = layer_ID;
        }

        public String getGWP_Units() {
            return GWP_Units;
        }

        public void setGWP_Units(String GWP_Units) {
            this.GWP_Units = GWP_Units;
        }

        public String getODP_Units() {
            return ODP_Units;
        }

        public void setODP_Units(String ODP_Units) {
            this.ODP_Units = ODP_Units;
        }

        public String getAP_Units() {
            return AP_Units;
        }

        public void setAP_Units(String AP_Units) {
            this.AP_Units = AP_Units;
        }

        public String getEP_Units() {
            return EP_Units;
        }

        public void setEP_Units(String EP_Units) {
            this.EP_Units = EP_Units;
        }

        public String getPOCP_Units() {
            return POCP_Units;
        }

        public void setPOCP_Units(String POCP_Units) {
            this.POCP_Units = POCP_Units;
        }

        public String getTotalPrimaryEnergyConsumption_Units() {
            return TotalPrimaryEnergyConsumption_Units;
        }

        public void setTotalPrimaryEnergyConsumption_Units(String totalPrimaryEnergyConsumption_Units) {
            TotalPrimaryEnergyConsumption_Units = totalPrimaryEnergyConsumption_Units;
        }

        public String getTotalWaterConsumption_Units() {
            return TotalWaterConsumption_Units;
        }

        public void setTotalWaterConsumption_Units(String totalWaterConsumption_Units) {
            TotalWaterConsumption_Units = totalWaterConsumption_Units;
        }

        public String getRenewableMaterialResourcesUse_Units() {
            return RenewableMaterialResourcesUse_Units;
        }

        public void setRenewableMaterialResourcesUse_Units(String renewableMaterialResourcesUse_Units) {
            RenewableMaterialResourcesUse_Units = renewableMaterialResourcesUse_Units;
        }

        public String getNonRenewableMaterialResource_Unit() {
            return NonRenewableMaterialResource_Unit;
        }

        public void setNonRenewableMaterialResource_Unit(String nonRenewableMaterialResource_Unit) {
            NonRenewableMaterialResource_Unit = nonRenewableMaterialResource_Unit;
        }
    }
}
