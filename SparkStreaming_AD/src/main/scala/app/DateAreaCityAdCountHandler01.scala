package app

import java.sql.{Connection, Date}
import java.text.SimpleDateFormat
import org.apache.spark.streaming.dstream.DStream

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming_AD
 * Package: app
 * ClassName: DateAreaCityAdCountHandler01 
 *
 * @author 18729 created on date: 2020/11/3 9:33
 * @version 1.0
 * @since JDK 1.8
 */
object DateAreaCityAdCountHandler01 {
  // 时间格式化对象
  private val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

  def saveDateAreaCityAdCountToMysql(DStream:DStream[Ads_log])={
    DStream.map{
      a=>{
        val str: String = sdf.format(new Date(a.timestamp))
        ((str,a.area,a.city,a.adid),1L)
      }
    }.reduceByKey(_+_)
      .foreachRDD{
        a=>{
          a.foreachPartition{
            a=>{
              val connection: Connection = util.JDBCUtil.getConnection
              a.foreach{
                case ((dt, area, city, adid), ct)=>{
                  util.JDBCUtil.executeUpdate(
                    connection,
                    """
                      |insert into area_city_ad_count (dt,area,city,adid,ct) values(?,?,?,?,?)
                      |""".stripMargin,
                    Array(dt,area,city,adid,ct)
                  )
                }
              }
              connection.close()
            }
          }
        }
      }
  }
}
