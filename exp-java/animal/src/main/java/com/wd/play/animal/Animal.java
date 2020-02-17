package com.wd.play.animal;

import java.util.Optional;

public abstract class Animal {
    String name;
    Integer age;
    Optional<String> sound;

    Animal(AnimalBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sound = Optional.empty();
    }
    abstract AnimalType whatAmI();

    String getName() {
        return name;
    }

    Integer getAge() {
        return age;
    }

    protected void setSound(String sound) {
        this.sound = Optional.ofNullable(sound);
    }

    protected void mute() {
        setSound(null);
    }

    abstract void listen(Action action);

    enum Action {
        NORMAL,
        ANNOYED,
        CALLED
    }

    abstract static class AnimalBuilder {
        String name;
        Integer age;

        abstract Animal build();
    }
}
