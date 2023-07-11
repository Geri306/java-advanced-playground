package executorframework;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class FlightService {
    public Stream<CompletableFuture<Quote>> getQuotes() {
        List<String> sites = List.of("site1", "site2", "site3");
        return sites.stream().map(this::getQuote);
    }

    public CompletableFuture<Quote> getQuote(String site) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting a quote from " + site);
            Random random = new Random();
            LongTask.simulate(1_000 + random.nextInt(2_000));
            int price = 100 + random.nextInt(10);
            return new Quote(site, price);
        });
    }
}
