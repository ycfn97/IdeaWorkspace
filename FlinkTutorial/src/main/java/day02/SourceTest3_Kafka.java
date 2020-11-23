package day02;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: SourceTest3_Kafka
 *
 * @author 18729 created on date: 2020/11/17 11:18
 * @version 1.0
 * @since JDK 1.8
 */
public class SourceTest3_Kafka {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "hadoop01:9092");
        properties.setProperty("group.id", "consumer-group");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("auto.offset.reset", "latest");

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

// 从kafka读取数据
        DataStream<String> dataStream = executionEnvironment
                .addSource( new FlinkKafkaConsumer011<String>("first", new SimpleStringSchema(), properties));
        dataStream.print();

        executionEnvironment.execute();
    }
}
