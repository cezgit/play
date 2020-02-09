package com.wd.play.functional;

import com.wd.play.support.domain.common.CollectionSamples;
import com.wd.play.support.domain.farm.Animal;
import com.wd.play.support.domain.farm.Dog;
import com.wd.play.support.domain.fruits.Fruit;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VariousExamples {

    public static void main(String[] args) {

        methodReferenceVarargConstructor(List.of("Ricky Fluffy", "Bobby"));
        functionIdentityWithCollectors(CollectionSamples.listOfFruits());
    }

    private static void functionIdentityWithCollectors(List<Fruit> fruits) {
        System.out.println("Create a Map form a List of Fruit, keyed in by names");
        System.out.println(fruits.stream().collect(Collectors.toMap(Fruit::getName, f -> f)));
        System.out.println("------------------------------");
    }

    private static void methodReferenceVarargConstructor(List<String> names) {

        System.out.println("Method Reference: Calling a vararg constructor for names: "+names);
        List<Animal> animals = names.stream()
            .map(name -> name.split(" "))
            .map(Dog::new)
            .collect(Collectors.toList());
        animals.forEach(System.out::println);
        System.out.println("------------------------------");
    }




    private static List<Double> mapIt(List<Double> numbers, Function<Double, Double> fx) {
        return numbers.stream().map(fx).collect(Collectors.toList());
    }
}
