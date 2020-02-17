package com.wd.play.patterns.structural;

import com.wd.play.io.*;
import com.wd.play.support.domain.shape.Circle;
import com.wd.play.support.domain.shape.Rectangle;
import com.wd.play.support.domain.shape.Shape;
import com.wd.play.support.domain.shape.ShapeRedDecorator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DecoratorPatternDemo {

    public static void main(String[] args) {

        System.out.println("DecoratorPatternDemo");

        shapeDecoratorDemo();
        System.out.println("------------------------------------------------------");
        dataEncryptionDecoratorDemo();
        System.out.println("======================================================");
    }

    private static void dataEncryptionDecoratorDemo() {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        String demoFileName = "OutputDemo.txt";

        FileDao fileDao = new FileDaoImpl(demoFileName);

        DataSourceDecorator encoded = new CompressionDecorator(new EncryptionDecorator(fileDao));
        encoded.writeData(salaryRecords);

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(fileDao.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());

        try {
            Files.deleteIfExists(Paths.get(demoFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void shapeDecoratorDemo() {
        Shape circle = new Circle();
        Shape redCircle = new ShapeRedDecorator(new Circle());
        Shape redRectangle = new ShapeRedDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
