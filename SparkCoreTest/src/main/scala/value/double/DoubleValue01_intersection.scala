package value.double

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.double
 * ClassName: DoubleValue01_intersection 
 *
 * @author 18729 created on date: 2020/9/24 10:52
 * @version 1.0
 * @since JDK 1.8
 */
object DoubleValue01_intersection {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[Int] = sc.makeRDD(1 to 4)

    val value1: RDD[Int] = sc.makeRDD(4 to 8)

//    交集
    value.intersection(value1).collect().foreach(println)

//    并集
    value.union(value1).collect().foreach(println)

//    差集
    value.subtract(value1).collect().foreach(println)
    value1.subtract(value).collect().foreach(println)


    //4.关闭连接
    sc.stop()
  }
}
