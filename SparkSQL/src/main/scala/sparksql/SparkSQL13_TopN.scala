package sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoder, Encoders, SparkSession, functions}
import org.apache.spark.sql.expressions.Aggregator

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: sparksql
 * ClassName: SparkSQL13_TopN 
 *
 * @author 18729 created on date: 2020/9/29 16:47
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL13_TopN {
  def main(args: Array[String]): Unit = {
    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    spark.sql("use default")

    spark.udf.register("city_remark", functions.udaf(new myUDAF()))

    spark.sql(
      """
        | select
        |    c.area, --地区
        |    c.city_name, -- 城市
        |    p.product_name, -- 商品
        |    v.click_product_id -- 点击商品id
        | from user_visit_action v
        | join city_info c
        | on v.city_id = c.city_id
        | join product_info p
        | on v.click_product_id = p.product_id
        | where click_product_id > -1
        |""".stripMargin).
      createOrReplaceTempView("t1")

    // 2. 分组计算每个区域，每个产品的点击量
    spark.sql(
      """
        |select
        |    t1.area, --地区
        |    t1.product_name, -- 商品名称
        |    count(*) click_count, -- 商品点击次数
        |    city_remark(t1.city_name) --城市备注
        |from t1
        |group by t1.area, t1.product_name
            """.stripMargin).
//      show(false)
      createOrReplaceTempView("t2")
//
    spark.sql(
      """
        |select
        |*,
        |rank() over(partition by t2.area order by t2.click_count desc) rank
        |from t2
        |""".stripMargin
    ).
//      show(false)
      createOrReplaceTempView("t3")
//
//    // 4. 每个区域取top3
    spark.sql(
      """
        |select
        |    *
        |from t3
        |where rank <= 3
            """.stripMargin).show(false)


    // 5 释放资源
    spark.stop()
  }
}

case class Buff(var totalcnt:Long,var citymap:mutable.Map[String,Long])

class myUDAF extends Aggregator[String,Buff,String]{
  override def zero: Buff = Buff(0L,mutable.Map[String,Long]())

  override def reduce(b: Buff, a: String): Buff = {
    b.totalcnt+=1
    val l: Long = b.citymap.getOrElse(a,0L)+1L
    b.citymap.update(a, l)
    b
  }

  override def merge(b1: Buff, b2: Buff): Buff = {
    b1.totalcnt+=b2.totalcnt
    b2.citymap.foreach{
      case(city,count)=>{
        val l: Long = b1.citymap.getOrElse(city,0L)+count
        b1.citymap.update(city,l)
      }
    }
    b1
  }



  override def finish(reduction: Buff): String = {

    val remarkList = ListBuffer[String]()
    reduction.citymap.toList.sortWith{
      (a,b)=>{
        a._2>b._2
      }
    }.take(3)
      .foreach{
        case(a,b)=>{
          val l: Long = b*100/reduction.totalcnt
          remarkList.append(a+" "+l+"%")
        }
      }
    remarkList.mkString(",")
  }

  override def bufferEncoder: Encoder[Buff] = Encoders.product

  override def outputEncoder: Encoder[String] = Encoders.STRING
}
