import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package: 
 * ClassName: Spark02_DStreamCreate_RDDQueue 
 *
 * @author 18729 created on date: 2020/10/1 11:06
 * @version 1.0
 * @since JDK 1.8
 */
object Spark02_DStreamCreate_RDDQueue {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    val queue: mutable.Queue[RDD[Int]] = new mutable.Queue[RDD[Int]]()

    ssc.queueStream(queue,oneAtATime = false).map((_,1)).reduceByKey(_+_).print()
    //启动采集器
    ssc.start()

    for (i <- 1 to 5) {
      queue += ssc.sparkContext.makeRDD(1 to 5, 10)
      Thread.sleep(2000)
    }
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
