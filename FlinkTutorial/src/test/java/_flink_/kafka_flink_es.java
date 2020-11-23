package _flink_;

import day03.sink.MyEsFunc;
import org.apache.directory.shared.kerberos.codec.apRep.actions.ApRepInit;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.http.HttpHost;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: _flink_
 * ClassName: kafka_flink_es
 *
 * @author 18729 created on date: 2020/11/19 14:53
 * @version 1.0
 * @since JDK 1.8
 */
public class kafka_flink_es {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop01:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "first");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        DataStreamSource<String> first = executionEnvironment.
                addSource(new FlinkKafkaConsumer011<String>("first",
                        new SimpleStringSchema(), properties));
//        first.print();
        ArrayList<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("hadoop01",9200));
        ElasticsearchSink<String> build = new ElasticsearchSink.Builder<String>(httpHosts, new MyEsFunc()).build();

        first.addSink(build);
        first.print();
        System.out.println(11111);
        executionEnvironment.execute();
    }
}
