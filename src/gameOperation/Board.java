package gameOperation;

import java.util.ArrayList;

public class Board {
    int numRows, numCols;
    ArrayList<Cell> _boardCells;

    public Board(int rows, int columns) {
        numRows = rows;
        numCols = columns;
        // Using a unary vector for safety/ inline practice
        this._boardCells = new ArrayList<>();
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) { //Fills it with spaces
                Cell c = new Cell(i, j, ' ');
                this._boardCells.add(c);
            }
        }
    }

    public Board() {
        numRows = 8;
        numCols = 8;
        // Using a unary vector for safety/ inline practice
        this._boardCells = new ArrayList<>();
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) { //Fills it with spaces
                Cell c = new Cell(i, j, ' ');
                this._boardCells.add(c);
            }
        }
    }

    public Board(Board oldBoard) {
        char copyValue;
        this.numRows = oldBoard.getNumRows();
        this.numCols = oldBoard.getNumCol();
        this._boardCells = new ArrayList<>();
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) { //Fills it with spaces
                copyValue = oldBoard.getCellValue(i, j);
                Cell c = new Cell(i, j, copyValue);
                this._boardCells.add(c);
            }
        }
    }


    public Cell getCell(int r, int c) {
        //Checks if Cell inside the row x column space
        if ((r >= 0) && (r < this.numRows) && (c >= 0) && (c < this.numCols)) {
            return this._boardCells.get(index(r, c));
        }
        return null;
    }


    public int getNumCol() {
        return this.numCols;
    }


    public int getNumRows() {
        return this.numRows;
    }


    public char getCellValue(int r, int c) {
        if ((r >= 0) && (r < this.numRows) && (c >= 0) && (c < this.numCols)) {
            Cell temp = this._boardCells.get(index(r, c));
            return temp.getValue();
        } else {
            return 0;
        }

    }

    public void setCell(int r, int c, char set) {
        if ((r >= 0) && (r < this.numRows) && (c >= 0) && (c < this.numCols)) {
            (this._boardCells.get(index(r, c))).setValue(set);
        }
    }


    private int index(int a, int b) {
        return (a * (this.numCols) + b);
    }
}
