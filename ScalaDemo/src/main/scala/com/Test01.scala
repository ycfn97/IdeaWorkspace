package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Test01 
 *
 * @author 18729 created on date: 2020/9/16 9:08
 * @version 1.0
 * @since JDK 1.8
 */
object Test01 {
  def main(args: Array[String]): Unit = {
    val p1=Person6.apply()
    println(p1.name)

    val p2=Person6("sunqi")
    println(p2.name)

  }
}

class Person6 private(cName:String){
  var name:String=cName
}

object Person6{
  def apply():Person6={
    println("apply空参被调用")
    new Person6("aaa")
  }

  def apply(name:String):Person6={
    println("apply有参被调用")
    new Person6(name)
  }
}
