package phoenix;

import java.sql.*;
import java.util.Properties;

public class TestThick {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:phoenix:hadoop01,hadoop02,hadoop03:2181";
        Properties props = new Properties();
        props.put("phoenix.schema.isNamespaceMappingEnabled","true");
        Connection connection = DriverManager.getConnection(url,props);
        PreparedStatement ps = connection.prepareStatement("select * from \"student\"");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1)+":" +rs.getString(2));
        }
    }
}
