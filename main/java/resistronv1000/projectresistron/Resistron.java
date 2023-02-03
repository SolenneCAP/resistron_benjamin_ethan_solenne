package resistronv1000.projectresistron;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Resistron extends Application {


    private static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Resistron.class.getResource("SceneBuilderResistron.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600 , 420);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Resistron");
        primaryStage.show();
    }
}
