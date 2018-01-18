package gameOperation;

import java.util.ArrayList;

public interface GraphicInterface {
    void fullDisplay(ArrayList<Cell> availablePositions, Board board);
    void displayMessage(String message);
}
