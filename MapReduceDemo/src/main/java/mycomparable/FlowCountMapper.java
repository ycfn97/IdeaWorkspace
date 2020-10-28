package mycomparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text,FlowBean,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits=value.toString().split("\t");
        context.write(new FlowBean(Long.parseLong(splits[splits.length-3]),Long.parseLong(splits[splits.length-2])),new Text(splits[1]));
    }
}
