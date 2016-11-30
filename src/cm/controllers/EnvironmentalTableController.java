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
    private ComboBox mix_ComboBox;
    @FXML
    private ComboBox impactCategory_ComboBox;
    @FXML
    private TableView<propertyMix> resourceConsumptionTable;
    @FXML
    private Label energyConsumptionLabel;
    @FXML
    private Label resourceConsumptionLabel;
            
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
        int mixIndex = mix_ComboBox.getSelectionModel().getSelectedIndex();
        //Get the whole selected Mix lists
        List<Mix> mixsTemp = new ArrayList<Mix>();
        String selectedMixItem = mix_ComboBox.getValue().toString();
        if(selectedMixItem == "All Mixes"){
            mixsTemp = layerTemp.getMixes();
        }else{
            Mix selectedMix = layerTemp.getMaterial(mixIndex);
            mixsTemp.add(selectedMix);
        }
        perfType = performanceType_ComboBox.getSelectionModel().getSelectedItem().toString();
        envImpactType = environmentalImpact_ComboBox.getSelectionModel().getSelectedItem().toString();
        valueType = rawValue_ComboBox.getSelectionModel().getSelectedItem().toString();
        impactCategory = impactCategory_ComboBox.getSelectionModel().getSelectedItem().toString();
        showData(mixsTemp,designID,layerID);
    }
    public void showData(List<Mix> mixes, String design_ID, String layer_ID){
        if(impactCategory != "All Resource Consumption Impact"){
            showEnergyConsumptionData(mixes,design_ID,layer_ID);
        }else {
            showResourceConsumptionData(mixes,design_ID,layer_ID);
        }
    }
    public void showEnergyConsumptionData(List<Mix> mixes, String design_ID, String layer_ID){
        energyConsumptionTable.getColumns().clear();
        energyConsumptionTable.layout();
        energyConsumptionTable.setVisible(true);
        energyConsumptionLabel.setVisible(true);
        resourceConsumptionTable.getColumns().clear();
        resourceConsumptionTable.layout();
        resourceConsumptionTable.setVisible(false);
        resourceConsumptionLabel.setVisible(false);
        //Obtain data from mixes
        ObservableList<propertyMix> data = FXCollections.observableArrayList();
        for(Mix amix:mixes){
            propertyMix pmix = new propertyMix();
            pmix.setDesign_ID(design_ID);
            pmix.setLayer_ID(layer_ID);
            pmix.setProduct_ID(amix.getProduct_ID()+amix.getZipCode());
            if(impactCategory == "GWP") {
                pmix.setGWP(getSingleDataValue(amix));
            }
            if(impactCategory == "ODP") {
                pmix.setODP(getSingleDataValue(amix));
            }
            if(impactCategory == "AP") {
                pmix.setAP(getSingleDataValue(amix));
            }
            if(impactCategory == "EP") {
                pmix.setEP(getSingleDataValue(amix));
            }
            if(impactCategory == "POCP") {
                pmix.setPOCP(getSingleDataValue(amix));
            }
            if(impactCategory == "PrimaryEnergyConsumption") {
                pmix.setTotalPrimaryEnergyConsumption(getSingleDataValue(amix));
            }
            if(impactCategory == "Impact Analysis Comparison of All Alternatives"){}
            if(impactCategory == "All Energy Consumption Impact"){
                List<Double> result = getDataValueArray(amix);
                pmix.setGWP(result.get(0));
                pmix.setODP(result.get(1));
                pmix.setAP(result.get(2));
                pmix.setEP(result.get(3));
                pmix.setPOCP(result.get(4));
                pmix.setTotalPrimaryEnergyConsumption(result.get(5));
            }
            data.add(pmix);
        }
        TableColumn<propertyMix,String> designColumn = new TableColumn("Design_ID");
        designColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> layerColumn = new TableColumn("Layer_ID");
        layerColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,String> productIDColumn = new TableColumn("Product_ID");
        productIDColumn.setPrefWidth(125.0);
        TableColumn<propertyMix,Double> gwpColumn = new TableColumn("GWP");
        gwpColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> odpColumn = new TableColumn("ODP");
        odpColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> apColumn = new TableColumn("AP");
        apColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> epColumn = new TableColumn("EP");
        epColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> pocpColumn = new TableColumn("POCP");
        pocpColumn.setPrefWidth(75.0);
        TableColumn<propertyMix,Double> tpecColumn = new TableColumn("EnergyConsumption");
        tpecColumn.setPrefWidth(125.0);

        if(impactCategory == "All Energy Consumption Impact"){
            energyConsumptionTable.getColumns().add(designColumn);
            designColumn.setCellValueFactory(new PropertyValueFactory<>("Design_ID"));
            energyConsumptionTable.getColumns().add(layerColumn);
            layerColumn.setCellValueFactory(new PropertyValueFactory<>("Layer_ID"));
            energyConsumptionTable.getColumns().add(productIDColumn);
            productIDColumn.setCellValueFactory(new PropertyValueFactory<>("Product_ID"));
            energyConsumptionTable.getColumns().add(gwpColumn);
            gwpColumn.setCellValueFactory(new PropertyValueFactory<>("GWP"));
            energyConsumptionTable.getColumns().add(odpColumn);
            odpColumn.setCellValueFactory(new PropertyValueFactory<>("ODP"));
            energyConsumptionTable.getColumns().add(apColumn);
            apColumn.setCellValueFactory(new PropertyValueFactory<>("AP"));
            energyConsumptionTable.getColumns().add(epColumn);
            epColumn.setCellValueFactory(new PropertyValueFactory<>("EP"));
            energyConsumptionTable.getColumns().add(pocpColumn);
            pocpColumn.setCellValueFactory(new PropertyValueFactory<>("POCP"));
            energyConsumptionTable.getColumns().add(tpecColumn);
            tpecColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrimaryEnergyConsumption"));
//            energyConsumptionTable.getColumns().add(totEneryConsumptionEmissionColumn);
//            pocpColumn.setCellValueFactory(new PropertyValueFactory<>("TotalEnergyConsumptionEmission"));
        }else{
            energyConsumptionTable.getColumns().add(designColumn);
            designColumn.setCellValueFactory(new PropertyValueFactory<>("Design_ID"));
            energyConsumptionTable.getColumns().add(layerColumn);
            layerColumn.setCellValueFactory(new PropertyValueFactory<>("Layer_ID"));
            energyConsumptionTable.getColumns().add(productIDColumn);
            productIDColumn.setCellValueFactory(new PropertyValueFactory<>("Product_ID"));
            if(impactCategory == "GWP"){
                energyConsumptionTable.getColumns().add(gwpColumn);
                gwpColumn.setCellValueFactory(new PropertyValueFactory<>("GWP"));
            }
            if(impactCategory == "ODP"){
                energyConsumptionTable.getColumns().add(odpColumn);
                odpColumn.setCellValueFactory(new PropertyValueFactory<>("ODP"));
            }
            if(impactCategory == "AP"){
                energyConsumptionTable.getColumns().add(apColumn);
                apColumn.setCellValueFactory(new PropertyValueFactory<>("AP"));
            }
            if(impactCategory == "EP"){
                energyConsumptionTable.getColumns().add(epColumn);
                epColumn.setCellValueFactory(new PropertyValueFactory<>("EP"));
            }
            if(impactCategory == "POCP"){
                energyConsumptionTable.getColumns().add(pocpColumn);
                pocpColumn.setCellValueFactory(new PropertyValueFactory<>("POCP"));
            }
            if(impactCategory == "PrimaryEnergyConsumption"){
                energyConsumptionTable.getColumns().add(tpecColumn);
                tpecColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrimaryEnergyConsumption"));
            }
        }
        //Insert value to table
        System.out.println("propertyMix size is: "+data.size());
        energyConsumptionTable.setItems(data);
    }

    public void showResourceConsumptionData(List<Mix> mixes, String design_ID, String layer_ID){
        resourceConsumptionTable.getColumns().clear();
        resourceConsumptionTable.layout();
        resourceConsumptionTable.setVisible(true);
        resourceConsumptionLabel.setVisible(true);
        energyConsumptionTable.getColumns().clear();
        energyConsumptionTable.layout();
        energyConsumptionTable.setVisible(false);
        energyConsumptionLabel.setVisible(false);
    }

    public double getSingleDataValue(Mix mix){
        //obtain key for CalcResult
        String s1=getKey1(impactCategory);
        String s2=getKey2(envImpactType);
        String s3=getKey3(valueType);
        String tot_Key = new StringBuilder().
                append(s1).append("_").
                append(s2).append("_").
                append(s3).toString();      //format should be like GWP_EPD_Ctb
        Double result = mix.CalcResult.get(tot_Key);
        //Double decimal formatting
        NumberFormat numberFormat = new DecimalFormat("#0.000");
        Double formattedResult = Double.valueOf(numberFormat.format(result));
        return formattedResult;
    }
    public List<Double> getDataValueArray(Mix mix){
        List<Double> results = new ArrayList<>();
        //obtain key for CalcResult
        List<String> impactList = new ArrayList<>();
        impactList.add("GWP");
        impactList.add("ODP");
        impactList.add("AP");
        impactList.add("EP");
        impactList.add("POCP");
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
            NumberFormat numberFormat = new DecimalFormat("#0.000");
            Double formattedResult = Double.valueOf(numberFormat.format(result));
            results.add(formattedResult);
        }
        return results;
    }
    private String getKey1(String impactCategory){
        String s1=null;
        if (impactCategory.equals("GWP")){s1 = "GWP";}
        else if (impactCategory.equals("ODP")){s1 = "ODP";}
        else if (impactCategory.equals("AP")){s1 = "AP";}
        else if (impactCategory.equals("EP")){s1 = "EP";}
        else if (impactCategory.equals("POCP")){s1 = "POCP";}
        else if(impactCategory.equals("PrimaryEnergyConsumption")){s1 = "TPEC";}
        else System.out.println("Can not identify an impact category");
        return s1;
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
        if(valueType == "Weighted impact per functional unit"){
            impactCategoryName.add(0,"Impact Analysis Comparison of All Alternatives");
            impactCategory_ComboBox.setItems(impactCategoryName);
        }else if(valueType == "Raw impact per functional unit"){
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

    public void setupMixNum_ComboBox(){
        // clean MixNum_ComBox first
        mixNum.clear();
        if(!design_ComboBox.getSelectionModel().isEmpty()){
            String selectedDeisgnKey = design_ComboBox.getValue().toString();
            if(!layer_ComboBox.getSelectionModel().isEmpty()){
                int layerOfIndex = layer_ComboBox.getSelectionModel().getSelectedIndex();
                int mixNumber = DESIGNS.get(selectedDeisgnKey)
                        .getLayer(layerOfIndex)
                        .getNumberofMaterials();
                if(mixNumber > 0){
                    for (int i = 1; i <= mixNumber;i ++){
                        StringBuilder sb = new StringBuilder("Mix ");
                        String mixName = sb.append(i).toString();
                        mixNum.add(mixName);
                    }
                    mixNum.addAll("All Mixes");
                    mix_ComboBox.setItems(mixNum);
                }else{
                    System.out.println("No mix was added!!");
                }
            }else{
                System.out.println("Select a certain layer first!!");
            }
        }else{
            System.out.println("Select a certain design first!!");
        }
    }
    public void cleanChart(){
        // clear old data
        energyConsumptionTable.getColumns().clear();
        energyConsumptionTable.layout();
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
    }
}
