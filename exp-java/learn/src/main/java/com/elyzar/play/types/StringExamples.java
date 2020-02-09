package com.elyzar.play.types;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExamples {
    public static void main(String[] args) {

        formatters();
        joiners();
        charCounters();
        patternMatchingCounter();
    }

    private static void formatters() {
        System.out.println(String.format("%s = %d", "joe", 35));
        // number separator
        System.out.println(String.format("The %s costs $%,.2f", "Car", 54999.99f));
        // enclose negative numbers in parentheses
        System.out.println(String.format("Absolute zero is %(.2f degrees Celsius", -273.15f));

        // Use the + character to include a positive or negative sign.
        System.out.println(String.format("Temperature of the Sun %,+d K", 5778));
        // Temperature of the Sun +5,778 K
        System.out.println(String.format("Temperature of Jupiter %,+d Celsius", -145));
        // Temperature of Jupiter -145 Celsius

        // left justify a number
        System.out.println(String.format("A left-justified number <%-10d>", 42)); // A left-justified number <42        >
    }

    private static void joiners() {
        StringJoiner sjr = new StringJoiner(",", "[", "]");
        sjr.add("Smart").add("Techie");
        System.out.println(sjr); // "[Smart,Techie]"
    }

    private static void charCounters() {
        long count = "Counting me".chars().filter(ch -> ch == 'n').count();
        System.out.println(count);
        long count2 = "Count me".codePoints().filter(ch -> ch == 'c').count();
        System.out.println(count2);

        // commons
        // int count = StringUtils.countMatches("elephant", "e”);
        // guava
        // int count = CharMatcher.is('e').countIn("elephant");
        // spring
        // int count = StringUtils.countOccurrencesOf("elephant", "e");
    }

    private static void patternMatchingCounter() {
        Pattern pattern = Pattern.compile("[^e]*e");
        Matcher matcher = pattern.matcher("elephant");
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println(count);
    }
}
