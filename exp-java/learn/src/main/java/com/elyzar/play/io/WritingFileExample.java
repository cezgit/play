package com.elyzar.play.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

// see https://www.baeldung.com/java-write-to-file

public class WritingFileExample {
    public static void main(String[] args) throws IOException {

        writeFiletoResources();
    }

    private static void writeFiletoResources() throws IOException {
        String[] words = {"hello","refer","world","level"};
        File fileToWriteTo = new File("written-test.txt");
        FileWriter fw = new FileWriter(fileToWriteTo);
        try (PrintWriter pw = new PrintWriter(fw)) {
            Stream.of(words).forEach(pw::println);
        }
        System.out.println(String.format("File %s written", fileToWriteTo));
        fileToWriteTo.deleteOnExit();
    }
}
