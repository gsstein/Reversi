package gameOperation;

import java.util.ArrayList;

public interface GraphicInterface {
    void displayBoard(Board b);
    void displayMoves(ArrayList<Cell> availablePositions, Board board);
    void displayMessage(String message);
    void displayMessage(char message);
    void displayPlayer(Player player);
    void displayCoordinate(int a, int b);
}
