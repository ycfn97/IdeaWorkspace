package value.keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.keyvalue
 * ClassName: KeyValue07_sortByKey 
 *
 * @author 18729 created on date: 2020/9/24 15:33
 * @version 1.0
 * @since JDK 1.8
 */
object KeyValue07_sortByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((3,"aa"),(6,"cc"),(2,"bb"),(1,"dd")))

    rdd.sortByKey(true).collect().foreach(println)
    rdd.sortByKey(false).collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
