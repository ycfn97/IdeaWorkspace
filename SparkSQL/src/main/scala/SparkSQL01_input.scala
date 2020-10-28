import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: 
 * ClassName: SparkSQL01_input 
 *
 * @author 18729 created on date: 2020/9/29 11:15
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL01_input {
  def main(args: Array[String]): Unit = {
  // 1 创建上下文环境配置对象
  val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("")

  // 2 创建SparkSession对象
  val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val frame: DataFrame = spark.read.json("input/user.json")

    frame.show()

    Thread.sleep(1000000)
  // 5 释放资源
  spark.stop()
  }
}
