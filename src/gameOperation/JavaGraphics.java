package gameOperation;

import reversiapp.GameWindowController;

import java.util.ArrayList;


public class JavaGraphics implements GraphicInterface {
    //Members
    private GameWindowController gameWindowController;

    /**
     * Receives game window controller object
     * @param gwc
     */
    public void getGameWindow(GameWindowController gwc) {
        gameWindowController = gwc;
    }


    /**
     * Calls the game window controller to show the available board and moves
     * @param availablePositions
     * @param b
     */
    public void fullDisplay(ArrayList<Cell> availablePositions, Board b) {
        gameWindowController.displayBoard(b, availablePositions);
    }

    /**
     * Calls the game window controller to show the message
     * @param message
     */
    public void displayMessage(String message) {
        gameWindowController.showMessage(message);
    }

    @Override
    public void displayPlayer(char player) {
        gameWindowController.displayPlayer(player);
    }



}
