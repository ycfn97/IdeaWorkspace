package app

import java.sql.{Connection, Date}
import java.text.SimpleDateFormat

import org.apache.spark.streaming.dstream.DStream
import util.JDBCUtil

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming_AD
 * Package: app
 * ClassName: DateAreaCityAdCountHandler
 *
 * @author 18729 created on date: 2020/11/2 16:28
 * @version 1.0
 * @since JDK 1.8
 */
object DateAreaCityAdCountHandler {
  private val format: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

  def saveDateAreaCityAdCountToMysql(filterAdsLogDStream: DStream[Ads_log]) = {
    filterAdsLogDStream.map(a => {
      val str: String = format.format(new Date(a.timestamp))
      ((str, a.area, a.city, a.adid), 1L)
    }).reduceByKey(_ + _)
      .foreachRDD(
        a => {
          a.foreachPartition(
            a => {
              //a.获取连接
              val connection: Connection = JDBCUtil.getConnection
              //b.写库
              a.foreach { case ((dt, area, city, adid), ct) =>
                JDBCUtil.executeUpdate(
                  connection,
                  """
                    |INSERT INTO area_city_ad_count (dt,area,city,adid,count)
                    |VALUES(?,?,?,?,?)
                    |ON DUPLICATE KEY
                    |UPDATE count=count+?;
                        """.stripMargin,
                  Array(dt, area, city, adid, ct, ct)
                )
              }
              //c.释放连接
              connection.close()
            }
          )
        }
      )
  }
}
