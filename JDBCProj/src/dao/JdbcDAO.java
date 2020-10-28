package dao;

import javabean.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();
    Class<T> clazz;

    public JdbcDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getList(String sql, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = util.jdbcUtil.getConnection();
            BeanListHandler<T> handler = new BeanListHandler<>(clazz);
            return queryRunner.query(connection, sql, handler, args);
        }finally {
            util.jdbcUtil.close(connection);
        }
    }

    public T getBean(String sql, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = util.jdbcUtil.getConnection();
            // 把结果集处理成一个对象
            BeanHandler<T> handler = new BeanHandler<>(clazz);
            return queryRunner.query(connection, sql, handler, args);
        } finally {
            util.jdbcUtil.close(connection);
        }
    }

    public Object getValue(String sql, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = util.jdbcUtil.getConnection();
            ScalarHandler scalarHandler = new ScalarHandler();
            return queryRunner.query(connection, sql, scalarHandler, args);
        } finally {
            util.jdbcUtil.close(connection);
        }
    }

    public int update(String sql, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = util.jdbcUtil.getConnection();
            return queryRunner.update(connection, sql, args);
        } finally {
            util.jdbcUtil.close(connection);
        }
    }
}
