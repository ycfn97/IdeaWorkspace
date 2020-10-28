package iterator

import scala.collection.mutable

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestMap 
 *
 * @author 18729 created on date: 2020/9/18 11:49
 * @version 1.0
 * @since JDK 1.8
 */
object TestMap {
  def main(args: Array[String]): Unit = {
//    val map = Map("a"->1,"b"->2,"c"->3)
//    for(elem<-map.keys){
//      println(map.get(elem).get)
//    }
//    println(map.getOrElse("d","invalid value"))
//    map.foreach((kv)=>(println(kv)))

  val map1=Map("a"->1,"b"->12,("c",32),("d",25))
    println(map1)

    println(map1("b"))
    println(map1.getOrElse("a",0))

//    for (elem <- map1) {
//      println(elem)
//    }
//
//    map1.foreach(println)
//    println("=============================")
//    map1.foreach((elem:(String,Int))=>println(elem))
//
//    for (value<-map1.values)print(value+"\t")
//    println("=============================")
//
//    val map2=mutable.Map(("a",1),("b",12),("c",32),("d",25))
//    map2.put("e",219)
//    println(map2.mkString(","))
//    map2+=(("f",123))
//    println(map2)
//
//    map2.remove("d")
//    println(map2)
//
//    map2.update("a",29)
//    println(map2)
//
//    val map3=map1++map2
//    println(map3)
//
//    map2++=map1
//    println(map2)
  }
}
