package com.elyzar.play.concurrency;

import com.elyzar.play.support.service.AnimalService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("STARTING...");
        System.out.println("------------------------------");

        // it can be explicitly completed by calling the complete() method without any synchronous wait
            // A future cannot be manually completed
        // it also allows you to combine multiple futures - build a pipeline data process in a series of actions
            // With A future, you don’t have the ability to attach a callback function to the Future and have it get called automatically when the Future’s result is available


        // CompletableFuture executes these tasks in a thread obtained from the global ForkJoinPool.commonPool()
        // However, you can supply an executable if you want to:
        // static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
        // static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
        completableFutureRunAsync();
        completableFutureSupplyAsync();

        // use thenApply() method to process and transform the result of a CompletableFuture when it arrives
        completableFutureThenApply();

        // If you don’t want to return anything from your callback function and just want to run some piece of code after the completion of the Future,
        // then you can use thenAccept() and thenRun() methods. These methods are consumers and are often used as the last callback in the callback chain.

        completableFutureThenAccept();
        completableFutureThenRun();

        // If your callback function returns a CompletableFuture, and you want a flattened result from the CompletableFuture chain
        // (which in most cases you would), then use thenCompose().

        // The thenCompose() method is similar to thenApply() in that both return a new Completion Stage.
        // However, thenCompose() uses the previous stage as the argument. It will flatten and return a Future with the result directly,
        // rather than a nested future as we observed in thenApply():
        combiningTwoFuturesWithThenCompose();

        // If you want to execute two independent Futures and do something with their results,
        // use the thenCombine method that accepts a Future and a Function with two arguments to process both results:
        combiningTwoFuturesWithThenCombine();

        // A simpler case is when you want to do something with two Futures‘ results, but don’t need to pass any resulting value down a Future chain. The thenAcceptBoth method is there to help
        combiningTwoFuturesByUsingThenAcceptBoth();

        combiningMultipleFuturesWithAllOf();
        combiningMultipleFuturesWithAnyOf();

        System.out.println("DONE!");
    }

    private static void completableFutureRunAsync() throws ExecutionException, InterruptedException {

        System.out.println("Run async a Runnable in a Completable Future...");

        // Run a task specified by a Runnable Object asynchronously.
        CompletableFuture<Void> future = CompletableFuture.runAsync(
                ConcurrencyUtil.createRunnableTask("i'm an async runnable running in a completable future", 5000));

        // Block and wait for the future to complete - nothing is returned
        System.out.println(future.get());

        System.out.println("------------------------------");
    }

    private static void completableFutureSupplyAsync() throws ExecutionException, InterruptedException {

        System.out.println("Supplied lambda in a Completable Future...");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

                System.out.println(String.format("%s: %s", Thread.currentThread().getName(),
                        "i'm a supplied lambda running async inside a completable future"));
                ConcurrencyUtil.sleep(1000);

            return "RESULT of a supplied async tasks from a compleatable future";
        });

        // Block and get the result of the Future
        System.out.println(future.get());

        System.out.println("------------------------------");
    }

    private static void completableFutureThenApply() throws ExecutionException, InterruptedException {

        System.out.println("Supplied lambda in a Completable Future followed by a thenApply callback");

        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the lambda...");
            ConcurrencyUtil.sleep(1000);
            return "Cezar";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture
                .thenApply(name -> "Hello " + name)
                .thenApply(greeting -> greeting + ", Welcome to my world!");

        System.out.println("After the lambda was called but before the future result is retrieved...");
        // Block and get the result of the future.
        System.out.println(greetingFuture.get()); // Hello Cezar
        System.out.println("------------------------------");
    }

    private static void completableFutureThenAccept() throws ExecutionException, InterruptedException {

        System.out.println("Supplied lambda in a Completable Future followed by a thenAccept callback");

        // CompletableFuture.thenAccept() takes a Consumer<T> and returns CompletableFuture<Void>.
        // It has access to the result of the CompletableFuture on which it is attached.

        CompletableFuture<Void> animalSearchFuture = CompletableFuture
                .supplyAsync(() -> AnimalService.getAnimal(1))
                .thenAccept(animal -> System.out.println(String.format("Got %s from remote animal service", animal.getName())));

        System.out.println("After the lambda was called but before the future result is retrieved...");
        System.out.println(animalSearchFuture.get());
        System.out.println("------------------------------");
    }

    private static void completableFutureThenRun() throws ExecutionException, InterruptedException {

        System.out.println("Supplied lambda in a Completable Future followed by a thenRun callback");

        // If you neither need the value of the computation nor want to return some value at the end of the chain,
        // then you can pass a Runnable lambda to the thenRun method (does not have access to the Future's result)

        CompletableFuture<Void> animalSearchFuture = CompletableFuture
                .supplyAsync(() -> AnimalService.getAnimal(1))
                .thenRun(() -> System.out.println("async method called"));

        System.out.println("After the lambda was called but before the future result is retrieved...");
        System.out.println(animalSearchFuture.get());

        System.out.println("------------------------------");
    }

    private static void combiningTwoFuturesWithThenCompose() throws ExecutionException, InterruptedException {
        System.out.println("Chain 2 futures sequentially by using thenCompose");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the first supplied lambda");
            return "Hello";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the second supplied lambda");
            return s + " World";
        }));

        System.out.println("After both futures were executed but before the composed future result is retrieved...");

        // The result of this chaining is itself a CompletableFuture that allows further chaining and combining.
        // This approach is ubiquitous in functional languages and is often referred to as a monadic design pattern.
        System.out.println(completableFuture.get());

        System.out.println("------------------------------");
    }

    private static void combiningTwoFuturesWithThenCombine() throws ExecutionException, InterruptedException {
        System.out.println("Execute 2 independent futures using thenCombine and do something with their results");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the first supplied lambda");
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the second supplied lambda");
            return " World";
        }), (s1, s2) -> {
            System.out.println("Inside the BiFunction callback to process both results");
            return s1 + s2;
        });

        System.out.println("After both futures were executed but before the combined future result is retrieved...");
        System.out.println(completableFuture.get());

        System.out.println("------------------------------");
    }

    private static void combiningTwoFuturesByUsingThenAcceptBoth() throws ExecutionException, InterruptedException {
        System.out.println("Execute 2 independent futures and then do something with their results");

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the first supplied lambda");
            return "Hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the second supplied lambda");
            return " World";
        }), (s1, s2) -> {
            System.out.println("Accepting the 2 future results...");
            System.out.println(s1 + s2);
        });

        System.out.println("------------------------------");
    }

    private static void combiningMultipleFuturesWithAllOf() throws ExecutionException, InterruptedException {
        System.out.println("Running multiple futures in parallel and waiting for their results with allOf");

        // wait for completion of all of the Futures provided as a var-arg
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        // the return type of the CompletableFuture.allOf() is a CompletableFuture<Void> and that's not very useful
        System.out.println("allOf won't get you the combined results directly:" + combinedFuture.get());

        // Instead you have to manually get results from Futures
        System.out.println("But you can combine the results of all the futures by using the join method");

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combined);
        System.out.println("------------------------------");
    }

    private static void combiningMultipleFuturesWithAnyOf() throws ExecutionException, InterruptedException {
        System.out.println("Running multiple futures in parallel and getting the result of the first one that completes with anyOf");

        // returns a new CompletableFuture which is completed when any of the given CompletableFutures complete, with the same result.

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            ConcurrencyUtil.sleep(2000);
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            ConcurrencyUtil.sleep(1000);
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            ConcurrencyUtil.sleep(3000);
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println("The first completed future is: "+anyOfFuture.get());

        // The problem with CompletableFuture.anyOf() is that if you have CompletableFutures that return results of different types,
        // then you won’t know the type of your final CompletableFuture.

        System.out.println("------------------------------");
    }
}

// https://www.baeldung.com/java-completablefuture
// https://www.callicoder.com/java-8-completablefuture-tutorial/