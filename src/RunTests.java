import java.util.ArrayList;


public class RunTests {


    public static void main(String[] args) {
        ArrayList<TestInterface> list = new ArrayList<>();
        TestCell testCell = new TestCell();
        TestBoard testBoard = new TestBoard();
        TestHumanPlayer testHumanPlayer = new TestHumanPlayer();
        TestStandardLogic testStandardLogic = new TestStandardLogic();
        list.add(testCell);
        list.add(testBoard);
        list.add(testHumanPlayer);
        list.add(testStandardLogic);

        for(int i = 0; i < list.size(); i++) {
            list.get(i).runAllTests();
        }
    }
}
