import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: 
 * ClassName: SparkSQL05_UDF 
 *
 * @author 18729 created on date: 2020/9/29 13:57
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL05_UDF {
  def main(args: Array[String]): Unit = {
    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val frame: DataFrame = spark.read.json("input/user.json")

    frame.createOrReplaceTempView("user")

    spark.udf.register("addName",(x:String)=>"Name:"+x)

    spark.sql("select addName(name),age from user").show()
    // 5 释放资源
    spark.stop()
  }
}
