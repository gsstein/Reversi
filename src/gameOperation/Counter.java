package gameOperation;

/**
 * Created by dan on 1/13/18.
 */
public class Counter {
    GameLogic game;

    public void setGameLogic(GameLogic gameToCount) {
        game  = gameToCount;
    }

    private int getPlayerScore(char c) {
        int counter = 0;
        Board currentBoard = game.getBoard();
        for (int i = 0; i < currentBoard.getNumRows() ; i++) {
            for(int j = 0; j < currentBoard.numCols ; j++) {
                if(currentBoard.getCellValue(i,j) == c) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int getWhiteScore() {
        int counter  = getPlayerScore('O');
        return counter;
    }

    public int getBlackScore() {
        int counter  = getPlayerScore('X');
        return counter;
    }

}
