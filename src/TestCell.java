

public class TestCell extends Test implements TestInterface {
    String className = "TestCell";

    public TestCell() {

    }

    public void runAllTests() {
        returnsCorrectCoordinatesAndSymbol();
        System.out.println("");
        correctIsSameValue();
        System.out.println("");
        equalMinorOperatorsWorking();
        System.out.println("");
    }

    //Tests coordinates
    public void returnsCorrectCoordinatesAndSymbol() {
        int x = 10;
        int y = 5;
        char symbol = 'U';
        Cell c = new Cell(x,y, symbol);
        ASSERT_EQ(className, "ReturnsCorrectCoordinatesAndSymbol", x, c.getXCord());
        ASSERT_EQ(className, "ReturnsCorrectCoordinatesAndSymbol", y, c.getYCord());
        ASSERT_EQ(className, "ReturnsCorrectCoordinatesAndSymbol",symbol, c.getValue());
    }

    //Tests isSameValue
    public void correctIsSameValue() {
        int x = 10;
        int y = 5;
        char symbol = 'U';
        Cell c = new Cell(x,y, symbol);
        ASSERT_NE(className, "CorrectIsSameValue", c.isSameValue('X'), true);
        ASSERT_EQ(className, "CorrectIsSameValue", c.isSameValue('U'), true);
    }


    //Tests < > and == for Cells
    public void equalMinorOperatorsWorking() {
        int x = 10;
        int y = 5;
        char symbol = 'U';
        Cell a = new Cell(x,y-1, 'P');
        Cell b = new Cell(x,y-1, symbol);
        Cell c = new Cell(x-1, y, symbol);
        //B is bigger than c
        ASSERT_EQ(className, "EqualMinorOperatorsWorking", b.lessThan(c), false);
        //Not smaller
        ASSERT_EQ(className, "EqualMinorOperatorsWorking", b.lessThan(b), false);
        //Different
        ASSERT_EQ(className, "EqualMinorOperatorsWorking", a.equals(c), false);
        //Equal
        ASSERT_EQ(className, "EqualMinorOperatorsWorking", a.equals(b), true);
    }
}
