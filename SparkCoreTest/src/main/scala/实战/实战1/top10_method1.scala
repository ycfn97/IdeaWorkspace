package 实战.实战1

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战.实战1
 * ClassName: top10_method1 
 *
 * @author 18729 created on date: 2020/9/27 22:46
 * @version 1.0
 * @since JDK 1.8
 */
object top10_method1 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    sc.textFile("input02")
      .map(
      a => {
        val datas: Array[String] = a.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong
        )
      }
    )
      .flatMap(
        a=>{
          if (a.click_category_id!= -1){
            List((a.city_id.toString,CategoryCountInfo(a.city_id.toString,1,0,0)))
          }else if (a.order_category_ids!="null"){
            val tuples: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
            for (id<-a.order_category_ids.split(",")){
              tuples.append((a.city_id.toString,CategoryCountInfo(a.city_id.toString,0,1,0)))
            }
            tuples
          }else if (a.pay_category_ids!="null"){
            val tuples: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
            for (id<-a.pay_category_ids.split(",")){
              tuples.append((a.city_id.toString,CategoryCountInfo(a.city_id.toString,0,0,1)))
            }
            tuples
          }else{
            Nil
          }
        }
      )
      .reduceByKey(
        (a,b)=>{
          a.orderCount=a.orderCount+b.orderCount
          a.clickCount=a.clickCount+b.clickCount
          a.payCount=a.payCount+b.payCount
          a
        }
      )
      .map(_._2)
      .sortBy(a=>((a.clickCount,a.orderCount,a.payCount),true))
      .take(10).foreach(println)

    Thread.sleep(100000000)
    //4.关闭连接
    sc.stop()
  }
}

//用户访问动作表
case class UserVisitAction(date: String,//用户点击行为的日期
                           user_id: Long,//用户的ID
                           session_id: String,//Session的ID
                           page_id: Long,//某个页面的ID
                           action_time: String,//动作的时间点
                           search_keyword: String,//用户搜索的关键词
                           click_category_id: Long,//某一个商品品类的ID
                           click_product_id: Long,//某一个商品的ID
                           order_category_ids: String,//一次订单中所有品类的ID集合
                           order_product_ids: String,//一次订单中所有商品的ID集合
                           pay_category_ids: String,//一次支付中所有品类的ID集合
                           pay_product_ids: String,//一次支付中所有商品的ID集合
                           city_id: Long)//城市 id

// 输出结果表
case class CategoryCountInfo(var categoryId: String,//品类id
                             var clickCount: Long,//点击次数
                             var orderCount: Long,//订单次数
                             var payCount: Long)//支付次数

