import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, SparkSession, functions}
import org.apache.spark.sql.expressions.Aggregator

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: 
 * ClassName: myUDAF 
 *
 * @author 18729 created on date: 2020/9/30 8:40
 * @version 1.0
 * @since JDK 1.8
 */
object myUDAF {
  def main(args: Array[String]): Unit = {
// 1 创建上下文环境配置对象
val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

// 2 创建SparkSession对象
val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val frame: DataFrame = spark.read.json("input/user.json")

    frame.createOrReplaceTempView("user")

    spark.udf.register("myAvg",functions.udaf(new myUDAF))

    spark.sql("select MyAvg(age) from user").show()
// 5 释放资源
spark.stop()
  }
}

//case class Buff(var sum:Long,var count:Long)

class myUDAF extends Aggregator[Long,Buff,Double]{
  override def zero: Buff = Buff(0L,0L)

  override def reduce(b: Buff, a: Long): Buff ={
    b.sum=b.sum+a
    b.count=b.count+1
    b
  }

  override def merge(b1: Buff, b2: Buff): Buff = {
    b1.sum=b1.sum+b2.sum
    b1.count=b1.count+b2.count
    b1
  }

  override def finish(reduction: Buff): Double = {
    reduction.sum.toDouble/reduction.count
  }

  override def bufferEncoder: Encoder[Buff] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}


