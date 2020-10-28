package value.key

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.key-value
 * ClassName: KeyValue04_aggregateByKey 
 *
 * @author 18729 created on date: 2020/9/24 11:32
 * @version 1.0
 * @since JDK 1.8
 */
object KeyValue04_aggregateByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)

    rdd.aggregateByKey(0)(Math.max(_,_),_+_).collect().foreach(println)

    Thread.sleep(100000000)
    //4.关闭连接
    sc.stop()
  }
}
