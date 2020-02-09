package com.elyzar.play.concurrency;

import com.elyzar.play.support.domain.ordering.Product;
import io.vavr.Tuple2;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureExample3 {

    public static void main(String[] args) {

        calculateAsyncDemo();
        System.out.println("------------------------------------------------------");
        productExample();
        System.out.println("------------------------------------------------------");
        multipleFuturesFollowedBy();
    }

    public static void calculateAsyncDemo() {
        Future f = calculateAsync(3000);
        while(!f.isDone()) {
            ConcurrencyUtil.sleep(3000);
            System.out.println("Waiting for completable future to be done...");
        }
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static Future<String> calculateAsync(int millis) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("I'm a task submitted to an Executor...");
            Thread.sleep(millis);
            completableFuture.complete("I'm DONE!");
            return null;
        });

        return completableFuture;
    }

    public static void productExample() {
        CompletableFuture<Product> p = getProduct(1);
        while(!p.isDone()) {
            System.out.println("waiting for product to be retrieved...");
            ConcurrencyUtil.sleep(2000);
        }

        System.out.println("completable done: "+p.isDone());
    }


    static CompletableFuture<Product> getProduct(int id) {

        ConcurrentHashMap<Integer, Product> cache = new ConcurrentHashMap();

        try {
            Product product = cache.get(id);
            if (product != null) {
                // Complete with the product from the cache if available
                System.out.println("completing immediately because the product is in the cache");
                return CompletableFuture.completedFuture(product);
            } else {
                CompletableFuture<Product> future = new CompletableFuture<>();
                Product p = getRemote(id); // Legacy retrieval
                cache.put(id, p);
                // Complete after legacy retrieval (for async, see next example)
                System.out.println("completing normally");
                future.complete(p);
                return future;
            }
        } catch (Exception e) {
            CompletableFuture<Product> future = new CompletableFuture<>();
            // Complete with an exception if something goes wrong
            System.out.println("completing exceptionally!");
            future.completeExceptionally(e);
            return future;
        }
    }


    static Product getRemote(Integer id) {
        System.out.println("Remote: getting the product is gonna take a bit");
        ConcurrencyUtil.sleep(5000);
        return new Product(id, "soccer ball");
    }

    public static void multipleFuturesFollowedBy() {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Tuple2<String, Integer>> firstBatchList = List.of(new Tuple2("firstBatch-1", 3000), new Tuple2("firstBatch-2", 5000));
        List<Tuple2<String, Integer>> secondBatchList = List.of(new Tuple2("secondBatch-1", 3000), new Tuple2("secondBatch-2", 2000));
        List<Tuple2<String, Integer>> thirdBatchList = List.of(new Tuple2("thirdBatch-1", 6000), new Tuple2("thirdBatch-2", 4000));

        List<CompletableFuture> firstBatchFutures = firstBatchList.stream().map(job -> asyncFuture(job._1, job._2, executor)).collect(Collectors.toList());
        CompletableFuture firstBatch = CompletableFuture.allOf(firstBatchFutures.toArray(new CompletableFuture[firstBatchFutures.size()]))
                .whenComplete((i, err1) -> {
                    List<CompletableFuture> secondBatchFutures = secondBatchList.stream().map(job -> asyncFuture(job._1, job._2, executor)).collect(Collectors.toList());
                    CompletableFuture secondBatch = CompletableFuture.allOf(secondBatchFutures.toArray(new CompletableFuture[secondBatchFutures.size()]))
                            .whenComplete((j, err2) -> {
                                List<CompletableFuture> thirdBatchFutures = thirdBatchList.stream().map(job -> asyncFuture(job._1, job._2, executor)).collect(Collectors.toList());
                                CompletableFuture thirdBatch = CompletableFuture.allOf(thirdBatchFutures.toArray(new CompletableFuture[thirdBatchFutures.size()]));
                                thirdBatch.join();
                            });
                    secondBatch.join();
                });
        firstBatch.join();
        executor.shutdown();
    }

    static CompletableFuture asyncFuture(String name, int millis, Executor executor) {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println(name);
            ConcurrencyUtil.sleep(millis);
            System.out.println(name+" is DONE!");
        }, executor);
        return cf;
    }

//    static ExecutorService executor = Executors.newFixedThreadPool(4, new ThreadFactory() {
//        int count = 1;
//        @Override
//        public Thread newThread(Runnable runnable) {
//            return new Thread(runnable, "custom-executor-" + count++);
//        }
//    });
}
