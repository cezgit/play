package com.elyzar.play.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExceptionHandlingExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("STARTING...");
        System.out.println("------------------------------");

        handleExceptionWithExceptionally(16);
        handleExceptionWithExceptionally(19);
        handleExceptionWithExceptionally(-1);

        handleExceptionUsingTheGenericHandleMethod(-1);

    }

    private static void handleExceptionWithExceptionally(Integer age) throws ExecutionException, InterruptedException {

        System.out.println("Catching exceptions with exceptionally");
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> whatAmI(age))
                .exceptionally(ex -> {
                    System.out.println("Oops! We have an exception - " + ex.getMessage());
                    return "Unknown!";
                });

        System.out.println("Maturity : " + maturityFuture.get());

        System.out.println("------------------------------");
    }

    private static void handleExceptionUsingTheGenericHandleMethod(Integer age) throws ExecutionException, InterruptedException {

        System.out.println("Catching exceptions with handle");
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> whatAmI(age))
                .handle((res, ex) -> {
                    if(ex != null) {
                        System.out.println("Oops! We have an exception - " + ex.getMessage());
                        return "Unknown!";
                    }
                    return res;
                });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    private static String whatAmI(Integer age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age can not be negative");
        }
        if (age > 18) {
            return "Adult";
        } else {
            return "Child";
        }
    }
}
