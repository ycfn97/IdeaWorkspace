//package day04.State;
//
//import bean.SensorReading;
//import org.apache.flink.api.common.functions.RichFlatMapFunction;
//import org.apache.flink.api.common.functions.RichMapFunction;
//import org.apache.flink.api.java.tuple.Tuple;
//import org.apache.flink.api.java.tuple.Tuple3;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.datastream.KeyedStream;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//
///**
// * Copyright(c) 2020-2021 sparrow All Rights Reserved
// * Project: FlinkTutorial
// * Package: day04.State
// * ClassName: State_OnTime
// *
// * @author 18729 created on date: 2020/11/21 11:52
// * @version 1.0
// * @since JDK 1.8
// */
//public class State_OnTime {
//    public static void main(String[] args) {
//        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
//        DataStreamSource<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777);
//        SingleOutputStreamOperator<SensorReading> map = hadoop01.map(new RichMapFunction<String, SensorReading>() {
//            @Override
//            public SensorReading map(String s) throws Exception {
//                String[] split = s.split(",");
//                return new SensorReading(split[0], Long.parseLong(split[1]), Double.parseDouble(split[2]));
//            }
//        });
//        KeyedStream<SensorReading, Tuple> sensorReadingTupleKeyedStream = map.keyBy("a");
//        sensorReadingTupleKeyedStream.flatMap(new RichFlatMapFunction<SensorReading, Tuple3<>>() {
//        })
//    }
//}
