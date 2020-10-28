package dependency

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: dependency
 * ClassName: Lineage02 
 *
 * @author 18729 created on date: 2020/9/26 19:44
 * @version 1.0
 * @since JDK 1.8
 */
object Lineage02 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[String] = sc.textFile("input01/1.txt")

    println(value.dependencies)

    val value1: RDD[String] = value.flatMap(a => a.split(" "))

    println(value1.dependencies)

    val value2: RDD[(String, Int)] = value1.map((_, 1))

    println(value2.dependencies)

    val value3: RDD[(String, Int)] = value2.reduceByKey(_ + _)

    println(value3.dependencies)

    value3.collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
