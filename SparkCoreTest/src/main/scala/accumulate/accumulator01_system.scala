package accumulate

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: accumulate
 * ClassName: accumulator01_system 
 *
 * @author 18729 created on date: 2020/9/27 10:42
 * @version 1.0
 * @since JDK 1.8
 */
object accumulator01_system {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3.创建RDD
    val dataRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 4)))

//    dataRDD.reduceByKey(_+_).collect().foreach(println)

    //3.2 如果不用shuffle，怎么处理呢？
//    var sum = 0
////     打印是在Executor端
//    dataRDD.foreach {
//      case (a, count) => {
//        sum = sum + count
//        println("sum=" + sum)
//      }
//    }
////     打印是在Driver端
//    println(("a", sum))

    val sum: LongAccumulator = sc.longAccumulator("sum")

    dataRDD.foreach {
      case (a, count) => {
        sum.add(count)
        println(sum.value)
      }
    }
    println(sum.value)
    //4.关闭连接
    sc.stop()
  }
}
