package com.wd.play.properties;

import java.io.IOException;
import java.util.Properties;

public class PropertiesExample {

    public static void main(String[] args) throws IOException {
        propertyFile();

    }

    private static void propertyFile() throws IOException {
        Properties props = new Properties();
        props.load(PropertiesExample.class.getClassLoader().getResourceAsStream("config.properties"));
        String value = props.getProperty("my.prop");
        System.out.println("value: "+value);
    }
}
