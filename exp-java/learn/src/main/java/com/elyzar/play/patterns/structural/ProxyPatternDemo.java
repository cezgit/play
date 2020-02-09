package com.wd.play.patterns.structural;

import com.wd.play.support.domain.media.image.Image;
import com.wd.play.support.domain.media.image.ProxyImage;

public class ProxyPatternDemo {

    public static void main(String[] args) {

        System.out.println("ProxyPatternDemo");

        // Use the ProxyImage to get object of RealImage class when required
        Image image = new ProxyImage("test_10mb.jpg");

        //image will be loaded from disk
        image.display();
        System.out.println("");

        //image will not be loaded from disk
        image.display();

        System.out.println("======================================================");
    }
}
