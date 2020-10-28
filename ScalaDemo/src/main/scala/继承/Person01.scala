package 继承

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: 继承
 * ClassName: Person01 
 *
 * @author 18729 created on date: 2020/9/15 16:14
 * @version 1.0
 * @since JDK 1.8
 */
class Person01(nameParam:String) {
  var name=nameParam
  var age:Int= _

  def this(nameParam:String,ageParam:Int){
    this(nameParam)
    this.age=ageParam

    println("父类辅助构造器")
  }
  println("父类主构造器")
}

class Emp(nameParam:String,ageParam:Int) extends Person01(nameParam, ageParam){
  var empNo:Int= _
  def this(nameParam:String,ageParam:Int,empNoParam:Int){
    this(nameParam,ageParam)
    this.empNo=empNoParam
    println("子类的辅助构造器")
  }
  println("子类主构造器")
}

object Test01{
  def main(args: Array[String]): Unit = {
    new Emp("z3",11,1001)
  }
}
