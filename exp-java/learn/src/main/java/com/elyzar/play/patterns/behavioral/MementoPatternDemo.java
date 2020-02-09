package com.elyzar.play.patterns.behavioral;

import com.elyzar.play.patterns.behavioral.memento.CareTaker;
import com.elyzar.play.patterns.behavioral.memento.Originator;

public class MementoPatternDemo {

    public static void main(String[] args) {

        System.out.println("MementoPatternDemo");
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());

        System.out.println("======================================================");
    }
}
