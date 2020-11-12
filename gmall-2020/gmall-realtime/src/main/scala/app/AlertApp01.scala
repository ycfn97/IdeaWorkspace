package app

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: app
 * ClassName: AlertApp01 
 *
 * @author 18729 created on date: 2020/11/10 9:45
 * @version 1.0
 * @since JDK 1.8
 */
import java.sql.Date
import java.text.SimpleDateFormat

import com.alibaba.fastjson.JSON
import bean.{CouponAlertInfo, EventLog}
import utils.MyEsUtil
import constant.GmallConstants
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import utils.MyKafkaUtil

import scala.util.control.Breaks._

object AlertApp01 {

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("event_app")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    val inputDstream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_EVENT,ssc)
    //1 格式转换成样例类
    val eventInfoDstream = inputDstream.map { record =>
      JSON.parseObject(record.value(), classOf[EventLog])
    }
    //2 开窗口
    val eventInfoWindowDstream = eventInfoDstream.window(Seconds(30),Seconds(5))
    //3同一设备 分组
    val groupbyMidDstream = eventInfoWindowDstream.map(eventInfo=>(eventInfo.mid,eventInfo)).groupByKey()
    //4 判断预警
    //  在一个设备之内
    //  1 三次及以上的领取优惠券 (evid coupon) 且 uid都不相同
    //  2 没有浏览商品(evid  clickItem)
    val checkCouponAlertDStream: DStream[(Boolean, CouponAlertInfo)] = groupbyMidDstream.map {
      case (mid, eventInfoItr) =>
      val couponUidsSet = new java.util.HashSet[String]()
      val itemIdsSet = new java.util.HashSet[String]()
      val eventIds = new java.util.ArrayList[String]()
      var notClickItem: Boolean = true
      breakable(
        for (eventInfo <- eventInfoItr) {
          eventIds.add(eventInfo.evid) //用户行为
          if (eventInfo.evid == "coupon") {
            couponUidsSet.add(eventInfo.uid) //用户领券的uid
            itemIdsSet.add(eventInfo.itemid) //用户领券的商品id
          } else if (eventInfo.evid == "clickItem") {
            notClickItem = false
            break()
          }
        }
      )
      //组合成元组  （标识是否达到预警要求，预警信息对象）
      (couponUidsSet.size() >= 3 && notClickItem, CouponAlertInfo(mid, couponUidsSet, itemIdsSet, eventIds, System.currentTimeMillis()))
    }
    //过滤
    val filteredDstream: DStream[(Boolean, CouponAlertInfo)] = checkCouponAlertDStream.filter{_._1}
    //增加一个id用于保存到es的时候进行去重操作
    val alertInfoWithIdDstream: DStream[(String, CouponAlertInfo)] = filteredDstream.map { case (flag, alertInfo) =>
      val period: Long = alertInfo.ts / 1000L / 60L
      val id: String = alertInfo.mid + "_" + period.toString
      (id, alertInfo)
    }
//    alertInfoWithIdDstream.foreachRDD{ rdd=>
//      rdd.foreachPartition{ alertInfoItr=>
//        val dateString: String = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())).split(" ")(0)
//        MyEsUtil.insertBulk(GmallConstants.ES_ALERT_INDEX_PRE+"_"+dateString,alertInfoItr)
//      }
//    }
    ssc.start()
    ssc.awaitTermination()

  }

}

