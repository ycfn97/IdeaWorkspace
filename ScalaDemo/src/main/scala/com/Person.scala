package com

import scala.beans.BeanProperty

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: Person 
 *
 * @author 18729 created on date: 2020/9/15 18:28
 * @version 1.0
 * @since JDK 1.8
 */
object Person {
//  def main(args: Array[String]): Unit = {
//    var person1=new Person
//    person1.setAge(20)
//    person1.setSex("男")
//    println(person1.name+person1.age+person1.sex)
//  }

//  def main(args: Array[String]): Unit = {
//    val person=new Person
//    person.say()
//    println(person.name)
//    println(person.age)
//  }

//  def main(args: Array[String]): Unit = {
//    val person=new Person()
//    person.f(1,2)
//  }

  def main(args: Array[String]): Unit = {
    var person=new Person("sunqi",20)
  }

}

class Person{
  var name:String= _
  var age:Int= _
  def this(age:Int){
    this
    this.age=age
    println("辅助构造器1")
  }

  def this(name:String,age:Int){
    this(age)
    this.name=name
    println("辅助构造器2")
  }
}

//class Person{
//  def f(n1:Int,n2:Int): Unit ={
//    println(n1+n2)
//  }
//}

//class Person{
//  private var name:String="bobo"
//  protected var age:Int=18
//  private[test] var sex:String="男"
//
//  def say(): Unit ={
//    println(name)
//  }
//}

//class Teacher extends Person{
//  def test(): Unit ={
//    this.age
//    this.sex
//  }
//}

//class Animal{
//  def test: Unit ={
//    new Person().sex
//  }
//}

/**
 * Bean属性（@BeanPropetry），可以自动生成规范的setXxx/getXxx方法
 */
//class Person{
//  var name:String="bobo"
//  @BeanProperty
//  var age:Int= _
//  @BeanProperty
//  var sex:String="男"
//}
