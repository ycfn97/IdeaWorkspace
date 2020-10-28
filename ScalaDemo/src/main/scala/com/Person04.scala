package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Person04 
 *
 * @author 18729 created on date: 2020/9/15 20:37
 * @version 1.0
 * @since JDK 1.8
 */
object Person04 {
  def main(args: Array[String]): Unit = {
    val teacher=new Teacher01
    println(teacher.name)
    teacher.hello()

    val taecher01:Person04=new Teacher01
    println(teacher.name)
    taecher01.hello()
  }
}

class Person04{
  val name:String="person"
  def hello(): Unit ={
    println("hello,person")
  }
}

class Teacher01 extends Person04{
  override val name: String = "teacher"

  override def hello(): Unit = {
    println("hello,teacher")
  }
}
