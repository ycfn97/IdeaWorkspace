package test;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
    /**
     * 通用查询操作
     * @param connection
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    public static<T>List<T> query(Connection connection,Class<T> clazz,String sql,Object...args) throws Exception {
        List<T> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                T object = clazz.newInstance();
                for (int i = 0; i < count; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(columnLabel);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(object, value);
                }
                list.add(object);
            }
            return list;
        } finally {
            util.jdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }


    /**
     * 通用的查询
     * @param connection 调用者传入的连接对象
     * @param sql 要执行的sql
     * @param args 用于替换sql中?的实参列表
     */
    public static void view(Connection connection, String sql, Object... args) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql); // 预编译sql
            for (int i = 0; i < args.length; i++) { // 批量替换sql中的所有?为实参.
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery(); // 执行查询
            ResultSetMetaData metaData = resultSet.getMetaData(); // metaData对象中包含的是虚表的表结构等原始数据
            int columnCount = metaData.getColumnCount(); // 获取虚表的列数
            System.out.println("----------------------------------------------------------------------------");
            for (int i = 0; i < columnCount; i++) { // 遍历所有列
                String columnLabel = metaData.getColumnLabel(i + 1); // 根据列索引依次获取列标签
                System.out.print(columnLabel + "\t"); // 打印表头
            }
            System.out.println();
            System.out.println("----------------------------------------------------------------------------");
            while (resultSet.next()) { // 遍历数据
                for (int i = 0; i < columnCount; i++) { // 再一次遍历所有列
                    String columnLabel = metaData.getColumnLabel(i + 1); // 动态取出各个列标签
                    Object value = resultSet.getObject(columnLabel); // 根据列标签再取实际值, 更灵活
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------");
        } finally {
            util.jdbcUtil.close(null, preparedStatement, resultSet);
        }
    }


    /**
     *通用更新操作，用于执行DDL,DML(除select)
     * @param connection 连接对象
     * @param sql
     * @param args 可变参数用于传递sql中的实参
     * @return
     * @throws Exception
     */
    public static int update(Connection connection,String sql,Object... args) throws Exception{
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            for (int i=0;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
            int i = preparedStatement.executeUpdate();
            return i;
        }finally {
            util.jdbcUtil.close(null,preparedStatement);
        }
    }


    /**
     * 通用更新操作，用于执行DDL,DML(除select)
     * @param sql
     * @param args 可变参数用于传递sql中的实参
     * @return
     */
    public static int update(String sql,Object... args) throws Exception{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=util.jdbcUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            for (int i=0;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
            int i = preparedStatement.executeUpdate();
            return i;
        }finally {
            util.jdbcUtil.close(connection,preparedStatement);
        }
    }
}
