package day02;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: Flink5_Transform_Map
 *
 * @author 18729 created on date: 2020/11/17 16:00
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink5_Transform_Map {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.readTextFile("E:\\IdeaWorkspace\\FlinkTutorial\\input\\hello.txt").map(a->a.length()).print();
        executionEnvironment.execute();
    }
}
