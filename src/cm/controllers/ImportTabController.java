package cm.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;


/**
 * Created by royg59 on 9/21/16.
 */
public class ImportTabController {
    ObservableList<String> DesignType = FXCollections.observableArrayList("New pavement","Overlay");       //Design type of pavement
    ObservableList<String> PavementType_newPavement = FXCollections.observableArrayList("Flexible pavement","Joint Reinforced concrete pavement"); //Pavement type
    ObservableList<String> PavementType_overlay = FXCollections.observableArrayList("AC over AC","AC over JRCP"); //Pavement type

    ObservableList<String> layerNum = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","others");       //number of layers in a design
    ObservableList<String> designNum = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","ohters");       //number of design

    @FXML
    private ComboBox Designtype;
    @FXML
    private ComboBox Pavementtype;
    @FXML
    private ComboBox LayerNum;
    @FXML
    private ComboBox DesignNum;


    public void initialize(){

        Designtype.setItems(DesignType);
        LayerNum.setItems(layerNum);
        DesignNum.setItems(designNum);
       // designNum.setItems(designNum);

    }
    public void SelectDesignType(){
        if (Designtype.getSelectionModel().isSelected(0))
            Pavementtype.setItems(PavementType_newPavement);
        if (Designtype.getSelectionModel().isSelected(1))
            Pavementtype.setItems(PavementType_overlay);
    }
    public void nextButton(){
//        Controller controller = new Controller();
//        controller.importTab_next();
    }
}
