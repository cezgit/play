package com.elyzar.play.animal;

import java.util.function.Consumer;

public class Dog extends Animal implements Speakable {

    private Dog(Builder builder) {
        super(builder);
    }

    @Override
    public String makeSound() {
        return sound.orElse("");
    }

    @Override
    public AnimalType whatAmI() {
        return AnimalType.DOG;
    }

    @Override
    public void listen(Action action) {
        switch (action) {
            case ANNOYED:  {
                setSound("Growl");
                break;
            }
            case CALLED: {
                setSound("Bark");
                break;
            }
            default: {
                mute();
                break;
            }
        }
    }

    @Override
    public String toString() {

        String rep = getName();
        if(sound.isPresent()) {
            rep += String.format(" says %s", makeSound());
        }
        return rep;
    }

    public static class Builder extends AnimalBuilder {
        public Dog.Builder with(Consumer<Dog.Builder> consumer) {
            consumer.accept(this);
            return this;
        }

        public Dog build() {
            return new Dog(this);
        }
    }
}
