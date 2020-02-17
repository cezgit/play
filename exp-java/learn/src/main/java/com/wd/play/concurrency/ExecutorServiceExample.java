package com.wd.play.concurrency;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("STARTING...");
        System.out.println("------------------------------");

        // CREATE an Executor
        // ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        executeRunnable(executor);
        submitCallable(executor);
        invokeAnyCallable(executor);
        invokeAllCallables(executor);
        submitCallableAsLambdaAndCheckIfFutureIsDone(executor);
        submitCallableAsLambdaAndCancelTheFuture(executor);
        scheduledExecutorService();

        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("DONE!");
    }

    private static void executeRunnable(ExecutorService executor) {
        System.out.println("CREATE and EXECUTE a Runnable...");

        // CREATE and EXECUTE a Runnable
        Runnable runnableTask = ConcurrencyUtil.createRunnableTask("i'm a runnable and i'm running", 1000);
        executor.execute(runnableTask);
        System.out.println("------------------------------");
    }

    private static void submitCallable(ExecutorService executor) {
        System.out.println("SUBMIT a Callable...");

        // CREATE and SUBMIT a Callable - returns the result of the executable as a Future
        Callable<String> callableTask = ConcurrencyUtil.createCallableTask("i'm a submitted callable", 1000);
        Future<String> future = executor.submit(callableTask);

        try {
            // calling the get() method while the task is still running will cause execution to block until the task is properly executed and the result is available
            // String result = future.get();

            // if you expect get() to be blocking for long running jobs, you can provide a timeout
            // if the execution period is longer than specified (in this case 2000 milliseconds), a TimeoutException will be thrown.
            String result = future.get(2000, TimeUnit.MILLISECONDS);

            System.out.println("RESULT of Submitted Callable: "+result);

            // The isDone() method can be used to check if the assigned task is already processed or not.
            // The Future interface also provides for the cancellation of task execution with the cancel() method, and to check the cancellation with isCancelled() method:
            // boolean canceled = future.cancel(true);
            // boolean isCancelled = future.isCancelled();

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------");
    }

    private static void invokeAnyCallable(ExecutorService executor) throws ExecutionException, InterruptedException {
        System.out.println("INVOKE any Callable...");

        // INVOKE ANY Callable task
        List<Callable<String>> callableTasks = List.of(ConcurrencyUtil.createCallableTask("i'm the first invoked callable", 1000),
                ConcurrencyUtil.createCallableTask("i'm the second invoked callable", 1000),
                ConcurrencyUtil.createCallableTask("i'm the third invoked callable", 1000));

        String result = executor.invokeAny(callableTasks);
        System.out.println("RESULT of any Submitted Callable: "+result);
        System.out.println("------------------------------");
    }

    private static void invokeAllCallables(ExecutorService executor) throws InterruptedException {
        System.out.println("INVOKE all Callables...");

        List<Callable<String>> callableTasks = List.of(ConcurrencyUtil.createCallableTask("i'm the first invoked callable", 1000),
                ConcurrencyUtil.createCallableTask("i'm the second invoked callable", 1000),
                ConcurrencyUtil.createCallableTask("i'm the third invoked callable", 1000));

        // INVOKE ALL Callable tasks
         List<Future<String>> futures = executor.invokeAll(callableTasks);
         ConcurrencyUtil.printFutures(futures);

        System.out.println("------------------------------");
    }

    private static void submitCallableAsLambdaAndCheckIfFutureIsDone(ExecutorService executor) {
        System.out.println("SUBMIT a Callable as a Lambda and check if future is done...");

        // SUBMIT a Callable as a Lambda
        Future future = executor.submit(() -> {
            Thread.sleep(1);
            return "Hello from a Callable Lambda!";
        });

        System.out.println("More processing before the future is done...");
        while (!future.isDone()) {
            System.out.println("Waiting for future...");
        }
        ConcurrencyUtil.getIfNotCancelled(future);
        System.out.println("------------------------------");
    }

    private static void submitCallableAsLambdaAndCancelTheFuture(ExecutorService executor) {
        System.out.println("SUBMIT a Callable as a Lambda and cancel the future...");

        Future future = executor.submit(() -> {
            Thread.sleep(10);
            return "Hello from another Callable Lambda that's about to be cancelled...";
        });
        future.cancel(true);
        System.out.println("More processing before the future is cancelled...");
        ConcurrencyUtil.getIfNotCancelled(future);

        System.out.println("------------------------------");
    }

    private static void scheduledExecutorService() throws InterruptedException {

        // runs tasks after some predefined delay and/or periodically.
        System.out.println("SCHEDULE a Callable...");

        // CREATE a ScheduledExecutor
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // SCHEDULE a Callable task
        Future<String> scheduledFuture1 = executorService.schedule(ConcurrencyUtil.createCallableTask(
                "i'm a scheduled callable and i'm running", 1000),
                1,
                TimeUnit.SECONDS);

        System.out.println(String.format("Result of scheduled executor: %s", scheduledFuture1));

        // execute a task after an initial delay of 100 milliseconds, and after that, it will execute the same task every 450 milliseconds
        // Future<String> resultFuture = service.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);

        // SCHEDULE a Runnable tasks with Fixed Delay
        ScheduledFuture scheduledFuture2 = executorService.scheduleWithFixedDelay(
                ConcurrencyUtil.createRunnableTask("i'm a runnable ran by a scheduled exec", 1000),
                100,
                150,
                TimeUnit.MILLISECONDS);

        System.out.println(String.format("Result of scheduled executor with fixed delay: %s", scheduledFuture2));

        // CREATE and EXECUTE a Runnable which will wait and CANCEL the SCHEDULED Runnable Task
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println("Stopping the scheduled execution with fixed delay.");
        scheduledFuture2.cancel(false);
        executorService.shutdown();

        System.out.println("------------------------------");
    }
}

// https://www.baeldung.com/java-executor-service-tutorial