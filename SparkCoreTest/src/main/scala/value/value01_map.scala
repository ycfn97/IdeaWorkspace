package value

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value01_map 
 *
 * @author 18729 created on date: 2020/9/22 15:23
 * @version 1.0
 * @since JDK 1.8
 */
object value01_map {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value = sc.makeRDD(Array(1, 2, 3, 4))

    val value1 = value.map(_ * 2)

    value1.collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
