package com.wd.play.support.domain.shape;

import com.wd.play.support.domain.common.color.Color;

public class Circle extends Dot {

    private int radius;

    public Circle() {
        super();
        radius = 0;
    }

    public Circle(Color color) {
        super(color);
    }

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public String accept(ShapeVisitor visitor) {
        return visitor.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void applyColor() {
        System.out.print("Circle filled with color ");
        color.applyColor();
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle with radius: "+radius);
    }

    @Override
    public void resize() {
        System.out.println("Resizing Circle");
    }
    @Override
    public String description() {
        return "Circle object";
    }
    @Override
    public boolean isHide() {
        return false;
    }

    @Override
    public void move(int x, int y) {
        System.out.println("Moving circle...");
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
