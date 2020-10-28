package mycombiner.mywordcountcombiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text k=new Text();
    private IntWritable v=new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string=value.toString();
        String[] split=string.split(" ");
        for (String str:split) {
            k.set(str);
            context.write(k,v);
        }
    }
}
