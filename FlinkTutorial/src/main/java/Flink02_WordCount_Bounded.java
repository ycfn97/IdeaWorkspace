import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: PACKAGE_NAME
 * ClassName: Flink02_WordCount_Bounded
 *
 * @author 18729 created on date: 2020/11/16 11:54
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink02_WordCount_Bounded {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> stringDataStream = executionEnvironment.readTextFile("E:\\IdeaWorkspace\\FlinkTutorial\\input\\hello.txt");

        stringDataStream
                .flatMap(new MyFlatMapper())
                .keyBy(0)
                .sum(1)
                .print();

        executionEnvironment.execute("Flink02_WordCount_Bounded");
    }
}
