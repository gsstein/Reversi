import java.util.ArrayList;


public class TestStandardLogic extends Test implements TestInterface {
    String className = "TestStandardLogic";
    GraphicInterface gi = new ConsoleGraphics();
    StandardLogic standardLogic = new StandardLogic(gi);
    MachinePlayer p1 = new MachinePlayer(standardLogic, gi, 'O');
    MachinePlayer p2 = new MachinePlayer(standardLogic, gi, 'X');

    public void runAllTests() {
        StartsBoardProperly();
        System.out.println("");
        GameEndsCorrectly();
        System.out.println("");
        GetCorrectWinner();
        System.out.println("");
        ValidPositionsAreCorrect();
        System.out.println("");
    }

    public void StartsBoardProperly() {
        //Starts the Game
        Player mp1 = (Player) p1;
        Player mp2 = (Player) p2;
        standardLogic.startGame(mp1, mp2);
        ConsoleGraphics cg;
        //Check if the board was filled correctly
        Board dummyBoard = new Board((standardLogic.getBoard()));
        for (int i = 0; i < dummyBoard.getNumRows(); i++) {
            for (int j = 0; j < dummyBoard.getNumCol(); j++) {
                if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    ASSERT_EQ(className, "StartsBoardProperly", dummyBoard.getCellValue(i,j), 'O');
                } else if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    ASSERT_EQ(className, "StartsBoardProperly", dummyBoard.getCellValue(i,j), 'X');
                } else {
                    ASSERT_EQ(className, "StartsBoardProperly", dummyBoard.getCellValue(i,j), ' ');
                }
            }
        }
        standardLogic.endGame();
    }

    public void GameEndsCorrectly() {
        //Starts the Board
        Player mp1 = p1;
        Player mp2 = p2;
        standardLogic.startGame(mp1,mp2);
        // 1 -  Test Check if gameEnded returns false value when there are plays left.
        ASSERT_EQ(className, "GameEndsCorrectly", standardLogic.gameEnded(), false);
        //2 - See if when gameEnded becomes true no plays are possible
        ArrayList<Cell> mp1moves;
        ArrayList<Cell> mp2moves;
        //Plays until gameEnded becomes true
        //Asserts there are plays left;
        while(!standardLogic.gameEnded()) {
            mp1moves = standardLogic.getValidPositions(mp1, standardLogic.getBoard());
            mp2moves = standardLogic.getValidPositions(mp2, standardLogic.getBoard());
            ASSERT_EQ(className, "GameEndsCorrectly", mp1moves.isEmpty() && mp2moves.isEmpty(), false);
            standardLogic.playNextTurn();
        }
        //Checks if no remaining plays are possible
        mp1moves = standardLogic.getValidPositions(mp1, standardLogic.getBoard());
        mp2moves = standardLogic.getValidPositions(mp2, standardLogic.getBoard());
        ASSERT_EQ(className, "GameEndsCorrectly", mp1moves.isEmpty() && mp2moves.isEmpty(), true);
        standardLogic.endGame(); //frees memory
    }

    public void GetCorrectWinner() {
        //Starts the Board
        Player mp1 = p1;
        Player mp2 = p2;
        standardLogic.startGame(mp1,mp2);
        while(!standardLogic.gameEnded()) {
            standardLogic.playNextTurn();
        }
        int counterP1 = 0;
        int counterP2 = 0;
        Board dummyBoard = new Board((standardLogic.getBoard()));
        for (int i = 0; i < dummyBoard.getNumRows(); i++) {
            for (int j = 0; j < dummyBoard.getNumCol(); j++) {
                if (dummyBoard.getCellValue(i,j) == p1.getPlayerIdChar()) {
                    counterP1++;
                } else if(dummyBoard.getCellValue(i,j) == p2.getPlayerIdChar()) {
                    counterP2++;
                }
            }
        }
        Player winner;
        winner = standardLogic.getWinner();
        if(counterP2 > counterP1) {
            ASSERT_EQ(className, "GetCorrectWinner", p2.getPlayerIdChar() == winner.getPlayerIdChar(),true);
        } else if(counterP2 < counterP1) {
            ASSERT_EQ(className, "GetCorrectWinner", p1.getPlayerIdChar() == winner.getPlayerIdChar(),true);
        } else {
            ASSERT_EQ(className, "GetCorrectWinner", winner == null,true);
        }
        standardLogic.endGame();
    }

    public void ValidPositionsAreCorrect() {
        //Starts the Board
        Player mp1 = p1;
        Player mp2 = p2;
        standardLogic.startGame(mp2,mp1);
        ArrayList<Cell> positionsP1 = standardLogic.getValidPositions(mp1,standardLogic.getBoard());
        ArrayList<Cell> positionsP2 = standardLogic.getValidPositions(mp2,standardLogic.getBoard());

        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP1.get(0).getXCord() == 2) && (positionsP1.get(0).getYCord() == 3), true);
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP1.get(1).getXCord() == 3) && (positionsP1.get(1).getYCord() == 2), true);
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP1.get(2).getXCord() == 4) && (positionsP1.get(2).getYCord() == 5), true);
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP1.get(3).getXCord() == 5) && (positionsP1.get(3).getYCord() == 4), true);
        ///For player2 now
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP2.get(0).getXCord() == 2) && (positionsP2.get(0).getYCord() == 4), true);
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP2.get(1).getXCord() == 3) && (positionsP2.get(1).getYCord() == 5), true);
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP2.get(2).getXCord() == 4) && (positionsP2.get(2).getYCord() == 2), true);
        ASSERT_EQ(className, "ValidPositionsAreCorrect", (positionsP2.get(3).getXCord() == 5) && (positionsP2.get(3).getYCord() == 3), true);
        standardLogic.endGame();
    }
}
