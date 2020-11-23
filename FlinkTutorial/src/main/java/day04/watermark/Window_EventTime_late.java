package day04.watermark;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.OutputTag;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day04
 * ClassName: Window_EventTime_late
 *
 * @author 18729 created on date: 2020/11/20 18:35
 * @version 1.0
 * @since JDK 1.8
 */
public class Window_EventTime_late {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        SingleOutputStreamOperator<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777).assignTimestampsAndWatermarks(new AscendingTimestampExtractor<String>() {
            @Override
            public long extractAscendingTimestamp(String element) {
                String[] split = element.split(",");
                return Long.parseLong(split[1]) * 1000L;
            }
        });
        SingleOutputStreamOperator<Tuple2<String, Integer>> map = hadoop01.map(new RichMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                String[] split = s.split(",");
                return new Tuple2<>(split[0], 1);
            }
        });
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = map.keyBy(0).timeWindow(Time.seconds(5)).allowedLateness(Time.seconds(2)).sideOutputLateData(new OutputTag<Tuple2<String, Integer>>("sideOutput") {
        }).sum(1);
        sum.print("main");
        sum.getSideOutput(new OutputTag<Tuple2<String,Integer>>("sideOutput"){}).print("sideOutput");
        executionEnvironment.execute();
    }
}
