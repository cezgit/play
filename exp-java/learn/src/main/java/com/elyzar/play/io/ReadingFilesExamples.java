package com.elyzar.play.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.baeldung.com/java-read-file

public class ReadingFilesExamples {
    public static void main(String[] args) throws URISyntaxException {

        readFileFromResources();
    }

    private static void readFileFromResources() throws URISyntaxException {
        URL resource = ReadingFilesExamples.class.getResource("/words");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {

            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
            System.out.println("collected words from a file in the resources folder: "+map);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------");
    }
}
