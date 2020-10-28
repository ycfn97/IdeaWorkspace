package test;

import javabean.Student;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommonUtilTest {

    @Test
    public void test08(){
        Connection connection=null;
        try {
            connection=util.jdbcUtil.getConnection();
            List list=CommonUtil.query(connection, Student.class, "select * from student where id > ?", 1);
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test07(){
        Connection connection=null;
        try {
            connection=util.jdbcUtil.getConnection();
            int i = CommonUtil.update(connection, "delete from student where name='小芳'");
            System.out.println(i+" rows affected.");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.jdbcUtil.close(connection);
        }
    }

    @Test
    public void test06(){
        Connection connection=null;
        try {
            connection=util.jdbcUtil.getConnection();
            int 小芳 = CommonUtil.update(connection, "insert into student(name, age, mobile) values(?, ?, ?)", "小芳", 20, "134234234234");
            System.out.println(小芳+" rows affected.");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.jdbcUtil.close(connection);
        }
    }

    @Test
    public void test05() {
        try {
            int update = CommonUtil.update("create table if not exists student(" +
                    "id int auto_increment," +
                    "name varchar(20)," +
                    "age int," +
                    "mobile varchar(20)," +
                    "gender enum('男','女') default '男'," +
                    "primary key(id))");
            System.out.println(update+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int 小明 = CommonUtil.update("insert into student(name, age, mobile) values(?, ?, ?)", "小明", 20, "134234234234");
            System.out.println(小明+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test04() throws Exception {
        String sql="insert into teacher(name,age,salary,birthday)values(?,?,?,?)";
        int sunqi = CommonUtil.update(sql, "sunqi", 23, 5000, "1997-12-30");
        System.out.println(sunqi+" rows affected.");
    }
}
