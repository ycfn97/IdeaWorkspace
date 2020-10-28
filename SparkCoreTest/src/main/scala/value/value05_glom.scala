package value

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: value05_glom 
 *
 * @author 18729 created on date: 2020/9/22 15:52
 * @version 1.0
 * @since JDK 1.8
 */
object value05_glom {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value = sc.makeRDD(1 to 4, 2)

    /**
     * 了解
     */
//    println(value.glom().map(_.max).collect().sum)

    value.glom().map(_.max*2).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
