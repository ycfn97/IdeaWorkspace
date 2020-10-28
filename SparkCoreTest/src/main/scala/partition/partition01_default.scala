package partition

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: partition
 * ClassName: partition01_default 
 *
 * @author 18729 created on date: 2020/9/22 19:10
 * @version 1.0
 * @since JDK 1.8
 */
object partition01_default {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value = sc.makeRDD(Array(1, 2, 3, 4))

    value.saveAsTextFile("output")

    //4.关闭连接
    sc.stop()
  }
}
