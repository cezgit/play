package com.elyzar.play.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExamples {
    public static void main(String[] args) {

        findAllMatches();
        replaceAllMatches();
        splitStringOnBoundaries();
//        findAllHoursAndMinutesInDate();
    }

    private static void findAllMatches() {
        String s = "123 is not 456";
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(s.substring(matcher.start(), matcher.end()));
        }

    }

    private static void replaceAllMatches() {
        Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher("SSN is 3434534");
        String result = matcher.replaceAll("#");
        System.out.println(result);
    }

    private static void splitStringOnBoundaries() {
        String[] words = "Hello There".split("\\s+");
        System.out.println(Arrays.toString(words));
    }

    private static void findAllHoursAndMinutesInDate() {
        String s = "13:30pm";
        Pattern pattern = Pattern.compile("(1?[0-9]):([0-5][0-9])[ap]m");
        Matcher matcher = pattern.matcher(s);
        for (int i = 1; i <= matcher.groupCount(); i++) {
            System.out.println(matcher.group(i));
        }
    }
}
