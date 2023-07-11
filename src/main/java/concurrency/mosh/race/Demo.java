package concurrency.mosh.race;


import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void show() {
        DownloadStatus status = new DownloadStatus();

        List<Thread> threads = new ArrayList<>();

        // 10 download tasks, each downloads 10k bytes
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threads.add(thread);
        }

        // wait for all download threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // total number of bytes we have downloaded (expected 100k, but it's not the case, instead, a different number each time)
        // race condition in action: multiple threads are racing to update the total number of bytes
        System.out.println(status.getTotalBytes());
    }
}
