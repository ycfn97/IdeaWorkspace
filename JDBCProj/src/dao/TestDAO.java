package dao;

import javabean.Student;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TestDAO {
    @Test
    public void test01() throws SQLException {
        StudentDAO studentDAO =new StudentDAO();
        List<Student> list=studentDAO.getList("select * from student where id > ?",0);
        for (Student student : list) {
            System.out.println(student);
        }
        System.out.println("*******************************");

        Student student = studentDAO.getBean("select * from student where id = ?", 2);
        System.out.println(student);
        Object value = studentDAO.getValue("select count(*) from student");
        System.out.println("value = " + value);

        studentDAO.update("delete from student where id = ?", 1);
    }

}
