package com.elyzar.play.javakot;

import com.elyzar.play.java.JavaService;
import com.elyzar.play.kotlin.KotlinService;

public class App {

    public static void main( String[] args ) {

        String language = args.length > 0 ? args[0] : "kotlin";
        switch (language) {
            case "java":
                new JavaService().sayHello();
                break;
            case "kotlin":
                new KotlinService().sayHello();
                break;
            default:
                // Do nothing
                break;
        }
    }
}
