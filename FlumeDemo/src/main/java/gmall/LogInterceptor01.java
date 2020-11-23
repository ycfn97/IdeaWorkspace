package gmall;

import com.alibaba.fastjson.JSON;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlumeDemo
 * Package: gmall
 * ClassName: LogInterceptor01
 *
 * @author 18729 created on date: 2020/11/23 18:53
 * @version 1.0
 * @since JDK 1.8
 */
public class LogInterceptor01 implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String string = new String(event.getBody(), StandardCharsets.UTF_8);
        try {
            JSON.parse(string);
            return event;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        Iterator<Event> iterator = list.iterator();
        List<Event> events = null;
        while (iterator.hasNext()){
            Event event =intercept(iterator.next());
            if (event!=null) events.add(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new LogInterceptor01();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
