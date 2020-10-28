package com{
  /**
   * Copyright(c) 2020-2021 sparrow All Rights Reserved
   * Project: ScalaDemo
   * Package: 面向对象
   * ClassName: Outer
   *
   * @author 18729 created on date: 2020/9/15 10:11
   * @version 1.0
   * @since JDK 1.8
   */
  object Outer {
    //  val out: String = "out"
    //
    //  def main(args: Array[String]): Unit = {
    //    println(Inner.in)
    //  }
    val out: String = "out"

    def main(args: Array[String]): Unit = {
      println(name)
      println(out)
    }
  }
}

  package object com{
    val name:String="com"
  }
