package value.key

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value.key-value
 * ClassName: KeyValue01_partitionBy 
 *
 * @author 18729 created on date: 2020/9/24 11:03
 * @version 1.0
 * @since JDK 1.8
 */
object KeyValue01_partitionBy {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((1,"aaa"),(2,"bbb"),(3,"ccc")),3)

    rdd.partitionBy(new HashPartitioner(2)).collect().foreach(println)

    rdd.partitionBy(new MyPartitioner(3)).collect().foreach(println)

//    Thread.sleep(1900009909)
    //4.关闭连接
    sc.stop()
  }
}

class MyPartitioner(num:Int) extends Partitioner{
  override def numPartitions: Int = num

  override def getPartition(key: Any): Int = {
    if (key.isInstanceOf[Int]) {
      val keyInt=key.asInstanceOf[Int]
      if (keyInt%2==0) 0
      else 1
    }else{
      2
    }
  }
}
