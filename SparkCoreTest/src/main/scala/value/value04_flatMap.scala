package value

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value04_flatMap 
 *
 * @author 18729 created on date: 2020/9/22 15:45
 * @version 1.0
 * @since JDK 1.8
 */
object value04_flatMap {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

//    val listRDD=sc.makeRDD(List(List(1,2),List(3,4),List(5,6),List(7)), 2)
//
//    listRDD.flatMap(List=>List).collect().foreach(println)

    sc.makeRDD(List(List(1,2),List(3,4),List(5,6),List(7)),2).flatMap(List=>List).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
