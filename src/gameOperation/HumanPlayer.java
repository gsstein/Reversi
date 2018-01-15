package gameOperation;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player implements PlayerInterface {
    GameLogic gameLogic;
    GraphicInterface display;
    char value;

    public HumanPlayer(GameLogic gl, GraphicInterface gi, char v) {
        gameLogic = gl;
        display = gi;
        value = v;
        setPlayerId(value);
    }

    public ArrayList<Integer> convertInputToCoord(String buffer) {
        String[] s;
        ArrayList<Integer> list = new ArrayList<>();
        //Check if correct format
        if(!buffer.contains(",") || buffer.length() <= (buffer.indexOf(',')) + 1) {
            list.add(-1);
            list.add(-1);
            return list;
        }
        s = buffer.split(",");
        //Convert string to valid move
        list.add(Integer.parseInt(s[0]));
        list.add(Integer.parseInt(s[1]));
        return list;
    }


    public ArrayList<Integer> makeMove() {
        Scanner scanner = new Scanner(System.in);

        //Get Valid Plays
        ArrayList<Cell> validMoves;
        validMoves = gameLogic.getValidPositions(this, gameLogic.getBoard());
        //Print moves
        display.displayPlayer(this);
        display.displayMessage(": it's your move.\nYour possible moves: ");
        display.displayMoves(validMoves, gameLogic.getBoard());

        boolean validChoice = false;
        ArrayList<Integer> playerInput = new ArrayList<>();
        //Waits for valid input
        while(!validChoice) {
            display.displayMessage("\nPlease enter your move row,col: ");

            String s = scanner.nextLine();

            playerInput = convertInputToCoord(s);
            //Cleans buffer
            for (int i = 0; i < validMoves.size(); i++) {
                if ((validMoves.get(i).getXCord() == playerInput.get(0)) && (validMoves.get(i).getYCord() ==  playerInput.get(1))) {
                    validChoice = true;
                    return playerInput;
                }
            }
            display.displayMessage("Invalid input!\n");
        }
        return playerInput;
    }

    public void outOfPlays() {
        display.displayPlayer(this);
        display.displayMessage("\nYou are out of plays!\n");
    }
}
