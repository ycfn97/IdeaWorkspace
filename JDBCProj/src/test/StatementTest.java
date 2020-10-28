package test;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
    @Test
    public void test01(){
        Connection connection=null;
        Statement statement=null;
        try {
            connection=util.jdbcUtil.getConnection();
            statement=connection.createStatement();
            System.out.println(statement);
            String sql="create table if not exists user(name varchar(20),password varchar(30))";
            int i = statement.executeUpdate(sql);
            System.out.println(i+" rows affected.");
            String sql1="insert into user(name,password)values('sunqi','143382')";
            int i1 = statement.executeUpdate(sql1);
            System.out.println(i1+" rows affected.");
            String sql2="insert into user(name,password)values('dongsheng','123456')";
            int i2 = statement.executeUpdate(sql2);
            System.out.println(i2+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.jdbcUtil.close(connection);
        }
    }
}
