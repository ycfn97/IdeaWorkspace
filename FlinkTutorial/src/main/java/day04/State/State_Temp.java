package day04.State;

import bean.SensorReading;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day04.State
 * ClassName: State_Temp
 *
 * @author 18729 created on date: 2020/11/21 11:24
 * @version 1.0
 * @since JDK 1.8
 */
public class State_Temp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777);
        SingleOutputStreamOperator<SensorReading> map = hadoop01.map(new RichMapFunction<String, SensorReading>() {
            @Override
            public SensorReading map(String s) throws Exception {
                String[] split = s.split(",");
                return new SensorReading(split[0], Long.parseLong(split[1]), Double.parseDouble(split[2]));
            }
        });
        KeyedStream<SensorReading, Tuple> sensorReadingTupleKeyedStream = map.keyBy("a");
//        sensorReadingTupleKeyedStream.print();
        SingleOutputStreamOperator<Tuple3<String,Double, Double>> tuple3SingleOutputStreamOperator = sensorReadingTupleKeyedStream.flatMap(new RichFlatMapFunction<SensorReading, Tuple3<String, Double, Double>>() {
            ValueState<Double> state=null;
            @Override
            public void open(Configuration parameters) throws Exception {
                 state = getRuntimeContext().getState(new ValueStateDescriptor<Double>("last-temp", Double.class));
            }

            @Override
            public void flatMap(SensorReading sensorReading, Collector<Tuple3<String, Double, Double>> collector) throws Exception {
                Double value = state.value();
                double c = sensorReading.getC();
                if (value!=null && Math.abs(value-c)>10.0D) collector.collect(new Tuple3<>(sensorReading.getA(),value,c));
                state.update(c);
            }
        });
        tuple3SingleOutputStreamOperator.print();
        executionEnvironment.execute();
    }
}
