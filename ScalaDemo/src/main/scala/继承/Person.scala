package 继承

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: 继承
 * ClassName: Person 
 *
 * @author 18729 created on date: 2020/9/15 15:27
 * @version 1.0
 * @since JDK 1.8
 */
class Person(name: String, var age: Int, val sex: String) {

}

object Test {

  def main(args: Array[String]): Unit = {

    var person = new Person("bobo", 18, "男")

    // （1）未用任何修饰符修饰，这个参数就是一个局部变量
    // printf(person.name)

    // （2）var修饰参数，作为类的成员属性使用，可以修改
    person.age = 19
    println(person.age)

    // （3）val修饰参数，作为类的只读属性使用，不能修改
    // person.sex = "女"
    println(person.sex)
  }
}
