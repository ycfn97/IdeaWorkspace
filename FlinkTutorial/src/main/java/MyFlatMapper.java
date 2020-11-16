import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: PACKAGE_NAME
 * ClassName: MyFlatMapper
 *
 * @author 18729 created on date: 2020/11/16 11:31
 * @version 1.0
 * @since JDK 1.8
 */
public class MyFlatMapper implements FlatMapFunction<String, Tuple2<String,Integer>> {
    @Override
    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
        String[] s1 = s.split(" ");
        for (String word:s1) {
            collector.collect(new Tuple2<String, Integer>(word,1));
        }
    }
}
