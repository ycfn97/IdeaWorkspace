package 实战

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value07_wordcount02 
 * 第一步，切分
 * 第二步，映射
 * 第三步，分组
 * 第四步，计数
 * @author 18729 created on date: 2020/9/24 0:01
 * @version 1.0
 * @since JDK 1.8
 */
object value07_wordcount02 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[String] = sc.parallelize(List("Hello Scala", "Hello Spark", "Hello World"))

    value.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(a=>(a._1,a._2.size)).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
