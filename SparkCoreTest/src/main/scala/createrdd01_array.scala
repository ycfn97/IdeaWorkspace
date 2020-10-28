import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 
 * ClassName: createrdd01_array 
 *
 * @author 18729 created on date: 2020/9/22 11:14
 * @version 1.0
 * @since JDK 1.8
 */
object createrdd01_array {

  var conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("[*]")

  def main(args: Array[String]): Unit = {
//    val conf:SparkConf=new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
//    val sc:SparkContext=new SparkContext(conf)
//    val rdd:RDD[Int]=sc.parallelize(Array(1,2,3,4,5,6,7,8))
//    val rdd1:RDD[Int]=sc.makeRDD(Array(1,2,3,4,5,6,7,8))
//    rdd1.collect().foreach(println)
//    sc.stop()
    val sc:SparkContext=new SparkContext(conf)
    val rdd:RDD[Int]=sc.parallelize(Array(1,2,3,4,5,6,7,8))
    val rdd1:RDD[Int]=sc.makeRDD(Array(1,2,3,4,5,6,7,8))
    rdd1.collect().foreach(println)

  }
}
