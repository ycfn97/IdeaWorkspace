package review

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: review
 * ClassName: re01 
 *
 * @author 18729 created on date: 2020/9/17 10:59
 * @version 1.0
 * @since JDK 1.8
 */
object re01 {
  def main(args: Array[String]): Unit = {
    val res = for(i <- 1 to 10) yield i*2
    println(res)
  }
}
