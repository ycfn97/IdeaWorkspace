package mypartion.mypartion;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PartionDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);

        job.setJarByClass(PartionDriver.class);
        job.setMapperClass(PartionMapper.class);
        job.setReducerClass(PartionReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        job.setPartitionerClass(PartionPartioner.class);
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job,new Path("E:\\hadooplocal\\input\\input\\inputflow"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\hadooplocal\\mypartion"));

        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
