package _flink_;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: _flink_
 * ClassName: kafka_flink_hbase
 *
 * @author 18729 created on date: 2020/11/18 21:36
 * @version 1.0
 * @since JDK 1.8
 */
public class kafka_flink_hbase {
    public static void main(String[] args) {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop01:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "first");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        DataStreamSource<String> first = executionEnvironment.addSource(new FlinkKafkaConsumer011<String>("first", new SimpleStringSchema(), properties));

        first.map(new MapFunction<String, DeviceData>() {
            @Override
            public DeviceData map(String s) throws Exception {
                String[] split = s.split(",");
                DeviceData deviceData = new DeviceData(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
                return deviceData;
            }
        }).
                addSink(new RichSinkFunction<DeviceData>() {
//            Logger logger = LoggerFactory.getLogger(HBaseWriter.class);
            org.apache.hadoop.conf.Configuration configuration;
            Connection connection = null;
            BufferedMutator mutator;
            int count = 0;
            @Override
            public void open(Configuration parameters) throws Exception {
                configuration = HBaseConfiguration.create();
                configuration.set("hbase.master","hadoop01:16010");
                configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
                configuration.set("hbase.zookeeper.property.clientPort","2181");
                connection = ConnectionFactory.createConnection(configuration);
                BufferedMutatorParams student = new BufferedMutatorParams(TableName.valueOf("student"));
                student.writeBufferSize(2*1024*1024);
                mutator=connection.getBufferedMutator(student);
            }

            @Override
            public void close() throws Exception {
                if (mutator!=null) mutator.close();
                if (connection!=null) connection.close();
            }

            @Override
            public void invoke(DeviceData values,Context context) throws Exception {
                //Date 1970-01-06 11:45:55  to 445555000
                long unixTimestamp= 0;
                try {
                    String gatherTime = values.gatherTime;
                    //毫秒和秒分开处理
                    if (gatherTime.length() > 20) {
                        long ms = Long.parseLong(gatherTime.substring(20, 23));
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gatherTime);
                        unixTimestamp = date.getTime() + ms;
                    } else {
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gatherTime);
                        unixTimestamp = date.getTime();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String RowKey = values.machID + String.valueOf(unixTimestamp);
                String Key = values.operationValue;
                String Value = values.operationData;
                System.out.println("Column Family=f1,  RowKey=" + RowKey + ", Key=" + Key + " ,Value=" + Value);
                Put put = new Put(RowKey.getBytes());
                put.addColumn("f1".getBytes(), Key.getBytes(), Value.getBytes());
                mutator.mutate(put);
                //每满500条刷新一下数据
                if (count >= 500){
                    mutator.flush();
                    count = 0;
                }
                count = count + 1;
            }
        });
    }
}


