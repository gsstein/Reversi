

public class TestHumanPlayer extends Test implements TestInterface {
    String className;

    public TestHumanPlayer() {
        className = "TestHumanPlayer";
    }

    public void runAllTests() {
        ReturnCorrectIDChar();
        System.out.println("");
    }

    public void ReturnCorrectIDChar() {
        ConsoleGraphics cg = new ConsoleGraphics();
        GraphicInterface gi = (GraphicInterface)cg;
        GameLogic sl = new StandardLogic(gi);
  //      GameLogic gl = sl;
        char x = 'X';
        HumanPlayer p1 = new HumanPlayer(sl, gi,x);
        Player hp = (Player)p1;
        ASSERT_EQ(className, "ReturnCorrectIDChar", hp.getPlayerIdChar(), x);
    }

}
