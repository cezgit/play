package com.wd.play;

import com.wd.play.patterns.behavioral.*;
import com.wd.play.patterns.behavioral.memento.IteratorPatternDemo;
import com.wd.play.patterns.creational.*;
import com.wd.play.patterns.structural.*;

public class RunAllPatterns {

    public static void main(String[] args) {
        // patterns
        FactoryMethodPatternDemo.main(null);
        AbstractFactoryPatternDemo.main(null);
        BuilderPatternDemo.main(null);
        PrototypePatternDemo.main(null);
        SingletonPatternDemo.main(null);

        AdapterPatternDemo.main(null);
        DecoratorPatternDemo.main(null);
        FacadePatternDemo.main(null);
        ProxyPatternDemo.main(null);
        BridgePatternDemo.main(null);
        // FlyweightPatternDemo.main(null);
        CompositePatternDemo.main(null);

        StrategyPatternDemo.main(null);
        ObserverPatternDemo.main(null);
        CommandPatternDemo.main(null);
        MediatorPatternDemo.main(null);
        ChainOfResponsibilityPatternDemo.main(null);
        TemplateMethodPatternDemo.main(null);
        VisitorPatternDemo.main(null);
        MementoPatternDemo.main(null);
        // StatePatternDemo.main(null);
        IteratorPatternDemo.main(null);
    }
}
