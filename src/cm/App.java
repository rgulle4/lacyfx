package cm;

import cm.controllers.LoadMaterialController;
import cm.models.AlternativeMat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;
    private ObservableList<AlternativeMat> MaterialData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/view.fxml"));
        primaryStage.setTitle("lacyfx WIP");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void showLoadMaterial() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("views/loadMaterial_window.fxml"));
        AnchorPane addNewMaterial = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Add New Material");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);

        Scene scene = new Scene(addNewMaterial);
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

    public static void main(String[] args) {
        launch(args);
    }
}
