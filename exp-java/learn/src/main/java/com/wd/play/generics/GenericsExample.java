package com.wd.play.generics;

import com.wd.play.support.domain.farm.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenericsExample {
    public static void main(String[] args) {

        // List.of(...) returns an immutable list - you can't add more elements to it

        parameterizedTypeSimple();
        upperBoundedWildcardExample(List.of(4,5,6,7));
        unboundedWildcardExample(List.of("one", "three"));
        unboundedWildcardExample(List.of(1, "three", false));

        List<Integer> ints = new ArrayList<>();
        ints.add(5);
        lowerBoundedWildcardExample(4, ints);

        lowerBoundedWildcardExample(4, new ArrayList<Number>());
        lowerBoundedWildcardExample(4, new ArrayList<Object>());

        functionalLowerBoundedExample(Stream.of(1, 5.1, 6));

        functionalLowerAndUpperBoundedExample(3.5);

        genericAnimals();
    }

    // simple parameterized type example
    static void parameterizedTypeSimple() {
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");
        // strings.add(new Date());    // Won’t compile
        // Integer i = strings.get(0); // for-each loop knows the contained data type is String
        for (String s : strings) {
            System.out.printf("%s has length %d%n", s, s.length());
        }
    }

    static void unboundedWildcardExample(Collection<?> things) {

        // containsAll has all the methods that it needs.

        Collection<?> allThings = List.of("one", "two", "three");
        System.out.println("allThings contains all of things? "+allThings.containsAll(things));

        allThings = List.of(1, "three", false);
        System.out.println("stringThings contains all of things? "+allThings.containsAll(things));

        // things.add("four"); // won't compile
    }

    static void upperBoundedWildcardExample(List<? extends Number> numbers) {
        numbers.forEach(n ->{
            Number currentVal = n;
            if(currentVal.intValue() > 5)
                System.out.println("this is greater than 5: "+n);
        });

        // numbers.add(4); // won't compile - you can't add things to an upper bounded wildcard list
    }

    static void lowerBoundedWildcardExample(Integer num, List<? super Integer> list) {

        // list can be a collection of Integers, Numbers or Objects
        if(!list.isEmpty()) {
            System.out.println(String.format("list starts with %d elements", list.size()));
            // you can get values out of the list
            System.out.println(String.format("first element is %d", list.get(0)));
        }

        // you can also add values to the list
        IntStream.rangeClosed(1, num).forEach(list::add);
        System.out.println(String.format("list has %d elements", list.size()));
    }

    static void functionalLowerBoundedExample(Stream stream) {
        Predicate<Number> predicate = n -> n.doubleValue() > 5;
        System.out.println("Printing values greater than 5 using a Predicate<Number> generic");
        stream.filter(predicate).forEach(System.out::println);
    }

    static void functionalLowerAndUpperBoundedExample(Number n) {
        Function<? super Double, ? extends Number> add1 = x -> x.doubleValue() + 1;
        System.out.println(String.format("adding 1 to %f is %f", n, add1.apply(n.doubleValue())));
    }

    static void genericAnimals() {
        Cage<Animal> catCage = new Cage<>();
        Cat missy = new Cat("missy"), tom = new Cat("tom");
        Dog wimpy = new Dog("wimpy"), aries = new Dog("aries");

        catCage.addAnimal(wimpy);
        catCage.addAnimal(missy);
        catCage.firstAnimalJump();

        Farm farm = new Farm();
        List<Cat> cats = List.of(tom);
        List<Dog> dogs = List.of(aries);
        farm.addAnimals(cats);
        farm.addAnimals(dogs);

        List<Object> furryAnimals = new ArrayList<>();
        farm.addDogs(furryAnimals, wimpy);
        farm.addCats(furryAnimals, missy);

        List<Animal> animals = List.of(missy, wimpy);
        makeLotsOfNoise(animals);
    }

    public static void makeLotsOfNoise(List<? extends Animal> animals) {
        animals.forEach(Animal::makeNoise);
    }

}


