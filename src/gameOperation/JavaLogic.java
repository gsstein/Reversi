package gameOperation;

import java.util.ArrayList;


public class JavaLogic extends StandardLogic {
    private int playerOneScore = 2, playerTwoScore = 2;
    private boolean lastTurnSkipped = false;



    public JavaLogic(GraphicInterface gi, int row, int col) {
        super(gi, row, col);
    }


    @Override
    /**
     * calculates the next board and displays it
     */
    public void playNextTurn() {
        ArrayList<Cell> validPositions = this.getValidPositions(this.currentPlayer, this.myBoard);
        if(validPositions.isEmpty()) { //Iterate over skipped turn
            if(gameEnded()) {
                endGame();
                graphicProvider.fullDisplay(validPositions, myBoard);
                return;
            }
            changeTurns();
            playNextTurn();
        } else {
            lastTurnSkipped = false;
            graphicProvider.fullDisplay(validPositions, myBoard);
        }
        //Ends, waits for GUI to call makeMove
    }

    @Override
    /**
     * calculates the knew board from the input, and plays next turn
     */
    public void makeMove(int i, int j) {
        //Make the move
        currentPlayer.setScore(currentPlayer.getScore() + 1);
        Cell c = myBoard.getCell(i, j);
        this.convertAndSpread(this.myBoard, c, currentPlayer);
        changeTurns();
        playNextTurn();
    }

    @Override
    /**
     * displays the winner
     */
    public void endGame() {
        Player winner = getWinner();
        this.graphicProvider.displayPlayer(winner.getPlayerIdChar());
        this.graphicProvider.displayMessage(" wins!\n");
    }

}
