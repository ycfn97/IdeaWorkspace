package 实战

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战
 * ClassName: require01_top10Category_method3
 *
 * @author 18729 created on date: 2020/9/27 14:05
 * @version 1.0
 * @since JDK 1.8
 */
object require01_top10Category_method3 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    sc.textFile("input02")
      .map(line=>{
        val datas: Array[String] = line.split("_")
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
      })
//      .collect().foreach(println)
      .flatMap(a=>(
        if (a.click_category_id != -1){
          List(CategoryCountInfo(a.click_category_id.toString,1,0,0))
        }else if(a.order_category_ids!="null"){
          val infoesToInfoes: ListBuffer[CategoryCountInfo] =new  ListBuffer[CategoryCountInfo]
          val strings: Array[String] = a.order_category_ids.split(",")
          for (id<-strings){
            infoesToInfoes.append(CategoryCountInfo(id,0,1,0))
          }
          infoesToInfoes
        }else if (a.pay_category_ids!="null"){
          val infoesToInfoes: ListBuffer[CategoryCountInfo] =new  ListBuffer[CategoryCountInfo]
          val strings: Array[String] = a.pay_category_ids.split(",")
          for (id<-strings){
            infoesToInfoes.append(CategoryCountInfo(id,0,0,1))
          }
          infoesToInfoes
        }else{
          Nil
        }
      ))
      .groupBy(info=>info.categoryId)
      .mapValues(a=>a.reduce(
          (a,b)=>{
            a.orderCount=a.orderCount+b.orderCount
            a.clickCount=a.clickCount+b.clickCount
            a.payCount=a.payCount+b.payCount
            a
          }
          ))
          .map(_._2)
          .sortBy(a=>(a.clickCount,a.orderCount,a.payCount),false)
      .collect().foreach(println)
    //      .take(10).foreach(println)

//    Thread.sleep(1000000)
    //4.关闭连接
    sc.stop()

//    sc.textFile("").map(_).flatMap(_).groupBy(_).mapValues(_).map(_._2).sortBy(_).collect().foreach(println)
//    1，获取对象，2，扁平化获得品类次数信息，3，按照城市id聚合，4，对聚合后的value相加，5，取第二个参数，6，排序并取前十
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
