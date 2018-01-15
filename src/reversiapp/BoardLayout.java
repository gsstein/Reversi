package reversiapp;

import gameOperation.Cell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import gameOperation.Board;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.List;

/**
 * Created by dan on 1/13/18.
 */
public class BoardLayout extends GridPane {

    //Members
    private Board gameBoard;
    Image black =  new Image(getClass().getResource("images/blackPiece.png").toExternalForm());
    Image white =  new Image(getClass().getResource("images/whitePiece.png").toExternalForm());
    int height = (int) this.getPrefHeight();
    int width = (int) this.getPrefWidth();

    public BoardLayout() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardLayout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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
            target =  new ImageView(black);
        } else if (gameBoard.getCellValue(row,col) == 'O') {
            target =  new ImageView(white);
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

    public void displayMoves(List<Cell> availablePositions, Board b) {
        //If no Board was provided through displayBoard
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
        }
    }

    public void showAvailability(int cellWidth, int cellHeight, StackPane st) {
        Color red = Color.rgb(250, 0, 0, 0.3);
        st.getChildren().add(new Circle(cellHeight/2 - 10, red));
    }
}
