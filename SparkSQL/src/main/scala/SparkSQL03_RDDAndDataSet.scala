import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: 
 * ClassName: SparkSQL03_RDDAndDataSet 
 *
 * @author 18729 created on date: 2020/9/29 13:46
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL03_RDDAndDataSet {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3.1 获取数据
    val LineRDD: RDD[String] = sc.textFile("input/user.txt")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._

    LineRDD.map{
      a=>{
        val strings: Array[String] = a.split(",")
          User(strings(0),strings(1).init.toInt)
      }
    }.toDS()
      .show()

    //4.关闭连接
    sc.stop()
  }
}

//case class User(name:String,age:Long)
