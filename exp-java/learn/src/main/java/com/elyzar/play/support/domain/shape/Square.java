package com.elyzar.play.support.domain.shape;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
    @Override
    public void resize() {
        System.out.println("Resizing Square");
    }
    @Override
    public String description() {
        return "Square object";
    }
    @Override
    public boolean isHide() {
        return false;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public String accept(ShapeVisitor visitor) {
        return null;
    }
}
