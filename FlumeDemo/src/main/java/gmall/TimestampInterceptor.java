package gmall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlumeDemo
 * Package: gmall
 * ClassName: TimestampInterceptor
 *
 * @author 18729 created on date: 2020/10/9 20:46
 * @version 1.0
 * @since JDK 1.8
 */
public class TimestampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String string = new String(body, StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(string);
        if (jsonObject.containsKey("ts")){
            String ts = jsonObject.getString("ts");
            event.getHeaders().put("timestamp",ts);
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event:list){
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new TimestampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
