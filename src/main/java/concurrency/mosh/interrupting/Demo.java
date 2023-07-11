package concurrency.mosh.interrupting;

public class Demo {

    public static void interrupting() {
        Thread thread = new Thread(new DownloadFileTask());
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // does not force the thread to stop what it's doing, it simply sends an interrupt request to the thread (and we should check for that request in that thread continuously and act accordingly)
        // if the thread is sleeping, and we send an interrupt request to it, it throws an exception. That is why we have to handle the InterruptedException when pausing a thread with .sleep()
        thread.interrupt();

    }
}
