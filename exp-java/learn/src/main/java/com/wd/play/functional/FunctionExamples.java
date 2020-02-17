package com.wd.play.functional;

import com.wd.play.support.domain.common.CollectionSamples;
import com.wd.play.support.domain.fruits.Fruit;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

// Function<T, R>       | T -> R        | apply() | IntFunction<R>, IntToDoubleFunction, IntToLongFunction
// BiFunction<T, U, R>  | (T, U) -> R 	| apply() | ToIntBiFunction<T,U>
public class FunctionExamples {
    public static void main(String[] args) {

        functionTransformsElementsOfAStream();
        functionForStreamFilter();
        biFunctionCalculator(3, 5);
    }

    private static void biFunctionCalculator(Integer i1, Integer i2) {

        System.out.println(String.format("A bifunction adder that takes 2 ints: %d and %d and, returns a string representing their sum", i1, i2));
        BiFunction<Integer, Integer, String> adder = (a, b) -> ": " + (a + b);
        System.out.println("Sum is: "+adder.apply(i1, i2));
        System.out.println("------------------------------");
    }

    private static void functionForStreamFilter() {

        System.out.println("Use functions to sort elements of a stream");
        List<Fruit> fruits = CollectionSamples.listOfFruits();
        final Function<Fruit, String> byColor = fruit -> fruit.getColor();
        final Function<Fruit, Integer> byWeight = fruit -> fruit.getWeight();
        System.out.println(String.format("Sorting fruits %s by color and weight", fruits));
        List<Fruit> sortedFruits = fruits.stream().sorted(Comparator.comparing(byColor).thenComparing(byWeight)).collect(toList());
        System.out.println("Sorted fruits: "+sortedFruits);
        System.out.println("------------------------------");
    }

    private static void functionTransformsElementsOfAStream() {
        System.out.println("Use a function to transform each element of a stream");
        Function<Object, String> f = (Object t) -> {
            System.out.println("Converting object to string...");
            return t.toString();
        };

        List<Object> data = List.of(2l, "peach", Instant.now());
        Consumer<String> c = (String t) -> System.out.println("Consuming string: "+t);
        data.stream().map(f).forEach(c);

        System.out.println("------------------------------");
    }
}
