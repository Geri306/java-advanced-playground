package concurrency.mosh;

public class Main {
    // JVM has a Thread scheduler that decides what threads to run for how long
    public static void main(String[] args) {
        // number of active threads in the current process
//        System.out.println("Thread.activeCount() = " + Thread.activeCount()); // -> 1. main thread running the main method, 2. background thread running the garbage collector (removes unused objects from memory)
        // get total number of available threads
//        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());

//        Demo.startingAndPausing();
//        Demo.joining();
        Demo.interrupting();
    }
}
