package reversiapp;

import gameOperation.Board;
import gameOperation.Cell;
import gameOperation.JavaLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.io.*;
import java.util.ArrayList;

public class BoardController extends GridPane {

    //Members
    private Board gameBoard;
    private Image firstPlayerToken;
    private Image secondPlayerToken;
    private int rowsAndColumnSize;
    private JavaLogic gameLogic;
    private Image tile1, tile2;
    private ArrayList<Cell> availablePlaysList;

    /**
     *
     * @param token1 the first player token
     * @param token2 the second player token
     * @param size the board size
     */
    public BoardController(Image token1, Image token2, int size) {
        firstPlayerToken = token1;
        secondPlayerToken = token2;
        rowsAndColumnSize = size;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardLayout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        try {
            tile1 = new Image(getClass().getResource("images/wood1.png").toExternalForm());
            tile2 = new Image(getClass().getResource("images/wood2.jpg").toExternalForm());
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * called when the board is resized
     */
    public void draw() {
        displayBoard(this.gameBoard, availablePlaysList);
    }

    /**
     * gets the JavaLogic of the game
     * @param logic
     */
    public void setLogic(JavaLogic logic) {
        gameLogic = logic;
    }

    /**
     * builds the board by calling drawTile (base), drawPiece (token), and showAvailability (valid move)
     * @param currentBoard
     * @param availablePositions
     */
    public void displayBoard(Board currentBoard, ArrayList<Cell> availablePositions) {
        //If no Board was provided through displayBoard
        if (currentBoard == null || availablePositions == null) {
            return;
        }
        this.gameBoard = currentBoard;
        availablePlaysList = availablePositions;

        this.getChildren().clear();
        int cellHeight = (int) this.getPrefHeight() / gameBoard.getNumRows();
        int cellWidth = (int) this.getPrefWidth() / gameBoard.getNumCol();

        for (int i = 0; i < gameBoard.getNumRows(); i++) {
            for (int j = 0; j < gameBoard.getNumCol(); j++) {
                StackPane cell = new StackPane();
                cell.setPrefHeight(cellHeight);
                cell.setPrefWidth(cellWidth);
                this.add(cell, j, i);
                drawTile(cellWidth, cellHeight, i, j, cell);
                drawPiece(cellWidth, cellHeight, i, j, cell);
                showAvailability(i, j, cell);
            }
        }
    }

    /**
     * adds appropriate token image to stack pane if the cell is X or O
     * @param cellWidth
     * @param cellHeight
     * @param row
     * @param col
     * @param st
     */
    private void drawPiece(int cellWidth, int cellHeight, int row, int col, StackPane st) {
        ImageView target;
        if (gameBoard.getCellValue(row,col) == 'X') {
            target =  new ImageView(firstPlayerToken);
        } else if (gameBoard.getCellValue(row,col) == 'O') {
            target =  new ImageView(secondPlayerToken);
        } else {
            return;
        }
        target.setFitWidth(cellWidth);
        target.setFitHeight(cellHeight);
        st.getChildren().add(target);
    }

    /**
     * sets the base image in a checkered pattern
     * @param cellWidth
     * @param cellHeight
     * @param row
     * @param col
     * @param st
     */
    private void drawTile(int cellWidth, int cellHeight, int row, int col, StackPane st) {
        ImageView imageView;
        if ((row + col)%2 == 0) {
            imageView = new ImageView(tile1);
        } else {
            imageView = new ImageView(tile2);
        }
        imageView.setFitHeight(cellHeight); imageView.setFitWidth(cellWidth);
        st.getChildren().add(imageView);
    }

    /**
     *
     * @return the token image of the current player
     */
    private Image getCurrentPlayerToken() {
        if(gameLogic.getCurrentPlayerId() == 'X') {
            return firstPlayerToken;
        }
        return secondPlayerToken;
    }

    /**
     * builds the button for the valid move cells
     * @param row
     * @param col
     * @return
     */
    private Button getPositionButton(int row, int col) {
        double buttonSize = this.getPrefHeight()/this.rowsAndColumnSize;
        Button temp = new Button();
        ImageView imageView = new ImageView(getCurrentPlayerToken());
        imageView.setFitHeight(buttonSize/3);
        imageView.setFitWidth(buttonSize/3);
        temp.setGraphic(imageView);
        temp.setPrefHeight(buttonSize);
        temp.setPrefWidth(buttonSize);
        temp.setOnAction(e-> gameLogic.makeMove(row, col));
        temp.setStyle("-fx-background-color:transparent");
        return temp;
    }

    /**
     * if the cell is in the list of valid positions, call getPositionButton
     * @param row
     * @param col
     * @param stackPane
     */
    private void showAvailability(int row, int col, StackPane stackPane) {
        for(Cell c : availablePlaysList) {
            if(c.getXCord() == row && c.getYCord() == col) {
                stackPane.getChildren().add(getPositionButton(row, col));
            }
        }
    }
}
