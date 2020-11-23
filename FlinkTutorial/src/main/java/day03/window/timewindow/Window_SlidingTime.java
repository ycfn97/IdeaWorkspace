package day03.window.timewindow;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03
 * ClassName: Window_SlidingTime
 *
 * @author 18729 created on date: 2020/11/18 16:14
 * @version 1.0
 * @since JDK 1.8
 */
public class Window_SlidingTime {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> input = executionEnvironment.socketTextStream("hadoop01",7777);
        SingleOutputStreamOperator<Tuple2<String, Integer>> tuple2SingleOutputStreamOperator = input.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] s1 = s.split(" ");
                for (String word:s1) {
                    collector.collect(new Tuple2<>(word,1));
                }
            }
        });
        KeyedStream<Tuple2<String, Integer>, Tuple> tuple2TupleKeyedStream = tuple2SingleOutputStreamOperator.keyBy(0);
        tuple2TupleKeyedStream.timeWindow(Time.seconds(30), Time.seconds(5)).reduce(new ReduceFunction<Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> stringIntegerTuple2, Tuple2<String, Integer> t1) throws Exception {
                return new Tuple2<>(stringIntegerTuple2.f0,stringIntegerTuple2.f1+t1.f1);
            }
        }).print();
        executionEnvironment.execute();
    }
}
