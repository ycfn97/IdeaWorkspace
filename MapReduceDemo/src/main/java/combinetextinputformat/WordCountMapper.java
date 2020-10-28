package combinetextinputformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text text=new Text();
    IntWritable intWritable=new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string=value.toString();
        String[] split=string.split(" ");
        for (String str:split) {
            text.set(str);
        }
        context.write(text,intWritable);
    }
}
