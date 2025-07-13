package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args); // Starts the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Code Snippet Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
