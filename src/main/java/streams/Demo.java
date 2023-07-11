package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {
    public static void imperativeVsDeclarative() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        // imperative programming (HOW it should be done?), e.g. SQL is a good example for declarative language
        // streams allow us to process a collection of data in a declarative, or more accurately functional way. Functional Programming is a special type of declarative programming but it brings some additional concepts.
        int count = 0;
        for (var movie : movies) {
            if (movie.getLikes() > 10) {
                count++;
            }
        }
        System.out.println("count = " + count);

        // rewrite previous code in a declarative (functional) way. We have a bunch of functions (e.g. filter, count) and we're composing these to express a complex logic. We don't have instructions to specify how something should be done. Instead, we're purely expressing what needs to be done. We don't have statements like setting the counter to zero and incrementing it. So with streams we can process a collection of objects in a functional way.
        var count2 = movies.stream()
                // filter needs a predicate -> a function that takes an object and returns a boolean
                .filter(m -> m.getLikes() > 10) // we wanna filter based on this condition
                .count();
        System.out.println("count2 = " + count2);
    }

    public static void creatingAStream() {
        int[] numbers = {1, 2, 3};
        Arrays.stream(numbers)
                // every stream has a forEach, in our case this forEach takes an IntConsumer which is a primitive specialisation of the Consumer interface. A Consumer interface represents an operation that takes an object and does not return a value, because it consumes that object.
                .forEach(n -> System.out.println(n));


        // we can also create a stream from an arbitrary number of objects
        // .of is a static factory method
        Stream.of(1, 2, 3, 4);

        // generate infinite streams (there are 2 ways)
        // WAY 1 - takes a supplier which is an operation that supplies or returns a value
        // This will generate an infinite stream of random numbers. This is not possible with collections because if we wanna store an infinite number of objects in a collection we are gonna run out of memory. But with streams, this function that we passed in as a parameter gonna get called every time we read a number from this stream. So these numbers are not gonna be generated ahead of time. This is what we call lazy evaluation.
        var stream = Stream.generate(() -> Math.random()); // at this point nothing happens when we run the program, no number is generated, we have not consumed anything, we don't have infinite numbers in the memory.
        stream
                .limit(3)
                .forEach(n -> System.out.println(n)); // when we pass a consumer to forEach, then forEach is going to continuously request a new number from the stream and then print it

        // WAY 2 - iterate method
        // 1. parameter: a seed -> an initial value
        // 2. parameter: a unary operator (a function that takes a value and returns a new value) that will modify the initial value (1. param)
        Stream.iterate(1, n -> ++n)
                .limit(10)
                .forEach(System.out::println);

    }

    public static void mappingElements() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        movies.stream()
                .map(movie -> movie.getTitle())
                .forEach(System.out::println);

        movies.stream()
                .mapToInt(movie -> movie.getLikes())
                .forEach(System.out::println);
    }

    public static void flatMapDemo() {
        // Stream<List/Set<X>> -> Stream<X>
        Stream<List<Integer>> stream = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));
        stream
                // pass a function that takes a list of integers and returns a stream
                .flatMap(list -> list.stream()) // if I comment it out two arrays will be printed
                .forEach(n -> System.out.println(n));
    }

    public static void filterDemo() {
        // IMPORTANT: our original data source here (list of movies) is not affected when we use stream on it and chain operations onto it
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );


        Predicate<Movie> isPopular = m -> m.getLikes() > 10;
        movies.stream()
                // INTERMEDIATE operators like ".map()" and ".filter()" return a new stream (a new pipe) so we can continue transforming that pipe to get the data we want
                .filter(isPopular) // up until this point nothing happens, just another stream will be returned (we might want to save it store it in a variable). The actual filtering happens when we chain a terminal operation, like forEach
                .forEach(m -> System.out.println(m.getTitle()));

    }

    public static void slicingStreams() {
        // limit(n);
        // skip(n) -> for pagination;
        // takeWhile(predicate) -> hey, as long as this condition is true keep taking from this stream;
        // takeWhile(predicate)

        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 30),
                new Movie("aa", 10),
                new Movie("bb", 30),
                new Movie("aaa", 20),
                new Movie("c", 20)
        );

        // 1000 movies
        // 10 movies per page
        // 3rd page
        // skip (20) = skip((page - 1) x pageSize)
        // limit (10) = limit(pageSize)

        movies.stream()
                .skip(20)
                .limit(10)
                .forEach(m -> System.out.println(m.getTitle()));


        movies.stream()
//                .takeWhile(m -> m.getLikes() < 30) // similar to filter but this stops the moment the predicate returns false
                .dropWhile(m -> m.getLikes() < 30) // opposite of takeWhile. It's gonna escape all the elements that match this criteria, and then take the rest.
                .forEach(m -> System.out.println(m.getTitle()));

    }

    public static void sortingStreams() {
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 20),
                new Movie("c", 30)
        );

        movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle).reversed())
                .forEach(m -> System.out.println(m.getTitle()));
    }

    public static void gettingUniqueElements() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("a", 10),
                new Movie("b", 20),
                new Movie("c", 30)
        );

        movies.stream()
                .map(Movie::getLikes)
                .distinct()
                .forEach(System.out::println);
    }

    public static void peekingElements() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 20),
                new Movie("c", 30)
        );

        movies.stream()
                .filter(m -> m.getLikes() > 10)
                .peek(m -> System.out.println("filtered: " + m.getTitle()))
                .map(Movie::getTitle)
                .peek(t -> System.out.println("mapped: " + t))
                .forEach(System.out::println);
    }

    public static void primitiveTypeStreams() {
        IntStream.range(1,5)
                .forEach(System.out::print);

        System.out.println();

        IntStream.rangeClosed(1,5)
                .forEach(System.out::print);
    }
}
