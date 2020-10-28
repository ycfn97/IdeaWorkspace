package keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package:
 * ClassName: keyvalue
 *
 * @author 18729 created on date: 2020/9/25 8:30
 * @version 1.0
 * @since JDK 1.8
 */
object reducebykey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[(Int, Int)] = sc.makeRDD(List((1, 2), (1, 3), (1, 4), (2, 1), (2, 3), (2, 5), (4, 6), (4, 8)))

    val value1: RDD[(Int, Int)] = value.reduceByKey(_ * _)

    value.collect().foreach(println)

    Thread.sleep(10000000)
    //4.关闭连接
    sc.stop()
  }
}
