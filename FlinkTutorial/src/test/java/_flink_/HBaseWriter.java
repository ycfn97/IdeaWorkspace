package _flink_;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: _flink_
 * ClassName: HBaseWriter
 *
 * @author 18729 created on date: 2020/11/19 10:22
 * @version 1.0
 * @since JDK 1.8
 */
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 写入HBase
 * 继承RichSinkFunction重写父类方法
 *
 * 写入hbase时500条flush一次, 批量插入, 使用的是writeBufferSize
 */
class HBaseWriter extends RichSinkFunction<DeviceData>{
    private static final Logger logger = LoggerFactory.getLogger(HBaseWriter.class);

    private static org.apache.hadoop.conf.Configuration configuration;
    private static Connection connection = null;
    private static BufferedMutator mutator;
    private static int count = 0;

    @Override
    public void open(Configuration parameters) throws Exception {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.master", "192.168.3.101:60020");
        configuration.set("hbase.zookeeper.quorum", "192.168.3.101");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedMutatorParams params = new BufferedMutatorParams(TableName.valueOf("t1"));
        params.writeBufferSize(2 * 1024 * 1024);
        mutator = connection.getBufferedMutator(params);
    }

    @Override
    public void close() throws IOException {
        if (mutator != null) {
            mutator.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public void invoke(DeviceData values, Context context) throws Exception {
        //Date 1970-01-06 11:45:55  to 445555000
        long unixTimestamp= 0;
        try {
            String gatherTime = values.gatherTime;
            //毫秒和秒分开处理
            if (gatherTime.length() > 20) {
                long ms = Long.parseLong(gatherTime.substring(20, 23));
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gatherTime);
                unixTimestamp = date.getTime() + ms;
            } else {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gatherTime);
                unixTimestamp = date.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String RowKey = values.machID + String.valueOf(unixTimestamp);
        String Key = values.operationValue;
        String Value = values.operationData;
        System.out.println("Column Family=f1,  RowKey=" + RowKey + ", Key=" + Key + " ,Value=" + Value);
        Put put = new Put(RowKey.getBytes());
        put.addColumn("f1".getBytes(), Key.getBytes(), Value.getBytes());
        mutator.mutate(put);
        //每满500条刷新一下数据
        if (count >= 500){
            mutator.flush();
            count = 0;
        }
        count = count + 1;
    }
}