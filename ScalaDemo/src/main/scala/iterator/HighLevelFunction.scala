package iterator

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: HighLevelFunction 
 *
 * @author 18729 created on date: 2020/9/18 20:57
 * @version 1.0
 * @since JDK 1.8
 */
object HighLevelFunction {
  def main(args: Array[String]): Unit = {
    val list=List(35, 48, 69, 54, 23, 91, 102, 68, 231, 563)
    val eventList=list.filter(_%2==0)
    val oddList=list.filter(_%2!=0)
    println(eventList)
    println(oddList)

    val mul2List=list.map(_*2)
    println(mul2List)

    val nestedList:List[List[Int]]=List(List(1,2,3),List(4,5,6),List(7,8,9))
    val flatList1=nestedList(0):::nestedList(1):::nestedList(2)
    println(flatList1)

    val flatList2=nestedList.flatten
    println(flatList2)

    println("======================================")
    val strings:List[String]=List("hello world","hello scala", "hello atguigu", "hello atguigu with scala")
    val splitStrings:List[Array[String]]=strings.map(_.split(" "))
    println(splitStrings.mkString(" "))
    val flattenWordList:List[String]=splitStrings.flatten
    println(flattenWordList)

    println("======================================")
    val flattenList=strings.flatMap(_.split(" "))
    println(flattenList)

    val groupedMap:Map[Int,List[Int]]=list.groupBy(_%2)
    println(groupedMap)

    val groupedWordMap:Map[Char,List[String]]=flattenWordList.groupBy(data=>data.charAt(0))
    println(groupedWordMap)
  }
}
