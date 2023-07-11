package collectors.groupingelements;

import collectors.Genre;
import collectors.Movie;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Demo {

    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10, Genre.THRILLER),
                new Movie("b", 20, Genre.ACTION),
                new Movie("c", 30, Genre.ACTION)
        );

        Map<Genre, List<Movie>> listResult = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre));

        System.out.println("listResult = " + listResult);






        Map<Genre, Set<Movie>> setResult = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre, Collectors.toSet()));

        System.out.println("setResult = " + setResult);






        Map<Genre, Long> countResult = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre, Collectors.counting()));

        System.out.println("countResult = " + countResult);






        Map<Genre, String> genreToJoinedTitlesMap = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre,
                        Collectors.mapping(
                                Movie::getTitle,
                                Collectors.joining(", "))));

        System.out.println("genreToJoinedTitlesMap = " + genreToJoinedTitlesMap);
    }
}
