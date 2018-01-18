package gameOperation;

import java.util.ArrayList;

public interface GameLogicInterface {
    boolean gameEnded();
    void startGame(Player p1, Player p2); //Allows for restarting matches
    Player getWinner();
    void playNextTurn();
    void endGame();
    Board getBoard();
    ArrayList<Cell> getValidPositions(Player player, Board gameBoard);
    void convertAndSpread(Board gameBoard, Cell start, Player player); //Makes the move and convert the necessary enemy cells
    ArrayList<Integer> getLastPlay();
    char getCurrentPlayerId();
    // Game Play actions
    boolean isPositionValid(Cell c, Player player, Board gameBoard);
    //Can be overridden if rules are defined differently
    ArrayList<Cell> generalCheck(Board gameBoard, Cell start, char target, int rowDif, int colDif);
    int getPlayerOneScore();
    int getPlayerTwoScore();
    void makeMove(int r, int c);
    void changeTurns();
}
