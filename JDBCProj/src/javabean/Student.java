package javabean;

/**
 * +--------+-----------------+------+-----+---------+----------------+
 * | Field  | Type            | Null | Key | Default | Extra          |
 * +--------+-----------------+------+-----+---------+----------------+
 * | id     | int             | NO   | PRI | NULL    | auto_increment |
 * | name   | varchar(20)     | YES  |     | NULL    |                |
 * | age    | int             | YES  |     | NULL    |                |
 * | mobile | varchar(20)     | YES  |     | NULL    |                |
 * | gender | enum('男','女') | YES  |     | 男      |                |
 * +--------+-----------------+------+-----+---------+----------------+
 */
public class Student {
    private int id;
    private  String name;
    private int age;
    private String mobile;
    private String gender;

    public Student(int id, String name, int age, String mobile, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.gender = gender;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
