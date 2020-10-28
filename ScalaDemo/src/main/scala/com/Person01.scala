package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Person01 
 *
 * @author 18729 created on date: 2020/9/15 19:18
 * @version 1.0
 * @since JDK 1.8
 */
object Person01 {
  def main(args: Array[String]): Unit = {
    var person=new Person01("bobo",20,"男")
    println(person.name)

    person.age=23
    println(person.age)

//    person.sex="女"
    println(person.sex)
  }
}

class Person01(var name:String,var age:Int,val sex:String){

}
