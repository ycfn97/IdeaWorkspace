package keyvalue

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: keyvalue
 * ClassName: sort 
 *
 * @author 18729 created on date: 2020/9/25 8:43
 * @version 1.0
 * @since JDK 1.8
 */
object sort {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

//    val value: RDD[(Int, Int)] = sc.makeRDD(List((1, 2), (1, 3), (1, 4), (2, 1), (2, 3), (2, 5), (4, 6), (4, 8)))

//    value.sortByKey()
//      .collect().foreach(println)
//
//    value.sortBy(_._2)
//      .collect().foreach(println)

//    sc.makeRDD(List((1, (1,2,3)), (2, (3,4,5)), (4, (5,6,7)), (6, (4,5,7))))
//      .sortByKey()
//      .collect().foreach(println)

    sc.makeRDD(List((1, Iterable(((4,12),25), ((4,25),11), ((4,27),13), ((4,13),21), ((4,8),21), ((4,1),20))), (2, Iterable(((0,7),5), ((0,18),15), ((0,3),16), ((0,11),19), ((0,0),15), ((0,27),23), ((0,26),24), ((0,21),14), ((0,22),11), ((0,17),15)))))
//      .groupBy(_._1)
      .mapValues(a=>a.toList.sortWith(_._2>_._2))
      .collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
