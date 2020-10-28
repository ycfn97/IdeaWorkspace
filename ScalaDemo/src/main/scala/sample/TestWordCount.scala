package sample

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: sample
 * ClassName: TestWordCount 
 *
 * @author 18729 created on date: 2020/9/16 23:44
 * @version 1.0
 * @since JDK 1.8
 */
object TestWordCount {
  def main(args: Array[String]): Unit = {
    val stringList=List("Hello Scala Hbase kafka", "Hello Scala Hbase", "Hello Scala", "Hello")

//    val wordList:List[String]=stringList.flatMap(str=>str.split(" "))
//    println(wordList)

    println(stringList.flatMap(string=>string.split(" ")))

//    val wordToWordMap:Map[String,List[String]]=wordList.groupBy(word=>word)
//    println(wordToWordMap)



//    val wordToCountMap:Map[String,Int]=wordToWordMap.map(tuple=>(tuple._1, tuple._2.size))
//    println(wordToCountMap)
//
//    val sortList: List[(String, Int)] = wordToCountMap.toList.sortWith {
//      (left, right) => {
//        left._2 > right._2
//      }
//    }
//    println(sortList)
//
//    val resultList: List[(String, Int)] = sortList.take(3)
//    println(resultList)
  }
}
