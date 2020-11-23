package day04.watermark;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day04
 * ClassName: Window_EventTime
 *
 * @author 18729 created on date: 2020/11/20 18:09
 * @version 1.0
 * @since JDK 1.8
 */
public class Window_EventTime {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        SingleOutputStreamOperator<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777).assignTimestampsAndWatermarks(new AscendingTimestampExtractor<String>() {
            @Override
            public long extractAscendingTimestamp(String element) {
                String[] split = element.split(",");
                return Long.parseLong(split[1])*1000L;
            }
        });
        SingleOutputStreamOperator<Tuple2<String, Integer>> map = hadoop01.map(new RichMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                String[] s1 = s.split(",");
                return new Tuple2<>(s1[0],1);
            }
        });
        map.keyBy(0).timeWindow(Time.seconds(5)).sum(1).print();
        executionEnvironment.execute();
    }
}
