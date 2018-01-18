package reversiapp;


import gameOperation.Board;
import gameOperation.Cell;
import gameOperation.JavaLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dan on 1/13/18.
 */
public class GameWindowController extends BorderPane {

    private BoardController gameBoard;
    private SidePanelController sidePanel;
    private JavaLogic javaLogic;

    public GameWindowController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        gameBoard = new BoardController(new File("settings.txt"));
        sidePanel = new SidePanelController();
        gameBoard.setPrefWidth(600);
        gameBoard.setPrefHeight(600);
        this.setRight(sidePanel); //Sets the VBox to the right
        this.setCenter(gameBoard); //Sets the gridPane to the center


        //Handling resize
        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            gameBoard.setPrefWidth(boardNewWidth);
            gameBoard.draw();
        });

        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            gameBoard.setPrefHeight(newValue.doubleValue());
            gameBoard.draw();
        });

        //Handling resize
        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            sidePanel.setPrefWidth(boardNewWidth);
        });

        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            sidePanel.setPrefHeight(newValue.doubleValue());
        });

    }

    public void setLogic(JavaLogic logic) {
        gameBoard.setLogic(logic);
        javaLogic = logic;
    }


    public void showMoves(Board board, ArrayList<Cell> availablePositions) {
        gameBoard.displayMoves(board, availablePositions);
        setPlayerOneScore(javaLogic.getPlayerOneScore());
        setPlayerTwoScore(javaLogic.getPlayerTwoScore());
    }

    public void showMessage(String msg) {

    }

    public void setText(String text) {
        sidePanel.setMessage(text);
    }

    public void setPlayerOneScore(int score) {
        sidePanel.setPlayerOneScore(score);
    }

    public void setPlayerTwoScore(int score) {
        sidePanel.setPlayerTwoScore(score);
    }
}