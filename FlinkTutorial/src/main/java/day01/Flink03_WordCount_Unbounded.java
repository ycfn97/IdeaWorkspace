package day01;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: PACKAGE_NAME
 * ClassName: Flink03_WordCount_Unbounded
 *
 * @author 18729 created on date: 2020/11/16 13:24
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink03_WordCount_Unbounded {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
//        executionEnvironment.setParallelism(4);全局指定并行度，有限度比局部低
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String host = parameterTool.get("host");
        int port = parameterTool.getInt("port");
        System.out.println(host);
        System.out.println(port);
        DataStreamSource<String> stringDataStreamSource = executionEnvironment.socketTextStream("hadoop01", 7777);
        stringDataStreamSource.flatMap(new MyFlatMapper())
//                .setParallelism(2)设置并行度，优先级最高，web界面优先级第三，命令行第四
                .keyBy(0)
                .sum(1)
                .print();
//                .setParallelism(1);输出设置并行度

        executionEnvironment.execute();
    }
}