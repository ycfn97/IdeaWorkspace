package combinetextinputformat;

import mymapreduce.WordcountDriver;
import mymapreduce.WordcountMapper;
import mymapreduce.WordcountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        1获取配置和job对象
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);

//        2关联driver类
        job.setJarByClass(WordcountDriver.class);

//        3关联map和reduce类
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);

//        4关联map最终输出类
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

//        5关联reduce最终输出类
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 如果不设置InputFormat，它默认用的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);

        //虚拟存储切片最大值设置4m
        CombineTextInputFormat.setMaxInputSplitSize(job, 20971520);

//        6设置input和output路径
        FileInputFormat.setInputPaths(job,new Path("E:\\hadooplocal\\input\\input\\inputcombinetextinputformat"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\hadooplocal\\combinetextoutput02"));

//        7提交job
        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
