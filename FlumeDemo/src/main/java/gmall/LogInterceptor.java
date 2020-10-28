package gmall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;


import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class LogInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {

        Event resultEvent = etl(event);

        return resultEvent;
    }

    private Event etl(Event event) {
        // String body = new String(event.getBody(), Charset.forName("utf-8"));
        String body = new String(event.getBody(), StandardCharsets.UTF_8);
        try {
            JSON.parse(body);
        }catch (JSONException e){
            return null ;
        }

        return event ;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        Iterator<Event> it = events.iterator();
        while(it.hasNext()){
            Event event = intercept(it.next());
            if(event == null){
                //从集合中移除
                it.remove();
            }
        }

        return events ;
    }

    @Override
    public void close() {

    }


    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}


