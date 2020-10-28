package sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: sparksql
 * ClassName: SparkSQL12_Hive 
 *
 * @author 18729 created on date: 2020/9/29 16:22
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL12_Hive {
  def main(args: Array[String]): Unit = {
    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    import spark.implicits._

    // 3 连接外部Hive，并进行操作
    spark.sql("show tables").show()
//    spark.sql("create table user3(id int, name string)")
//    spark.sql("insert into user3 values(1,'zs')")
//    spark.sql("select * from user3").show

    // 5 释放资源
    spark.stop()
  }
}
