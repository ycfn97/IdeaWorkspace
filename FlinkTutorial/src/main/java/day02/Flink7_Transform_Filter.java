package day02;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: Flink7_Transform_Filter
 *
 * @author 18729 created on date: 2020/11/17 16:45
 * @version 1.0
 * @since JDK 1.8
 */
public class Flink7_Transform_Filter {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.readTextFile("E:\\IdeaWorkspace\\FlinkTutorial\\sensor\\sensor.txt")
                .filter(new FilterFunction<String>() {
                    @Override
                    public boolean filter(String s) throws Exception {
                        Double s1 = Double.parseDouble(s.split(",")[2]);
                        return s1>30.0D;
                    }
                }).print();
        executionEnvironment.execute();
    }
}
