package streams.reducers;

import streams.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Demo {

    public static void simpleReducers() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 20),
                new Movie("c", 30)
        );

        boolean result = movies.stream()
                .anyMatch(m -> m.getLikes() > 20);

        System.out.println("anyMatch result = " + result);

        result = movies.stream()
                .allMatch(m -> m.getLikes() > 20);

        System.out.println("allMatch result = " + result);

        result = movies.stream()
                .noneMatch(m -> m.getLikes() > 20);

        System.out.println("noneMatch result = " + result);

        Movie result2 = movies.stream()
                .findFirst()
                .get();

        System.out.println("findFirst = " + result2.getTitle());

        result2 = movies.stream()
                .findAny()
                .get();

        System.out.println("findAny = " + result2.getTitle());

        result2 = movies.stream()
                .max(Comparator.comparing(Movie::getLikes))
                .get();

        System.out.println("max = " + result2.getTitle());

    }

    public static void reducingAStream() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 20),
                new Movie("c", 30)
        );

        // [10, 20, 30]
        // [30, 30]
        // [60]
        Optional<Integer> oSum = movies.stream()
                .map(m -> m.getLikes())
//                .reduce((a, b) -> a + b);
                .reduce(Integer::sum); // if we use this we get back an Optional<Integer>


        // we supply a default value with 'orElse' if we have an empty stream to avoid getting an exception (in case we would have an empty stream where we would call 'get()')
        System.out.println("oSum = " + oSum.orElse(0));


        // CLEANER ALTERNATIVE TO THE PREVIOUS SOLUTION
        Integer sum = movies.stream()
                .map(m -> m.getLikes())
                .reduce(0, Integer::sum); // it returns an Integer object instead of an Optional<Integer>

        System.out.println("sum = " + sum);
    }
}
