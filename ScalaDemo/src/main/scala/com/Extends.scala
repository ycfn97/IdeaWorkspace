package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Extends 
 *
 * @author 18729 created on date: 2020/9/16 11:45
 * @version 1.0
 * @since JDK 1.8
 */
object Extends {
  def main(args: Array[String]): Unit = {
    val student17=new Student17("alice",18)
    student17.study()
    val student18=new Student17("bob",25)
    println(WorkDay.MONDAY)
  }
}

class Person17(var name:String,var age:Int){
//  def sayHi
}

class Student17(name:String,age:Int) extends Person17(name,age){
  def study(): Unit ={
    println("student is studying")
  }

}

object WorkDay extends Enumeration{
  val MONDAY=Value(1,"星期一")
}

/**
 * junit
 */
object TestApp extends App{
  println("app start")
  type a=String
  var aa:a="aaa"
  println(aa)
}