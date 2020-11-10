package app

import java.text.SimpleDateFormat

import bean.{OrderDetail, OrderInfo}
import com.alibaba.fastjson.JSON
import constant.GmallConstants
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

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
    //创建配置文件对象 注意：Streaming程序至少不能设置为local，至少需要2个线程
    val conf: SparkConf = new SparkConf().setAppName("Spark01_W").setMaster("local[*]")
    //创建Spark Streaming上下文环境对象
    val ssc = new StreamingContext(conf,Seconds(3))

    val value = utils.MyKafkaUtil.getKafkaStream(GmallConstants.GMALL_ORDER_DETAIL, ssc)
    val value1 = utils.MyKafkaUtil.getKafkaStream(GmallConstants.GMALL_ORDER_INFO, ssc)

    val value2: DStream[(String, OrderInfo)] = value1.map {
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

    val value3: DStream[(String, OrderDetail)] = value.map {
      a => {
        val detail = JSON.parseObject(a.value(), classOf[OrderDetail])
        (detail.order_id, detail)
      }
    }

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
