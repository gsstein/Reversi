

public class TestBoard extends Test implements TestInterface {
    String className;
    Board myBoard;

    public TestBoard() {
        className = "TestBoard";
        myBoard = new Board();
    }

    public void runAllTests() {
        boardStartsWithEmptyCells();
        System.out.println("");
        insertionRetrievalOfValues();
        System.out.println("");
        coordinatesAreCorrect();
        System.out.println("");
        copyConstructorWorks();
        System.out.println("");
    }

    //Tests that each cell is initiated as empty, ' '
    public void boardStartsWithEmptyCells() {
        int columns = 8;
        int rows = 6;
        Board b = new Board(rows,columns);
        for (int i = 0; i < b.getNumRows(); i++) {
            for (int j = 0; j < b.getNumRows(); j++) {
                ASSERT_EQ(className, "BoardStartsWithEmptyCells", b.getCellValue(i,j), ' ');
            }
        }
    }

    //Tests that getCellValue returns cell value
    public void insertionRetrievalOfValues() {
        int row = myBoard.getNumRows()/2;
        int col = myBoard.getNumCol()/2;
        myBoard.setCell(row,col,'Z');
        ASSERT_EQ(className, "InsertionRetrievalOfValues", myBoard.getCellValue(row,col), 'Z');
    }

    //Tests getXCord and getYCord methods
    public void coordinatesAreCorrect() {
        int row = myBoard.getNumRows()/2 - 1;
        int col = myBoard.getNumCol()/2 + 1;

//        Cell c = myBoard.getCell(row,col);
//        ASSERT_EQ(className, "CoordinatesAreCorrect", c.getXCord(), row);
//        ASSERT_EQ(className, "CoordinatesAreCorrect", c.getYCord(), col);
    }

    //Tests the copy constructor
    public void copyConstructorWorks() {
        for (int i = 0; i < myBoard.getNumRows(); i++) {
            for (int j = 0; j < myBoard.getNumRows(); j++) {
                myBoard.setCell(i,j,(char)('A' + 1));
            }
        }
        Board copyBoard = new Board(myBoard);
        for (int i = 0; i < myBoard.getNumRows(); i++) {
            for (int j = 0; j < myBoard.getNumRows(); j++) {
                ASSERT_EQ(className, "copyConstructorWorks", myBoard.getCellValue(i,j),
                        copyBoard.getCellValue(i,j));
                ASSERT_EQ(className, "copyConstructorWorks", myBoard.getCell(i,j) == copyBoard.getCell(i,j), false);
            }
        }
    }
}
