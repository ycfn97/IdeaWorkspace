package app

import java.sql.Date
import java.text.SimpleDateFormat
import java.util

import bean.{CouponAlertInfo, EventLog}
import com.alibaba.fastjson.JSON
import constant.GmallConstants
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.{SparkConf, streaming}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import utils.MyEsUtil

import scala.util.control.Breaks.{break, breakable}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: app
 * ClassName: AlertApp 
 *
 * @author 18729 created on date: 2020/11/10 9:10
 * @version 1.0
 * @since JDK 1.8
 */
object AlertApp {
  def main(args: Array[String]): Unit = {
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    val value = utils.MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_EVENT, ssc)

    val format = new SimpleDateFormat("yyyy-MM-dd HH")
    val value2 = value.map {
      a => {
        val value1 = JSON.parseObject(a.value(), classOf[EventLog])
        val str = format.format(new Date(value1.ts))
        val strings: Array[String] = str.split(" ")
        value1.logDate = strings(0)
        value1.logHour = strings(1)
        (value1.mid, value1)
      }
    }

    val value1 = value2.window(streaming.Minutes(5))
    val value3: DStream[(Boolean, CouponAlertInfo)] = value1.groupByKey().map {
      case (mid, iter) => {
        val strings = new util.HashSet[String]()
        val strings2 = new util.HashSet[String]()
        val strings1 = new util.ArrayList[String]()
        var noClick: Boolean = true

        breakable {
          iter.foreach(
            log => {
              val evid = log.evid
              strings1.add(evid)
              if ("clickItem".equals(evid)) {
                noClick = false
                break()
              } else if ("coupon".equals(evid)) {
                strings.add(log.uid)
                strings2.add(log.itemid)
              }
            }
          )
        }
        (strings.size() >= 3 && noClick, bean.CouponAlertInfo(mid, strings, strings2, strings1, System.currentTimeMillis()))
      }
    }

    value3.filter(_._1).map(_._2).print()
    value3.filter(_._1).map(_._2).foreachRDD(rdd => {
      rdd.foreachPartition(iter => {
        //创建索引名
        val todayStr: String = format.format(new Date(System.currentTimeMillis())).split(" ")(0)
        val indexName = s"${GmallConstants.ES_ALERT_INDEX_PRE}-$todayStr"

        //处理数据,补充docId
        val docList: List[(String, CouponAlertInfo)] = iter.toList.map(alertInfo => {
          val min: Long = alertInfo.ts / 1000 / 60
          (s"${alertInfo.mid}-$min", alertInfo)
        })
        //执行批量写入操作
        MyEsUtil.insertBulk(indexName, docList)
      })
    })
    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
