package reversiapp;

import gameOperation.Game;
import gameOperation.JavaGraphics;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by dan on 1/13/18.
 */
public class TestRunner extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //Game Scene
            HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            HBox menu = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));

            Scene gameScene = new Scene(root,800,800);
            gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            //Main menu Scene
            HBox mainMenu = new HBox();
            Scene menuScene = new Scene(mainMenu,800,800);
            Button butQuit = new Button("Quit Game");
            Button butPlay = new Button ("Play Game");

            //Main Menu Buttons
            butPlay.setOnAction(e-> {primaryStage.setScene(gameScene);
            });
            butQuit.setOnAction(e->primaryStage.close());

            primaryStage.setOnCloseRequest(e->primaryStage.close()); //Change if we want to handle exit

            mainMenu.getChildren().addAll(butPlay,butQuit);

            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(menuScene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
