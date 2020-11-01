package DStream

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package: DStream
 * ClassName: SparkStreaming08_reduceByKeyAndWindow 
 *
 * @author 18729 created on date: 2020/10/31 15:59
 * @version 1.0
 * @since JDK 1.8
 */
object SparkStreaming08_reduceByKeyAndWindow {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop01", 9999)

    val value1: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_, 1))
    //    .reduceByKeyAndWindow((a:Int,b:Int)=>(a+b),Seconds(12),Seconds(3)).print()

    // 4 窗口参数说明： 算法逻辑，窗口12秒，滑步6秒
    /*
    val wordToSumDStream: DStream[(String, Int)]= wordToOne.reduceByKeyAndWindow(
        (x: Int, y: Int) => (x + y),
        (x: Int, y: Int) => (x - y),
        Seconds(12),
        Seconds(6)
    )*/

    // 保存数据到检查点
    ssc.checkpoint("./ck")


    // 处理单词统计次数为0的问题
    val wordToSumDStream: DStream[(String, Int)]= value1.reduceByKeyAndWindow(
      (x: Int, y: Int) => (x + y),
      (x: Int, y: Int) => (x - y),
      Seconds(12),
      Seconds(4),
      new HashPartitioner(2),
      (x:(String, Int)) => x._2 > 0
    )

    // 5 打印
    wordToSumDStream.print()

//    启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
