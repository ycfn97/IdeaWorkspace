package day04.Process_API;

import bean.SensorReading;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day04.Process_API
 * ClassName: ProcessFunc_SideOutPut
 *
 * @author 18729 created on date: 2020/11/20 21:04
 * @version 1.0
 * @since JDK 1.8
 */
public class ProcessFunc_SideOutPut {
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
        SingleOutputStreamOperator<SensorReading> low = sensorReadingTupleKeyedStream.process(new KeyedProcessFunction<Tuple, SensorReading, SensorReading>() {
            @Override
            public void processElement(SensorReading value, Context ctx, Collector<SensorReading> out) throws Exception {
                double c = value.getC();
                if (c > 30.0D) {
                    out.collect(value);
                } else {
                    ctx.output(new OutputTag<String>("low"){}, value.getA());
                }
            }
        });
        low.print("high");
        low.getSideOutput(new OutputTag<String>("low"){}).print("low");
        executionEnvironment.execute();
    }
}
