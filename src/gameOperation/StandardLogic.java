package gameOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StandardLogic implements GameLogicInterface {
    protected GraphicInterface graphicProvider;
    protected int numCols, numRows;
    protected Player player1, player2, currentPlayer;
    protected Board myBoard;
    private ArrayList<Integer> lastPlay;

    private boolean lastTurnSkipped = false;

    public StandardLogic(GraphicInterface gi, int row, int col) {
        lastPlay = new ArrayList<>();
        this.graphicProvider = gi;
        numCols = col;
        numRows = row;
        this.player1 = null;
        this.player2 = null;
        this.myBoard = null;
        this.currentPlayer = null;
        this.lastPlay.add(-1);
    }

    public StandardLogic(GraphicInterface gi) {
        lastPlay = new ArrayList<>();
        this.graphicProvider = gi;
        numCols = 8;
        numRows = 8;
        this.player1 = null;
        this.player2 = null;
        this.myBoard = null;
        this.currentPlayer = null;
        this.lastPlay.add(-1);
    }

    public void startGame(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        currentPlayer = p1;
        player1.setScore(2);
        player2.setScore(2);
        this.setBoard();
    }


    public boolean gameEnded() {
        if(lastTurnSkipped) {
            return true;
        }
        lastTurnSkipped = true;
        return false;
    }

    public Player getWinner() {
        this.graphicProvider.fullDisplay(null, myBoard);
        if(player1.getScore() == player2.getScore()) {
            return null;
        }
        if(player1.getScore() > player2.getScore()) {
            return player1;
        }
        return player2;
    }

    public void makeMove(int r, int c) {
        currentPlayer.setScore(currentPlayer.getScore() + 1);
        Cell dummy = new Cell(r, c, ' ');
        this.convertAndSpread(this.myBoard, dummy, currentPlayer);
        this.graphicProvider.displayMessage(Character.toString(currentPlayer.getPlayerIdChar()));
        this.graphicProvider.displayMessage(" played (");
        this.graphicProvider.displayMessage(Integer.toString(r));
        this.graphicProvider.displayMessage(" ,");
        this.graphicProvider.displayMessage(Integer.toString(c));
        this.graphicProvider.displayMessage(")");
        this.graphicProvider.displayMessage("\n");
    }

    public void playNextTurn() {
        this.graphicProvider.displayMessage("Current board:\n\n");
        ArrayList<Cell> validPositions = this.getValidPositions(this.currentPlayer, this.myBoard);
        graphicProvider.fullDisplay(validPositions, myBoard);
        if(!validPositions.isEmpty()) {
            ArrayList<Integer> playerChoice = currentPlayer.makeMove();
            //Saves the valid play
            lastPlay.add(0, playerChoice.get(0));
            lastPlay.add(1, playerChoice.get(1));
            makeMove(playerChoice.get(0), playerChoice.get(1));
            lastTurnSkipped = false;
        } else {
            currentPlayer.outOfPlays();
            lastTurnSkipped = true;
        }
        changeTurns();
    }

    public void changeTurns() {
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = player2;
        } else {
            this.currentPlayer = player1;
        }
    }


    public Board setBoard() {
        this.myBoard = new Board(this.numRows, this.numCols);
        this.myBoard.getCell(myBoard.getNumRows() / 2 - 1, myBoard.getNumRows() / 2 - 1).setValue('X');
        myBoard.getCell(myBoard.getNumRows() / 2, myBoard.getNumCol() / 2).setValue('X');
        myBoard.getCell(myBoard.getNumRows() / 2 - 1, myBoard.getNumCol() / 2).setValue('O');
        myBoard.getCell(myBoard.getNumRows() / 2, myBoard.getNumRows() / 2 - 1).setValue('O');
        return (this.myBoard);
    }

    public boolean isPositionValid(Cell c, Player player, Board gameBoard) {
        // temp is bigger than one if opponent's pieces were caught in between the beginning and end of the path
        char target = player.getPlayerIdChar();
        ArrayList<Cell> temp;
        Cell start = c;
        temp = checkUp(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkDown(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkLeft(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkRight(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkRight(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkDigRightUp(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkDigRightDown(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkDigLeftDown(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        temp = checkDigLeftUp(start, target, gameBoard);
        if (temp.size() > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Cell> generalCheck(Board gameBoard, Cell start, char target, int rowDif, int colDif) {
        int i = 0;
        int j = 0;
        ArrayList<Cell> conversionPath = new ArrayList<>();
        Cell c = gameBoard.getCell(start.getXCord() + i, start.getYCord() + j); //Get the cell

        if(c == null) {
            return conversionPath;
        }

        boolean keepChecking = true;
        while(keepChecking) {
            i = i + rowDif; // Adapts to desired direction
            j = j + colDif; // Adapts to desired direction
            c = gameBoard.getCell(start.getXCord() + i, start.getYCord() + j); //Get the cell

            if(c == null || c.getValue() == ' ') {
                keepChecking = false;
                conversionPath.clear();
            } else if(c.getValue() == target) { //If target acquired
                keepChecking = false;
            } else {
                conversionPath.add(c);
            }
        }
        return conversionPath;
    }

    @Override
    public int getPlayerOneScore() {
        return player1.getScore();
    }

    @Override
    public int getPlayerTwoScore() {
        return player2.getScore();
    }


    public ArrayList<Cell> getValidPositions(Player player, Board gameBoard) {
        ArrayList<Cell> validPositions = new ArrayList<>();
        for (int i = 0; i < gameBoard.getNumRows(); i++) {
            for (int j = 0; j < gameBoard.getNumCol(); j++) {
                Cell c = gameBoard.getCell(i, j);
                if(c.getValue() == ' ') {
                    if(this.isPositionValid(c,player, gameBoard)) {
                        validPositions.add(c);
                    }
                }

            }
        }
        return validPositions;
    }


    public ArrayList<Cell> checkUp(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, 1, 0);
    }

    public ArrayList<Cell> checkDown(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, -1, 0);
    }

    public ArrayList<Cell> checkLeft(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, 0, -1);
    }

    public ArrayList<Cell> checkRight(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, 0, 1);
    }

    public ArrayList<Cell> checkDigRightUp(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, 1, 1);
    }

    public ArrayList<Cell> checkDigRightDown(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, -1, 1);
    }

    public ArrayList<Cell> checkDigLeftUp(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, 1, -1);
    }

    public ArrayList<Cell> checkDigLeftDown(Cell start, char target, Board gameBoard) {
        return generalCheck(gameBoard, start, target, -1, -1);
    }

    public void endGame() {
        Player winner = getWinner();
        this.graphicProvider.displayMessage(Character.toString(winner.getPlayerIdChar()));
        this.graphicProvider.displayMessage(" wins!\n");
    }

    public void convertAndSpread(Board gameBoard, Cell start, Player player) {
        gameBoard.setCell(start.getXCord(),start.getYCord(), player.getPlayerIdChar());
        //modifies the start symbol, for it's just a copy
        start.setValue(player.getPlayerIdChar());
        this.propagateConversion(start, gameBoard);
    }

    public void propagateConversion(Cell start, Board gameBoard) {
        //Converts all the possible paths in any of the 8 possible directions
        char value = start.getValue();
        convertPath(checkUp(start, value, gameBoard),value, gameBoard);
        convertPath(checkLeft(start, value, gameBoard),value, gameBoard);
        convertPath(checkRight(start, value, gameBoard),value, gameBoard);
        convertPath(checkDown(start, value, gameBoard),value, gameBoard);
        convertPath(checkDigLeftUp(start, value, gameBoard),value, gameBoard);
        convertPath(checkDigLeftDown(start, value, gameBoard),value, gameBoard);
        convertPath(checkDigRightDown(start, value, gameBoard),value, gameBoard);
        convertPath(checkDigRightUp(start, value, gameBoard),value, gameBoard);
    }

    //propagateConversion subroutine
    public void convertPath(ArrayList<Cell> path, char value, Board gameBoard) {
        if(!path.isEmpty()) {
            updateScore(path.size());
            for (int i = 0; i < (path.size()); i++) {
                Cell c = path.get(i);
                gameBoard.setCell(c.getXCord(),c.getYCord(), value);
            }
        }
    }

    public Board getBoard() {
        return this.myBoard;
    }

    public ArrayList<Integer> getLastPlay() {
        return this.lastPlay;
    }


    public char getCurrentPlayerId() {
        return this.currentPlayer.getPlayerIdChar();
    }

    private void updateScore(int score) {
        if(currentPlayer == player1) {
            player1.setScore(player1.getScore() + score);
            player2.setScore(player2.getScore() - score);
        } else {
            player1.setScore(player1.getScore() - score);
            player2.setScore(player2.getScore() + score);
        }
    }
}
