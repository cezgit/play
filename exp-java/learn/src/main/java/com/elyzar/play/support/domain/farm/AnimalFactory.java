package com.elyzar.play.support.domain.farm;

import com.elyzar.play.support.domain.common.Speakable;

import java.util.Optional;

public class AnimalFactory {

    public static Optional<Animal> getAnimal(String type, String name) {
        if(type.equals("dog"))
            return Optional.of(new Dog(name));
        else if (type.equals("cat"))
            return Optional.of(new Cat(name));
        else
            return Optional.empty();
    }

    public static Optional<Speakable> getSpeakableAnimal(String type, String name) {
        if(type.equals("dog"))
            return Optional.of(new Dog(name));
        else if (type.equals("cat"))
            return Optional.of(new Cat(name));
        else
            return Optional.empty();
    }
}
