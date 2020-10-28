package iterator

import scala.collection.mutable.ArrayBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestArrayBuffer 
 *
 * @author 18729 created on date: 2020/9/16 16:33
 * @version 1.0
 * @since JDK 1.8
 */
object TestArrayBuffer {
  def main(args: Array[String]): Unit = {
    var arr01=ArrayBuffer(1,2,3)
    for (i<-arr01){
      println(i)
    }
    println(arr01.length)
    println(arr01.hashCode())

    arr01.+=(4)
    arr01.append(5)
    println(arr01.mkString(","))

    arr01.insert(0,6,7)
    println(arr01.mkString(","))

    arr01(0)=10
    println(arr01.mkString(","))
  }
}




