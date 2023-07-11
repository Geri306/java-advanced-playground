package blocks;

public class BlockTester {

    static int foo = 0;

    // We use INITIALIZER BLOCK in Java if we want to execute a fragment of code for every object which is seen widely in enterprising industries in development.
    {
        System.out.println("3. init block1 called on object instantiation (right before constructor)");
        System.out.println("object id: " + ++foo);
    }

    {
        System.out.println("4. init block2 called on object instantiation (right before constructor)");
        System.out.println("object id: " + foo);
    }

    // STATIC BLOCK: in order to call any static block, there is no specified way as static block executes automatically when the class is loaded in memory. Refer to the below illustration for understanding how static block is called.
    static {
        int num = 1;
        System.out.println("1. static block loaded into memory");
        System.out.println("2. same static block prints static variable (object ID): " + foo);
    }

    static void print() {
        System.out.println("static method called from constructor");
    }

    public BlockTester() {
        System.out.println("5. constructor called");
        print();
    }

}
