package partition

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: partition
 * ClassName: partition02_Array 
 *
 * @author 18729 created on date: 2020/9/22 19:13
 * @version 1.0
 * @since JDK 1.8
 */
object partition02_Array {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

//    val value = sc.makeRDD(Array(1, 2, 3, 4), 4)

//    val value1 = sc.makeRDD(Array(1, 2, 3, 4), 3)

    val value2 = sc.makeRDD(Array(1, 2, 3, 4, 5,6,7,8,9,10), 3)

//    value.saveAsTextFile("output02")

//    value1.saveAsTextFile("output03")

    value2.saveAsTextFile("output04")

    //4.关闭连接
    sc.stop()
  }
}
