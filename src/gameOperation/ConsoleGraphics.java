package gameOperation;

import java.util.ArrayList;

public class ConsoleGraphics implements GraphicInterface {

    public ConsoleGraphics() {

    }

    public void fullDisplay(ArrayList<Cell> availablePositions, Board b) {
        displayBoard(b);
        if(!(availablePositions == null || availablePositions.isEmpty())) {
            System.out.print("Your possible moves: ");
            displayMoves(availablePositions, b);
        }
    }

    public void displayBoard(Board b) {
        //Prints the top numbers
        System.out.print(" |");
        for (int j = 0; j < b.getNumCol(); j++ ) {
            System.out.print(" " +  j + " |");
        }
        System.out.println("");
        System.out.print("--"); //Starts the line
        for (int j = 0; j < b.getNumCol(); j++ ) {
            System.out.print("----");
        }
        System.out.println("");

        //Prints the rest of the table
        for (int i = 0; i < b.getNumRows(); i++ ) {
            System.out.print(i + "|"); //Prints the row number
            for (int j = 0; j <b.getNumCol(); j++ ) { //Prints the chars
                System.out.print(" " + b.getCellValue(i,j) + " |");
            }
            System.out.println("");
            System.out.print("--"); //Starts the line
            for (int j = 0; j < b.getNumCol(); j++ ) {
                System.out.print("----");
            }
            System.out.println("");
        }
    }

    public void displayMoves(ArrayList<Cell> availablePositions, Board board) {
        System.out.println("");
        for (int i= 0; i < availablePositions.size(); i++) {
            availablePositions.get(i).printCell();
        }
        System.out.println("");
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayMessage(char message) {
        System.out.print(message);
    }

    public void displayPlayer(Player player) {
        if (player == null) {
            System.out.println("Congratulations to both players!\n" + "It was a tie.");
        } else {
            System.out.print("Player(" + player.getPlayerIdChar() + ")");
        }
    }

    public void displayCoordinate(int a, int b) {
        System.out.println("(" + a + "," + b + ")");
    }
}
