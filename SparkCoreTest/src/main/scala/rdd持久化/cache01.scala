package rdd持久化

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: rdd持久化
 * ClassName: cache01 
 *
 * @author 18729 created on date: 2020/9/25 15:41
 * @version 1.0
 * @since JDK 1.8
 */
object cache01 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[(String, Int)] = sc.textFile("input01")
      .flatMap(a => a.split(" "))
      .map {
        word => {
          println("***************")
          (word, 1)
        }
      }
    value.cache()
    value.collect().foreach(println)
    println("----------------")
value.collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
