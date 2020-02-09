package com.elyzar.play.patterns.behavioral;

import com.elyzar.play.support.domain.other.TextFile;
import com.elyzar.play.support.domain.other.TextFileOperationExecutor;

public class CommandPatternDemo {

    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();
        TextFile textFile = new TextFile("file1.txt");
        textFileOperationExecutor.executeOperation(textFile::open);
        textFileOperationExecutor.executeOperation(textFile::save);

        textFileOperationExecutor.executeOperation(() ->  "Opening file file1.txt");
        textFileOperationExecutor.executeOperation(() -> "Saving file file1.txt");
    }


}
