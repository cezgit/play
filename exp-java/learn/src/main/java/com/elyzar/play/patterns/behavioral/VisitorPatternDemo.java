package com.elyzar.play.patterns.behavioral;

import com.elyzar.play.support.domain.shape.*;

public class VisitorPatternDemo {

    public static void main(String[] args) {

        System.out.println("VisitorPatternDemo");

        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape c1 = new CompoundShape(4);
        c1.add(dot);
        c1.add(circle);
        c1.add(rectangle);

        CompoundShape c2 = new CompoundShape(5);
        c2.add(dot);
        c1.add(c2);

        export(circle, c1);

        System.out.println("======================================================");
    }

    private static void export(Shape... shapes) {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        System.out.println(exportVisitor.export(shapes));
    }
}
