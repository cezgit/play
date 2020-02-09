package com.elyzar.play.patterns.creational;

public interface AbstractFactory<T> {
    T create(String factoryType) ;
}
