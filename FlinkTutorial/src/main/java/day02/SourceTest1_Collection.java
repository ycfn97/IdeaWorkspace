package day02;

import bean.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: SourceTest1_Collection
 *
 * @author 18729 created on date: 2020/11/17 11:01
 * @version 1.0
 * @since JDK 1.8
 */
public class SourceTest1_Collection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 1.Source:从集合读取数据
        DataStream<SensorReading> sensorDataStream = env.fromCollection(
                Arrays.asList(
                        new SensorReading("sensor_1", 1547718199L, 35.8),
                        new SensorReading("sensor_6", 1547718201L, 15.4),
                        new SensorReading("sensor_7", 1547718202L, 6.7),
                        new SensorReading("sensor_10", 1547718205L, 38.1)
                )
        );
        // 2.打印
        sensorDataStream.print();
        // 3.执行
        env.execute();
    }
}
