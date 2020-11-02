import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package: 
 * ClassName: SparkStreaming11_stop 
 *
 * @author 18729 created on date: 2020/11/2 9:59
 * @version 1.0
 * @since JDK 1.8
 */
object SparkStreaming11_stop {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    // 设置优雅的关闭
    conf.set("spark.streaming.stopGracefullyOnShutdown", "true")

    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop01", 9999)

    value.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    // 开启监控程序
    new Thread(new MonitorStop(ssc)).start()

    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}

class MonitorStop(ssc: StreamingContext) extends Runnable{
  override def run(): Unit = {
    // 获取HDFS文件系统
    val fs: FileSystem = FileSystem.get(new URI("hdfs://hadoop01:9820"),new Configuration(),"atguigu")

    while (true){
      Thread.sleep(5000)
      // 获取/stopSpark路径是否存在
      val result: Boolean = fs.exists(new Path("hdfs://hadoop01:9820/stopspark"))

      if (result){

        val state: StreamingContextState = ssc.getState()
        // 获取当前任务是否正在运行
        if (state == StreamingContextState.ACTIVE){
          // 优雅关闭
          ssc.stop(stopSparkContext = true, stopGracefully = true)
          System.exit(0)
        }
      }
    }
  }
}