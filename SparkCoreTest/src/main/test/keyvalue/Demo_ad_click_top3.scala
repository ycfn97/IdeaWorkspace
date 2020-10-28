package keyvalue

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.key-value
 * ClassName: test01 
 *
 * @author 18729 created on date: 2020/9/24 11:56
 * @version 1.0
 * @since JDK 1.8
 */
object Demo_ad_click_top3 {
  def main(args: Array[String]): Unit = {
//1.创建SparkConf并设置App名称
val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

//2.创建SparkContext，该对象是提交Spark App的入口
val sc: SparkContext = new SparkContext(conf)

//    sc.textFile("input/agent.log")
//      .map(line=>(line.split(" ")(1)+"-"+line.split(" ")(4),1))
//      .reduceByKey(_+_)
//      .map(a=>(a._1.split("-")(0),(a._1.split("-")(1),a._2)))
//      .groupByKey()
//      .mapValues(data=>data.toList.sortWith((a,b)=>(a._2>b._2)).take(3))
//      .collect().foreach(println)

        sc.textFile("input/agent.log")
          .map(line=>((line.split(" ")(1),line.split(" ")(4)),1))
          .reduceByKey((a,b)=>(a+b))
          .groupBy(_._1._1)
//          .mapValues(a=>a.toList.sortWith(_._2>_._2).take(3))
          .collect().foreach(println)



    Thread.sleep(1000000000)
//4.关闭连接
sc.stop()
  }
}

