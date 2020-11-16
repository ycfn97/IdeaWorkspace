package DStream

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package:
 * ClassName: SparkStreaming05_Transform
 *
 * @author 18729 created on date: 2020/10/31 15:15
 * @version 1.0
 * @since JDK 1.8
 */
object SparkStreaming05_Transform {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf, Seconds(3))

    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop01", 9999)

    // 在Driver端执行，全局一次
    println("111111111:" + Thread.currentThread().getName)

    //4 转换为RDD操作
    val wordToSumDStream: DStream[(String, Int)] = value.transform(
      rdd => {
        // 在Driver端执行(ctrl+n JobGenerator)，一个批次一次
        println("222222:" + Thread.currentThread().getName)
        val words: RDD[String] = rdd.flatMap(_.split(" "))
        val wordToOne: RDD[(String, Int)] = words.map(x => {
          // 在Executor端执行，和单词个数相同
          println("333333:" + Thread.currentThread().getName)
          (x, 1)
        })
        val value: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)
        value
      }
    )
    //5 打印
    wordToSumDStream.print
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
