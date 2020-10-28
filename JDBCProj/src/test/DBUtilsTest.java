package test;

import javabean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtilsTest {


    @Test
    public void test03() throws SQLException {
        QueryRunner queryRunner=new QueryRunner();
        Connection connection=util.jdbcUtil.getConnection();
        String sql="select count(*) from customer";
        ScalarHandler<Customer> beanListHandler = new ScalarHandler();
        Object query = queryRunner.query(connection, sql, beanListHandler);
        System.out.println(query);
        connection.close();
    }


    @Test
    public void test02() throws SQLException {
        QueryRunner queryRunner=new QueryRunner();
        Connection connection=util.jdbcUtil.getConnection();
        String sql="select * from customer where id >?";
        BeanListHandler<Customer> beanListHandler = new BeanListHandler(Customer.class);
        List<Customer> query = queryRunner.query(connection, sql, beanListHandler, 0);
        System.out.println(query);
        connection.close();
    }


    @Test
    public void test01() throws SQLException {
        QueryRunner runner=new QueryRunner();
        Connection connection=util.jdbcUtil.getConnection();
        String sql="insert into customer(name,age,gender,email)values(?,?,?,?)";
        int update = runner.update(connection, sql, "sunqi", 23, "男", "ycfn97@gmail.com");
        System.out.println(update+" rows affected.");
        connection.close();
    }
}
