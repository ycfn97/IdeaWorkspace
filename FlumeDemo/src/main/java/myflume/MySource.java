package myflume;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MySource extends AbstractSource implements Configurable, PollableSource {

    private String field;
    private Long delay;

    @Override
    public Status process() throws EventDeliveryException {
        HashMap<String,String> headerMap=new HashMap<>();

        SimpleEvent simpleEvent = new SimpleEvent();

        for (int i = 0; i < 5; i++) {
            simpleEvent.setHeaders(headerMap);
            simpleEvent.setBody((field+i).getBytes());
            getChannelProcessor().processEvent(simpleEvent);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Status.BACKOFF;
            }
        }
        return Status.READY;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    @Override
    public void configure(Context context) {
        delay = context.getLong("delay");
        field = context.getString("field","Hello");
    }
}
