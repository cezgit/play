package com.wd.play.concurrency;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * When dealing with a large amount of data, you may want to process the data concurrently for better performance
 * In this example, we split a list of metadata in batches, each batch to be executed concurrently
 * A batch can represent for example a list of files, each file consisting of multiple records to be copied and saved (in our case 10)
 * After splitting the list of batches, we process each batch concurrently and then join the results (total count of records processed)
 */
public class BatchProcessor {

    public void run() {

        ExecutorService exe = Executors.newFixedThreadPool(4);
        List<Integer> metaData = IntStream.range(1, 101).boxed().collect(Collectors.toList());

        // 100 metas partitioned in batches of 8 gives us 13 batches (12*8+4=100)
        List<List<Integer>> batches = Lists.partition(metaData, 8);

        // create futures, one future per batch, however, only 4 futures are executed at a time because the executor pool is set to 4
        final List<CompletableFuture<Result>> futures = batches.stream().map(batch -> {
            return CompletableFuture.supplyAsync(() -> {
                return metaProcessor.apply(batch);
            }, exe);
        }).collect(Collectors.toList());

        AtomicInteger totalRecords = new AtomicInteger(0);
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((input, exception) -> {
            futures.forEach(cf -> totalRecords.set(totalRecords.get() + cf.join().recordCount));
            System.out.println("Total records processed was: "+ (totalRecords.get()));
        });
        exe.shutdown();
    }

    /**
     * process a batch of metas
     * each meta generates 10 records so the totalRecords is batch.size() * 10
     */
    private Function<List<Integer>, Result> metaProcessor = (List<Integer> batch) -> {
        Random random = new Random();
        AtomicInteger totalRecords = new AtomicInteger(0);
        System.out.println("processing batch: "+batch);
        batch.forEach(i -> {
            totalRecords.set(totalRecords.get()+10);
            ConcurrencyUtil.sleep(1000);
        });
        System.out.println("batch " + batch + " generated "+ totalRecords.get() +" records");
        return new Result(totalRecords.get());
    };

    class Result {
        Integer recordCount;
        public Result(Integer recordCount) {
            this.recordCount = recordCount;
        }
    }
}
