package com.elyzar.play.support.domain.shape;

public class ShapeRoundedFactory implements ShapeAbstractFactory {
    @Override
    public Shape getShape(String shapeType) {
        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new RectangleRounded();
        }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new SquareRounded();
        }
        return null;
    }
}
