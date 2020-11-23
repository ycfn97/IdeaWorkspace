package day02;

import bean.SensorReading;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.Collections;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: Flink_Split_Select
 *
 * @author 18729 created on date: 2020/11/18 9:34
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink_Split_Select {
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
        SplitStream<SensorReading> split = sensorDataStream.split(new OutputSelector<SensorReading>() {
            @Override
            public Iterable<String> select(SensorReading sensorReading) {
                return sensorReading.getC() > 30 ? Collections.singletonList("high") : Collections.singletonList("low");
            }
        });
        DataStream<SensorReading> high = split.select("high");
        DataStream<SensorReading> low = split.select("low");
        DataStream<SensorReading> select = split.select("high", "low");

//        high.print();
        low.print();
//        select.print();

        env.execute();
    }
}
