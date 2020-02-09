package com.elyzar.play;

import com.elyzar.play.concurrency.CompletableFutureExample;
import com.elyzar.play.concurrency.ExceptionHandlingExample;
import com.elyzar.play.concurrency.ExecutorServiceExample;
import com.elyzar.play.functional.*;
import com.elyzar.play.generics.GenericsExample;
import com.elyzar.play.io.ReadingFilesExamples;
import com.elyzar.play.io.WritingFileExample;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class RunAll {

    public static void main(String[] args) throws
            ExecutionException, InterruptedException, URISyntaxException, IOException {

        // concurrency
        ExecutorServiceExample.main(null);
        CompletableFutureExample.main(null);
        ExceptionHandlingExample.main(null);

        // files
        ReadingFilesExamples.main(null);
        WritingFileExample.main(null);

        // generics
        GenericsExample.main(null);

        // functional
        SupplierExamples.main(null);
        ConsumerExamples.main(null);
        PredicateExamples.main(null);
        FunctionExamples.main(null);
        OptionalExamples.main(null);
        SortingExamples.main(null);
        CollectorsExamples.main(null);
        StreamExamples.main(null);
        StreamExceptions.main(null);
        VariousExamples.main(null);
    }

}
