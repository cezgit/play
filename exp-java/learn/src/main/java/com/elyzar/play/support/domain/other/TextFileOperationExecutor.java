package com.elyzar.play.support.domain.other;

import java.util.ArrayList;
import java.util.List;

public class TextFileOperationExecutor {

    private final List<TextFileOperation> textFileOperations = new ArrayList<>();

    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        System.out.println("executing operation: "+textFileOperation.execute());
        return textFileOperation.execute();
    }
}
