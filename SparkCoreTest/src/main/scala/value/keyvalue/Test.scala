package value.keyvalue

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.keyvalue
 * ClassName: Test 
 *
 * @author 18729 created on date: 2020/9/27 8:55
 * @version 1.0
 * @since JDK 1.8
 */
object Test {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    sc.textFile("input")
      .map(a=>((a.split(" ")(1),a.split(" ")(4)),1))
      .reduceByKey(_+_)
      .groupBy(_._1._1)
      .mapValues(a=>a.toList.sortWith(_._2>_._2).take(3))
      .collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
