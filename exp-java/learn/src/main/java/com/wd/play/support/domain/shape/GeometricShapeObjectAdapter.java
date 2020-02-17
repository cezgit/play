package com.wd.play.support.domain.shape;

/**
 * Object adapters use the composition and can wrap classes as well as interfaces.
 * It contains a reference to the class or interfaces object instance.
 * The object adapter is the easier one and can be applied in most of the scenarios.
 */
public class GeometricShapeObjectAdapter implements Shape {

    private GeometricShape adaptee;

    public GeometricShapeObjectAdapter(GeometricShape adaptee) {
        super();
        this.adaptee = adaptee;
    }
    @Override
    public void draw() {
        adaptee.drawShape();
    }
    @Override

    public void resize() {
        System.out.println(description() + " can't be resized. Please create new one with required values.");
    }
    @Override
    public String description() {
        if (adaptee instanceof Triangle) {
            return "Triangle object";
        } else if (adaptee instanceof Rhombus) {
            return "Rhombus object";
        } else {
            return "Unknown object";
        }
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
