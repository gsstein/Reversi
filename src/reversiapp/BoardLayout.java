package reversiapp;

import gameOperation.Cell;
import gameOperation.GameLogicInterface;
import gameOperation.GraphicInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import gameOperation.Board;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dan on 1/13/18.
 */
public class BoardLayout extends GridPane {

    //Members
    private Board gameBoard;
    Image firstPlayerToken;
    Image secondPlayerToken;
    int height = (int) this.getPrefHeight();
    int width = (int) this.getPrefWidth();
    int rows;
    int columns;
    GameLogicInterface gameLogicInterface;

    public BoardLayout(File settingsFile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardLayout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            getSettingsFromFile(settingsFile);
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setLogic(GameLogicInterface gl) {
        gameLogicInterface = gl;
    }

    public void draw() {
        //If no Board was provided through displayBoard
        if (this.gameBoard == null) {
            return;
        }
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / gameBoard.getNumRows();
        int cellWidth = width / gameBoard.getNumCol();

        for (int i = 0; i < gameBoard.getNumRows(); i++) {
            for (int j = 0; j < gameBoard.getNumCol(); j++) {
                StackPane cell = new StackPane();
                this.add(cell, j, i);
                drawTile(cellWidth, cellHeight, i, j, cell);
                drawPiece(cellWidth, cellHeight, i, j, cell);
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
        Color darkGreen = Color.rgb(75,	156, 81);
        Color lightGreen = Color.rgb(48, 171, 56);
        if ((row + col)%2 == 0) {
            st.getChildren().add(new Rectangle(cellWidth, cellHeight, darkGreen));
        } else {
            st.getChildren().add(new Rectangle(cellWidth, cellHeight, lightGreen));
        }
    }

    public void displayBoard(Board currentBoard) {
        this.gameBoard = currentBoard; //When draw is called the current Board config will be displayed
        draw();
    }

    public void displayMoves(List<Cell> availablePositions) {
        for (Cell coord : availablePositions) {
            showAvailability(coord);
        }

        /*
        if (this.gameBoard == null) {
            return;
        }
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / gameBoard.getNumRows();
        int cellWidth = width / gameBoard.getNumCol();

        for (int i = 0; i < gameBoard.getNumRows(); i++) {
            for (int j = 0; j < gameBoard.getNumCol(); j++) {
                if(availablePositions != null && availablePositions.contains(gameBoard.getCell(i, j))) {


                    StackPane cell = new StackPane();
                    this.add(cell, j, i);
                    showAvailability(cellWidth, cellHeight, cell);

                }
            }
        }*/
    }

    public void showAvailability(int cellWidth, int cellHeight, StackPane st) {
        Color red = Color.rgb(250, 0, 0, 0.3);
        st.getChildren().add(new Circle(cellHeight/2 - 10, red));
    }

    private Button showAvailability(Cell c) {
        Button temp = new Button();
        temp.setOnAction(e-> return new Pair<Integer,Integer>(c.getXCord(), c.getYCord());
        temp.setStyle( //Make it round
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 15px; " +
                        "-fx-min-height: 15px; " +
                        "-fx-max-width: 15px; " +
                        "-fx-max-height: 15px;"
        );

        StackPane cell = new StackPane();
        this.add(cell, c.getYCord(), c.getXCord());
        cell.getChildren().add(temp);
        return temp;
    }

    private void getSettingsFromFile(File fileName) throws IOException {
        FileReader fileReader;
        ArrayList<String> stringBuffer = new ArrayList<String>();
        try {
            if (!fileName.exists()) {
                PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                writer.close();
            } else {

                fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.add(line);
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        //From the settings file
        if (stringBuffer.isEmpty()) { //default values
            rows = 8;
            columns = 8;
            firstPlayerToken =  new Image(getClass().getResource("images/blackPiece.png").toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource("images/whitePiece.png").toExternalForm());
            return;
        }
        //File definitions
        rows = Integer.parseInt(stringBuffer.get(0));
        columns = Integer.parseInt(stringBuffer.get(1));
        int playerOrder = Integer.parseInt(stringBuffer.get(3));
        if (playerOrder == 0) { //Regular order
            firstPlayerToken =  new Image(getClass().getResource(stringBuffer.get(4)).toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource(stringBuffer.get(5)).toExternalForm());
        } else { //Inverse order
            firstPlayerToken =  new Image(getClass().getResource(stringBuffer.get(5)).toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource(stringBuffer.get(4)).toExternalForm());
        }
    }
}
