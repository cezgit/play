package com.wd.play.patterns.creational;

public interface AbstractFactory<T> {
    T create(String factoryType) ;
}
