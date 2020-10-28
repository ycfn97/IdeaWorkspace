package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: TestTrait01 
 *
 * @author 18729 created on date: 2020/9/16 10:43
 * @version 1.0
 * @since JDK 1.8
 */
object TestTrait01 {
  def main(args: Array[String]): Unit = {
    println(new MyBall().describe())
  }
}

trait Ball{
  def describe():String={
  "ball"
  }
}

trait Color extends Ball{
  override def describe(): String = "blue - "+super.describe()
}

trait Category extends Ball{
  override def describe(): String = "foot - "+super.describe()
}

class MyBall extends Color with Category{
  override def describe(): String = "my ball is a "+super.describe()
}

