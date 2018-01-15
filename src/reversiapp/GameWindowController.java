package reversiapp;

import gameOperation.Board;
import gameOperation.Cell;
import gameOperation.ConsoleGraphics;
import gameOperation.StandardLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by dan on 1/13/18.
 */
public class GameWindowController implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private BorderPane gamePane;

    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        BoardLayout board = new BoardLayout();
        board.setPrefWidth(600);
        board.setPrefHeight(600);
        StandardLogic sl = new StandardLogic(new ConsoleGraphics());
        sl.setBoard();
        board.displayBoard(sl.getBoard());
        ArrayList<Cell> dummy = new ArrayList<Cell>();
        dummy.add(new Cell(5, 5, ' '));
        dummy.add(new Cell(5, 6, ' '));
        board.displayMoves(dummy, sl.getBoard());

        root.getChildren().add(board);
    }

}
