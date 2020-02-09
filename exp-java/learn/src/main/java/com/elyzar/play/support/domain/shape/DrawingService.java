package com.wd.play.support.domain.shape;

import java.util.ArrayList;
import java.util.List;

public class DrawingService {

    List<Shape> shapes = new ArrayList<>();
    public DrawingService() {
        super();
    }
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return new ArrayList<>(shapes);
    }

    public void draw() {
        if (shapes.isEmpty()) {
            System.out.println("Nothing to draw!");
        } else {
            shapes.stream().forEach(shape -> shape.draw());
        }
    }
    public void resize() {
        if (shapes.isEmpty()) {
            System.out.println("Nothing to resize!");
        } else {
            shapes.stream().forEach(shape -> shape.resize());
        }
    }
}
