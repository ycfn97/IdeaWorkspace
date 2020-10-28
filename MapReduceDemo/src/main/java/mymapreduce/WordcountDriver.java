package mymapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 远程提交到HA
 */
public class WordcountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//         获取配置和job对象
        Configuration entries = new Configuration();
        Job job=Job.getInstance(entries);

        //设置HDFS NameNode的地址
        entries.set("fs.defaultFS", "mycluster");
        //指定MapReduce运行在Yarn上
        entries.set("mapreduce.framework.name","yarn");
        //指定mapreduce可以在远程集群运行
        entries.set("mapreduce.app-submission.cross-platform","true");
        //指定Yarn resourcemanager的位置
        entries.set("yarn.resourcemanager.hostname","cluster-yarn1");

//         关联本driver类
        job.setJarByClass(WordcountDriver.class);
//         关联map和reduce类
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);
//         设置map最终输出数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//         设置reducer最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
//         设置程序输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
//         提交job
        boolean b=job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}