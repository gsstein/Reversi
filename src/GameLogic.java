import java.util.ArrayList;

abstract class GameLogic implements GameLogicInterface {

    public abstract boolean gameEnded();
    public abstract void startGame(Player p1, Player p2); //Allows for restarting matches
    public abstract Player getWinner();
    public abstract void playNextTurn();
    public abstract void endGame();
    public abstract Board getBoard();
    public abstract ArrayList<Cell> getValidPositions(Player player, Board gameBoard);
    public abstract void convertAndSpread(Board gameBoard, Cell start, Player player); //Makes the move and convert the necessary enemy cells
    public abstract ArrayList<Integer> getLastPlay();
    public abstract char getCurrentPlayerId();
    // Game Play actions
    public abstract boolean isPositionValid(Cell c, Player player, Board gameBoard);
    //Can be overrided if rules are defined differently
    public abstract ArrayList<Cell> generalCheck(Board gameBoard, Cell start, char target, int rowDif, int colDif);
}
