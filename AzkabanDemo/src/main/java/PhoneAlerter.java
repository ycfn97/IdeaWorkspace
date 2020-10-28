import azkaban.alert.Alerter;
import azkaban.executor.ExecutableFlow;
import azkaban.executor.Executor;
import azkaban.executor.ExecutorManagerException;
import azkaban.sla.SlaOption;
import azkaban.utils.Props;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import java.util.List;

public class PhoneAlerter implements Alerter {

    private static final Logger logger = Logger.getLogger(PhoneAlerter.class);

    private String appKey;
    private String url;

    public PhoneAlerter(Props props) {
        appKey = props.getString("my.alert.appKey", "2b12b530-7e4a-4c7f-ace9-82b492e8c86c");
        url = props.getString("my.alert.url", "http://api.aiops.com/alert/api/event");
        logger.info("Appkey: " + appKey);
        logger.info("URL: " + url);
    }

    /**
     * 成功的通知
     *
     * @param exflow
     * @throws Exception
     */
    @Override
    public void alertOnSuccess(ExecutableFlow exflow) throws Exception {

    }

    /**
     * 出现问题的通知
     *
     * @param exflow
     * @param extraReasons
     * @throws Exception
     */
    @Override
    public void alertOnError(ExecutableFlow exflow, String... extraReasons) throws Exception {
        //一般来说网络电话服务都是通过HTTP请求发送的，这里可以调用shell发送HTTP请求
        JsonObject alert = new JsonObject();
        alert.addProperty("app", appKey);
        alert.addProperty("eventId", exflow.getId());
        alert.addProperty("eventType", "trigger");
        alert.addProperty("alarmContent", exflow.getId() + " fails!");
        alert.addProperty("priority", "2");
        String[] cmd = new String[8];
        cmd[0] = "curl";
        cmd[1] = "-H";
        cmd[2] = "Content-type: application/json";
        cmd[3] = "-X";
        cmd[4] = "POST";
        cmd[5] = "-d";
        cmd[6] = alert.toString();
        cmd[7] = url;
        logger.info("Sending phone alert!");
        Runtime.getRuntime().exec(cmd);

    }

    /**
     * 首次出现问题的通知
     *
     * @param exflow
     * @throws Exception
     */
    @Override
    public void alertOnFirstError(ExecutableFlow exflow) throws Exception {

    }

    @Override
    public void alertOnSla(SlaOption slaOption, String slaMessage) throws Exception {

    }

    @Override
    public void alertOnFailedUpdate(Executor executor, List<ExecutableFlow> executions, ExecutorManagerException e) {

    }

}