package com.wd.play.support.domain.shape;

import com.wd.play.support.domain.common.color.Color;

public class Dot extends ShapeAbstract implements Shape {

    private int id;
    private int x;
    private int y;

    public Dot() {
    }

    public Dot(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {

    }

    public Dot(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
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

    public String accept(ShapeVisitor visitor) {
        return visitor.visitDot(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
