package test;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionTest {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = util.jdbcUtil.getConnection();
            // 1) 开启事务, setAutoCommit(false)
            connection.setAutoCommit(false);
            // 2) 事务由多条DML组成
            CommonUtil.view(connection, "select * from student");
            CommonUtil.update(connection,"delete from student");
            System.out.println("删除数据后, 查看表");
            CommonUtil.view(connection, "select * from student");

            CommonUtil.update(connection,"insert into abc");

            // 3) 如果没有任何问题发生事务提交 commit
            connection.commit();
        } catch (Exception e) {
            // 4) 如果有问题发生则事务回滚 rollback
            try {
                connection.rollback();
                System.out.println("删除失败，回滚事务");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 5) 还原设置
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            util.jdbcUtil.close(connection);
        }
    }
}
