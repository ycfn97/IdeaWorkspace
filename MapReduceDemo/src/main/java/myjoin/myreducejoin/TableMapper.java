package myjoin.myreducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {

    private String filename;
    TableBean tableBean=new TableBean();
    Text text=new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line=value.toString();
        if (filename.contains("order")){
            String [] split=line.split("\t");
            text.set(split[1]);
            tableBean.setId(split[0]);
            tableBean.setPid(split[1]);
            tableBean.setAmount(Integer.parseInt(split[2]));
            tableBean.setPname("");
            tableBean.setFlag("order");
        }else {
            String [] split=line.split("\t");
            text.set(split[0]);
            tableBean.setId("");
            tableBean.setPid(split[0]);
            tableBean.setAmount(0);
            tableBean.setPname(split[1]);
            tableBean.setFlag("pd");
        }
        context.write(text,tableBean);
    }

    /**
     * 输入文件有多个，在setup方法里面获取每行数据的filename，
     * 看类的继承数，ctrl+h
     * 提升全局：ctrl+alt+f
     *
     * @param context 承上启下
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        filename = inputSplit.getPath().getName();
    }
}
