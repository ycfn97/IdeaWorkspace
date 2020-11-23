package day01;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: PACKAGE_NAME
 * ClassName: Flink01_WordCount_Batch
 *
 * @author 18729 created on date: 2020/11/16 11:16
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink01_WordCount_Batch {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        String s = "E:\\IdeaWorkspace\\FlinkTutorial\\input\\hello.txt";
        DataSource<String> stringDataSource = executionEnvironment.readTextFile(s);
        stringDataSource
                .flatMap(new MyFlatMapper())
                .groupBy(0)
                .sum(1)
                .print();
    }
}
