package sample

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: sample
 * ClassName: TestWordCount01 
 *
 * @author 18729 created on date: 2020/9/17 0:34
 * @version 1.0
 * @since JDK 1.8
 */
object TestWordCount01 {

  def main(args: Array[String]): Unit = {

    // 第一种方式（不通用）
    val tupleList = List(("Hello Scala Spark World ", 4), ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))

    val stringList: List[String] = tupleList.map(t=>(t._1 + " ") * t._2)

    //val words: List[String] = stringList.flatMap(s=>s.split(" "))
    val words: List[String] = stringList.flatMap(_.split(" "))

    //在map中，如果传进来什么就返回什么，不要用_省略
    val groupMap: Map[String, List[String]] = words.groupBy(word=>word)
    //val groupMap: Map[String, List[String]] = words.groupBy(_)

    // (word, list) => (word, count)
    val wordToCount: Map[String, Int] = groupMap.map(t=>(t._1, t._2.size))

    val wordCountList: List[(String, Int)] = wordToCount.toList.sortWith {
      (left, right) => {
        left._2 > right._2
      }
    }.take(3)

    //tupleList.map(t=>(t._1 + " ") * t._2).flatMap(_.split(" ")).groupBy(word=>word).map(t=>(t._1, t._2.size))
    println(wordCountList)
  }
}
