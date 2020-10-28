package accumulate

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: accumulate
 * ClassName: accumulator02_updateCount 
 *
 * @author 18729 created on date: 2020/9/27 19:05
 * @version 1.0
 * @since JDK 1.8
 */
object accumulator02_updateCount {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3.创建RDD
    val dataRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 4)))

    val sum: LongAccumulator = sc.longAccumulator("sum")

    val value: RDD[(String, Int)] = dataRDD.map(
      a => {
        sum.add(1)
        a
      }
    )
    value.foreach(println)
    println(sum.value)
    value.collect()
    println(sum.value)
    //4.关闭连接
    sc.stop()
  }
}
