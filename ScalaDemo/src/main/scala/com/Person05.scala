package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Person05 
 *
 * @author 18729 created on date: 2020/9/15 20:49
 * @version 1.0
 * @since JDK 1.8
 */
object Person05 {
  def main(args: Array[String]): Unit = {
    val person=new Person05 {
      override val name: String = "sunqi"

      override def hello(): Unit = "hello,everyone"
    }
  }
}

abstract class Person05{
  val name:String
  def hello():Unit
}
