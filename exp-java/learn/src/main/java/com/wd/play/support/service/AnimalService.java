package com.wd.play.support.service;

import com.wd.play.support.domain.farm.Animal;
import com.wd.play.support.domain.farm.Dog;
import com.wd.play.support.util.GenericBuilder;

public class AnimalService {

    public static Animal getAnimal(Integer animalId) {
        System.out.println("Looking for animal with animalId: "+animalId);
        Dog otto = GenericBuilder.of(Dog::new).with(Dog::setName, "Otto").build();
        System.out.println("Found one...");
        return otto;
    }

    public static Animal getAnimal(String name) {
        return new Dog();
    }
}
