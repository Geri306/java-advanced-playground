package lambdas;

public class LambdasDemo {

    public static void classicLambda() {
        greet(message -> System.out.println(message));
    }

    public static void greet(Printer printer) {
        printer.print("Hello world");
    }
}
