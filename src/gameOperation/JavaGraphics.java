package gameOperation;
import javafx.scene.layout.BorderPane;
import reversiapp.BoardController;
import reversiapp.GameWindowController;

import java.util.ArrayList;

/**
 * Created by dan on 1/13/18.
 */
public class JavaGraphics implements GraphicInterface {
    //Members
    private GameWindowController gameWindowController;

    public JavaGraphics() {

    }

    public void getGameWindow(GameWindowController gwc) {
        gameWindowController = gwc;
    }

    @Override
    public void displayBoard(Board b) {
        //not in use
    }

    @Override
    //Paints the available Cells red
    public void displayMoves(ArrayList<Cell> availablePositions, Board b) {
        gameWindowController.showMoves(b, availablePositions);
    }

    @Override
    public void displayMessage(String message) {
        gameWindowController.showMessage(message);
    }

    @Override
    public void displayMessage(char message) {

    }

    @Override
    public void displayPlayer(Player player) {

    }

    @Override
    public void displayCoordinate(int a, int b) {
        //Not in use
    }
}
