package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: TestTrait 
 *
 * @author 18729 created on date: 2020/9/16 10:15
 * @version 1.0
 * @since JDK 1.8
 */
object TestTrait {
  def main(args: Array[String]): Unit = {
    var teacher=new Teacher22
    teacher.say()
    teacher.eat()

    val t2=new Teacher22 with SexTrait{
      override var sex: String = "男"
    }

    println(t2.sex)
  }
}

trait PersonTrait1{
  var name:String= _
  var age:Int
  def eat(): Unit ={
    println("eat")
  }
  def say():Unit
}

trait SexTrait{
  var sex:String
}

class Teacher22 extends PersonTrait1 with java.io.Serializable{
  override var age: Int = _

  override def say(): Unit ={
    println("say")
  }
}
