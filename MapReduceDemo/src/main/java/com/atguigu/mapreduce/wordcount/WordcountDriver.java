package com.atguigu.mapreduce.wordcount;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 1 获取配置信息以及获取job对象
        Configuration conf = new Configuration();

        //设置HDFS NameNode的地址
        conf.set("fs.defaultFS", "hdfs://hadoop01:9820");
        //指定MapReduce运行在Yarn上
        conf.set("mapreduce.framework.name","yarn");
        //指定mapreduce可以在远程集群运行
        conf.set("mapreduce.app-submission.cross-platform","true");
        //指定Yarn resourcemanager的位置
        conf.set("yarn.resourcemanager.hostname","hadoop02");

        Job job = Job.getInstance(conf);

        //远程向集群提交任务,因为集群上没有自己写的代码,因此要设置下jar包
        job.setJar("E:/IdeaWorkspace/MapReduceDemo/target/MapReduceDemo-1.0-SNAPSHOT.jar");

        // 2 关联本Driver程序的jar
        job.setJarByClass(WordcountDriver.class);

        // 3 关联Mapper和Reducer的jar
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);

        // 4 设置Mapper输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5 设置最终输出kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 6 设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}