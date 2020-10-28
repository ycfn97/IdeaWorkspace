package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class jdbcUtil {

    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if (dataSource==null){
            try {
                Properties properties=new Properties();
                InputStream in=jdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
                properties.load(in);
                in.close();
                dataSource= DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Connection connection = dataSource.getConnection();
        return connection;
    }

    /**
     *获得数据库连接
     * @return 连接对象
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnectionOld() throws IOException, ClassNotFoundException, SQLException {
        Properties properties=new Properties();
        /**
         * 通过properties配置文件存储驱动和数据库信息，并通过IO流读取到properties集合中
         */
        InputStream inputStream=jdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(inputStream);
        inputStream.close();

        /**
         * 从集合中获取驱动和数据库信息供类加载和驱动获得连接
         */
        String driverClassName=properties.getProperty("driverClassName");
        String url=properties.getProperty("url");
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");

        /**
         * 自动注册驱动，并通过驱动管理器获得数据库连接
         */
        Class.forName(driverClassName);
        Connection connection= DriverManager.getConnection(url,user,password);
        return  connection;
    }

    /**
     *释放资源
     * @param connection 数据库连接
     */
    public static void close(Connection connection){
        close(connection,null);
    }

    /**
     * 释放资源
     * @param connection 数据库连接
     * @param statement 处理流
     */
    public static void close(Connection connection, Statement statement){
        close(connection,statement,null);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultset){
        if (resultset!=null){
            try {
                resultset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
