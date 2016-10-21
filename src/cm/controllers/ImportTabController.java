package cm.controllers;

import cm.models.Design;
import cm.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import static cm.App.designMap;


/**
 * Created by royg59 on 9/21/16.
 */
public class ImportTabController {




    ObservableList<String> DesignType = FXCollections.observableArrayList("New pavement","Overlay");       //Design type of pavement
    ObservableList<String> PavementType_newPavement = FXCollections.observableArrayList("Flexible pavement","Joint Reinforced concrete pavement"); //Pavement type
    ObservableList<String> PavementType_overlay = FXCollections.observableArrayList("AC over AC","AC over JRCP"); //Pavement type

    ObservableList<String> layerNum = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","others");       //number of layers in a design
    ObservableList<String> designNum = FXCollections.observableArrayList("1","2","3");       //number of design

    @FXML
    private ComboBox Designtype1;
    @FXML
    private ComboBox Pavementtype1;
    @FXML
    private ComboBox LayerNum1;
    @FXML
    private ComboBox Designtype2;
    @FXML
    private ComboBox Pavementtype2;
    @FXML
    private ComboBox LayerNum2;
    @FXML
    private ComboBox Designtype3;
    @FXML
    private ComboBox Pavementtype3;
    @FXML
    private ComboBox LayerNum3;

    @FXML
    private ComboBox DesignNum;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox vBox2;
    @FXML
    private VBox vBox3;


    private ComboBox[] Designtype = new ComboBox[10];

    private VBox[] VBox = new VBox[10];

    private ComboBox[] PavementType = new ComboBox[10];

    private ComboBox[] LayerNum = new ComboBox[10];



    private void initializeNew(){

        VBox[0] = vBox1;
        VBox[1] = vBox2;
        VBox[2] = vBox3;
        Designtype[0] = Designtype1;
        Designtype[1] = Designtype2;
        Designtype[0] = Designtype3;
        LayerNum[0] = LayerNum1;
        LayerNum[1] = LayerNum2;
        LayerNum[2] = LayerNum3;
        PavementType[0] = Pavementtype1;
        PavementType[1] = Pavementtype2;
        PavementType[2] = Pavementtype3;


    }


    public void initialize(){
        DesignNum.setItems(designNum);

        VBox[0] = vBox1;
        VBox[1] = vBox2;
        VBox[2] = vBox3;
        Designtype[0] = Designtype1;
        Designtype[1] = Designtype2;
        Designtype[2] = Designtype3;
        LayerNum[0] = LayerNum1;
        LayerNum[1] = LayerNum2;
        LayerNum[2] = LayerNum3;
        PavementType[0] = Pavementtype1;
        PavementType[1] = Pavementtype2;
        PavementType[2] = Pavementtype3;

        for (int i=0;i<3;i++){
            //hidden all the design choices
            VBox[i].setVisible(false);
            Designtype[i].setItems(DesignType);
            LayerNum[i].setItems(layerNum);
        }
//        // set up all the comboBox items
//        DesignNum.setItems(designNum);
//        //Design 1
//        Designtype1.setItems(DesignType);
//        LayerNum1.setItems(layerNum);
//        //Design 2
//        Designtype2.setItems(DesignType);
//        LayerNum2.setItems(layerNum);
//        //Design 3
//        Designtype3.setItems(DesignType);
//        LayerNum3.setItems(layerNum);

       // designNum.setItems(designNum);

        //set up special number of design to be visible

        if (DesignNum.getSelectionModel().isSelected(0)){
            for (int i=0;i<Integer.parseInt(DesignNum.getValue().toString());i++){
                VBox[i].setVisible(true);
            }
            for (int i = Integer.parseInt(DesignNum.getValue().toString()); i<3;i++){
                VBox[i].setVisible(false);
            }

        }
        if (DesignNum.getSelectionModel().isSelected(1)){
            for (int i=0;i<Integer.parseInt(DesignNum.getValue().toString());i++){
                VBox[i].setVisible(true);
            }
            for (int i = Integer.parseInt(DesignNum.getValue().toString()); i<3;i++){
                VBox[i].setVisible(false);
            }
        }
        if (DesignNum.getSelectionModel().isSelected(2)){
            for (int i=0;i<Integer.parseInt(DesignNum.getValue().toString());i++){
                VBox[i].setVisible(true);
            }
            for (int i = Integer.parseInt(DesignNum.getValue().toString()); i<3;i++){
                VBox[i].setVisible(false);
            }

            // instantiate 3 design objects
            Design design1 = new Design();
            Design design2 = new Design();
            Design design3 = new Design();

            // shove them into the hashmap
            Model.designs.put("Design1", design1);
            Model.designs.put("Design2", design2);
            Model.designs.put("Design3", design3);
        }

    }
    public void SelectDesignType(){

        for (int i=0;i<3;i++) {
            if (Designtype[i].getSelectionModel().isSelected(0))
            PavementType[i].setItems(PavementType_newPavement);
            if (Designtype[i].getSelectionModel().isSelected(1))
            PavementType[i].setItems(PavementType_overlay);
        }
    }
    public void nextButton(){

        LayerIn


        Design design = new Design();
        for (int i =1;i<= Integer.parseInt(DesignNum.getValue().toString());i++){
            StringBuilder ID = new StringBuilder("Design");
            String designID =ID.append(Integer.toString(i)).toString();
            design.setDesign_ID(designID);

            design.setDesign_Type(Designtype[i-1].getValue().toString());
            design.setPavement_Type(PavementType[i-1].getValue().toString());
            design.setNumberOfLayers(Integer.parseInt(LayerNum[i-1].getValue().toString()));


            designMap.put(design.getDesign_ID(),design);

            Model.designs.get("design1").setDesign_Type(
                  Designtype[0].getValue().toString()
            );
            Model.designs.get("design1").setPavement_Type(
                  PavementType[0].getValue().toString()
            );
            Model.designs.get("design1").setNumberOfLayers(
                  Integer.parseInt(LayerNum[0].getValue().toString())
            );

            DesignT



        }
        System.out.println("All the basic design information was saved!!");
    }
}
