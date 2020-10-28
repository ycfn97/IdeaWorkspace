package mymapreducecompress;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordcountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text K=new Text();
    private IntWritable V=new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] split = string.split(" ");
        for (String str:split) {
            K.set(str);
            context.write(K,V);
        }
    }
}
