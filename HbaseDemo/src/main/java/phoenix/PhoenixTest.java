package phoenix;

import java.sql.*;
import org.apache.phoenix.queryserver.client.ThinClientUtil;

public class PhoenixTest {
    public static void main(String[] args) throws SQLException {

        String connectionUrl = ThinClientUtil.getConnectionUrl("hadoop01", 8765);

        Connection connection = DriverManager.getConnection(connectionUrl);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
        }

        //关闭
        connection.close();

    }
}