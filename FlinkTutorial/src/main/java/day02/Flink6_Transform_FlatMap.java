package day02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.environment.*;
import org.apache.flink.util.Collector;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: Flink6_Transform_FlatMap
 *
 * @author 18729 created on date: 2020/11/17 16:30
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink6_Transform_FlatMap {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.readTextFile("E:\\IdeaWorkspace\\FlinkTutorial\\sensor\\sensor.txt")
                .flatMap((FlatMapFunction<String, String>) (s, collector) -> {
                    String[] split = s.split(",");
                    for (String s1 : split) {
                        collector.collect(s1);
                    }
                }).print();

        executionEnvironment.execute();
    }
}
