package com;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: TestDemic
 *
 * @author 18729 created on date: 2020/9/15 20:44
 * @version 1.0
 * @since JDK 1.8
 */
public class TestDynamic {
    public static void main(String[] args) {

        Teacher02 teacher = new Teacher02();
        Person055 teacher1 = new Teacher02();

        System.out.println(teacher.name);
        teacher.hello();

        System.out.println(teacher1.name);
        teacher1.hello();
    }
}

class Person055 {

    public String name = "person";
    public void hello() {
        System.out.println("hello person");
    }

}
class Teacher02 extends Person055 {

    public String name = "teacher";

    @Override
    public void hello() {
        System.out.println("hello teacher");
    }

}

