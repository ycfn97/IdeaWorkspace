package myjoin.myreducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {
    ArrayList<TableBean> orderBeans;
    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
        orderBeans=new ArrayList<>();
        TableBean pdBean=new TableBean();
        for (TableBean tableBean:values) {
            if ("order".equals(tableBean.getFlag())){
                TableBean tmpBean=new TableBean();
                try {
                    BeanUtils.copyProperties(tmpBean,tableBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                orderBeans.add(tmpBean);
            }else {
                try {
                    BeanUtils.copyProperties(pdBean,tableBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (TableBean orderBean:orderBeans){
            orderBean.setPname(pdBean.getPname());
            context.write(orderBean,NullWritable.get());
        }
    }
}
