package reversiapp;

import gameOperation.Board;
import gameOperation.Cell;
import gameOperation.GameLogicInterface;
import gameOperation.JavaLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dan on 1/13/18.
 */
public class BoardController extends GridPane {

    //Members
    private Board gameBoard;
    private Image firstPlayerToken;
    private Image secondPlayerToken;
    private int rowsAndColumnSize;
    private JavaLogic gameLogic;
    private Image tile1, tile2;
    private ArrayList<Cell> availablePlaysList;

    public BoardController(File settingsFile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardLayout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            getSettingsFromFile(settingsFile);
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

    public void draw() {
        displayMoves(this.gameBoard, availablePlaysList);
    }

    public void setLogic(JavaLogic logic) {
        gameLogic = logic;
    }

    public void displayMoves(Board currentBoard, ArrayList<Cell> availablePositions) {
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

    private Image getCurrentPlayerToken() {
        if(gameLogic.getCurrentPlayerId() == 'X') {
            return firstPlayerToken;
        }
        return secondPlayerToken;
    }

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

    private void showAvailability(int row, int col, StackPane stackPane) {
        for(Cell c : availablePlaysList) {
            if(c.getXCord() == row && c.getYCord() == col) {
                stackPane.getChildren().add(getPositionButton(row, col));
            }
        }
    }

    private void getSettingsFromFile(File fileName) throws IOException {
        ArrayList<String> stringBuffer = new ArrayList<String>();
        try {
            if (fileName.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.add(line);
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (stringBuffer.isEmpty()) { //default values
            rowsAndColumnSize = 8;
            firstPlayerToken =  new Image(getClass().getResource("images/blackPiece.png").toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource("images/whitePiece.png").toExternalForm());
            return;
        }
        //From the settings file
        rowsAndColumnSize = Integer.parseInt(stringBuffer.get(0));
        int playerOrder = Integer.parseInt(stringBuffer.get(1));
        if (playerOrder == 1) { //Regular order
            firstPlayerToken =  new Image(getClass().getResource(stringBuffer.get(2)).toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource(stringBuffer.get(3)).toExternalForm());
        } else { //Inverse order
            firstPlayerToken =  new Image(getClass().getResource(stringBuffer.get(3)).toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource(stringBuffer.get(2)).toExternalForm());
        }
    }
}
