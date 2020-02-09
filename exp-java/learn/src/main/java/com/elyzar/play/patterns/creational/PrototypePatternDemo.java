package com.wd.play.patterns.creational;

import com.wd.play.support.domain.media.PrototypeFactory;

public class PrototypePatternDemo {

    public static void main(String[] args) {

        System.out.println("PrototypePatternDemo");

        try {
            Cloneable movie = PrototypeFactory.getInstance(PrototypeFactory.ModelType.MOVIE);
            System.out.println(movie);

            Cloneable album  = PrototypeFactory.getInstance(PrototypeFactory.ModelType.ALBUM);
            System.out.println(album);

            Cloneable show  = PrototypeFactory.getInstance(PrototypeFactory.ModelType.SHOW);
            System.out.println(show);
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("======================================================");
    }
}
