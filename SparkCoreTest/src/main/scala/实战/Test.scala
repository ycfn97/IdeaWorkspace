package 实战

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战
 * ClassName: 方案2 
 *
 * @author 18729 created on date: 2020/9/27 11:25
 * @version 1.0
 * @since JDK 1.8
 */
object Test {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //4.关闭连接
    sc.stop()
  }
}
