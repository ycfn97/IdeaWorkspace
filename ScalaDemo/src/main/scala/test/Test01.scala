package test

import scala.math._
//import sqrt
/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: test
 * ClassName: Test01 
 *
 * @author 18729 created on date: 2020/9/18 18:39
 * @version 1.0
 * @since JDK 1.8
 */
object Test01 {
  def main(args: Array[String]): Unit = {
    var b:Int=3;b=6
//    val a:String=3
    def countdown(n:Int): Unit ={
      0 to n foreach(print)
    }

    countdown(10)
    for (i<- 1 to 3;j<- 1 to 3;if i!=j){
      print((10*i+j)+" ")
    }

//    val list=List[String]('a','b','c')
    Array(1,7,2,9).sorted
    println(Array(1,7,2,9).sorted.mkString(","))

    val map:Map[String,Int]=Map("book"->5,"pen"->2).map(m=>m._1->m._2*2)
    println(map)
  }
}
