package com.wd.play.patterns.behavioral;

import com.wd.play.support.domain.other.TextFile;
import com.wd.play.support.domain.other.TextFileOperationExecutor;

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
