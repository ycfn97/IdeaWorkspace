package handler

import bean.StartUpLog
import org.apache.spark.streaming.dstream.DStream

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: handler
 * ClassName: DauHandler
 *
 * @author 18729 created on date: 2020/11/5 14:06
 * @version 1.0
 * @since JDK 1.8
 */
object DauHandler {

  def filterByMid(filterdByRedis: DStream[StartUpLog]): DStream[StartUpLog] = {
    //1.转换数据结构 log ==> (mid_logDate,log)
    val midDateToLogDStream: DStream[((String, String), StartUpLog)] = filterdByRedis.map(log => ((log.mid, log.logDate), log))
    //2.按照Key分组
    val midDateToLogIterDStream: DStream[((String, String), Iterable[StartUpLog])] = midDateToLogDStream.groupByKey()
    //KV结构数据:   是否需要Key,数据是否需要压平
    //map            不需要Key,不需要压平
    //mapValues        需要Key,不需要压平
    //flatMap        不需要Key,需要压平
    //flatMapValues    需要Key,需要压平
    //3.对Value按照时间排序取第一条
    //    val midDateToLogListDStream: DStream[((String, String), List[StartUpLog])] = midDateToLogIterDStream.mapValues(iter => {
    //      iter.toList.sortWith(_.ts < _.ts).take(1)
    //    })
    //4.将集合压平
    //    midDateToLogListDStream.flatMap(_._2)
    midDateToLogIterDStream.flatMap { case ((mid, date), iter) =>
      iter.toList.sortWith(_.ts < _.ts).take(1)
    }
  }


  def filterByRedis(value1: DStream[StartUpLog]) = {
    val value2 = value1.filter(
      a => {
        val client = utils.RedisUtil.getJedisClient
        val value = s"DAU:${a.logDate}"
        val boolean = client.sismember(value, a.mid)
        client.close()
        !boolean
      }
    )

    val value = value1.transform {
      a => {
        a.mapPartitions(
          a => {
            val client = utils.RedisUtil.getJedisClient
            val value3 = a.filter(
              a => {
                val value = s"DAU:${a.logDate}"
                !client.sismember(value, a.mid)
              }
            )
            client.close()
            value3
          }
        )
      }
    }
    value
  }


  def saveMidToRedis(value1: DStream[StartUpLog]) = {
    value1.foreachRDD(
      a=>{
        a.foreachPartition(
          a=>{
            val client = utils.RedisUtil.getJedisClient
            a.foreach(
              a=>{
                val value = s"DAU:${a.logDate}"
                client.sadd(value, a.mid)
              }
            )
            client.close()
          }
        )
      }
    )
  }

}
