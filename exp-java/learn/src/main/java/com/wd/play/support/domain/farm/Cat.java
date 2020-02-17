package com.wd.play.support.domain.farm;

import com.wd.play.support.domain.common.Speakable;

public class Cat extends Animal implements Speakable {

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void jump() {
        System.out.println(String.format("%s jumped like a cat!", name));
    }

    @Override
    public void makeNoise() {
        System.out.println(String.format("%s says MEEEEAAAAOOOOOWWWW!", name));
    }

    @Override
    public String speak() {
        return "Meeeaaaw";
    }
}
