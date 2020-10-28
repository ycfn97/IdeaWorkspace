package iterator

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: CommonOperation 
 *
 * @author 18729 created on date: 2020/9/18 18:30
 * @version 1.0
 * @since JDK 1.8
 */
object CommonOperation {
  def main(args: Array[String]): Unit = {
    val list=List(23,54,68,91,15)
    val set=Set(1,2,3,4,5,6)

    println(list.length)
    println(list.size)
    println(set.size)

    list.foreach(println)
    println("============================")
    for (elem<-list){
      print(elem+"\t")
    }

    println()

    val iter=list.iterator
    while (iter.hasNext)
      print(iter.next()+" ")


    println()
   println(list.mkString("---"))
    println(list.contains(91))
  }
}
