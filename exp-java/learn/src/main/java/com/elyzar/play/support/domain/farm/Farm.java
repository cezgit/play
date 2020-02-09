package com.elyzar.play.support.domain.farm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Farm {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimals(Collection<? extends Animal> newAnimals) {
        animals.addAll(newAnimals);
    }

    public static void addDogs(List<? super Animal> list, Dog dog) {
        list.add(dog);
    }

    public static void addCats(List<? super Animal> list, Cat cat) {
        list.add(cat);
    }
}
