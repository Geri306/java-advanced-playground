package concurrency.mosh.threadsafety.synchronisation.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Demo {
    public static void show() {
        List<Thread> threads = new ArrayList<>();
        List<DownloadFileTask> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DownloadFileTask task = new DownloadFileTask();
            tasks.add(task);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Optional<Integer> totalBytes = tasks.stream()
                .map(t -> t.getStatus().getTotalBytes())
                .reduce(Integer::sum);

        System.out.println(totalBytes);
    }
}
