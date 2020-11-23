package day03.sink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;

import java.util.ArrayList;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03.sink
 * ClassName: Sink_ES
 *
 * @author 18729 created on date: 2020/11/19 10:40
 * @version 1.0
 * @since JDK 1.8
 */
public class Sink_ES {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> sensor = executionEnvironment.readTextFile("sensor");

        ArrayList<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("hadoop01",9200));
        ElasticsearchSink<String> build = new ElasticsearchSink.Builder<String>(httpHosts, new MyEsFunc()).build();

        System.out.println(build);
        sensor.addSink(build);
        executionEnvironment.execute();
    }
}
