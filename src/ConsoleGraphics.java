import java.util.ArrayList;

public class ConsoleGraphics implements GraphicInterface {

    public ConsoleGraphics() {

    }

    @Override
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

    @Override
    public void displayMoves(ArrayList<Cell> availablePositions) {
        System.out.println("");
        for (int i= 0; i < availablePositions.size(); i++) {
            availablePositions.get(i).printCell();
        }
        System.out.println("");
    }

    @Override
    public void displayMessage(String message) {
        System.out.print(message);
    }

    @Override
    public void displayMessage(char message) {
        System.out.print(message);
    }

    @Override
    public void displayPlayer(Player player) {
        if (player == null) {
            System.out.println("Congratulations to both players!\n" + "It was a tie.");
        } else {
            System.out.print("Player(" + player.getPlayerIdChar() + ")");
        }
    }

    @Override
    public void displayCoordinate(int a, int b) {
        System.out.println("(" + a + "," + b + ")");
    }
}
