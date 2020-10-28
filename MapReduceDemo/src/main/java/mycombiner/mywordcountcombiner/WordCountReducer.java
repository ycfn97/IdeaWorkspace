package mycombiner.mywordcountcombiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable,Text, IntWritable> {
    int sum=0;
    IntWritable intWritable=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable i:values) {
            sum=sum+i.get();
        }
        intWritable.set(sum);
        context.write(key,intWritable);
    }
}
