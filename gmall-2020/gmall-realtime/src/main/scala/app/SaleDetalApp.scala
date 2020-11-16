package app

import java.sql.{Connection, Date}
import java.text.SimpleDateFormat
import java.util

import bean.{OrderDetail, OrderInfo, SaleDetail, UserInfo}
import com.alibaba.fastjson.JSON
import constant.GmallConstants
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis
import utils.JdbcUtil

import scala.collection.JavaConverters.asScalaSetConverter
import scala.collection.mutable.ListBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: app
 * ClassName: SaleDetalApp
 * 将orderinfo和orderdetail数据进行双流join，根据user_id查询redis，补全用户信息
 * @author 18729 created on date: 2020/11/10 19:35
 * @version 1.0
 * @since JDK 1.8
 */
object SaleDetalApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))
    val value = utils.MyKafkaUtil.getKafkaStream(GmallConstants.GMALL_ORDER_DETAIL, ssc)
    val value1 = utils.MyKafkaUtil.getKafkaStream(GmallConstants.GMALL_ORDER_INFO, ssc)
    val info: DStream[(String, OrderInfo)] = value1.map {
      a => {
        val orderInfo = JSON.parseObject(a.value(), classOf[OrderInfo])
        //b.取出创建时间 yyyy-MM-dd HH:mm:ss
        val create_time: String = orderInfo.create_time
        //c.给时间重新赋值
        val dateTimeArr: Array[String] = create_time.split(" ")
        orderInfo.create_date = dateTimeArr(0)
        orderInfo.create_hour = dateTimeArr(1).split(":")(0)
        //d.数据脱敏
        val consignee_tel: String = orderInfo.consignee_tel
        val tuple: (String, String) = consignee_tel.splitAt(4)
        orderInfo.consignee_tel = tuple._1 + "*******"
        //e.返回结果
        (orderInfo.id, orderInfo)
      }
    }
    val detal: DStream[(String, OrderDetail)] = value.map {
      a => {
        val detail = JSON.parseObject(a.value(), classOf[OrderDetail])
        (detail.order_id, detail)
      }
    }
    //双流JOIN(普通JOIN)
//    val value2: DStream[(String, (OrderInfo, OrderDetail))] = info.join(detal)
//        value2.print(100)

    val value2: DStream[(String, (Option[OrderInfo], Option[OrderDetail]))] = info.fullOuterJoin(detal)

//    value2.mapPartitions(
//      a=>{
//        val client: Jedis = utils.RedisUtil.getJedisClient
//        val details: ListBuffer[SaleDetail] = new ListBuffer[SaleDetail]
//        a.foreach{
//          case (orderid,(orderinfo,orderDetail))=>{
//            val value3: String = s"orderinfo:${orderid}"
//            val value4: String = s"orderdetail:${orderid}"
//            if (orderinfo.isDefined){
//              val value5: OrderInfo = orderinfo.get
//              if (orderDetail.isDefined){
//                val value6: OrderDetail = orderDetail.get
//                val detail: SaleDetail = new SaleDetail(value5, value6)
//                details+=detail
//              }
//              import org.json4s.native.Serialization
//              implicit val formats = org.json4s.DefaultFormats
//              val str: String = Serialization.write(orderinfo)
//              client.set(value3,str)
//              client.expire(value3,100)
//
//              val strings: util.Set[String] = client.smembers(value4)
//              strings.asScala.foreach(
//                a=>{
//
//                }
//              )
//            }
//          }
//        }
//    }
//    )

    val value6: DStream[SaleDetail] = value2.mapPartitions(
      a => {
        val client = utils.RedisUtil.getJedisClient
        val details = new ListBuffer[SaleDetail]
        a.foreach {
          case (orderid, (orderinfo, orderdetail)) => {
            val infoRedisKey = s"OrderInfo:$orderid"
            val detailRedisKey = s"OrderDetail:$orderid"
            if (orderinfo.isDefined) {
              val value3 = orderinfo.get
              if (orderdetail.isDefined) {
                val value4 = orderdetail.get
                val detail = new SaleDetail(value3, value4)
                details += detail
              }
              // 将info数据写入redis,给后续的detail数据使用
              import org.json4s.native.Serialization
              implicit val formats = org.json4s.DefaultFormats
              val orderInfoJson: String = Serialization.write(orderinfo)
              client.set(infoRedisKey, orderInfoJson)
              client.expire(infoRedisKey, 100)

              val strings: util.Set[String] = client.smembers(detailRedisKey)
              strings.asScala.foreach(
                a => {
                  val detail = JSON.parseObject(a, classOf[OrderDetail])
                  details += new SaleDetail(value3, detail)
                }
              )
            } else {
              val value5 = orderdetail.get
              if (client.exists(infoRedisKey)) {
                val str = client.get(infoRedisKey)
                val info1 = JSON.parseObject(str, classOf[OrderInfo])
                details += new SaleDetail(info1, value5)
              } else {
                import org.json4s.native.Serialization
                implicit val formats = org.json4s.DefaultFormats
                val str = Serialization.write(value5)
                client.sadd(detailRedisKey, str)
                client.expire(detailRedisKey, 100)
              }
            }
          }
        }
        client.close()
        details.iterator
      }
    )

//    value6.print(100)

    value6.mapPartitions(
      a=>{
        val client: Jedis = utils.RedisUtil.getJedisClient
        a.map(
          a=>{
            s""
          }
        )
      }
    )

    val value3: DStream[SaleDetail] = value6.mapPartitions(
      a => {
        val client: Jedis = utils.RedisUtil.getJedisClient
        val details = a.map {
          a => {
            val str: String = s"UserInfo:${a.user_id}"
            if (client.exists(str)) {
              val str1 = client.get(str)
              val info1: UserInfo = JSON.parseObject(str1, classOf[UserInfo])
              a.mergeUserInfo(info1)
            } else {
              val connection: Connection = utils.JdbcUtil.getConnection
              val str1: String = JdbcUtil.getUserInfoFromMysql(connection, "select * from user_info where id=?", Array(a.user_id))
              val info1: UserInfo = JSON.parseObject(str1, classOf[UserInfo])
              a.mergeUserInfo(info1)
              connection.close()
            }
            a
          }
        }
        client.close()
        details
      }
    )

        value3.print()

    val format: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    value3.foreachRDD(
      a=>{
        a.foreachPartition(
          a=>{
            val value4: String = s"${GmallConstants.ES_SALE_DETAIL_INDEX_PRE}-${format.format(new Date(System.currentTimeMillis()))}"
            val tuples: List[(String, SaleDetail)] = a.toList.map(
              a => (a.order_detail_id, a)
            )
            utils.MyEsUtil.insertBulk(value4,tuples)
          }
        )
      }
    )

//    val sdf = new SimpleDateFormat("yyyy-MM-dd")
//    value3.foreachRDD(
//      a=>{
//        a.foreachPartition(
//          a=>{
//            s""
//          }
//        )
//      }
//    )

    //测试
//        value.foreachRDD(rdd=>{
//          rdd.foreach(record=>println(record.value()))
//        })
//        value1.foreachRDD(rdd=>{
//          rdd.foreach(record=>println(record.value()))
//        })


    //启动采集器
    ssc.start()
    //默认情况下，上下文对象不能关闭
    //ssc.stop()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
