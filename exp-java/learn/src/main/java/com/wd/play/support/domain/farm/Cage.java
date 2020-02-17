package com.wd.play.support.domain.farm;

import java.util.ArrayList;
import java.util.List;

public class Cage<T extends Animal> {
    List<T> animals = new ArrayList<>();
    public void addAnimal(T animal) {
        animals.add(animal);
    }

    public void firstAnimalJump() {
        T animal = animals.get(0);
        animal.jump();
    }
}
