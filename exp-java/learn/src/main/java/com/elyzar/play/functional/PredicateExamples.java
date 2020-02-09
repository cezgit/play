package com.elyzar.play.functional;

import com.elyzar.play.support.domain.common.CollectionSamples;
import com.elyzar.play.support.domain.fruits.Fruit;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Predicate<T> | T -> boolean 	| test() |	IntPredicate, LongPredicate, DoublePredicate
public class PredicateExamples {
    public static void main(String[] args) {

        functionWithPredicate();
        composingFunctions();
        biPredicates();
    }

    private static void biPredicates() {

        System.out.println("BiPredicate examples");
        BiPredicate<Integer, Integer> bi = (x, y) -> x >= y;
        System.out.println("is 2 ge than 3? "+bi.test(2, 3));
        System.out.println("is 2 ge than 2? "+bi.test(2, 2));

        BiPredicate<Integer, String> condition = (i, s)-> i > 20 && s.startsWith("R");
        System.out.println("is 30 gt 20 and Ram starting with R? "+condition.test(30,"Ram"));
        System.out.println("------------------------------");
    }

    private static void composingFunctions() {

        System.out.println("Composing predicates...");

        List<Fruit> fruits = CollectionSamples.listOfFruits();

        Predicate<Fruit> redApple = a -> "red".equals(a.getColor());
        Predicate<Fruit> notRedApple = redApple.negate();
        Predicate<Fruit> redAndBig = redApple
                .and(a -> a.getWeight() > 150)
                .or(a -> a.getWeight() < 300);

        Integer notRedAppleCount = fruits.stream().filter(notRedApple).collect(Collectors.toList()).size();
        Integer redAndBigAppleCount = fruits.stream().filter(redAndBig).collect(Collectors.toList()).size();

        System.out.println("Count of not red apples: "+notRedAppleCount);
        System.out.println("Count of red and big apples: "+redAndBigAppleCount);
        System.out.println("------------------------------");
    }

    private static void functionWithPredicate() {

        System.out.println("Function returning a predicate using the first function argument (Currying)");

        String startingLetter = "N";
        final List<String> friends = List.of("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        System.out.println("Count all friends that start with "+startingLetter+ " in "+friends);
        final Function<String, Predicate<String>> startsWithLetter = letter -> (name -> name.startsWith(letter));
        final long friendCount = friends.stream().filter(startsWithLetter.apply("N")).count();
        System.out.println(String.format("Friend count starting with %s is %d", startingLetter, friendCount));

        System.out.println("------------------------------");
    }
}
