package dao;

import javabean.Student;

public class StudentDAO extends JdbcDAO<Student>{
    public StudentDAO() {
        super(Student.class);
    }
}
