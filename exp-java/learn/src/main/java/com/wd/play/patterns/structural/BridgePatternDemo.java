package com.wd.play.patterns.structural;

import com.wd.play.support.domain.common.color.ColorGreen;
import com.wd.play.support.domain.common.color.ColorRed;
import com.wd.play.support.domain.device.*;
import com.wd.play.support.domain.shape.Circle;
import com.wd.play.support.domain.shape.Rectangle;
import com.wd.play.support.domain.shape.ShapeAbstract;

public class BridgePatternDemo {
    public static void main(String[] args) {

        System.out.println("BridgePatternDemo");
        bridgePatternWithShapes();
        System.out.println("------------------------------------------------------");
        bridgePatternWithDevices();
        System.out.println("======================================================");
    }

    private static void bridgePatternWithDevices() {
        // Remotes act as abstractions, and devices are their implementations.
        // Thanks to the common interfaces, same remotes can work with different devices and vice versa.
        // The Bridge pattern allows changing or even creating new classes without touching the code of the opposite hierarchy.
        testDevice(new Tv());
        testDevice(new Radio());
    }

    private static void bridgePatternWithShapes() {
        // use the bridge pattern to decouple the interfaces from the implementation - color and shape
        // you extract one of the dimensions into a separate class hierarchy (color), so that the original
        // classes will reference an object of the new hierarchy,
        // instead of having all of its state and behaviors within one class.

        ShapeAbstract circle = new Circle(new ColorRed());
        circle.applyColor();

        ShapeAbstract rectangle = new Rectangle(new ColorGreen());
        rectangle.applyColor();
    }

    private static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        basicRemote.volumeUp();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
