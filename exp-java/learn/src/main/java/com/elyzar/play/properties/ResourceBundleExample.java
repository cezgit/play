package com.elyzar.play.properties;

import java.util.Locale;
import java.util.ResourceBundle;

// https://www.baeldung.com/java-resourcebundle

public class ResourceBundleExample {

    public static void main(String[] args) {
        resourceBundle();
    }

    private static void resourceBundle() {

        Locale en_US = new Locale("en", "US");

        // file name must be: bundle_en_US.properties
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", en_US);

        System.out.println(bundle.getString("continueButton"));
        System.out.println(bundle.getString("cancelButton"));
        System.out.println(bundle.getString("helloLabel"));
    }
}
