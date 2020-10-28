package test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
    @Test
    public void test02() throws Exception {
        Properties properties=new Properties();
        InputStream in=getClass().getClassLoader().getResourceAsStream("druid.properties");
        properties.load(in);
        in.close();
        DataSource dataSource= DruidDataSourceFactory.createDataSource(properties);
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void test01() throws SQLException {
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://192.168.150.4:3306/jdbc");
        druidDataSource.setUsername("sunqi");
        druidDataSource.setPassword("143382");
        Connection connection=druidDataSource.getConnection();
        System.out.println(connection.getClass());
        connection.close();
    }
}
