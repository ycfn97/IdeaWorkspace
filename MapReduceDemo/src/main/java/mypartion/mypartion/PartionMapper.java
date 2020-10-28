package mypartion.mypartion;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PartionMapper extends Mapper<LongWritable, Text,FlowBean,Text> {
    Text text=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string=value.toString();
        String[] split=string.split("\t");

        String phoneNum=split[1];
        long upFlow=Long.parseLong(split[split.length-3]);
        long downFlow=Long.parseLong(split[split.length-2]);

        text.set(phoneNum);
        FlowBean flowBean=new FlowBean(upFlow,downFlow);
        context.write(flowBean,text);
    }
}
