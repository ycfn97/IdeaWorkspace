package app

import java.sql.{Connection, Date}
import java.text.SimpleDateFormat

import com.alibaba.druid.util.JdbcUtils
import org.apache.spark.streaming.dstream.DStream

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming_AD
 * Package: app
 * ClassName: BlackListHandler01 
 *
 * @author 18729 created on date: 2020/11/3 9:01
 * @version 1.0
 * @since JDK 1.8
 */
object BlackListHandler01 {

//  过滤黑名单
  def FilterByBlackList(DStream:DStream[Ads_log])={
    DStream.filter{
      a=>{
        val connection: Connection = util.JDBCUtil.getConnection
        val bool: Boolean = util.JDBCUtil.isExist(
          connection,
          """
            |select * from user_ad_count where userid=?
            |""".stripMargin,
          Array(a.userid)
        )
        connection.close()
        !bool
      }
    }
  }

  //时间格式化对象
  private val sdf = new SimpleDateFormat("yyyy-MM-dd")

//  添加黑名单
  def addBlackList(DStream: DStream[Ads_log])={
    DStream.map(
      a=>{
        val str: String = sdf.format(new Date(a.timestamp))
        ((str,a.userid,a.adid),1L)
      }
    ).reduceByKey(_+_).foreachRDD{
      a=>{
        a.foreachPartition{
          a=>{
            val connection: Connection = util.JDBCUtil.getConnection
            a.foreach {
              case ((dt, userid, adid), count) => {
                util.JDBCUtil.executeUpdate(
                  connection,
                  """
                    |insert into table user_ad_count(dt,userid,adid,count) values(?,?,?,?) on duplicate key update count=count+?
                    |""".stripMargin,
                  Array(dt,userid,adid,count,count)
                )
                val l: Long = util.JDBCUtil.getDataFromMysql(
                  connection,
                  """
                    |select count from user_ad_count where dt=? and userid=? and adid=?
                    |""".stripMargin,
                  Array(dt, userid, adid)
                )
                if (l>=30){
                  util.JDBCUtil.executeUpdate(
                    connection,
                    """
                      |insert into black_list (userid) values(?) on duplicate key userid=?
                      |""".stripMargin,
                    Array(userid,userid)
                  )
                }
              }
            }
            connection.close()
          }
        }
      }
    }
  }
}
