package day03.sink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03
 * ClassName: Sink_Redis
 *
 * @author 18729 created on date: 2020/11/18 11:26
 * @version 1.0
 * @since JDK 1.8
 */
public class Sink_Redis {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> sensor = executionEnvironment.readTextFile("sensor");
        FlinkJedisPoolConfig hadoop01 = new FlinkJedisPoolConfig.Builder().setHost("hadoop01").setPort(6379).build();
        sensor.addSink(new RedisSink<>(hadoop01, new RedisMapper<String>() {
            @Override
            public RedisCommandDescription getCommandDescription() {
                return new RedisCommandDescription(RedisCommand.HSET,"sensor");
            }
            @Override
            public String getKeyFromData(String s) {
                return s.split(",")[0];
            }
            @Override
            public String getValueFromData(String s) {
                return s.split(",")[2];
            }
        }));
        executionEnvironment.execute();
    }
}
