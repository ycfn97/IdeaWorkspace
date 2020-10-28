package test;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 预处理，避免了SQL注入
 */
public class PreparedStatementTest {

    @Test
    public void test03(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=util.jdbcUtil.getConnection();
            String sql="create table if not exists teacher(id int auto_increment,name varchar(20),age int,salary double,birthday date,primary key(id))";
            preparedStatement=connection.prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            System.out.println(i+" rows affected.");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.jdbcUtil.close(connection,preparedStatement);
        }
    }

    @Test
    public void test02(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            /**
             * 获得连接
             */
            connection=util.jdbcUtil.getConnection();
            /**
             * 书写sql并通过预处理对sql字段进行预处理
             */
            String sql="insert into customer(name,age,gender,email)values(?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"sunqi");
            preparedStatement.setInt(2,23);
            preparedStatement.setString(3,"男");
            preparedStatement.setString(4,"ycfn97@gmail.com");
            /**
             * 执行更新
             */
            int i = preparedStatement.executeUpdate();
            System.out.println(i+" rows affected.");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            /**
             * 释放连接
             */
            util.jdbcUtil.close(connection,preparedStatement);
        }
    }

    @Test
    public void test1(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=util.jdbcUtil.getConnection();
            String sql="create table if not exists customer(id int auto_increment,name varchar(20),age int,gender enum('男','女') default '男',email varchar(50),primary key(id))";
            preparedStatement=connection.prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            System.out.println(i+" rows affected.");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.jdbcUtil.close(connection,preparedStatement);
        }

    }
}
