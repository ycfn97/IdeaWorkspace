package sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: sparksql
 * ClassName: SparkSQL10_MySQL_Read 
 *
 * @author 18729 created on date: 2020/9/29 15:39
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL10_MySQL_Read {
  def main(args: Array[String]): Unit = {
    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._

    // 3.1 通用的load方法读取
    val df: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://hadoop01:3306/gmall?serverTimezone=GMT%2B8&useSSL=false")
      .option("driver", "com.mysql.cj.jdbc.Driver")
      .option("user", "root")
      .option("password", "143382")
      .option("dbtable", "user_info")
      .load()

    // 3.2 创建视图
    df.createOrReplaceTempView("user")

    // 3.3 查询想要的数据
    spark.sql("select id, name from user").show()
    // 5 释放资源
    spark.stop()
  }
}
