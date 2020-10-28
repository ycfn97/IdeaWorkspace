package iterator

import scala.collection.mutable

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestQueue 
 *
 * @author 18729 created on date: 2020/9/20 21:00
 * @version 1.0
 * @since JDK 1.8
 */
object TestQueue {
  def main(args: Array[String]): Unit = {
//    val que=new mutable.Queue[String]()
//    que.enqueue("a","b","c")
//    println(que.dequeue())
//    println(que.dequeue())
//    println(que.dequeue())

    val result1 = (0 to 100).map{case _ => Thread.currentThread.getName}
    val result2 = (0 to 100).par.map{case _ => Thread.currentThread.getName}

    println(result1)
    println(result2)
  }
}
