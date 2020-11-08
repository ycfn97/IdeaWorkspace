package app

import bean.{OrderInfo, StartUpLog}
import com.alibaba.fastjson.JSON
import constant.GmallConstants
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.phoenix.spark._

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: app
 * ClassName: GmvApp 
 *
 * @author 18729 created on date: 2020/11/7 9:23
 * @version 1.0
 * @since JDK 1.8
 */
object GmvApp {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf, Seconds(5))
    val value: InputDStream[ConsumerRecord[String, String]] = utils.MyKafkaUtil.getKafkaStream(GmallConstants.GMALL_ORDER_INFO, ssc)
    val value2: DStream[OrderInfo] = value.map(
      a => {
        val value1 = JSON.parseObject(a.value(), classOf[OrderInfo])
        val strings: Array[String] = value1.create_time.split(" ")
        value1.create_date = strings(0)
        val strings1: Array[String] = strings(1).split(":")
        value1.create_hour = strings1(0)
        value1.consignee_tel = value1.consignee_tel.splitAt(4)._1 + "******"
        println(a.value())
        value1
      }
    )

    value2.foreachRDD(
      a => {
        println("aaaaaaaaaaaaa")
        a.saveToPhoenix("GMALL2020_ORDER_INFO", classOf[OrderInfo].getDeclaredFields.map(_.getName.toUpperCase())
          , HBaseConfiguration.create(), Some("hadoop01,hadoop02,hadoop03:2181"))
      }
    )
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}


