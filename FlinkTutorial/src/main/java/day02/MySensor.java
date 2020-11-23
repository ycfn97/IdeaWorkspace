package day02;

import bean.SensorReading;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.HashMap;
import java.util.Random;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: MySensor
 *
 * @author 18729 created on date: 2020/11/17 11:39
 * @version 1.0
 * @since JDK 1.8
 */
public class MySensor implements SourceFunction<SensorReading> {
    private boolean running=true;

    @Override
    public void run(SourceContext<SensorReading> sourceContext) throws Exception {
        Random random = new Random();
        HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            stringDoubleHashMap.put("sensor_"+(i+1),60+random.nextGaussian()*20);
        }
        while (running){
            for( String sensorId: stringDoubleHashMap.keySet() ){
                Double newTemp = stringDoubleHashMap.get(sensorId) + random.nextGaussian();
                stringDoubleHashMap.put(sensorId, newTemp);
                sourceContext.collect( new SensorReading(sensorId, System.currentTimeMillis(), newTemp));
            }
            Thread.sleep(1000L);
        }
    }
    @Override
    public void cancel() {
        this.running=false;
    }
}
