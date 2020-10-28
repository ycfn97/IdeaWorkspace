package value

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value06_groupby 
 *
 * @author 18729 created on date: 2020/9/22 15:54
 * @version 1.0
 * @since JDK 1.8
 */
object value06_groupby {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value = sc.makeRDD(1 to 10, 2)

//    value.groupBy(_%2).collect().foreach(println)

    value.groupBy(_%3).collect().foreach(println)

//    val rdd1: RDD[String] = sc.makeRDD(List("hello","hive","hadoop","spark","scala"))

//    rdd1.groupBy(_.substring(0,1)).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
