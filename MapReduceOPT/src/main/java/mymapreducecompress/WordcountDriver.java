package mymapreducecompress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DeflateCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordcountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//         获取配置和job对象
        Configuration entries = new Configuration();

        // 开启map端输出压缩
        entries.setBoolean("mapreduce.map.output.compress", true);
//        // 设置map端输出压缩方式
        entries.setClass("mapreduce.map.output.compress.codec", DeflateCodec.class, CompressionCodec.class);

        Job job=Job.getInstance(entries);
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

        // 设置reduce端输出压缩开启
//        FileOutputFormat.setCompressOutput(job, true);
        // 设置压缩的方式
//        FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);

//         设置程序输入输出路径
        FileInputFormat.setInputPaths(job,new Path("E:\\hadooplocal\\input\\input\\inputbigwords"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\hadooplocal\\compressoutput"));
//         提交job
        boolean b=job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}