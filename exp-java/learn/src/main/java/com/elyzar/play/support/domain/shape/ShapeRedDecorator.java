package com.elyzar.play.support.domain.shape;

public class ShapeRedDecorator extends ShapeDecorator {

    public ShapeRedDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
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

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
