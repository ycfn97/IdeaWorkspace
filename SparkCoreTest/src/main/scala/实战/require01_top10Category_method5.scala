package 实战

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

class MyAccumulate extends AccumulatorV2[UserVisitAction, mutable.Map[(String, String), Long]] {
  var map=mutable.Map[(String,String),Long]()

  override def isZero: Boolean =map.isEmpty

  override def copy(): AccumulatorV2[UserVisitAction, mutable.Map[(String, String), Long]] = new MyAccumulate()

  override def reset(): Unit = map.clear()

  override def add(v: UserVisitAction): Unit = {
    if (v.click_category_id!= -1){
      val tuple: (String, String) = (v.click_category_id.toString, "click")
      map(tuple)=map.getOrElse(tuple,0L)+1L
    }
    else if(v.order_category_ids!="null"){
      val strings: Array[String] = v.order_category_ids.split(",")

      for (id<-strings){
        val tuple: (String, String) = (id, "order")
        map(tuple)=map.getOrElse(tuple,0L)+1L
      }
    }
    else if(v.pay_category_ids != "null"){
      val strings: Array[String] = v.pay_category_ids.split(",")

      for (id<-strings){
        val tuple: (String, String) = (id, "pay")
        map(tuple)=map.getOrElse(tuple,0L)+1L
      }
    }
  }

  override def merge(other: AccumulatorV2[UserVisitAction, mutable.Map[(String, String), Long]]): Unit = {
    other.value.foreach{
      case(category,count)=>{
        map(category)=map.getOrElse(category,0L)+count
      }
    }
  }

  override def value: mutable.Map[(String, String), Long] = map
}


/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战
 * ClassName: require01_top10Category_method5
 *
 * @author 18729 created on date: 2020/9/27 18:41
 * @version 1.0
 * @since JDK 1.8
 */
object require01_top10Category_method5 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[UserVisitAction] = sc.textFile("input02")
      .map {
        line => {
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
        }
      }

    val accumulate: MyAccumulate = new MyAccumulate

    sc.register(accumulate,"accumulate")

    value.foreach(a=>accumulate.add(a))

    val value1: mutable.Map[(String, String), Long] = accumulate.value

    value1.groupBy(_._1._1)
      .foreach(println)
    //4.关闭连接
    sc.stop()
  }
}

