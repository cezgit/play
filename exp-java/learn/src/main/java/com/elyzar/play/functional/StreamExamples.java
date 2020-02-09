package com.wd.play.functional;

import com.wd.play.support.domain.common.CollectionSamples;
import com.wd.play.support.domain.company.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args) {

        createStreams();
        concatenateStreams();
        streamConversions();
        streamReducingOperations();
        parallelStreams();
        infiniteStrams();
        streamVariousOperation();
    }

    private static void infiniteStrams() {

        System.out.println("Infinite Streams");
        System.out.println("Stream.generate with limit of 3");
        Stream.generate(Math::random)
            .limit(3)
            .forEach(System.out::println);

        Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);
        List<Integer> list = evenNumStream
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("\nStream.iterate with limit of 3: "+list);

        System.out.println("------------------------------");
    }

    private static void parallelStreams() {
        System.out.println("Stream.parallel");

        Employee[] arrayOfEmps = {
                new Employee("1", "Jeff Bezos", 100000.0),
                new Employee("2", "Bill Gates", 200000.0),
                new Employee("3", "Mark Zuckerberg", 300000.0)
        };
        List<Employee> empList = Arrays.asList(arrayOfEmps);
        empList.stream().parallel().forEach(e -> e.salaryIncrement(10000.0));
        System.out.println("------------------------------");
    }

    private static void concatenateStreams() {
        System.out.println("Stream.concat - concatenating multiple streams");
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("F", "G", "H");
        Stream.concat(Stream.concat(first, second), third).collect(Collectors.toList());

        System.out.println("------------------------------");
    }

    private static void streamVariousOperation() {

        System.out.println("Stream.peak - Using multiple peek methods");

        IntStream.rangeClosed(1, 5)
            .peek(n -> System.out.printf("original: %d%n", n)) // Print value before doubling
            .map(n -> n * 2)
            .peek(n -> System.out.printf("doubled : %d%n", n)) // Print value after doubling but before filtering
            .filter(n -> n % 3 == 0)
            .peek(n -> System.out.printf("filtered: %d%n", n)) // Print value after filtering but before summing
            .sum();

        System.out.println("\nStream.flatMap - flatten a stream of Customers, each Customer having multiple Orders");
        CollectionSamples.listOfCustomersWithOrders().stream()
            .flatMap(customer -> customer.getOrders().stream())
            .forEach(System.out::println); // Order{id=1}, Order{id=2}, Order{id=3}, Order{id=4}, Order{id=5}

        System.out.println("------------------------------");
    }

    private static void streamReducingOperations() {

        String[] strings = "this is an array of strings".split(" ");

        System.out.println("Stream.count");
        long count = Arrays.stream(strings).map(String::length).count();

        System.out.println("Stream.sum");
        int totalLength = Arrays.stream(strings).mapToInt(String::length).sum();

        System.out.println("Stream.average");
        OptionalDouble ave = Arrays.stream(strings).mapToInt(String::length).average();

        System.out.println("Stream.max and Stream.min without Comparator only on primitive streams");
        OptionalInt max = Arrays.stream(strings).mapToInt(String::length).max();
        OptionalInt min = Arrays.stream(strings).mapToInt(String::length).min();

        System.out.println("Stream.reduce - to sum stream elements 1 to 5: "+Stream.iterate(1L, i -> i + 1).limit(5).parallel().reduce(0L, Long::sum));
        System.out.println("LongStream.sum - to sum stream elements 1 to 5: "+LongStream.rangeClosed(1, 5).parallel().sum());

        System.out.println("\nThere are two overloaded versions of the reduce method in IntStream:");

        int sum = IntStream.rangeClosed(1, 10).reduce((x, y) -> x + y).orElse(0); // 55
        System.out.println("Stream.reduce - provide a binary operator - add all numbers from 1 to 10: "+sum+"\n");

        int doubleSum = IntStream.rangeClosed(1, 10).reduce(0, (x, y) -> {
            System.out.println(String.format("x(initially 0 and then result of prev op): %d; y(element): %d, result: %d", x, y, x+2*y));
            return x + 2 * y;
        }); // 110
        System.out.println("\nStream.reduce - provide initial value (identity) and a binary operator (op) - add all numbers (doubled) from 1 to 10: "+doubleSum);
        System.out.println("In the lambda expression, you can think of the first argument of the binary operator as an accumulator, and the second argument as the value of each element in the stream");
        System.out.println("This is really doing: result = accumulator.applyAsInt(result, element)\n");

        int sum2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(0, Integer::sum);
        System.out.println("Reduce with a binary operator - sum all numbers from 1 to 10: "+sum2);

        Integer max2 = Stream.of(3, 1, 4, 1, 5, 9).reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("Reduce with a binary operator - max number from a stream of ints: "+max2);
        System.out.println("It's the equivalent of using the Stream.max function with a Comparator: "+Stream.of(3, 1, 4, 1, 5, 9).max(Comparator.naturalOrder())+"\n");
        System.out.println("------------------------------");
    }

    private static void createStreams() {

        System.out.println("Create a Stream using IntStream.of and boxed");
        IntStream.of(3, 1, 4)
                .boxed()
                .forEach(System.out::println);
        System.out.println("------------------------------");

        System.out.println("Create a Stream using IntStream.of and mapToObj to convert each element from a primitive to an instance of the wrapper class");
        IntStream.of(3, 1, 4)
                .mapToObj(Integer::valueOf)
                .forEach(System.out::println);
        System.out.println("------------------------------");

        System.out.println("Create a Stream using Stream.generate");
        Stream.generate(Math::random)
                .limit(3)
                .forEach(System.out::println);
        System.out.println("------------------------------");

        System.out.println("Create a Stream using Stream.iterate and print the values");
        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L))
                .limit(3)
                .forEach(System.out::println);
        System.out.println("------------------------------");

        System.out.println("Create a Stream using Stream.iterate and then collect the values to a List");
        List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE) )
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(nums);
        System.out.println("------------------------------");
    }

    private static void streamConversions() {

        System.out.println("Convert a Stream to a String - Collectors.joining - concatenating a list");
        String names = Stream.of("Gomez", "Morticia", "Wednesday", "Pugsley").collect(Collectors.joining(","));

        System.out.println("Convert a Stream to a List - Collectors.toList");
        List<String> strings = Stream.of("this", "is", "a", "list", "of", "strings").collect(Collectors.toList());

        System.out.println("Convert a Stream to an Array - IntStream.of and toArray");
        int[] intArray = IntStream.of(3, 1, 4).toArray();
        // int[] intArray = IntStream.of(3, 1, 4, 1, 5, 9).toArray(int[]::new);

        System.out.println("Convert an array to a String - Arrays.stream and Collectors.joining");
        String[] munsters = { "Herman", "Lily", "Eddie", "Marilyn", "Grandpa" };
        names = Arrays.stream(munsters).collect(Collectors.joining(","));


        System.out.println("------------------------------");
    }
}
