package iterator

import scala.collection.mutable

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestSet 
 *
 * @author 18729 created on date: 2020/9/18 11:23
 * @version 1.0
 * @since JDK 1.8
 */
object TestSet {
  def main(args: Array[String]): Unit = {
    val set=Set(1,2,3,4,5,6)
    val set1=Set(1,2,3,4,5,6,3)
    for (x<-set1){
      println(x)
    }

    println("=====================================")

    val set2=mutable.Set(1,2,3,4,5,6)
    set2+=7
    set2.foreach(println)

    val ints=set2.+(9)
    println(ints)
    println("set2:"+set2)

    println("=====================================")

    val set3=set1.union(set2)
    println(set3.mkString(","))
  }
}
