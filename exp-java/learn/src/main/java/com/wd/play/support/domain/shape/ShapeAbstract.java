package com.wd.play.support.domain.shape;

import com.wd.play.support.domain.common.color.Color;

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
