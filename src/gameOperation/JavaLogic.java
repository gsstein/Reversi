package gameOperation;

import java.util.ArrayList;


public class JavaLogic extends StandardLogic {
    private int playerOneScore = 2, playerTwoScore = 2;
    private boolean lastTurnSkipped = false;



    public JavaLogic(GraphicInterface gi, int row, int col) {
        super(gi, row, col);
    }


    @Override
    public void playNextTurn() {
        ArrayList<Cell> validPositions = this.getValidPositions(this.currentPlayer, this.myBoard);
        if(validPositions.isEmpty()) { //Iterate over skipped turn
            currentPlayer.outOfPlays();
            if(gameEnded()) {
                endGame();
            }
            changeTurns();
            playNextTurn();
        } else {
            lastTurnSkipped = false;
            graphicProvider.displayMoves(validPositions, myBoard);
        }
        //Ends, waits for GUI to call makeMove
    }

    @Override
    public void makeMove(int i, int j) {
        //Make the move
        currentPlayer.setScore(currentPlayer.getScore() + 1);
        Cell c = myBoard.getCell(i, j);
        this.convertAndSpread(this.myBoard, c, currentPlayer);
        changeTurns();
        playNextTurn();
    }

}
