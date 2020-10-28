package action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: action
 * ClassName: action06_takeOrdered 
 *
 * @author 18729 created on date: 2020/9/25 20:46
 * @version 1.0
 * @since JDK 1.8
 */
object action06_takeOrdered {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val ints: Array[Int] = sc.makeRDD(List(1, 3, 2, 4)).takeOrdered(2)

    println(ints.mkString(","))
    //4.关闭连接
    sc.stop()
  }
}
