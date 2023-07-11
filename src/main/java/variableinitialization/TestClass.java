package variableinitialization;

public class TestClass {
    public String fieldString;
    public int fieldInt;
    public char fieldChar;

    void print() {
        String localString;
        int localInt;
        char localChar;
        System.out.println("fieldString = " +fieldString);
//        System.out.println("localString = " + localString); -> variable localString might not have been initialized
        System.out.println("fieldInt = " + fieldInt);
//        System.out.println("localInt = " + localInt); -> variable localInt might not have been initialized
        System.out.println("fieldChar = " + fieldChar);
//        System.out.println("localChar = " + localChar); -> variable localChar might not have been initialized
    }
}
