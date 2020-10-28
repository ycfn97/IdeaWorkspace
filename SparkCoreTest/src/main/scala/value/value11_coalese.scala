package value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value11_coalese 
 *
 * @author 18729 created on date: 2020/9/24 9:53
 * @version 1.0
 * @since JDK 1.8
 */
object value11_coalese {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 4)

    value.coalesce(2).mapPartitionsWithIndex((index,data)=>data.map((index,_))).collect().foreach(println)

    Thread.sleep(1000000)
    //4.关闭连接
    sc.stop()
  }
}
