package concurrency.mosh.interrupting;

public class DownloadFileTask implements Runnable {

    @Override
    public void run() {
        System.out.println("downloading a file: " + Thread.currentThread().getName());

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            System.out.println("Downloading byte " + i);
        }

        System.out.println("download complete: " + Thread.currentThread().getName());
    }
}
