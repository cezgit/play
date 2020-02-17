package com.wd.play.support.domain.shape;

import com.wd.play.support.domain.common.color.Color;

public class Rectangle extends ShapeAbstract implements Shape {

    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle() {
    }

    public Rectangle(Color c) {
        super(c);
    }

    public Rectangle(int id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String accept(ShapeVisitor visitor) {
        return visitor.visitRectangle(this);
    }

    @Override
    public void move(int x, int y) {
        // move shape
    }

    @Override
    public void applyColor() {
        System.out.print("Rectangle filled with color ");
        color.applyColor();
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
    @Override
    public void resize() {
        System.out.println("Resizing Rectangle");
    }
    @Override
    public String description() {
        return "Rectangle object";
    }
    @Override
    public boolean isHide() {
        return false;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

