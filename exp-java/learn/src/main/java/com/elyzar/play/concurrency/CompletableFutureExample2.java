package com.elyzar.play.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// https://dzone.com/articles/20-examples-of-using-javas-completablefuture
// https://github.com/manouti/completablefuture-examples
public class CompletableFutureExample2 {

    public static void main(String[] args) {

        // Running a Simple Asynchronous Stage
        runAsyncExample();

        // Applying a Function on the Previous Stage
        thenApplyExample();

        thenApplyExample2();

        // Asynchronously Applying a Function on a Previous Stage
        thenApplyAsyncExample();

        // Asynchronously Applying a Function on  a Previous Stage Using a Custom Executor
        thenApplyAsyncWithExecutorExample();

        // Consuming the Result of the Previous Stage
        thenAcceptExample();

        // Asynchronously Consuming the Result of the Previous Stage
        thenAcceptAsyncExample();

        // Completing a Computation Exceptionally
        completeExceptionallyExample();

        // Canceling a Computation
        cancelExample();

        // Applying a Function to the Result of Either of Two Completed Stages
        applyToEitherExample();

        // Consuming the Result of Either of Two Completed Stages
        acceptEitherExample();

        // Running a Runnable Upon Completion of Both Stages
        runAfterBothExample();

        // Accepting the Results of Both Stages in a BiConsumer
        thenAcceptBothExample();

        // Applying a BiFunction on Results of Both Stages
        thenCombineExample();

        // Asynchronously Applying a BiFunction on Results of Both Stages
        thenCombineAsyncExample();

        // Composing CompletableFutures
        thenComposeExample();

        // Creating a Stage That Completes When Any of Several Stages Completes
        anyOfExample();

        // Creating a Stage That Completes When All Stages Complete
        allOfExample();

        // Creating a Stage That Completes Asynchronously When All Stages Complete
        allOfAsyncExample();
    }

    private static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println("I'm a Task");
            ConcurrencyUtil.sleep(2000);
        });
        System.out.println("is the task done yet? "+cf.isDone());
        ConcurrencyUtil.sleep(3000);
        System.out.println("Task should be done: "+cf.isDone());

        System.out.println("------------------------------------------------------");
    }

    private static void thenApplyExample() {

        // The below example takes the completed CompletableFuture from example #1, which bears the result string "message" and applies a function that converts it to uppercase
        // CompletableFuture.completedFuture - Returns a new CompletableFuture that is already completed with the given value

        CompletableFuture cf = CompletableFuture.completedFuture("OK, I'm DONE!").thenApply(s -> {
            System.out.println("Task - thenApply");
            System.out.println("is current thread a daemon: "+Thread.currentThread().isDaemon());
            ConcurrencyUtil.sleep(1000);
            return s.toUpperCase();
        });
        System.out.println("CompleatableFuture.getNow(): "+cf.getNow(null));

        System.out.println("------------------------------------------------------");
    }

    private static void thenApplyExample2() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("I'm a running Task");
            ConcurrencyUtil.sleep(2000);
            return "I'm DONE!";
        });
        CompletableFuture<String> future = completableFuture.thenApply(s -> s + " What's next?");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------------------------");
    }

    private static void thenApplyAsyncExample() {

        // By appending the Async suffix to the method in the previous example, the chained CompletableFuture would execute asynchronously (using ForkJoinPool.commonPool()).

        CompletableFuture cf = CompletableFuture.completedFuture("OK, I'm DONE!").thenApplyAsync(s -> {
            System.out.println("Task - thenApplyAsync");
            System.out.println("is current thread a daemon: "+Thread.currentThread().isDaemon());
            ConcurrencyUtil.sleep(1000);
            return s.toUpperCase();
        });
        System.out.println("CompleatableFuture.getNow(): "+cf.getNow("Value is absent because cf is not done yet"));
        System.out.println("CompleatableFuture.join(): "+cf.join());

        System.out.println("------------------------------------------------------");
    }

    private static void thenApplyAsyncWithExecutorExample() {

        // A very useful feature of asynchronous methods is the ability to provide an Executor to use it to execute the desired CompletableFuture.
        // This example shows how to use a fixed thread pool to apply the uppercase conversion Function

        CompletableFuture cf = CompletableFuture.completedFuture("OK, I'm DONE!").thenApplyAsync(s -> {
            System.out.println("is current thread name starting with custom-executor? "+Thread.currentThread().getName().startsWith("custom-executor-"));
            System.out.println("is current thread a daemon: "+Thread.currentThread().isDaemon());
            ConcurrencyUtil.sleep(2000);
            return s.toUpperCase();
        }, executor);
        System.out.println("CompleatableFuture.getNow(): "+cf.getNow("Value is absent because cf is not done yet"));
        System.out.println("CompleatableFuture.join(): "+cf.join());
        executor.shutdown();

        System.out.println("------------------------------------------------------");
    }

    private static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });


    private static void thenAcceptExample() {

        // If the next stage accepts the result of the current stage but does not need to return a value in the computation (i.e. its return type is void),
        // then instead of applying a Function, it can accept a Consumer

        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));
        System.out.println("Result was empty? "+result.length());

        System.out.println("------------------------------------------------------");
    }

    private static void thenAcceptAsyncExample() {

        // Again, using the async version of thenAccept, the chained CompletableFuture would execute asynchronously

        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        System.out.println("Result was empty? "+result.length());

        System.out.println("------------------------------------------------------");
    }

    private static void completeExceptionallyExample() {

        // For simplicity, the operation takes a string and converts it to uppercase, and we simulate a delay in the operation of 2 seconds.
        // To do that, we will use the thenApplyAsync(Function, Executor) method, where the first argument is the uppercase function,
        // and the executor is a delayed executor that waits for 1 second before actually submitting the operation to the common ForkJoinPool.

        /*
        First, we create a CompletableFuture that is already completed with the value "OK, I'm DONE!".
        Next, we call thenApplyAsync, which returns a new CompletableFuture. This method applies an uppercase conversion in an asynchronous fashion
        upon completion of the first stage (which is already complete, thus the Function will be immediately executed).
        This example also illustrates a way to delay the asynchronous task using the delayedExecutor(timeout, timeUnit) method.

        We then create a separate “handler” stage, exceptionHandler, that handles any exception by returning another message "message upon cancel".
        Next, we explicitly complete the second stage with an exception. This makes the join() method on the stage, which is doing the uppercase operation,
        throw a CompletionException (normally join() would have waited for 2 second to get the uppercase string). It will also trigger the handler stage.
         */

        CompletableFuture cf = CompletableFuture.completedFuture("OK, I'm DONE!")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS));

        // Returns a new CompletionStage that, when this stage completes either normally or exceptionally, is executed with this stage's result and exception as arguments to the supplied function.
        CompletableFuture exceptionHandler = cf.handle((s, th) -> (th != null) ? String.format("message upon cancel for s: %s and th: %s", s, th) : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        System.out.println("Was not completed exceptionally: "+cf.isCompletedExceptionally());

        try {
            cf.join();
            System.out.println("Should have thrown an exception");
        } catch(CompletionException ex) { // just for testing
            System.out.println(ex.getCause().getMessage());
        }
        System.out.println("message: "+exceptionHandler.join());

        System.out.println("------------------------------------------------------");
    }

    private static void cancelExample() {

        // Very close to exceptional completion, we can cancel a computation via the cancel(boolean mayInterruptIfRunning) method from the Future interface.
        // For CompletableFuture, the boolean parameter is not used because the implementation does not employ interrupts to do the cancellation.
        // Instead, cancel() is equivalent to completeExceptionally(new CancellationException()).

        CompletableFuture cf = CompletableFuture.completedFuture("OK, I'm DONE!")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        System.out.println("Was not canceled: "+ cf.cancel(true));
        System.out.println("Was not completed exceptionally: "+ cf.isCompletedExceptionally());

        System.out.println("canceled message: "+cf2.join());

        System.out.println("------------------------------------------------------");
    }

    private static void applyToEitherExample() {

        // This example creates a CompletableFuture that applies a Function to the result of either of two previous stages (no guarantees on which one will be passed to the Function).
        // The two stages in question are: one that applies an uppercase conversion to the original string and another that applies a lowercase conversion

        String original = "Message";

        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s));

        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original)
                        .thenApplyAsync(s -> delayedLowerCase(s)), s -> s + " from applyToEither");

        System.out.println(cf2.join()+" ends with: from applyToEither");

        System.out.println("------------------------------------------------------");
    }

    private static void acceptEitherExample() {

        // Similar to the previous example, but using a Consumer instead of a Function (the dependent CompletableFuture has a type void)

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        System.out.println("Result ends with acceptEither: " +result.toString().endsWith("acceptEither"));

        System.out.println("------------------------------------------------------");
    }


    private static void runAfterBothExample() {

        // This example shows how the dependent CompletableFuture that executes a Runnable triggers upon completion of both of two stages.
        // Note that all the stages below run synchronously, where a stage first converts a message string to uppercase, then a second converts the same message string to lowercase.

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        System.out.println("Result was empty: "+ (result.length() > 0));

        System.out.println("------------------------------------------------------");
    }

    private static void thenAcceptBothExample() {

        // Instead of executing a Runnable upon completion of both stages, using BiConsumer allows processing of their results if needed:

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        System.out.println("MESSAGE: "+ result.toString());

        System.out.println("------------------------------------------------------");
    }

    private static void thenCombineExample() {

        // If the dependent CompletableFuture is intended to combine the results of two previous CompletableFutures by applying a function on them
        // and returning a result, we can use the method thenCombine().
        // The entire pipeline is synchronous, so getNow() at the end would retrieve the final result, which is the concatenation of the uppercase and the lowercase outcomes.

        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        System.out.println("MESSAGE: "+ cf.getNow(null));

        System.out.println("------------------------------------------------------");
    }

    private static void thenCombineAsyncExample() {

        // Similar to the previous example, but with a different behavior: since the two stages upon which CompletableFuture depends both run asynchronously,
        // the thenCombine() method executes asynchronously, even though it lacks the Async suffix.
        // This is documented in the class Javadocs: “Actions supplied for dependent completions of non-async methods may be performed by the thread
        // that completes the current CompletableFuture, or by any other caller of a completion method.”
        // Therefore, we need to join() on the combining CompletableFuture to wait for the result.

        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        System.out.println("MESSAGE: "+ cf.join());

        System.out.println("------------------------------------------------------");
    }

    private static void thenComposeExample() {

        // We can use composition using thenCompose() to accomplish the same computation done in the previous two examples.
        // This method waits for the first stage (which applies an uppercase conversion) to complete.
        // Its result is passed to the specified Function, which returns a CompletableFuture, whose result will be the result of the returned CompletableFuture.
        // In this case, the Function takes the uppercase string (upper), and returns a CompletableFuture that converts the original string to lowercase and then appends it to upper.

        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                        .thenApply(s -> upper + s));
        System.out.println("MESSAGE: "+ cf.join());

        System.out.println("------------------------------------------------------");
    }

    private static void anyOfExample() {

        // Illustrates how to create a CompletableFuture that completes when any of several CompletableFutures completes, with the same result.
        // Several stages are first created, each converting a string from a list to uppercase.
        // Because all of these CompletableFutures are executing synchronously (using thenApply()), the CompletableFuture returned from anyOf()
        // would execute immediately, since by the time it is invoked, all stages are completed.
        // We then use the whenComplete(BiConsumer<? super Object, ? super Throwable> action), which processes the result (asserting that the result is uppercase).

        StringBuilder result = new StringBuilder();
        List messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase((String) s)))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                System.out.println("isUpperCase: "+isUpperCase((String) res));
                result.append(res);
            }
        });
        System.out.println("Result was empty: "+ (result.length() > 0));

        System.out.println("------------------------------------------------------");
    }

    private static void allOfExample() {

        // The next two examples illustrate how to create a CompletableFuture that completes when all of several CompletableFutures completes,
        // in a synchronous and then asynchronous fashion, respectively. The scenario is the same as the previous example:
        // a list of strings is provided where each element is converted to uppercase.

        StringBuilder result = new StringBuilder();
        List messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase((String) s)))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> System.out.println("isUpperCase: "+isUpperCase((String) cf.getNow(null))));
            result.append("done");
        });
        System.out.println("Result was empty: "+ (result.length() > 0));

        System.out.println("------------------------------------------------------");
    }

    private static void allOfAsyncExample() {

        // By switching to thenApplyAsync() in the individual CompletableFutures, the stage returned by allOf() gets executed by one of the common pool
        // threads that completed the stages. So we need to call join() on it to wait for its completion.

        StringBuilder result = new StringBuilder();
        List messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase((String) s)))
                .collect(Collectors.toList());
        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> System.out.println(isUpperCase((String) cf.getNow(null))));
                    result.append("done");
                });
        allOf.join();
        System.out.println("Result was empty: "+ (result.length() > 0));

        System.out.println("------------------------------------------------------");
    }



    private static String delayedUpperCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }

    private static String delayedLowerCase(String s) {
        randomSleep();
        return s.toLowerCase();
    }

    private static boolean isUpperCase(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static Random random = new Random();

    private static void randomSleep() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            // ...
        }
    }
}
