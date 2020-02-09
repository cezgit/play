package com.wd.play.patterns.creational;


import com.wd.play.support.domain.common.Speakable;
import com.wd.play.support.domain.farm.Animal;
import com.wd.play.support.domain.farm.AnimalFactory;
import com.wd.play.support.domain.shape.Circle;
import com.wd.play.support.domain.shape.Shape;
import com.wd.play.support.domain.shape.ShapeFactoryFunctional;

import java.util.Optional;
import java.util.function.Supplier;

// factory -> supplier
public class FactoryMethodPatternDemo {

    public static void main(String[] args) {
        System.out.println("FactoryMethodPatternDemo");
        animalFactory();
        System.out.println("------------------------------------------------------");
        functionalShapeFactory();

        System.out.println("======================================================");
    }

    private static void functionalShapeFactory() {
        Supplier<ShapeFactoryFunctional> shapeFactory =  ShapeFactoryFunctional::new;
        //call draw method of circle
        Shape circle = shapeFactory.get().getShape("circle");
        circle.draw();
        ((Circle)circle).setRadius(1);
        circle.draw();
        Shape anotherCircle = shapeFactory.get().getShape("circle");
        anotherCircle.draw();

        //call draw method of Rectangle
        shapeFactory.get().getShape("rectangle").draw();
    }

    private static void animalFactory() {
        try {
            Animal dog = makeAnimal("dog", "sprocket");
            dog.jump();

            Animal cat = makeAnimal("cat", "lena");
            cat.jump();

            Animal rabbit = makeAnimal("rabbit", "yellowtail");
            rabbit.jump();
        } catch (NoAnimalException e) {
            System.out.println("------ Exception: "+e.getMessage());
        }
        System.out.println("======================================================");
    }

    private static Animal makeAnimal(String animalType, String name) throws NoAnimalException {

        Optional<Speakable> speakableAnimal = AnimalFactory.getSpeakableAnimal(animalType, name);
        System.out.println(speakableAnimal.isPresent() ? speakableAnimal.get().speak() : "mute");

        Optional<Animal> animal = AnimalFactory.getAnimal(animalType, name);
        return animal.orElseThrow(() -> new NoAnimalException(String.format("no animal of type %s exists", animalType)));
    }

    public static class NoAnimalException extends Exception {
        public NoAnimalException(String message) {
            super(message);
        }
    }
}
