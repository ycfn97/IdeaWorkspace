package 实战

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战.实战1
 * ClassName: require03_PageFlow
 *
 * @author 18729 created on date: 2020/9/28 11:47
 * @version 1.0
 * @since JDK 1.8
 */
object require03_PageFlow {
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
     * ====================================对象封装============================================
     */

    val ids = List(1, 2, 3, 4, 5, 6, 7)
    val strings: List[String] = ids.zip(ids.tail)
      .map {
        case (a, b) => {
          a + "-" + b
        }
      }
//    println(ids)
//    println(ids.tail)
//    println(strings)

    /**
     * =====================================拉链，工具============================================
     */

    val map: Map[Long, Long] = value1.filter(a => ids.init.contains(a.page_id))
      .map(a => (a.page_id, 1L))
      .reduceByKey(_ + _).collect().toMap

    println(map)

    /**
     * ======================================分母,页面访问次数=============================================
     */

    value1.groupBy(_.session_id)
      .mapValues{
      a=>{
        val actions: List[UserVisitAction] = a.toList.sortWith {
          (a, b) => {
            a.action_time < b.action_time
          }
        }
        val longs: List[Long] = actions.map(_.page_id)
        val tuples: List[(Long, Long)] = longs.zip(longs.tail)
        tuples.map {
          case (a, b) => {
            a + "-" + b
          }
        }
          .filter(a => strings.contains(a))
      }
    }.map(_._2)
      .flatMap(a=>a)
      .map((_,1))
      .reduceByKey(_+_)
      .foreach {
        case (pageflow, sum) => {
          val pageIds: Array[String] = pageflow.split("-")
          val pageIdSum: Long = map.getOrElse(pageIds(0).toLong, 1L)

          println(pageflow + "=" + sum.toDouble / pageIdSum)
        }
      }

    Thread.sleep(1000000000)
    //4.关闭连接
    sc.stop()
  }
}

