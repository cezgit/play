package com.elyzar.play.support.domain.shape;

import com.elyzar.play.support.domain.common.color.Color;

public abstract class ShapeAbstract implements Shape {

    //use composition to create a bridge between shape and color
    protected Color color;

    public ShapeAbstract() {
    }

    public ShapeAbstract(Color c){
        this.color = c;
    }

    public abstract void applyColor();
    public abstract void draw();
}
