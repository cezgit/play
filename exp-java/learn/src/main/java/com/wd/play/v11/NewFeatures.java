package com.wd.play.v11;

public class NewFeatures {
    public static void main(String[] args) {


        System.out.println("String.repeat - repeat string N times: "+"foo".repeat(3));
        System.out.println("------------------------------");
        System.out.println("String.lines - create stream from lines:");
        "a\nb\n".lines().forEach(System.out::println);
        System.out.println("------------------------------");

        // strip, stripTrailing
        System.out.println("String.stripLeading: "+" foo".stripLeading());
        System.out.println("------------------------------");

        System.out.println("String.isBlank: "+"    ".isBlank());

    }
}
