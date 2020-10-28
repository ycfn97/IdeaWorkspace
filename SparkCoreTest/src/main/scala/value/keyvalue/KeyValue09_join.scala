package value.keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.keyvalue
 * ClassName: KeyValue09_join 
 *
 * @author 18729 created on date: 2020/9/24 15:41
 * @version 1.0
 * @since JDK 1.8
 */
object KeyValue09_join {
  def main(args: Array[String]): Unit = {
//1.创建SparkConf并设置App名称
val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

//2.创建SparkContext，该对象是提交Spark App的入口
val sc: SparkContext = new SparkContext(conf)

    //3.1 创建第一个RDD
    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((1, "a"), (2, "b"), (3, "c")))

    //3.2 创建第二个pairRDD
    val rdd1: RDD[(Int, Int)] = sc.makeRDD(Array((1, 4), (2, 5), (4, 6)))

    rdd.join(rdd1).collect().foreach(println)
//4.关闭连接
sc.stop()
  }
}
