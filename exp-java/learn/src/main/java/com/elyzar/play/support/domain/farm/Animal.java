package com.elyzar.play.support.domain.farm;

public abstract class Animal {
    String name;
    public abstract void jump();
    public abstract void makeNoise();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
