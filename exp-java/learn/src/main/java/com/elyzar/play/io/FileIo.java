package com.elyzar.play.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FileIo {
    public static void main(String[] args) throws IOException, InterruptedException {

        dirOperations();
        fileOperations();

        readLinesFromFile();                        // BufferedReader(FileReader).lines
        readLinesFromFilePath();                    // Files.lines(Paths.get)
        readLinesFromFileUrl();                     // Files.lines(Paths.get)

        processDirFilesAsStream();                  // Files.list(Paths.get)
        processDirDirsAsStream();                   // Files.walk(Paths.get)

        findDirFilesWithProp("fileio");    // Files.find(Paths.get, maxDepth, Matcher)

        monitorFileChanges();
    }

    static void dirOperations() throws IOException {

        // print all files in the current folder
        Files.list(Paths.get(".")).forEach(System.out::println);

        // print all folders in the current folder
        Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);

        // get all files from all folders one level deep
        List<File> files = Stream.of(new File(".").listFiles())
                .flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
                .collect(toList());

    }

    static void fileOperations() throws IOException {

        // create a new file and write some text to it - uses commons.io.FileUtils
        FileUtils.writeStringToFile(new File("words"), "some text", Charset.forName("UTF-8"));

        // find all hidden files
        final File[] files = new File(".").listFiles(File::isHidden);

        // check if a file exists - uses commons.io.FileUtils
        boolean fileExists = FileUtils.listFiles(new File("/tmp"), new NameFileFilter("fileName"), TrueFileFilter.INSTANCE).size() > 0;

        // checks if a file is older than some date - uses commons.io.FileUtils
        FileUtils.isFileOlder(new File("word"), LocalDate.now().minusDays(1).toEpochDay());

        // find all files ending with .java
        Files.newDirectoryStream(Paths.get("."), path -> path.toString().endsWith(".java")).forEach(System.out::println);

        // read file as a string from path
        String content = new String(Files.readAllBytes(Paths.get("duke.json")));

        // read a file as an input stream from resources - on a non-static class use this.getClass().getClassLoader()....
        InputStream is = FileIo.class.getClassLoader().getResourceAsStream("duke.json");

        // delete a file - surpasses exceptions if file doesn’t exist - uses commons.io.FileUtils
        FileUtils.deleteQuietly(new File("/tmp"));

    }

    static void readLinesFromFile() {
        try (Stream<String> lines = new BufferedReader(new FileReader("words")).lines()) {
            lines.limit(10).forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException ex) {
            System.out.println("file not found!");
        }
    }

    static void readLinesFromFilePath() {
        try (Stream<String> lines = Files.lines(Paths.get("/path/to/words"))) {
            lines.limit(10).forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException ex) {
            System.out.println("file not found!");
        }
    }

    static void readLinesFromFileUrl() {
        URL resource = ReadingFilesExamples.class.getResource("/words");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            lines.limit(10).forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    static void processDirFilesAsStream() {
        try (Stream<Path> files = Files.list(Paths.get("src/main/java"))) {
            files.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void processDirDirsAsStream() {
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void findDirFilesWithProp(String fileName) {
        try (Stream<Path> paths = Files.find(
                Paths.get("src/main/java"),
                Integer.MAX_VALUE, // max depth
                (path, attributes) -> !attributes.isDirectory() && path.toString().contains(fileName))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void monitorFileChanges() throws IOException, InterruptedException {

        // Watch for file changes in current directory and iterate through the events to get the details of the updates
        final Path path = Paths.get(".");

        final WatchService watchService = path.getFileSystem().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
        if(watchKey != null) {
            watchKey.pollEvents().stream().forEach(event -> System.out.println(event.context()));
        }
    }

}
