package com.wd.play.patterns.creational;

public class SingletonPatternDemo {
    public static void main(String[] args) {

        System.out.println("SingletonPatternDemo");

        SingletonClass sc = SingletonClass.getInstance();
        System.out.println(sc);
        System.out.println("======================================================");
    }
}
