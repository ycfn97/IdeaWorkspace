
import java.lang.Integer;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: PACKAGE_NAME
 * ClassName: WordCount
 *
 * @author 18729 created on date: 2020/11/18 10:08
 * @version 1.0
 * @since JDK 1.8
 */
public class WordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> hadoop01 = executionEnvironment.socketTextStream("hadoop01", 7777);
        hadoop01.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                collector.collect(new Tuple2<String,Integer>(s,1));
            }
        }).keyBy(0).sum(1).print();
        executionEnvironment.execute();
    }
}
