package com.elyzar.play.providers;

import com.elyzar.play.concurrency.ConcurrencyUtil;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RangeExamples {
    public static void main(String[] args) {
        IntStream.range(1, 3);  // > 1, 2
        IntStream.rangeClosed(1, 3);    // > 1, 2, 3
        Stream<Integer> stream = IntStream.range(1, 5).boxed();
        IntStream.range(1, 5).map(i -> i * i); // > 1, 4, 9, 16
        IntStream.range(1, 5).reduce(1, (x, y) -> x * y); // > 24
        IntStream.range(1, 5).parallel().forEach(i -> heavyOperation());

    }

    public static void heavyOperation() {
        ConcurrencyUtil.sleep(3000);
    }
}
