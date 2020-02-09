package com.elyzar.play.animal;

import java.util.ArrayList;
import java.util.List;

public class ActionObservable {

    private Animal.Action currentAction;

    private List<Animal> animals = new ArrayList<>();

    public void addListener(Animal animal) {
        this.animals.add(animal);
    }

    public void removeListener(Animal animal) {
        this.animals.remove(animal);
    }

    public void update(AnimalType animalType, Animal.Action action) {
        animals.stream()
                .filter(animal -> animal.whatAmI() == animalType)
                .forEach(animal -> animal.listen(action));
    }

    public void update(Animal.Action action) {
        animals.forEach(animal -> animal.listen(action));
    }

}
