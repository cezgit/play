package com.elyzar.play.patterns.creational;

import com.elyzar.play.support.domain.shape.Shape;
import com.elyzar.play.support.domain.shape.ShapeAbstractFactory;
import com.elyzar.play.support.domain.shape.ShapeFactoryProducer;

public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        System.out.println("AbstractFactoryPatternDemo");

        //get rounded shape factory
        ShapeAbstractFactory shapeFactory = ShapeFactoryProducer.getFactory(false);
        //get an object of Shape Rounded Rectangle
        Shape shape1 = shapeFactory.getShape("RECTANGLE");
        //call draw method of Shape Rectangle
        shape1.draw();
        //get an object of Shape Rounded Square
        Shape shape2 = shapeFactory.getShape("SQUARE");
        //call draw method of Shape Square
        shape2.draw();
        //get rounded shape factory
        ShapeAbstractFactory shapeFactory1 = ShapeFactoryProducer.getFactory(true);
        //get an object of Shape Rectangle
        Shape shape3 = shapeFactory1.getShape("RECTANGLE");
        //call draw method of Shape Rectangle
        shape3.draw();
        //get an object of Shape Square
        Shape shape4 = shapeFactory1.getShape("SQUARE");
        //call draw method of Shape Square
        shape4.draw();
        System.out.println("======================================================");
    }
}
