package day03.sink;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03.sink
 * ClassName: MyEsFunc
 *
 * @author 18729 created on date: 2020/11/19 10:54
 * @version 1.0
 * @since JDK 1.8
 */
public class MyEsFunc implements ElasticsearchSinkFunction<String> {
    @Override
    public void process(String s, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
        String[] split = s.split(",");
        HashMap<String,String> stringHashMap = new HashMap<>();
        stringHashMap.put("id",split[0]);
        stringHashMap.put("ts",split[1]);
        stringHashMap.put("temp",split[2]);

        IndexRequest source = Requests.indexRequest().index("sensor").type("_doc").source(stringHashMap);
        requestIndexer.add(source);
        System.out.println(stringHashMap);
    }
}
