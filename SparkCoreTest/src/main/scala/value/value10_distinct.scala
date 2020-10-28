package value

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value10_distinct 
 *
 * @author 18729 created on date: 2020/9/23 8:28
 * @version 1.0
 * @since JDK 1.8
 */
object value10_distinct {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    sc.makeRDD(Array(1,2,2,3,4,5,5,6,7)).distinct(2).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
