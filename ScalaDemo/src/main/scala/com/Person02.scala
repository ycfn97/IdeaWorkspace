package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Person02 
 *
 * @author 18729 created on date: 2020/9/15 20:14
 * @version 1.0
 * @since JDK 1.8
 */
object Person02 {
  def main(args: Array[String]): Unit = {
    new Emp("sunqi",20,1001)
  }
}

class Person02(nameParam:String){
  var name=nameParam
  var age:Int= _
  def this(nameParam:String,ageParam:Int){
    this(nameParam)
    this.age=ageParam
    println("父类辅助构造器")
  }
  println("父类主构造器")
}

class Emp(nameParam:String,ageParam:Int) extends Person02(nameParam,ageParam){
  var empNo:Int= _
  def this(nameParam:String,ageParam:Int,empNoParam:Int){
    this(nameParam,ageParam)
    this.empNo=empNoParam
    println("子类辅助构造器")
  }
  println("子类主构造器")
}
