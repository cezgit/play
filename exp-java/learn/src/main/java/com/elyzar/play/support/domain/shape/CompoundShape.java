package com.wd.play.support.domain.shape;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {

    public int id;
    public List<Shape> children = new ArrayList<>();

    public CompoundShape(int id) {
        this.id = id;
    }

    @Override
    public void move(int x, int y) {
        // move shape
    }

    @Override
    public void draw() {
        // draw shape
    }

    @Override
    public void resize() {

    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public boolean isHide() {
        return false;
    }

    public int getId() {
        return id;
    }

    @Override
    public String accept(ShapeVisitor visitor) {
        return visitor.visitCompoundGraphic(this);
    }

    public void add(Shape shape) {
        children.add(shape);
    }
}
