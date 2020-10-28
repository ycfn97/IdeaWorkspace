package chapter02

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: chapter02
 * ClassName: ExceptionDemo 
 *
 * @author 18729 created on date: 2020/9/12 20:14
 * @version 1.0
 * @since JDK 1.8
 */
object ExceptionDemo {
  def main(args: Array[String]): Unit = {
    try{
      var n=10/0
    }catch {
      case ex:ArithmeticException=>{
        println("发生算术异常")
      }
      case ex: Exception=>{
        println("发生了异常1")
        println("发生了异常2")
      }
    }finally {
      println("finally")
    }
  }
}
