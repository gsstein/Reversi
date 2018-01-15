

public class Test {

    public void ASSERT_EQ(String className, String functionName, Object a, Object b) {
        if(a.equals(b)) {
            System.out.print("PASSED    ");
        } else {
            System.out.print("  FAILED    ");
        }
        System.out.println(className + ":" + functionName);
    }


    public void ASSERT_NE(String className, String functionName, Object a, Object b) {
        if(!a.equals(b)) {
            System.out.print("PASSED    ");
        } else {
            System.out.print("  FAILED    ");
        }
        System.out.println(className + ":" + functionName);
    }

}
