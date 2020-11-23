package day04.State;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day04.day04.State
 * ClassName: State_WordCount
 *
 * @author 18729 created on date: 2020/11/21 9:04
 * @version 1.0
 * @since JDK 1.8
 */
public class State_WordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777);
        hadoop01.map(new RichMapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        }).keyBy(0).map(new RichMapFunction<Tuple2<String, Integer>, Tuple2<String, Integer>>() {
            ValueState<Integer> count=null;
            @Override
            public void open(Configuration parameters) throws Exception {
                count = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("count", Integer.class, 0));
            }

            @Override
            public Tuple2<String, Integer> map(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                Integer value = count.value();
                value++;
                count.update(value);
                return new Tuple2<>(stringIntegerTuple2.f0,value);
            }
        }).print();
        executionEnvironment.execute();
    }
}
