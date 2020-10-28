package mypartion.myflowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowsumDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[] { "E:\\hadooplocal\\input\\input\\inputflow", "E:\\hadooplocal\\partitioneroutput01" };
        Configuration configuration=new Configuration();
        Job job= Job.getInstance(configuration);

        job.setJarByClass(FlowsumDriver.class);

        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        // 4 指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
