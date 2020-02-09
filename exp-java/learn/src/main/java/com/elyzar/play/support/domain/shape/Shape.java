package com.elyzar.play.support.domain.shape;

public interface Shape {
    void draw();
    void resize();
    String description();
    boolean isHide();

    void move(int x, int y);
    String accept(ShapeVisitor visitor);
}
