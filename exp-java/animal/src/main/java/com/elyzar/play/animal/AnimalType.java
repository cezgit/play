package com.wd.play.animal;

import java.util.stream.Stream;

public enum AnimalType {

    CAT("cat"),
    DOG("dog"),
    NONE("");

    private String name;

    AnimalType(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static AnimalType fromName(String name) {
        return Stream.of(values())
                .filter(a -> a.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + name));
    }

}
