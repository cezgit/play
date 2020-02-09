package com.wd.play.enums.math;

import java.util.Arrays;
import java.util.Collection;

public class MathEnum {

    public static void main(String[] args) {
        double x = args.length == 2 ? Double.parseDouble(args[0]) : 2;
        double y = args.length == 2 ? Double.parseDouble(args[1]) : 3;
        test(Arrays.asList(BasicOperation.values()), x, y);
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    public enum BasicOperation implements Operation {
        PLUS("+") { public double apply(double x, double y) { return x + y; } },
        MINUS("-") { public double apply(double x, double y) { return x - y; } },
        TIMES("*") { public double apply(double x, double y) { return x * y; } },
        DIVIDE("/") { public double apply(double x, double y) { return x / y; } };
        private final String symbol;
        BasicOperation(String symbol) { this.symbol = symbol; }
        @Override public String toString() { return symbol; }
    }
    public enum ExtendedOperation implements Operation {
        EXP("^") {
            public double apply(double x, double y) { return Math.pow(x, y); }
        },
        REMAINDER("%") {
            public double apply(double x, double y) { return x % y; }
        };
        private final String symbol;
        ExtendedOperation(String symbol) { this.symbol = symbol; }
        @Override public String toString() { return symbol; }
    }

    private static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet)
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        System.out.println("---------------------------------------------");
    }

}
