package value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value09_sample 
 *
 * @author 18729 created on date: 2020/9/22 22:41
 * @version 1.0
 * @since JDK 1.8
 */
object value09_sample {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6))

    value.sample(false,0.5,3).collect().foreach(println)

    println("===========================")

    value.sample(true,2,3).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
