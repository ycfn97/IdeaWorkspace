package app

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.xml.dtd.ContentModelParser.value

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming_AD
 * Package: app
 * ClassName: RealTimeAPP01
 *
 * @author 18729 created on date: 2020/11/3 8:42
 * @version 1.0
 * @since JDK 1.8
 */
object RealTimeAPP01 {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))
    val properties: Properties = util.PropertiesUtil.load("config.properties")
    val str: String = properties.getProperty("kafka.topic")
    val value1: InputDStream[ConsumerRecord[String, String]] = util.MyKafkaUtil.getKafkaStream(str, ssc)
    val value2: DStream[Ads_log] = value1.map(a => {
      var value = a.value().split(" ")
      Ads_log(value(0).toLong, value(1), value(2), value(3), value(4))
    })

    val value3: DStream[Ads_log] = BlackListHandler.filterByBlackList(value2)
    BlackListHandler.addBlackList(value3)

    
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}

// 时间 地区 城市 用户id 广告id
case class Ads_log(timestamp: Long, area: String, city: String, userid: String, adid: String)
