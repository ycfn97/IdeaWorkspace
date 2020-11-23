package day02;

import bean.SensorReading;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: Flink_Transform_Reduce
 *
 * @author 18729 created on date: 2020/11/17 18:40
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink_Transform_Reduce {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 1.Source:从集合读取数据
        DataStream<SensorReading> sensorDataStream = env.fromCollection(
                Arrays.asList(
                        new SensorReading("sensor_1", 1547718199L, 35.8),
                        new SensorReading("sensor_6", 1547718201L, 15.4),
                        new SensorReading("sensor_7", 1547718202L, 6.7),
                        new SensorReading("sensor_10", 1547718205L, 38.1),
                        new SensorReading("sensor_1", 1547718198L, 35.8),
                        new SensorReading("sensor_1", 1547718200L, 36.8),
                        new SensorReading("sensor_1", 1547718201L, 36.9)
                )
        );
        KeyedStream<SensorReading, Tuple> id = sensorDataStream.keyBy("a");
        id.reduce(new ReduceFunction<SensorReading>() {
            @Override
            public SensorReading reduce(SensorReading sensorReading, SensorReading t1) throws Exception {
                return new SensorReading(
                        sensorReading.getA(),
                        sensorReading.getB(),
                        Math.min(sensorReading.getC(),t1.getC())
                );
            }
        }).print();
        env.execute();
    }
}
