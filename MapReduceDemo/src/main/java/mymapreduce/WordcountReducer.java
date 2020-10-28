package mymapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordcountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    int sum;
    IntWritable V=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        sum=0;
        for (IntWritable i:values) {
            sum+=i.get();
        }
        V.set(sum);
        context.write(key,V);
    }
}
