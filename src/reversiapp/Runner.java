package reversiapp;

import gameOperation.ConsoleGraphics;
import gameOperation.Game;
import gameOperation.JavaGraphics;
import gameOperation.StandardLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;


public class Runner extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
