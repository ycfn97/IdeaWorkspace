package test;

import org.junit.Test;
import util.jdbcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTest {

    @Test
    public void test05(){
        Connection connection=null;
        try {
            connection=util.jdbcUtil.getConnection();
            System.out.println(connection);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.close(connection);
        }
    }

    @Test
    public void test04() throws ClassNotFoundException, SQLException, IOException {
//        String driverClassName="com.mysql.cj.jdbc.Driver";
//        String url="jdbc:mysql://192.168.150.4:3306/jdbc";
//        String user="sunqi";
//        String password="143382";

        Properties properties=new Properties();
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(inputStream);
        inputStream.close();

        String driverClassName=properties.getProperty("driverClassName");
        String url=properties.getProperty("url");
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");

        Class.forName(driverClassName);
        Connection connection=DriverManager.getConnection(url,user,password);
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void test03() throws SQLException, ClassNotFoundException {
//        只要加载类，就会自动完成注册（静态代码块在类加载时自动完成注册)
//        public class Driver extends NonRegisteringDriver implements java.sql.Driver {
//            public Driver() throws SQLException {
//            }
//
//            static {
//                try {
//                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//                } catch (SQLException var1) {
//                    throw new RuntimeException("Can't register driver!");
//                }
//            }
//        }
//        Class aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Driver driver=(Driver) aClass.newInstance();
        String url="jdbc:mysql://192.168.150.4:3306/jdbc";
//        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, "sunqi", "143382");
        System.out.println(connection);
    }

    @Test
    public void test02() throws SQLException {
        Driver driver=new com.mysql.cj.jdbc.Driver();
        String url="jdbc:mysql://192.168.150.4:3306/jdbc";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, "sunqi", "143382");
        System.out.println(connection);
    }

    @Test
    public void test01() throws SQLException {
        Driver driver=new com.mysql.cj.jdbc.Driver();
        String url="jdbc:mysql://192.168.150.4:3306/jdbc";
        Properties properties=new Properties();
        properties.setProperty("user","sunqi");
        properties.setProperty("password","143382");
        Connection connection=driver.connect(url,properties);
        System.out.println(connection);
    }
}
