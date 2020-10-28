package iterator

import scala.collection.mutable

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: HighLevelFunction_Reduce 
 *
 * @author 18729 created on date: 2020/9/19 9:07
 * @version 1.0
 * @since JDK 1.8
 */
object HighLevelFunction_Reduce {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4)
//    val i:Int=list.reduce(_-_)
//    val s:Int=list.reduce(_+_)
//    println(i)
//    println(s)

    val i = list.foldLeft(1)((x,y)=>x-y)

    val i1 = list.foldRight(10)((x,y)=>x-y)

    println(i)
    println(i1)

    // 两个Map的数据合并
    val map1 = mutable.Map("a"->1, "b"->2, "c"->3)
    val map2 = mutable.Map("a"->4, "b"->5, "d"->6)

    val map3: mutable.Map[String, Int] = map2.foldLeft(map1) {
      (map, kv) => {
        val k = kv._1
        val v = kv._2

        map(k) = map.getOrElse(k, 0) + v

        map
      }
    }

//    println(map3)
  }
}
