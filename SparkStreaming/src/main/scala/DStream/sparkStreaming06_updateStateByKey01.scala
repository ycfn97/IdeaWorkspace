package DStream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package: DStream
 * ClassName: sparkStreaming06_updateStateByKey01 
 *
 * @author 18729 created on date: 2020/11/1 17:43
 * @version 1.0
 * @since JDK 1.8
 */
object sparkStreaming06_updateStateByKey01 {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    ssc.checkpoint("./ck")

    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop01", 9999)

    value.flatMap(_.split(" ")).map((_,1))
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}