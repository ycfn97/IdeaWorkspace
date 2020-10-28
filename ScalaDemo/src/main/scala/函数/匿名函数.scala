package 函数

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: 函数
 * ClassName: 匿名函数 
 *
 * @author 18729 created on date: 2020/9/15 11:16
 * @version 1.0
 * @since JDK 1.8
 */
object 匿名函数 {
  def main(args: Array[String]): Unit = {
    def operation(array:Array[Int],function:Int=>Int)={
      for (ele<- array) yield function(ele)
    }
    def function(ele:Int)={
      ele+1
    }
    println(operation(Array(1,2,3,4),function).mkString(","))
  }
}
