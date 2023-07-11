package concurrency.mosh;

public class Demo {
    public static void startingAndPausing() {
        System.out.println(Thread.currentThread().getName()); // or .getId()

        // these threads start and run in parallel
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask());
            thread.start();
        }

    }

    public static void joining() {
        Thread thread = new Thread(new DownloadFileTask());
        thread.start();

        try {
            // makes the main thread (the current thread that is executing this code) wait for the completion of the download thread. So this call will block the current thread until the download thread has finished.
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("file is ready to be scanned");
    }

    public static void interrupting() {
        Thread thread = new Thread(new DownloadFileTask());
        thread.start();


    }
}
