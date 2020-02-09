package com.wd.play.patterns.creational;

public class SingletonClass {

    private SingletonClass() {}

    /**
     * Since the initialization creates the static variable SINGLE_INSTANCE in a sequential way, all concurrent invocations of the getInstance()
     * will return the same correctly initialized SINGLE_INSTANCE without synchronization overhead.
     */
    private static class SingletonClassHolder {
        static final SingletonClass SINGLE_INSTANCE = new SingletonClass();
    }
    public static SingletonClass getInstance() {
        return SingletonClassHolder.SINGLE_INSTANCE;
    }

    @Override
    public String toString() {
        return "I'm a Singleton";
    }
}
