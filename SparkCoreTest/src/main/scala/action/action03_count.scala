package action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: action
 * ClassName: action03_count 
 *
 * @author 18729 created on date: 2020/9/25 14:46
 * @version 1.0
 * @since JDK 1.8
 */
object action03_count {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    println(sc.makeRDD(List(1,2,3,4)).count())
    //4.关闭连接
    sc.stop()
  }
}
