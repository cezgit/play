package com.elyzar.play.support.domain.farm;

import com.elyzar.play.support.domain.common.Speakable;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Dog extends Animal implements Speakable {
    public Dog() {}
    public Dog(String name) {
        this.name = name;
    }
    public Dog(String... names) {
        System.out.println("Creating new dog with names: "+asList(names));
        this.name = Arrays.stream(names).collect(Collectors.joining(","));
    }

    @Override
    public void jump() {
        System.out.println(String.format("%s jumped like a dog!", name));
    }

    @Override
    public void makeNoise() {
        System.out.println(String.format("%s says WOOOOF WOOOOF WOOOOF!", name));
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String speak() {
        return "Woooof";
    }
}
