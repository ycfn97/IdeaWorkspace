package serialize

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: serialize
 * ClassName: serializable03_Kryo 
 *
 * @author 18729 created on date: 2020/9/25 10:58
 * @version 1.0
 * @since JDK 1.8
 */
object serializable03_Kryo {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")                // 替换默认的序列化机制
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // 注册需要使用kryo序列化的自定义类
      .registerKryoClasses(Array(classOf[Searcher]))


    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)


    //4.关闭连接
    sc.stop()
  }
}

class Searcher(val query: String) {

  def isMatch(s: String) = {
    s.contains(query)
  }

  def getMatchedRDD1(rdd: RDD[String]) = {
    rdd.filter(isMatch)
  }

  def getMatchedRDD2(rdd: RDD[String]) = {
    val q = query
    rdd.filter(_.contains(q))
  }
}
