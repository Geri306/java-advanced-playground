package streams.collectors;

import streams.Movie;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo {

    // with collectors, we can collect the result of a stream into a data structure
    public static void collectors() {

        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 20),
                new Movie("c", 30)
        );


        List<Movie> result = movies.stream()
                .filter(m -> m.getLikes() > 30)
                // The Collectors class has a bunch of static factory methods for creating collector objects
//                .collect(Collectors.toList())
                .toList();

        System.out.println("result = " + result);




        // key (title)
        // value (likes)
        Map<String, Integer> titleToLikesMap = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .collect(Collectors.toMap(Movie::getTitle, Movie::getLikes));

        System.out.println("titleToLikesMap = " + titleToLikesMap);





        Map<String, Movie> titleToMovieMap = movies.stream()
                .filter(m -> m.getLikes() > 10)
//                .collect(Collectors.toMap(Movie::getTitle, m -> m));
                .collect(Collectors.toMap(Movie::getTitle, Function.identity())); // 'Function.identity()' instead of 'm -> m'

        System.out.println("titleToMovieMap = " + titleToMovieMap);




        Integer sumOfLikes = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .collect(Collectors.summingInt(Movie::getLikes));

        int primitiveSumOfLikes = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .mapToInt(Movie::getLikes)
                .sum();

        System.out.println("sumOfLikes = " + sumOfLikes);





        IntSummaryStatistics summaryStatistics = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .collect(Collectors.summarizingInt(Movie::getLikes));

        System.out.println("summaryStatistics = " + summaryStatistics);






        String joinedTitles = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .map(Movie::getTitle)
                .collect(Collectors.joining(", ")); //

        System.out.println("joinedTitles = " + joinedTitles);

    }
}
