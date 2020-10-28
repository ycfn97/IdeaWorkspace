package test;

import javabean.Customer;
import javabean.Student;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ResultSetTest {

    @Test
    public <T> void test4() {
        List<T> list = new ArrayList<T>(); // 保存多个对象, 有多少条记录就有多少个对象.
        String sql = "select id,name,age, gender,email from customer where id > ?";
        Object[] args = {0};
        Class<T> clazz = (Class<T>) Customer.class;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = util.jdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                T object = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1); // 标签就是列名, 同时也Student类的属性名;
                    Object value = resultSet.getObject(columnLabel); // value将来可用于对象的属性值.
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(object, value);
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.jdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }

    @Test
    public void test3(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=util.jdbcUtil.getConnection();
            String sql = "select id, name, age, mobile, gender from student where id > ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,2);
//            将查询结果放进结果集中
            resultSet=preparedStatement.executeQuery();
//            metadata中包含虚表的结构和原始数据
            ResultSetMetaData metaData=resultSet.getMetaData();
            int columnCount=metaData.getColumnCount();
            for (int i = 0; i <columnCount; i++) {
//                获取虚表列名
                String columnLabel=metaData.getColumnLabel(i+1);
                System.out.print(columnLabel+"\t");
            }
            System.out.println();
            System.out.println("------------------------------------------------------------");
            while (resultSet.next()){
                for (int i = 0; i <columnCount; i++) {
                    String columnLabel=metaData.getColumnLabel(i+1);
//                    根据列标签获取当前行的列值
                    Object value=resultSet.getObject(columnLabel);
                    System.out.print(value+ "\t");
                }
                System.out.println();
            }
            System.out.println("-------------------------------------------------------------");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.jdbcUtil.close(connection,preparedStatement,resultSet);
        }
    }

    @Test
    public void test2() {
        List<Student> list = new ArrayList<>(); // 保存多个对象, 有多少条记录就有多少个对象.
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = util.jdbcUtil.getConnection();
            String sql = "select name, age, id, mobile, gender from student where id > ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // 使用列标签比使用列索引更好. 列标签就是虚表的列名
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String mobile = resultSet.getString("mobile");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, mobile, gender); // ORM Object Relation Mapping
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.jdbcUtil.close(connection, preparedStatement, resultSet);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }



    @Test
    public void test1() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = util.jdbcUtil.getConnection();
            String sql = "select name, age, id, mobile mob, gender stuGender from student where id > ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // 使用列标签比使用列索引更好. 列标签就是虚表的列名
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String mobile = resultSet.getString("mob");
                String gender = resultSet.getString("stuGender");
                System.out.println(id + "\t" + name + "\t" + age + "\t" + mobile + "\t" + gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.jdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }
}
