package value

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value13_sortBy 
 *
 * @author 18729 created on date: 2020/9/24 10:20
 * @version 1.0
 * @since JDK 1.8
 */
object value13_sortBy {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value = sc.makeRDD(List(2, 1, 3, 4, 6, 5))

    value.sortBy(a=>a,false).collect().foreach(println)

    Thread.sleep(10000000)

    //4.关闭连接
    sc.stop()
  }
}
