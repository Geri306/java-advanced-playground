package collectors.partitioningelements;

import collectors.Genre;
import collectors.Movie;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {

    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10, Genre.THRILLER),
                new Movie("b", 20, Genre.ACTION),
                new Movie("c", 30, Genre.ACTION)
        );


        Map<Boolean, List<Movie>> mapOfPartitionedListsOfMovieObjects = movies.stream()
                .collect(Collectors.partitioningBy(m -> m.getLikes() > 20));

        System.out.println(
                "mapOfPartitionedListsOfMovieObjects.get(true) = "
                        + mapOfPartitionedListsOfMovieObjects.get(true));





        Map<Boolean, List<String>> mapOfPartitionedListsOfMovieNames = movies.stream()
                .collect(Collectors.partitioningBy(
                        m -> m.getLikes() > 20,
                        Collectors.mapping(
                                Movie::getTitle,
                                Collectors.toList()
                        )));

        System.out.println(
                "mapOfPartitionedListsOfMovieNames = "
                        + mapOfPartitionedListsOfMovieNames);






        Map<Boolean, String> mapOfPartitionedAndJoinedMovieNames = movies.stream()
                .collect(Collectors.partitioningBy(
                        m -> m.getLikes() > 20,
                        Collectors.mapping(
                                Movie::getTitle,
                                Collectors.joining(", ")
                        )));

        System.out.println("mapOfPartitionedAndJoinedMovieNames = " + mapOfPartitionedAndJoinedMovieNames);
    }
}
