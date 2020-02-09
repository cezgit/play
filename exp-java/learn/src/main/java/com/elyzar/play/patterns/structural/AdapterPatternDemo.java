package com.wd.play.patterns.structural;

import com.wd.play.support.domain.media.mediaplayer.MediaPlayer;
import com.wd.play.support.domain.media.mediaplayer.Playable;
import com.wd.play.support.domain.shape.*;

/**
 * clients call your program using an established interface
 * you want to add similar exiting functionality to your program but this new code is accessed through a different interface
 * you don't want to make changes to the established interface so you write an adapter which uses the new code without
 * making any changes to the interface that the client is already using
 */
public class AdapterPatternDemo {

    public static void main(String[] args) {

        System.out.println("AdapterPatternDemo");
        mediaAdapterDemo();
        System.out.println("------------------------------------------------------");
        shapeAdapterDemo();
        System.out.println("======================================================");
    }

    private static void mediaAdapterDemo() {
        Playable mediaPlayer = new MediaPlayer();
        mediaPlayer.play("mp3", "Go West.mp3");
        mediaPlayer.play("mp4", "Purple Rain.mp4");
    }

    private static void shapeAdapterDemo() {
        System.out.println("Creating drawing of shapes...");
        DrawingService drawingService = new DrawingService();
        drawingService.addShape(new Rectangle());
        drawingService.addShape(new Circle());

        System.out.println("Drawing...");
        drawingService.draw();
        System.out.println("Resizing...");
        drawingService.resize();

        // object adapter pattern
        System.out.println("Use the object adapter to also draw the new Geometric shapes");
        System.out.println("Creating drawing of shapes...");
        drawingService.addShape(new GeometricShapeObjectAdapter(new Triangle()));
        drawingService.addShape(new GeometricShapeObjectAdapter(new Rhombus()));
        System.out.println("Drawing...");
        drawingService.draw();
        System.out.println("Resizing...");
        drawingService.resize();

        // class adapter pattern
        System.out.println("Use the class adapter to also draw the adapted shapes");
        System.out.println("Creating drawing of shapes...");
        drawingService.addShape(new TriangleAdapter());
        drawingService.addShape(new RhombusAdapter());
        System.out.println("Drawing...");
        drawingService.draw();
        System.out.println("Resizing...");
        drawingService.resize();
    }
}
