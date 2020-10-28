package accumulate

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: accumulate
 * ClassName: broadcast01 
 *
 * @author 18729 created on date: 2020/9/28 10:12
 * @version 1.0
 * @since JDK 1.8
 */
object broadcast01 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3.采用集合的方式，实现rdd1和list的join
    val rdd: RDD[String] = sc.makeRDD(List("WARN:Class Not Find", "INFO:Class Not Find", "DEBUG:Class Not Find"))
    val list: String = "WARN"

    val value: Broadcast[String] = sc.broadcast(list)

    rdd.filter{
      log=>{
        log.contains(value.value)
      }
    }
      .foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
