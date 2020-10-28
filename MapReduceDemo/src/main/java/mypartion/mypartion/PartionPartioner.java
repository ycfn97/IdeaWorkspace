package mypartion.mypartion;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartionPartioner extends Partitioner<FlowBean,Text> {
    @Override
    public int getPartition( FlowBean flowBean,Text text, int numPartitions) {
        String phoneNum=text.toString().substring(0,3);
        int partition=4;
        // 2 判断是哪个省
        if ("136".equals(phoneNum)) {
            partition = 0;
        }else if ("137".equals(phoneNum)) {
            partition = 1;
        }else if ("138".equals(phoneNum)) {
            partition = 2;
        }else if ("139".equals(phoneNum)) {
            partition = 3;
        }

        return partition;
    }
}
