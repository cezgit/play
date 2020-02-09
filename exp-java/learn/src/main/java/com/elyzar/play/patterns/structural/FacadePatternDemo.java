package com.wd.play.patterns.structural;

import com.wd.play.support.domain.shape.ShapeMakerFacade;

public class FacadePatternDemo {

    public static void main(String[] args) {
        System.out.println("FacadePatternDemo");
        ShapeMakerFacade shapeMaker = new ShapeMakerFacade();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();

        System.out.println("======================================================");
    }
}
