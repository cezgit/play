package com.wd.play.animal;

import java.util.Optional;
import java.util.function.Consumer;

public class Cat extends Animal implements Speakable {

    private Integer lives;
    private TailState tailState;

    private Cat(Builder builder) {
        super(builder);
        this.lives = builder.lives;
        this.tailState = TailState.NOT_MOVING;
    }

    @Override
    public String makeSound() {
        return sound.orElse("");
    }

    @Override
    public AnimalType whatAmI() {
        return AnimalType.CAT;
    }

    @Override
    void listen(Action action) {
        switch (action) {
            case ANNOYED: {
                tailState = TailState.WAGGING;
                mute();
                break;
            }
            case CALLED: {
                tailState = TailState.NOT_MOVING;
                setSound("Meow");
                break;
            }
            default: {
                tailState = TailState.NOT_MOVING;
                mute();
                break;
            }
        }
    }

    public Integer getLives() {
        return lives;
    }

    public TailState tailAction() {
        return tailState;
    }

    @Override
    public String toString() {
        Optional<Integer> oLives = Optional.ofNullable(getLives());
        String rep = getName();
        if(oLives.isPresent()) {
            rep += String.format(" with %d lives", oLives.get());
        }
        if(sound.isPresent()) {
            rep += String.format(" says %s", makeSound());
        }
        if(tailState != TailState.NOT_MOVING) {
            if(sound.isPresent()) {
                rep += String.format(" and");
            }
            rep += String.format(" is %s its tail", tailAction());
        }
        return rep;
    }

    public static class Builder extends AnimalBuilder {
        public Integer lives;
        Builder with(Consumer<Builder> consumer) {
            consumer.accept(this);
            return this;
        }

        public Cat build() {
            return new Cat(this);
        }
    }

    enum TailState {
        WAGGING,
        NOT_MOVING
    }
}
