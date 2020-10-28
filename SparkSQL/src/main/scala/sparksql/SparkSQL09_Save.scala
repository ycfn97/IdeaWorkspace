package sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: sparksql
 * ClassName: SparkSQL09_Save 
 *
 * @author 18729 created on date: 2020/9/29 15:36
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL09_Save {
  def main(args: Array[String]): Unit = {
    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val frame: DataFrame = spark.read.format("json").load("input/user.json")

//    frame.write.save("output")

//    frame.write.format("json").save("output01")

    spark.read.format("json").load("output01").show
    // 5 释放资源
    spark.stop()
  }
}
