package mypartion.myflowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 循环输出，避免总流量相同情况
        for (Text text : values) {
            context.write(text, key);
        }
    }

    //    @Override
//    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
//        long sum_upFlow=0;
//        long sum_downFlow=0;
//        for (FlowBean flowBean:values) {
//            sum_downFlow+=flowBean.getDownFlow();
//            sum_upFlow+=flowBean.getUpFlow();
//        }
//        FlowBean v=new FlowBean(sum_upFlow,sum_downFlow);
//
//        context.write(key,v);
//    }
}
