package com.elyzar.play.support.domain.shape;

public class ShapeFactoryProducer {

    public static ShapeAbstractFactory getFactory(boolean rounded){
        if(rounded){
            return new ShapeRoundedFactory();
        }else{
            return new ShapeFactory();
        }
    }
}
