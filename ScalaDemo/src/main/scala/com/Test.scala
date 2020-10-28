package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Test 
 *
 * @author 18729 created on date: 2020/9/16 0:07
 * @version 1.0
 * @since JDK 1.8
 */
object Test {
  def main(args: Array[String]): Unit = {
    println(Person11.country)
  }
}

object Person11{
  var country:String="China"
}

class Person11{
  var name:String="bobo"
}

