package chapter02

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: chapter02
 * ClassName: TestMatchGuard 
 *
 * @author 18729 created on date: 2020/9/12 20:26
 * @version 1.0
 * @since JDK 1.8
 */
object TestMatchGuard {

  def main(args: Array[String]): Unit = {

    def abs(x: Int) = x match {
      case i: Int if i >= 0 => i
      case j: Int if j < 0 => -j
      case _ => "type illegal"
    }

    println(abs(-5))
  }
}