package reversiapp;

import gameOperation.Board;
import gameOperation.ConsoleGraphics;
import gameOperation.StandardLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import reversiapp.BoardController;

import java.io.File;

public class Run extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BoardController root = new BoardController(new File("settings.txt"));

            StandardLogic logic = new StandardLogic(new ConsoleGraphics());
            Board b = logic.setBoard();
//            root.displayBoard(b);
            Scene scene = new Scene(root, 600, 600);
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
