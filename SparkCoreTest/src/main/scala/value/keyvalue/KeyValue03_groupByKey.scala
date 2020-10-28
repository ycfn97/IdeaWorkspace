package value.key

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.key-value
 * ClassName: KeyValue03_groupByKey 
 *
 * @author 18729 created on date: 2020/9/24 11:20
 * @version 1.0
 * @since JDK 1.8
 */
object KeyValue03_groupByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd = sc.makeRDD(List(("a",1),("b",5),("a",5),("b",2)))

    rdd.groupByKey().collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
