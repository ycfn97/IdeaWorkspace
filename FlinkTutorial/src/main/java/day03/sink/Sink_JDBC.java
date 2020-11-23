package day03.sink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03
 * ClassName: Sink_JDBC
 *
 * @author 18729 created on date: 2020/11/18 14:11
 * @version 1.0
 * @since JDK 1.8
 */
public class Sink_JDBC {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> sensor = executionEnvironment.readTextFile("sensor");
        sensor.addSink(new JdbcSink());
        executionEnvironment.execute();
    }
}
