package 实战.实战1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战.实战1
 * ClassName: require03 
 *
 * @author 18729 created on date: 2020/9/29 8:17
 * @version 1.0
 * @since JDK 1.8
 */
object require03 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[String] = sc.textFile("input02")

    val value1: RDD[UserVisitAction] = value.map {
      a => {
        val strings: Array[String] = a.split("_")
        UserVisitAction(
          strings(0),
          strings(1).toLong,
          strings(2),
          strings(3).toLong,
          strings(4),
          strings(5),
          strings(6).toLong,
          strings(7).toLong,
          strings(8),
          strings(9),
          strings(10),
          strings(11),
          strings(12).toLong
        )
      }
    }

    /**
     *==============================封装对象====================================
     */

    val ints: List[Int] = List(1, 2, 3, 4, 5, 6, 7)
    val tuples: List[(Int, Int)] = ints.zip(ints.init)
    val list: List[Any] = ints.zip(ints.tail).map {
        case (a, b) => {
          a + "-" + b
        }
      }
//    println(ints.init)
//    println(ints.tail)
//    println(tuples)
//    println(list)

    /**
     * ================================工具====================================
     */

    val map: Map[Long, Long] = value1.filter(a => {
      ints.init.contains(a.page_id)
    }).map(a => (a.page_id, 1L)).reduceByKey(_ + _).collect().toMap

    /**
     * ===============================分母====================================
     */



    /**
     * ================================分子=====================================
     */

    value1.collect()

    //4.关闭连接
    sc.stop()
  }
}
