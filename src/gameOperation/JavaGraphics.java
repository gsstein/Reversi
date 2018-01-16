package gameOperation;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import reversiapp.BoardLayout;

import java.io.IOException;
import java.util.ArrayList;
import reversiapp.SideGameMenu;
import java.awt.print.Printable;
/**
 * Created by dan on 1/13/18.
 */
public class JavaGraphics extends BorderPane implements GraphicInterface {
    //Members
    private SideGameMenu sidePanel;
    private BoardLayout gameBoard;
    JavaLogic javaLogic = new JavaLogic(this);

    public JavaGraphics(SideGameMenu sideOptions, BoardLayout gameBoard) {
        this.sidePanel = sideOptions;
        this.gameBoard = gameBoard;
        gameBoard.setLogic(javaLogic);
    }

    @Override
    public void displayBoard(Board b) {
        this.gameBoard.displayBoard(b);
    }

    @Override
    //Paints the available Cells red
    public void displayMoves(ArrayList<Cell> availablePositions, Board b) {
       this.gameBoard.displayMoves(availablePositions, b);
    }

    @Override
    public void displayMessage(String message) {
        this.sidePanel.setMessagesDisplay(message);
    }

    @Override
    public void displayMessage(char message) {
        this.sidePanel.setMessagesDisplay(message);
    }

    public void displayMessage(ArrayList<Printable> msg) {
        for(Printable o : msg) {
            this.sidePanel.setMessagesDisplay(o);
        }
    }

    @Override
    public void displayPlayer(Player player) {
        this.sidePanel.setMessagesDisplay("Player " + player.getPlayerIdChar());
    }

    @Override
    public void displayCoordinate(int a, int b) {
        //Not in use
    }

//    public Cell getMove() {
//        return gameBoard.getMove();
//    }

}
