package day03.sink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day03
 * ClassName: JdbcSink
 *
 * @author 18729 created on date: 2020/11/18 14:13
 * @version 1.0
 * @since JDK 1.8
 */
public class JdbcSink extends RichSinkFunction<String> {
    Connection conn = null;
    PreparedStatement insertStmt = null;
    @Override
    public void open(Configuration parameters) throws Exception {
        conn= DriverManager.getConnection("jdbc:mysql://hadoop01:3306/school?serverTimezone=GMT%2B8&useSSL=false","root","143382");
        insertStmt=conn.prepareStatement("insert into sensor(id,temp) values(?,?) on duplicate key update temp=?");
    }
    @Override
    public void invoke(String value, Context context) throws Exception {
        String[] split = value.split(",");
            insertStmt.setString(1, split[0]);
            insertStmt.setDouble(2, Double.parseDouble(split[2]));
            insertStmt.setDouble(3, Double.parseDouble(split[2]));
            insertStmt.execute();
    }
    @Override
    public void close() throws Exception {
        insertStmt.close();
        conn.close();
    }

}
