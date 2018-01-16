package gameOperation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gavriella on 1/16/18.
 */

public class JavaLogic implements GameLogicInterface {
    private GraphicInterface graphicProvider;
    private int numCols, numRows;
    private Player player1, player2, currentPlayer;
    private Board myBoard;
    private ArrayList<Integer> lastPlay;
    private int playerOneScore = 2, playerTwoScore = 2;
    boolean lastTurnSkipped = false;

    public JavaLogic(GraphicInterface gi, int row, int col) {
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

    public JavaLogic(GraphicInterface gi) {
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
        this.setBoard();
    }

    //Called on button press quit
    public void killGame() {
        System.exit(0);
    }

    public boolean gameEnded() {
        return ((((getValidPositions(player1, this.myBoard)).isEmpty())
                && ((getValidPositions(player2, this.myBoard)).isEmpty())));
    }

    public Player getWinner() {
        this.graphicProvider.displayBoard(myBoard);
        if(playerOneScore == playerTwoScore) {
            return null;
        }
        if(playerOneScore > playerTwoScore) {
            return player1;
        }
        return player2;
    }

    boolean gameOver() {
        if(lastTurnSkipped) {
            return true;
        }
        lastTurnSkipped = true;
        return false;
    }

    public void playNextTurn() {
        this.graphicProvider.displayBoard(myBoard);
        ArrayList<Cell> validPositions = this.getValidPositions(this.currentPlayer, this.myBoard);
        if(validPositions.isEmpty()) { //Iterate over skipped turn
            currentPlayer.outOfPlays();
            if(gameOver()) {
                endGame();
            }
            graphicProvider.displayMessage("Skipped turn\n");
            changeTurns();
            playNextTurn();
        } else {
            lastTurnSkipped = false;
            graphicProvider.displayMoves(validPositions, myBoard);
        }
        //Ends, waits for GUI to call makeMove
    }


    public void makeMove(int i, int j) {
        //Make the move
        Cell c = myBoard.getCell(i, j);
        this.convertAndSpread(this.myBoard, c, currentPlayer);
        updateScore(1);

        this.graphicProvider.displayPlayer(currentPlayer);
        this.graphicProvider.displayMessage(" played ");
        this.graphicProvider.displayCoordinate(i, j);
        this.graphicProvider.displayMessage("\n");
        changeTurns();

        playNextTurn(); //Or should the event handler call playNextTurn?
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
//                conversionPath.add(c);
                keepChecking = false;
            } else {
                conversionPath.add(c);
            }
        }
        return conversionPath;
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
//        Collections.sort(validPositions);
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
        this.graphicProvider.displayPlayer(winner);
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
        updateScore(path.size());
        if(!path.isEmpty()) {
            for (int i = 0; i < (path.size()); i++) {
                Cell c = path.get(i);
                gameBoard.setCell(c.getXCord(),c.getYCord(), value);
            }
        }
    }

    public void updateScore(int score) {
        if(currentPlayer == player1) {
            playerOneScore += score;
            playerTwoScore -= score;
        } else {
            playerOneScore -= score;
            playerTwoScore += score;
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

}
