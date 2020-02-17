package com.wd.play.collections;

import com.wd.play.support.domain.blog.Article;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

// https://www.deadcoderising.com/2017-02-14-java-8-declarative-ways-of-modifying-a-map-using-compute-merge-and-replace/
public class MapOperations {

    public static void main(String[] args) {

        List<Article> javaList1 = List.of(new Article("java title2", "java contents"), new Article("java title1", "java contents"));
        List<Article> scalaList1 = List.of(new Article("scala title1", "scala contents"), new Article("scala title2", "scala contents"));
        List<Article> javaList2 = List.of(new Article("java title4", "java contents"), new Article("java title3", "java contents"));

        Map<String, List<Article>> categoryMap = Map.ofEntries(
            entry("java", javaList1),
            entry("scala", scalaList1)
        );

        modifyIfKeyPresent(categoryMap);
        addIfKeyNotPresent(javaList2, categoryMap);
        fetchIfKeyNotPresent(categoryMap);
        addOrMergeIfKeyPresentOrNot(javaList2, categoryMap);
        replaceValues(categoryMap);

    }

    private static void replaceValues(Map<String, List<Article>> categoryMap) {
        // replacing all values in a map
        categoryMap.replaceAll((key, val) -> getUpdatedListFor(key));
        /**
         * equivalent to:
         * for (String key : categoryMap.keys()) {
         *   categoryMap.put(key, getUpdatedListFor(key));
         * }
         */}

    private static void addOrMergeIfKeyPresentOrNot(List<Article> javaList2, Map<String, List<Article>> categoryMap) {
        // merging new data with existing data
        categoryMap.merge("java", javaList2, (list1, list2) ->
                Stream.of(list1, list2)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList()));
        /**
         * equivalent to:
         * if (categoryMap.containsKey("java")) {
         *   categoryMap.get("java").addAll(javaList2);
         * } else {
         *   categoryMap.put("java", javaList2);
         * }
         */}

    private static void fetchIfKeyNotPresent(Map<String, List<Article>> categoryMap) {
        // specify how to obtain the value for the key, if it’s not in the collection yet.
        // if the given key exists in the map already, you get the corresponding value back.
        // if not, the mapping function is executed and the resulting values is put into the map and returned to you. That’s more or less how caches work.
        categoryMap.computeIfAbsent("java", MapOperations::heavyOperationToFetchArticles);
    }

    private static void addIfKeyNotPresent(List<Article> javaList2, Map<String, List<Article>> categoryMap) {
        // add data to a map only if key does not exist
        categoryMap.putIfAbsent("java", javaList2);
    }

    private static void modifyIfKeyPresent(Map<String, List<Article>> categoryMap) {
        // only modify if key already exists in the map
        categoryMap.computeIfPresent("java", (key, value) -> sortAlphabetically(value));
        /**
         * equivalent to:
         * if (categoryMap.containsKey("java")) {
         *   categoryMap.put("Java", sortAlphabetically(categoryMap.get("java")));
         * }
         */}

    private static List<Article> getUpdatedListFor(String key) {
        return new ArrayList<>();
    }

    private static List<Article> heavyOperationToFetchArticles(String s) {
        return new ArrayList<>();
    }

    private static List<Article> sortAlphabetically(List<Article> articles) {
        articles.sort(Comparator.comparing(Article::getTitle));
        return articles;
    }
}
