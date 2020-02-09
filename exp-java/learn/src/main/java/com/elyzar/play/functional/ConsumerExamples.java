package com.elyzar.play.functional;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// Consumer<T> | T->void | accept() | IntConsumer, LongConsumer, DoubleConsumer
public class ConsumerExamples {

    public static void main(String[] args) {

        consumerExample("consume me");
        consumerAcceptingAList(List.of(1,2,3));
        biConsumerExample(5, " Chapters");
        biConsumerMathExampleUsingAndThen(10, 6);
    }

    private static void consumerAcceptingAList(List<Integer> ints) {
        System.out.println(String.format("consume a list of ints %s printing each int multiplied by 2", ints));
        Consumer<List<Integer>> listDoubler = list -> list.stream().forEach(i -> { System.out.println(i * 2); });
        listDoubler.accept(ints);
        System.out.println("------------------------------");
    }

    private static void consumerExample(String consumable) {
        System.out.println("Consumer: invoking accept");
        Consumer<String> c = (String t) -> System.out.println(t);
        c.accept(consumable);
        System.out.println("------------------------------");
    }

    private static void biConsumerMathExampleUsingAndThen(int i, int j) {
        System.out.println(String.format("consuming 2 integers and using andThen:", i, j));
        BiConsumer<Integer, Integer> addition = (a, b) -> {
            System.out.println(String.format("params are: %d and %d", a, b));
            System.out.println(String.format("addition result: %d", a + b));
        };
        BiConsumer<Integer, Integer> subtraction = (a, b) -> {
            System.out.println(String.format("params are: %d and %d", a, b));
            System.out.println(String.format("subtraction result: %d", a - b));
        };
        System.out.println(String.format("Invoking addition andThen subtraction on %d and %d", i, j));
        addition.andThen(subtraction).accept(i, j);
        System.out.println("------------------------------");
    }

    private static void biConsumerExample(int i, String s) {
        System.out.println(String.format("consuming an int: %d and a String: %s to form another string:", i, s));
        BiConsumer<Integer, String> consumer = (a, b) -> System.out.println(a + b);
        consumer.accept(i, s);
        System.out.println("------------------------------");
    }
}


