import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 
 * ClassName: WordCount 
 *
 * @author 18729 created on date: 2020/9/22 10:23
 * @version 1.0
 * @since JDK 1.8
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[String] = sc.textFile("input")

    val value1: RDD[String] = value.flatMap(_.split(" "))

    val value2: RDD[(String, Int)] = value1.map((_, 1))

    val value3: RDD[(String, Int)] = value2.reduceByKey(_ + _)

    value3.collect().foreach(println)

    Thread.sleep(10000000)
    //4.关闭连接
    sc.stop()
  }
}
