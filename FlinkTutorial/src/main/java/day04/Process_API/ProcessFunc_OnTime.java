package day04.Process_API;

import bean.SensorReading;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimerService;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day04.watermark.Process_API
 * ClassName: ProcessFunc
 *
 * @author 18729 created on date: 2020/11/20 19:43
 * @version 1.0
 * @since JDK 1.8
 */
public class ProcessFunc_OnTime {
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
        sensorReadingTupleKeyedStream.process(new KeyedProcessFunction<Tuple, SensorReading, String>() {
            @Override
            public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
                System.out.println("time:"+timestamp);
                out.collect("定时器工作了！");
            }

            @Override
            public void processElement(SensorReading value, Context ctx, Collector<String> out) throws Exception {
                TimerService timerService = ctx.timerService();
                long ts = timerService.currentProcessingTime();
                System.out.println(ts);
                timerService.registerProcessingTimeTimer(ts+5000L);
                out.collect(value.getA());
            }
        }).print();
        executionEnvironment.execute();
    }
}
