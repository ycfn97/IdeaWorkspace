import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: 
 * ClassName: Test 
 *
 * @author 18729 created on date: 2020/9/24 8:47
 * @version 1.0
 * @since JDK 1.8
 */
object Test {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val value: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4))

    val value1: RDD[(Int, Int)] = value.map((_, 1))

    value1.collect().foreach(println)

    val value2 : RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 2)

    value2.groupBy(_%2==0).collect().foreach(println)

    val value3 : RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 2)

    value3.filter(_%2==0).collect().foreach(println)

    val value4 : RDD[Int] = sc.makeRDD(1 to 10, 2)

    value4.sample(true,0.5).collect().foreach(println)

    value4.sample(false,0.5).collect().foreach(println)

    val value5 : RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4,4,6,2,3), 2)

    value5.distinct(2).collect().foreach(println)

    val value6: RDD[String] = sc.textFile("input")

    value6.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(a=>(a._1,a._2.size)).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
