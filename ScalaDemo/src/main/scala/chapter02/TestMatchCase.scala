package chapter02

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: chapter02
 * ClassName: TestMatchCase 
 *
 * @author 18729 created on date: 2020/9/12 20:18
 * @version 1.0
 * @since JDK 1.8
 */
object TestMatchCase {
  def main(args: Array[String]): Unit = {
    var a:Int =10
    var b:Int =20
    var operator:Char='d'

    var result=operator match{
      case '+'=>a+b
      case '-'=>a-b
      case '*'=>a*b
      case '/'=>a/b
      case _=>"illegal"
    }

    println(result)
  }
}
