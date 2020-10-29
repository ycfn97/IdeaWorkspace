package rdd持久化

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: rdd持久化
 * ClassName: checkpoint01 
 *
 * @author 18729 created on date: 2020/9/25 15:41
 * @version 1.0
 * @since JDK 1.8
 */
object checkpoint01 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    // 需要设置路径，否则抛异常：Checkpoint directory has not been set in the SparkContext
    sc.setCheckpointDir("./checkpoint1")

    //3. 创建一个RDD，读取指定位置文件:hello atguigu atguigu
    val lineRdd: RDD[String] = sc.textFile("input1")

    //3.1.业务逻辑
    val wordRdd: RDD[String] = lineRdd.flatMap(line => line.split(" "))

    val wordToOneRdd: RDD[(String, Long)] = wordRdd.map {
      word => {
        (word, System.currentTimeMillis())
      }
    }

    //3.5 增加缓存,避免再重新跑一个job做checkpoint
    //        wordToOneRdd.cache()

    //3.4 数据检查点：针对wordToOneRdd做检查点计算
    wordToOneRdd.checkpoint()

    //3.2 触发执行逻辑
    wordToOneRdd.collect().foreach(println)
    // 会立即启动一个新的job来专门的做checkpoint运算

    //3.3 再次触发执行逻辑
    wordToOneRdd.collect().foreach(println)
    wordToOneRdd.collect().foreach(println)

    Thread.sleep(10000000)

    //4.关闭连接
    sc.stop()

  }
}
