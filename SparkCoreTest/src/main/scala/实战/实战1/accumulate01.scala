package 实战.实战1

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 实战.实战1
 * ClassName: accumulate01 
 *
 * @author 18729 created on date: 2020/9/28 8:28
 * @version 1.0
 * @since JDK 1.8
 */
object accumulate01 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)


    //4.关闭连接
    sc.stop()
  }
}

class Myaccumulator extends AccumulatorV2[String,mutable.Map[String,Long]] {
  var map = mutable.Map[String, Long]()

  override def isZero: Boolean =map.isEmpty

  override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = new Myaccumulator()

  override def reset(): Unit = map.clear()

  override def add(v: String): Unit = {
    if (v.startsWith("H")){
      map(v)=map.getOrElse(v,0L)+1L
    }
  }

  override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {

  }

  override def value: mutable.Map[String, Long] = map
}
