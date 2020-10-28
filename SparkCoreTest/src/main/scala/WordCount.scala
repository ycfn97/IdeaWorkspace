import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 
 * ClassName: WordCount 
 *
 * @author 18729 created on date: 2020/9/22 10:23
 * @version 1.0
 * @since JDK 1.8
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    //1.创建SparkConf并设置App名称
    val conf = new SparkConf().setAppName("WC").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc = new SparkContext(conf)

    //3.读取指定位置文件:hello atguigu atguigu
    val lineRdd: RDD[String] = sc.textFile("input")

    //4.读取的一行一行的数据分解成一个一个的单词（扁平化）(hello)(atguigu)(atguigu)
    val wordRdd: RDD[String] = lineRdd.flatMap(line => line.split(" "))

    //5. 将数据转换结构：(hello,1)(atguigu,1)(atguigu,1)
    val wordToOneRdd: RDD[(String, Int)] = wordRdd.map(word => (word, 1))

    //6.将转换结构后的数据进行聚合处理 atguigu:1、1 =》1+1  (atguigu,2)
    val wordToSumRdd: RDD[(String, Int)] = wordToOneRdd.reduceByKey((v1, v2) => v1 + v2)

    //7.将统计结果采集到控制台打印
    val wordToCountArray: Array[(String, Int)] = wordToSumRdd.collect()
    wordToCountArray.foreach(println)

    //一行搞定
    //sc.textFile(args(0)).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile(args(1))

    //8.关闭连接
    sc.stop()
  }
}
