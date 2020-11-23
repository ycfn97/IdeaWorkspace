package action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: action
 * ClassName: action_Aggregate 
 *
 * @author 18729 created on date: 2020/11/23 8:48
 * @version 1.0
 * @since JDK 1.8
 */
object action_Aggregate {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3具体业务逻辑
    //3.1 创建第一个RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 8)

    //3.2 将该RDD所有元素相加得到结果
    //val result: Int = rdd.aggregate(0)(_ + _, _ + _)
    val result: Int = rdd.aggregate(10)(_ + _, _ + _)

    println(result)


    //4.关闭连接
    sc.stop()
  }
}
