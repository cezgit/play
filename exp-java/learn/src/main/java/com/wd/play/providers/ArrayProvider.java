package com.wd.play.providers;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayProvider {

    public static void main(String[] args) {

        int[] a = IntStream.range(1, 100).toArray();
        System.out.println(a.length);

        a = IntStream.rangeClosed(1, 10).toArray();
        System.out.println(a.length);

        a = IntStream.iterate(1, n -> n + 1).limit(10).toArray();
        System.out.println(a.length);

        // or
        int[] array = new int[100];
        Arrays.setAll(array, i -> i + 1);
        System.out.println(array.length);

        // https://stackoverflow.com/questions/3387373/fill-arrays-with-ranges-of-numbers
    }
}
