package com.elyzar.play.support.domain.common;

/**
 * Why clone method is Protected in Object class :- For the security reason, if any developer by mistaken implements the cloneable interface,
 * then even java does not allow to create the clone of the object outside the class hierarchy. If we want to access clone outside the class
 * hierarchy we need to provide our own implementation to provide the clone outside.
 * https://howtodoinjava.com/java/cloning/cloneable-interface-is-broken-in-java/
 */
public interface PrototypeCapable extends Cloneable {
    PrototypeCapable clone() throws CloneNotSupportedException;
}
