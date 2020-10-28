package join.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable,Text,Text,TableBean> {

    private String filename;
    private Text outK = new Text();
    private TableBean outV = new TableBean();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取对应文件名称
        InputSplit split = context.getInputSplit();
        FileSplit fileSplit = (FileSplit) split;
        filename = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //判断是哪个文件,然后针对文件进行不同的操作
        if(filename.contains("order")){  //订单表的处理
            String[] split = line.split("\t");
            //封装outK
            outK.set(split[1]);
            //封装outV
            outV.setId(split[0]);
            outV.setPid(split[1]);
            outV.setAmount(Integer.parseInt(split[2]));
            outV.setPname("");
            outV.setFlag("order");
        }else {                             //商品表的处理
            String[] split = line.split("\t");
            //封装outK
            outK.set(split[0]);
            //封装outV
            outV.setId("");
            outV.setPid(split[0]);
            outV.setAmount(0);
            outV.setPname(split[1]);
            outV.setFlag("pd");
        }
        //写出KV
        context.write(outK,outV);
    }
}