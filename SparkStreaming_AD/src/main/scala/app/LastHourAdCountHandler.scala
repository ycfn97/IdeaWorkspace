package app

import java.sql.Date
import java.text.SimpleDateFormat

import org.apache.spark.streaming
import org.apache.spark.streaming.Minutes
import org.apache.spark.streaming.dstream.DStream
/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming_AD
 * Package: app
 * ClassName: LastHourAdCountHandler 
 *
 * @author 18729 created on date: 2020/11/2 16:52
 * @version 1.0
 * @since JDK 1.8
 */
object LastHourAdCountHandler {

  def getAdHourMintToCount(value2: DStream[Ads_log]):DStream[(String, List[(String, Long)])]={
    value2.window(Minutes(2)).map(
      a=>{
        val str: String = format.format(new Date(a.timestamp))
        ((a.adid,str),1L)
      }
    ).reduceByKey(_+_)
      .map{
        case((adid,hm),count)=>{
          (adid,(hm,count))
        }
      }.groupByKey()
      .mapValues(iter => iter.toList.sortWith(_._1 < _._1))
  }

  private val format: SimpleDateFormat = new SimpleDateFormat("HH:mm")
}
