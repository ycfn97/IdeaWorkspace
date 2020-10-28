package myetl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string=value.toString();
        context.getCounter("ETL","TOTAL").increment(1);
        boolean result=parseLog(string,context);
        if (result) context.write(new Text(string),NullWritable.get());
    }

    private boolean parseLog(String string, Context context) {
        if (string.split(" ").length>=11){
            context.getCounter("ETL","True").increment(1);
            return true;
        }
        context.getCounter("ETL","FALSE").increment(1);
        return false;
    }
}
