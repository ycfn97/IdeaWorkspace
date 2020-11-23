package day03.window.countwindow;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03.window.countwindow
 * ClassName: Window_Sliding
 *
 * @author 18729 created on date: 2020/11/18 20:31
 * @version 1.0
 * @since JDK 1.8
 */
public class Window_Sliding {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> tuple2SingleOutputStreamOperator = hadoop01.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                collector.collect(new Tuple2<>(s, 1));
            }
        });
        KeyedStream<Tuple2<String, Integer>, Tuple> tuple2TupleKeyedStream = tuple2SingleOutputStreamOperator.keyBy(0);
        tuple2TupleKeyedStream.countWindow(6,2).sum(1).print();
        executionEnvironment.execute();
    }
}
