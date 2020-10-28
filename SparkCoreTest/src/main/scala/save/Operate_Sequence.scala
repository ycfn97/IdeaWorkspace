package save

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: save
 * ClassName: Operate_Sequence 
 *
 * @author 18729 created on date: 2020/9/25 16:41
 * @version 1.0
 * @since JDK 1.8
 */
object Operate_Sequence {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    sc.makeRDD(Array((1,2),(3,4),(5,6)))
      .saveAsSequenceFile("output02")

    sc.sequenceFile[Int,Int]("output02").collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}
