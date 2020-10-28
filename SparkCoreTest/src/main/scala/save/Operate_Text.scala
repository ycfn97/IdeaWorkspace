package save

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: save
 * ClassName: Operate_Text 
 *
 * @author 18729 created on date: 2020/9/25 16:38
 * @version 1.0
 * @since JDK 1.8
 */
object Operate_Text {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3. 创建RDD
    val dataRDD: RDD[Int] = sc.makeRDD(List(1,2,3,4,1,2),2)

    //3.1 聚合
    val resultRDD: RDD[(Int, Int)] = dataRDD.map((_,1)).reduceByKey(_+_)

    // Job：一个Action算子就会生成一个Job；
    //3.2 job1打印到控制台
    resultRDD.collect().foreach(println)

    //3.3 job2输出到磁盘
    resultRDD.saveAsTextFile("output")

    Thread.sleep(1000000)
    //4.关闭连接
    sc.stop()
  }
}
