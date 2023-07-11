package executorframework;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFuturesDemo {
    public static void show() {
        LocalTime start = LocalTime.now();
        FlightService service = new FlightService();
        List<CompletableFuture<Void>> futures = service.getQuotes()
                .map(future -> future.thenAccept(System.out::println))
                .toList();
        CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    LocalTime end = LocalTime.now();
                    Duration duration = Duration.between(start, end);
                    System.out.println("Retrieved all quotes in " + duration.toMillis() + " msec.");
                });
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
