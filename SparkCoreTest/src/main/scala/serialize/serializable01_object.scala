package serialize

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: serialize
 * ClassName: serializable01_object 
 *
 * @author 18729 created on date: 2020/9/25 10:37
 * @version 1.0
 * @since JDK 1.8
 */
object serializable01_object {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val user1 = new User()
    user1.name = "zhangsan"

    val user2 = new User()
    user2.name = "lisi"

    val userRDD1: RDD[User] = sc.makeRDD(List(user1, user2))
    userRDD1.collect().foreach(println)
    //4.关闭连接
    sc.stop()
  }
}

//class User {
//    var name: String = _
//}
class User extends Serializable {
  var name: String = _
}
