package _flink_;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: _flink_
 * ClassName: kafka_flink_mysql
 *
 * @author 18729 created on date: 2020/11/18 21:00
 * @version 1.0
 * @since JDK 1.8
 */
public class kafka_flink_mysql {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop01:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "first");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        DataStreamSource<String> first = executionEnvironment.addSource(new FlinkKafkaConsumer011<String>("first", new SimpleStringSchema(), properties));

        first.addSink(new RichSinkFunction<String>() {
            Connection conn = null;
            PreparedStatement insertStmt = null;

            @Override
            public void open(Configuration parameters) throws Exception {
                conn = DriverManager.getConnection("jdbc:mysql://hadoop01:3306/school?serverTimezone=GMT%2B8&useSSL=false", "root", "143382");
                insertStmt = conn.prepareStatement("insert into sensor(id,temp) values(?,?) on duplicate key update temp=?");
            }

            @Override
            public void invoke(String value, Context context) throws Exception {
                String[] split = value.split(",");
                insertStmt.setString(1, split[0]);
                insertStmt.setDouble(2, Double.parseDouble(split[1]));
                insertStmt.setDouble(3, Double.parseDouble(split[1]));
                insertStmt.execute();
            }

            @Override
            public void close() throws Exception {
                insertStmt.close();
                conn.close();
            }
        });
        executionEnvironment.execute();
    }
}
