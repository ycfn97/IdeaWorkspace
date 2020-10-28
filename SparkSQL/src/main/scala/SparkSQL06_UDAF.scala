import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, SparkSession, functions}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: 
 * ClassName: SparkSQL06_UDAF 
 *
 * @author 18729 created on date: 2020/9/29 14:05
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL06_UDAF {
  def main(args: Array[String]): Unit = {
    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val frame: DataFrame = spark.read.json("input/user.json")

    frame.createOrReplaceTempView("user")

    spark.udf.register("MyAvg",functions.udaf(new MyAvgUDAF))

    spark.sql("select MyAvg(age) from user").show()

    // 5 释放资源
    spark.stop()
  }
}

case class Buff(var sum:Long,var count:Long)

class MyAvgUDAF extends Aggregator[Long, Buff, Double] {

  // 初始化缓冲区
  override def zero: Buff = Buff(0L, 0L)

  // 将输入的年龄和缓冲区的数据进行聚合
  override def reduce(buff: Buff, age: Long): Buff = {
    buff.sum = buff.sum + age
    buff.count = buff.count + 1
    buff
  }

  // 多个缓冲区数据合并
  override def merge(buff1: Buff, buff2: Buff): Buff = {
    buff1.sum = buff1.sum + buff2.sum
    buff1.count = buff1.count + buff2.count
    buff1
  }

  // 完成聚合操作，获取最终结果
  override def finish(buff: Buff): Double = {
    buff.sum.toDouble / buff.count
  }

  // SparkSQL对传递的对象的序列化操作（编码）
  // 自定义类型就是product   自带类型根据类型选择
  override def bufferEncoder: Encoder[Buff] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}