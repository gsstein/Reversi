package gameOperation;

import java.util.ArrayList;

public class MachinePlayer extends Player {
    GameLogicInterface currentGameLogic;
    GraphicInterface graphicProvider;
    char value;
    

    public MachinePlayer(GameLogicInterface gl, GraphicInterface gi, char v) {
        currentGameLogic = gl;
        graphicProvider = gi;
        value = v;
        setPlayerId(value);
    }
    


    public ArrayList<Integer> makeMove() {
        //Print moves
        graphicProvider.displayMessage("It's");
        graphicProvider.displayMessage(Character.toString(this.getPlayerIdChar()));
        graphicProvider.displayMessage("'s turn\n");
        return findBestMove();
    }

    public ArrayList<Integer> findBestMove() {
        Cell bestPlay = null;
        int smallestEnemyScore = (currentGameLogic.getBoard().getNumCol())*
			 (currentGameLogic.getBoard().getNumRows());
        int currentScore;

        Board initialBoard = this.currentGameLogic.getBoard();
        Board tempBoard;
        ArrayList<Cell> tempPositions = this.currentGameLogic.getValidPositions(this, initialBoard);
        //Parse through the valid positions choosing the one with the lowest max number of pieces for the enemy
        for (int i = 0; i < tempPositions.size(); i++) {
            tempBoard = new Board(initialBoard);
            Cell dummy = tempPositions.get(i);
            //Makes the play on a copy of the Board
            currentGameLogic.convertAndSpread(tempBoard, dummy, (Player)this);
            currentScore = this.findOpponentMaxCells(tempBoard);
            if (currentScore <= smallestEnemyScore) {
                smallestEnemyScore = currentScore;
                bestPlay = tempPositions.get(i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(bestPlay.getXCord());
        list.add(bestPlay.getYCord());
        return list;
    }

    public int countEnemyCells(Board b, Player enemy) {
        int counter = 0;
        for (int i = 0; i < b.getNumRows(); i++) {
            for (int j = 0; j < b.getNumCol(); j++) {
                if (b.getCellValue(i,j) == enemy.getPlayerIdChar()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int findOpponentMaxCells(Board targetBoard) {
        char enemyChar = 0;
        Player enemyDummy = null;
        Board pointToTarget;
        //Finds enemy symbol
        for (int i = 0; i < targetBoard.getNumRows(); i++) {
            for (int j = 0; j < targetBoard.getNumCol(); j++) {
                enemyChar = targetBoard.getCellValue(i,j);
                if ((enemyChar != ' ') && (enemyChar != this.getPlayerIdChar())) {
                    i = targetBoard.getNumRows();//Stops the outer loop
                    j = i; //leaves the inner loop
                }
            }
        }
        //Creates enemy simulation
        MachinePlayer mp = new MachinePlayer(this.currentGameLogic, this.graphicProvider, enemyChar);
        enemyDummy = mp;

        int bestMoveScore = 0;
        int currentMoveScore = 0;
        pointToTarget = targetBoard;
        ArrayList<Cell> tempPositions = this.currentGameLogic.getValidPositions(enemyDummy, pointToTarget);
        //Loops through all enemy plays
        Board tempBoard;
        for (int i = 0; i < tempPositions.size(); i++) {
            tempBoard = new Board(targetBoard);
            Cell dummy = tempPositions.get(i);
            currentGameLogic.convertAndSpread(tempBoard, dummy,enemyDummy);
            currentMoveScore = countEnemyCells(tempBoard,enemyDummy);
            //Updates the highest score
            if (currentMoveScore >= bestMoveScore) {
                bestMoveScore = currentMoveScore;
            }
        }
        return bestMoveScore;

    }

    public void outOfPlays() {
        this.graphicProvider.displayMessage(Character.toString(this.getPlayerIdChar()));
        this.graphicProvider.displayMessage("\nYou are out of plays!\n");
    }
}
