package com.wd.play.support.domain.shape;

public class SquareRounded implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside RoundedSquare::draw() method.");
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

    @Override
    public void move(int x, int y) {

    }

    @Override
    public String accept(ShapeVisitor visitor) {
        return null;
    }
}
