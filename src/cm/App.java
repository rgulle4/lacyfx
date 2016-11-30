package cm;

import cm.controllers.LoadMixController;
import cm.controllers.LoadMixController_newVersion;
import cm.models.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public final class App extends Application {
    private static Stage primaryStage;
    private ObservableList<Mix> mixData = FXCollections.observableArrayList();



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/view.fxml"));
        primaryStage.setTitle("lacyfx WIP");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("views/css/application.css")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showLoadMaterial(Layer currentLayer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource("views/loadMix_window_newVersion.fxml"));
        AnchorPane addNewMaterialAcnhorPane = fxmlLoader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Add New Mix");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);

        /* -- pass currentLayer --------- */
        LoadMixController_newVersion loadMixController_newVersion
              = fxmlLoader.<LoadMixController_newVersion>getController();

        loadMixController_newVersion.setCurrentLayer(currentLayer);
        /* ------------------------------ */

        Scene scene = new Scene(addNewMaterialAcnhorPane);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
    }

    public static void showEnvironmentalScoreReport() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("views/EnvironmentalScoreReport.fxml"));
        AnchorPane showReport = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Environmental Performance");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);

        Scene scene = new Scene(showReport);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
    }

    public static void showEnvironmentalDataTable() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("views/EnvironmentalDataTable.fxml"));
        AnchorPane showTable = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Environmental Performance");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);

        Scene scene = new Scene(showTable);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // TODO: replace layerMap with new Design.layers list

    // TODO: think about IA for the materialMap, which is for use case where user wants to evaluate what happens one design when just one of its layers is changed.

    // TODO: start implementing the calculations for 1 design, 1 layer, 1 material.

    //create a static hashtable to store values for TransportationParameters step by step
    public static Map<String,Design> designMap = new Hashtable<String,Design>();
    public static Map<String,Layer> layerMap = new Hashtable<String,Layer>();
    public static Map<String,Mix> materialMap = new Hashtable<String,Mix>();
    public static Map<String,Weights> weightsMap = new Hashtable<String,Weights>();
    public static Map<String,TransportationParameters> transportationParametersMap = new Hashtable<String,TransportationParameters>();
//    public static Map<String,>
}
