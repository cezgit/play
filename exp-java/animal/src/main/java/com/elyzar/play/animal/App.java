package com.elyzar.play.animal;


import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

// http://gitlab.cj.com/cjdev/animal-exercise

/**
 * You are tasked with writing the logic for our animal simulator application. Animals react to different situations by changing their state, making sounds, or both. Sounds made by an animal are detected by a sound recorder who's implementation is out of scope for this exercise. You will still have to test that the correct sounds are being recorded, so use a fake, stub, or mock for the sound recorder.
 *
 * Given a bunch of animals (for example, cats and dogs).
 * When something happens to those animals (for example, they see someone, are annoyed, or someone pets them).
 * Then ensure that the sound detector hears the appropriate sounds and each animal has its state updated.
 * Requirements
 * Release 1, how animals behave when they see someone
 *
 * When a cat sees someone, it makes a "meow" sound
 * When a dog sees someone, it makes a "bark" sound
 * Multiple animals see someone
 * Given several animals
 * When they see someone
 * Then the appropriate sounds are recorded
 *
 * Release 2, how animals behave when they are annoyed
 *
 * When a cat is annoyed, its wagging tail state becomes true
 * When a dog is annoyed, it makes a "growl" sound
 * Multiple animals are annoyed
 * Given several animals
 * When they are annoyed
 * Then the appropriate sounds are recorded and states are changed
 *
 * Release 3, how animals behave when someone pets them
 *
 * When someone pets a cat, its purring state becomes true and it makes a "purr" sound
 * When someone pets a dog, it tail wagging state becomes true
 * Multiple animals are pet by someone
 * Given several animals
 * When they are pet by someone
 * Then the appropriate sounds are recorded and states are changed
 */

public class App {
    public static void main( String[] args ){

        List<Animal> animals = List.of(
                new Cat.Builder().with(cat -> {
                    cat.name = "Kitty";
                    cat.lives = 5;
                    cat.age = 4;
                }).build(),
                new Cat.Builder().with(cat -> cat.name = "Eli").build(),
                new Dog.Builder().with(dog -> dog.name = "Doggy").build(),
                new Dog.Builder().with(dog -> dog.name = "Ricki").build()
        );

        ActionObservable actionObservable = new ActionObservable();
        animals.forEach(animal -> actionObservable.addListener(animal));

        Scanner userInput = new Scanner(System.in);
        while(true) {
            System.out.println("What do you wanna say?");

            String input = userInput.nextLine();

            if (!input.isEmpty()) {
                String[] inputArgs = input.split(" ");
                if(inputArgs.length == 1) {
                    if(inputArgs[0].equalsIgnoreCase("bye"))
                        System.exit(1);
                    else if(inputArgs[0].equalsIgnoreCase("hello")) {
                        actionObservable.update(Animal.Action.CALLED);
                        animals.stream().forEach(System.out::println);
                    }
                    else if(inputArgs[0].equalsIgnoreCase("boo")) {
                        actionObservable.update(Animal.Action.ANNOYED);
                        animals.stream().forEach(System.out::println);
                    }
                    else {
                        System.out.println("not sure what you mean...");
                    }

                }
                else if(inputArgs.length == 2) {
                    Function<AnimalType, Predicate<Animal>> byAnimalType = animalType -> animal -> animal.whatAmI() == animalType;
                    AnimalType animalType;
                    if(inputArgs[0].equalsIgnoreCase("hello")) {
                        animalType = getAnimalType(inputArgs[1]);
                        actionObservable.update(animalType, Animal.Action.CALLED);
                        animals.stream().filter(byAnimalType.apply(animalType)).forEach(System.out::println);
                    }
                    else if(inputArgs[0].equalsIgnoreCase("boo")) {
                        animalType = getAnimalType(inputArgs[1]);
                        actionObservable.update(animalType, Animal.Action.ANNOYED);
                        animals.stream().filter(byAnimalType.apply(animalType)).forEach(System.out::println);
                    }
                }
            }
        }
    }

    private static AnimalType getAnimalType(String animalId) {
        AnimalType animalType = AnimalType.NONE;
        try {
            animalType = AnimalType.fromName(animalId);
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("There's no %s around here...", animalId));
        }
        return animalType;
    }
}
