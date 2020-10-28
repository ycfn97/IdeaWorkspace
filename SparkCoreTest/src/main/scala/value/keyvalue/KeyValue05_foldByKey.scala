package value.key

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.key-value
 * ClassName: KeyValue05_foldByKey 
 *
 * @author 18729 created on date: 2020/9/24 13:19
 * @version 1.0
 * @since JDK 1.8
 */
object KeyValue05_foldByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val list: List[(String, Int)] = List(("a",1),("a",1),("a",1),("b",1),("b",1),("b",1),("b",1),("a",1))

    val value: RDD[(String, Int)] = sc.makeRDD(list)

    value.foldByKey(0)(_+_).collect().foreach(println)

//    Thread.sleep(1000000000)
    //4.关闭连接
    sc.stop()
  }
}
