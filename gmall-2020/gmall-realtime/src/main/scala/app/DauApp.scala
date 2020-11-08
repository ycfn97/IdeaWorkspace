package app

import java.sql.Date
import java.text.SimpleDateFormat

import bean.StartUpLog
import com.alibaba.fastjson.JSON
import constant.GmallConstants
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.phoenix.spark.toProductRDDFunctions
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: app
 * ClassName: RealtimeStartupApp 
 *
 * @author 18729 created on date: 2020/11/4 14:49
 * @version 1.0
 * @since JDK 1.8
 */
object DauApp {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(5))

    val value: InputDStream[ConsumerRecord[String, String]] = utils.MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_STARTUP, ssc)
//    kafka中数据是有key的，我们将值放在value中，并取出放到样例类中
//    value.map(_.value()).map{a=>{将json串封装进样例类，然后进一步取出需要的数据}}
    val format: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH")
    val value1: DStream[StartUpLog] = value.map(_.value()).map {
      a => {
        val log: StartUpLog = JSON.parseObject(a, classOf[StartUpLog])
        val str: String = format.format(new Date(log.ts))
        val strings: Array[String] = str.split(" ")
        log.logDate = strings(0)
        log.logHour = strings(1)
        log
      }
    }

//
    val value2 = handler.DauHandler.filterByRedis(value1)

    val filterdByMid: DStream[StartUpLog] = handler.DauHandler.filterByMid(value2)

//    value1.cache()
//    value1.count().print()
//    value2.cache()
//    value2.count().print()

    handler.DauHandler.saveMidToRedis(filterdByMid)

    filterdByMid.foreachRDD(rdd => {

      rdd.saveToPhoenix("GMALL2020_DAU",
        Seq("MID", "UID", "APPID", "AREA", "OS", "CH", "TYPE", "VS", "LOGDATE", "LOGHOUR", "TS"),
        HBaseConfiguration.create(),
        Some("hadoop01,hadoop02,hadoop03:2181"))
    })

//    value.foreachRDD(a=>{
//      a.foreach(
//        a=>{
//          println(a.value())
//        }
//      )
//    })
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
