package iterator

import scala.collection.mutable.ListBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestList 
 *
 * @author 18729 created on date: 2020/9/16 22:30
 * @version 1.0
 * @since JDK 1.8
 */
object TestList {
  def main(args: Array[String]): Unit = {
//    val list:List[Int]=List(1,2,3,4)
//    val list5=1::2::3::4::Nil
//    list5.foreach(println)
//
//    println("==================================")
//
//    val list1=ListBuffer(1,2,3,4)
//    list1+=(5)
//    list1.append(6)
//    list1.insert(1,8)
//    list1.foreach(println)
//    var list6=list1::list5
//    println(list6.mkString(","))
//
//    var list7=list1++list5
//    println(list6.mkString(","))
//    list7.-=(4)
//    println(list7.mkString(","))

//    var list:List[Int]=List(1,2,3,4,5,6,7)
//    println(list.length)
//    println(list.size)
//    list.foreach(println)
//
//    val iter=list.iterator
//    while (iter.hasNext)
//      println(iter.next()+"\t")

//    val list1: List[Int] = List(1, 2, 3, 4, 5, 6, 7)
//    val list2: List[Int] = List(4, 5, 6, 7, 8, 9, 10)
//
//    //（1）获取集合的头
//    println(list1.head)
//
//    //（2）获取集合的尾（不是头的就是尾）
//    println(list1.tail)
//
//    //（3）集合最后一个数据
//    println(list1.last)
//
//    //（4）集合初始数据（不包含最后一个）
//    println(list1.init)
//
//    //（5）反转
//    println(list1.reverse)
//
//    //（6）取前（后）n个元素
//    println(list1.take(3))
//    println(list1.takeRight(3))
//
//    //（7）去掉前（后）n个元素
//    println(list1.drop(3))
//    println(list1.dropRight(3))
//
//    println(list1.union(list2))
//
//    println(list1.intersect(list2))
//
//    println(list1.diff(list2))
//
//    println(list1.zip(list2))
//
//    println("===================================")
//
//    //（12）滑窗
//    list1.sliding(2, 5).foreach(println)
//
//    list1.sliding(3).foreach(println)

//    var list=List(1,5,-3,4,2,-7,6)
//    println(list.sum)
//    println(list.product)
//    println(list.max)
//    println(list.min)
//    println(list.sortBy(x=>x))
//    println(list.sortBy(x=>x.abs))
//    // （5.3）按元素大小升序排序
//    println(list.sortWith((x, y) => x < y))
//
//    // （5.4）按元素大小降序排序
//    println(list.sortWith((x, y) => x > y))

    val list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    val wordList: List[String] = List("hello world", "hello atguigu", "hello scala")

//    println(list.filter(_%2==0))
//
//    println(list.map(_+1))

    println(nestedList.flatten)

//    println(wordList.flatMap(_.split(" ")))
//
//    println(list.groupBy(_%2))
//
//    println(wordList.flatMap(_.split(" ")).groupBy(_.charAt(0)))

  }
}
