package iterator

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: iterator
 * ClassName: DimArray 
 *
 * @author 18729 created on date: 2020/9/16 21:06
 * @version 1.0
 * @since JDK 1.8
 */
object DimArray {
  def main(args: Array[String]): Unit = {
    var arr=Array.ofDim[Int](3,4)
    arr(1)(2)=88
    for (i <- arr) { //i 就是一维数组
      for (j <- i) {
        print(j + " ")
      }
      println()
    }

    print(arr(1).mkString(" "))

    var arr1=Array.ofDim[Int](2,3,4,5,6)
    arr1(0)(2)(1)(3)(4)=100
    arr1.foreach(_.foreach(_.foreach(_.foreach(_.foreach(println)))))
  }
}
