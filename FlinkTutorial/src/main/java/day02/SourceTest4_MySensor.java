package day02;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: SourceTest4_MySensor
 *
 * @author 18729 created on date: 2020/11/17 11:48
 * @version 1.0
 * @since JDK 1.8
 */
public class SourceTest4_MySensor {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.addSource(new MySensor()).print();
        executionEnvironment.execute();
    }
}
