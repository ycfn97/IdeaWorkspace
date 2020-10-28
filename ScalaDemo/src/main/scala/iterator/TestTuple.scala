package iterator

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: TestTuple 
 *
 * @author 18729 created on date: 2020/9/18 15:08
 * @version 1.0
 * @since JDK 1.8
 */
object TestTuple {
  def main(args: Array[String]): Unit = {
    val tuple1=Tuple3[Int,String,Boolean](129,"hello",true)
    val tuple2:(Int,String,Boolean)=(129,"hello",true)
    println(tuple1)
    println(tuple2)

    val map=Map(("a",1),("b",12),"c"->32)

    println(tuple2.productElement(2))
    println("=======================================")
    for (elem<-tuple2.productIterator){
      println(elem)
    }
  }
}
