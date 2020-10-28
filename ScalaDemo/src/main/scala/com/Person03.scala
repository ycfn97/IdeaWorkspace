package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Person03 
 *
 * @author 18729 created on date: 2020/9/15 20:24
 * @version 1.0
 * @since JDK 1.8
 */
abstract class Person03 {
  val name:String
  def hello():Unit
}

class Teacher extends Person03{
  override val name: String = "sunqi"

  override def hello(): Unit = {
    println("集成方法")
  }
}
