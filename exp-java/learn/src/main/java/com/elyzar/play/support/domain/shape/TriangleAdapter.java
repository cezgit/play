package com.elyzar.play.support.domain.shape;

/**
 * Class adapters use inheritance and can wrap a class only.
 * I can't wrap an interface since, by definition, it must be derived from some base class.
 */
public class TriangleAdapter extends Triangle implements Shape {

    public TriangleAdapter() {
        super();
    }
    @Override
    public void draw() {
        this.drawShape();
    }
    @Override
    public void resize() {
        System.out.println("Triangle can't be resized. Please create new one with required values.");
    }
    @Override
    public String description() {
        return "Triangle object";
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
