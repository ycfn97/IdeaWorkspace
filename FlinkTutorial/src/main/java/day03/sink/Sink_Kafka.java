package day03.sink;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03
 * ClassName: Sink_Kafka
 *
 * @author 18729 created on date: 2020/11/18 11:21
 * @version 1.0
 * @since JDK 1.8
 */
public class Sink_Kafka {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> sensor = executionEnvironment.readTextFile("sensor");
        DataStreamSink<String> test = sensor.addSink(new FlinkKafkaProducer011<String>("hadoop01:9092", "first", new SimpleStringSchema()));
        executionEnvironment.execute();
    }
}
