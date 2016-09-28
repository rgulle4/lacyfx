package cm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/view.fxml"));
        primaryStage.setTitle("lacyfx WIP");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    private static Stage Stage;
    public static void showfile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load designed file");
        File file = fileChooser.showOpenDialog(Stage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
