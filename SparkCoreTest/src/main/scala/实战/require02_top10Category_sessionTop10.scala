package 实战

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战
 * ClassName: require01_top10Category_method4
 *
 * @author 18729 created on date: 2020/9/27 15:22
 * @version 1.0
 * @since JDK 1.8
 */
object require02_top10Category_sessionTop10 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value1: RDD[UserVisitAction] = sc.textFile("input02")
      .map(a => {
        val strings: Array[String] = a.split("_")
        UserVisitAction(
          strings(0),
          strings(1).toLong,
          strings(2),
          strings(3).toLong,
          strings(4),
          strings(5),
          strings(6).toLong,
          strings(7).toLong,
          strings(8),
          strings(9),
          strings(10),
          strings(11),
          strings(12).toLong
        )
      })

    val strings: Array[String] = value1.flatMap(
      a => {
        if (a.click_category_id != -1) {
          List((a.click_category_id.toString, CategoryCountInfo(a.click_category_id.toString, 1, 0, 0)))
        }
        else if (a.order_category_ids != "null") {
          var list: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
          for (id <- a.order_category_ids.split(",")) {
            list.append((id, CategoryCountInfo(id, 0, 1, 0)))
          }
          list
        }
        else if (a.pay_category_ids != "null") {
          var list: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
          for (id <- a.pay_category_ids.split(",")) {
            list.append((id, CategoryCountInfo(id, 0, 0, 1)))
          }
          list
        }
        else {
          Nil
        }
      }
    )
      .reduceByKey(
        (info1, info2) => {
          info1.orderCount = info1.orderCount + info2.orderCount
          info1.clickCount = info1.clickCount + info2.clickCount
          info1.payCount = info1.payCount + info2.payCount

          info1
        }
      ).map(_._2)
      .sortBy(a => (a.clickCount, a.orderCount, a.payCount), true)
      .take(10)
      .map(_.categoryId)

    /**
     * =======================================需求二=================================================
     */

    val value: Broadcast[Array[String]] = sc.broadcast(strings)
    value1.filter{
      a=>{
        if (a.click_category_id!= -1){
          value.value.contains(a.click_category_id.toString)
        }else{
          false
        }
      }
    }
      .map{
        a=>(a.click_category_id+"--"+a.city_id,1)
      }
      .reduceByKey(_+_)
      .map{
        case(key,sum)=>{
          val strings1: Array[String] = key.split("--")
          ((strings1(0),(strings1(1),sum)))
        }
      }
      .groupByKey()
      .mapValues(
        a=>{
          a.toList.sortWith{
            (a,b)=>(a._2>b._2)
          }.take(10)
        }
      )
      .foreach(println)
    Thread.sleep(1000000000)
    //4.关闭连接
    sc.stop()
  }
}

////用户访问动作表
//case class UserVisitAction(date: String, //用户点击行为的日期
//                           user_id: Long, //用户的ID
//                           session_id: String, //Session的ID
//                           page_id: Long, //某个页面的ID
//                           action_time: String, //动作的时间点
//                           search_keyword: String, //用户搜索的关键词
//                           click_category_id: Long, //某一个商品品类的ID
//                           click_product_id: Long, //某一个商品的ID
//                           order_category_ids: String, //一次订单中所有品类的ID集合
//                           order_product_ids: String, //一次订单中所有商品的ID集合
//                           pay_category_ids: String, //一次支付中所有品类的ID集合
//                           pay_product_ids: String, //一次支付中所有商品的ID集合
//                           city_id: Long) //城市 id
//
//// 输出结果表
//case class CategoryCountInfo(var categoryId: String, //品类id
//                             var clickCount: Long, //点击次数
//                             var orderCount: Long, //订单次数
//                             var payCount: Long) //支付次数



