package com.elyzar.play.functional;

import com.elyzar.play.support.domain.media.Movie;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.util.Arrays.asList;

public class SortingExamples {

    public static void main(String[] args) throws URISyntaxException {

        List<String> cities = asList("Milan", "london", "San Francisco", "Tokyo", "New Delhi");
        cities.sort(CASE_INSENSITIVE_ORDER);
        System.out.println("cities sorted alphabetically using case insensitive order: "+cities);
        cities.sort(Comparator.naturalOrder());
        System.out.println("cities sorted alphabetically using natural order: "+cities);

        System.out.println("------------------------------");

        sortUsingComparatorComparing(asList(
                new Movie("Lord of the rings"),
                new Movie("Back to the future"),
                new Movie("Carlito's way"),
                new Movie("Pulp fiction")));

        sortUsingComparatorComparingDouble(asList(
                new Movie("Lord of the rings", 8.8),
                new Movie("Back to the future", 8.5),
                new Movie("Carlito's way", 7.9),
                new Movie("Pulp fiction", 8.9)
        ));

        sortUsingCustomComparator(asList(
                new Movie("Lord of the rings", 8.8, true),
                new Movie("Back to the future", 8.5, false),
                new Movie("Carlito's way", 7.9, true),
                new Movie("Pulp fiction", 8.9, false)
        ));

        sortWithComparatorThenComparing(asList(
                new Movie("Lord of the rings", 8.8, true),
                new Movie("Back to the future", 8.5, false),
                new Movie("Carlito's way", 7.9, true),
                new Movie("Pulp fiction", 8.9, false)
        ));

        sortMaps();
    }

    private static void sortMaps() throws URISyntaxException {
        System.out.println("create a map of word counts keyed in by their length and sort it by their length");
        URL resource = SortingExamples.class.getResource("/words");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

            map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(e -> System.out.printf("Length %d: %2d words%n", e.getKey(), e.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------");
    }

    private static void sortWithComparatorThenComparing(List<Movie> movies) {
        System.out.println("sorting movies by chaining comparators first by starred movies and then by rating");
        movies.sort(Comparator.comparing(Movie::getStarred)
                .reversed()
                .thenComparing(Comparator.comparing(Movie::getRating)
                .reversed())
        );
        movies.forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void sortUsingCustomComparator(List<Movie> movies) {
        System.out.println("sorting movies by using a lambda as a custom comparator");
        movies.sort((m1, m2) -> {
            if(m1.getStarred() == m2.getStarred())
                return 0;
            return m1.getStarred() ? -1 : 1;
        });

        movies.forEach(System.out::println);

        System.out.println("\nsame result if we're using Comparator.comparing with a keyExtractor and a keyComparator");
        movies.sort(Comparator.comparing(Movie::getStarred, (star1, star2) -> {
            if(star1 == star2){
                return 0;
            }
            return star1 ? -1 : 1;
        }));
        movies.forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void sortUsingComparatorComparingDouble(List<Movie> movies) {
        System.out.println("sorting movies by using Comparator.comparingDouble by rating");
        movies.sort(Comparator.comparingDouble(Movie::getRating).reversed());
        movies.forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void sortUsingComparatorComparing(List<Movie> movies) {
        System.out.println("sorting movies by using Comparator.comparing by title");
        movies.sort(Comparator.comparing(Movie::getTitle));
        movies.forEach(System.out::println);
        System.out.println("------------------------------");
    }
}
