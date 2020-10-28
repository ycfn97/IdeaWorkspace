package value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value07_wordcount 
 * 第一步，切分
 * 第二步，映射
 * 第三步，分组
 * 第四步，计数
 * @author 18729 created on date: 2020/9/22 16:20
 * @version 1.0
 * @since JDK 1.8
 */
object value07_wordcount {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val strList: List[String] = List("Hello Scala", "Hello Spark", "Hello World")

    val value = sc.makeRDD(strList)

    val value1: RDD[String] = value.flatMap(str => str.split(" "))

    val value2: RDD[(String, Int)] = value1.map(word => (word, 1))

    val value3: RDD[(String, Iterable[(String, Int)])] = value2.groupBy(t => t._1)

    val value4: RDD[(String, Int)] = value3.map {
      case (a, b) => (a, b.size)
    }

    value4.collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
