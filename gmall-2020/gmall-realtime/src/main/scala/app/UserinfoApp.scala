package app

import constant.GmallConstants
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import utils.MyKafkaUtil

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: app
 * ClassName: UserinfoApp 
 * 将用户新增及变化数据缓存进redis
 * @author 18729 created on date: 2020/11/10 19:35
 * @version 1.0
 * @since JDK 1.8
 */
object UserinfoApp {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    //3.消费Kafka用户主题数据创建流
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.GMALL_USER_INFO, ssc)


    //打印测试
    kafkaDStream.foreachRDD(rdd => {
      rdd.foreach(record => println(record.value()))
    })
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
