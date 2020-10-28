package iterator

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestArray 
 *
 * @author 18729 created on date: 2020/9/16 15:23
 * @version 1.0
 * @since JDK 1.8
 */
object TestArray {
  def main(args: Array[String]): Unit = {
    var arr01=new Array[Int](4)
    println(arr01.length)

    arr01(3)=10
    for (arr02<-arr01){
      println(arr02)
    }

    val arr2:Array[Int]=Array(1,2,3,4,5)
    println("===============================")
    println(arr2(1))

    arr2.update(0,11)
    println(arr2(0))

    println(arr01.mkString(","))

    def printx(elem:Int): Unit ={
      println(elem)
    }

    arr01.foreach(printx)
    println("================================")
    arr01.foreach(println)

    println(arr01)
    val newArr:Array[Int]=arr01 :+5
    println(newArr)

    val iter:Iterator[Int]=arr01.iterator
    while (iter.hasNext){
      println(iter.next())
    }

    var arr03:Array[Int]=Array(1,2,3,4,5,6)
    println(arr03.length)
    arr03.foreach(println)

  }
}
