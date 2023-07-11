package concurrency.mosh;

import concurrency.mosh.race.DownloadStatus;

public class DownloadFileTask implements Runnable{

    @Override
    public void run() {
        System.out.println("downloading a file: " + Thread.currentThread().getName());

        // pausing the execution of a thread
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("download complete: " + Thread.currentThread().getName());
    }
}
