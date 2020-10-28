import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkSQL
 * Package: 
 * ClassName: SparkSQL02_RDDAndDataFrame 
 *
 * @author 18729 created on date: 2020/9/29 11:41
 * @version 1.0
 * @since JDK 1.8
 */
object SparkSQL02_RDDAndDataFrame {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3.1 获取数据
    val LineRDD: RDD[String] = sc.textFile("input/user.txt")

    //3.2 RDD准备完成
    val UserRDD: RDD[(String, Int)] = LineRDD.map {
      line =>
        val fields = line.split(",")
        (fields(0), fields(1).trim.toInt)
    }

    //4. 创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._

    UserRDD.toDF("name","age").show

    UserRDD.map {
      case (a, b) => User(a, b)
    }.toDF()

    //6.关闭连接
    sc.stop()
  }
}

case class User(name:String, age:Long)
